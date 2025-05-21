package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.CustomUserDetails;
import com.luisnomosquera.snaplabs.dto.request.UsuarioUpdateDto;
import com.luisnomosquera.snaplabs.dto.response.MazoDto;
import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.mapper.MazoMapper;
import com.luisnomosquera.snaplabs.mapper.UsuarioMapper;
import com.luisnomosquera.snaplabs.service.CloudinaryService;
import com.luisnomosquera.snaplabs.service.UsuarioService;
import com.luisnomosquera.snaplabs.util.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private MazoMapper mazoMapper;

    @GetMapping("/{uuid}")
    public String showUsuario(Model model, @PathVariable String uuid, Authentication authentication) {
        Optional<Usuario> usuario = usuarioService.getUsuarioByUuid(uuid);
        if (usuario.isPresent()) {
            UsuarioUpdateDto usuarioDto = usuarioMapper.toUsuarioDto(usuario.get());
            String url = cloudinaryService.getFotoPerfil(usuarioDto.getUrlFoto());
            model.addAttribute("fotoPerfil", url);
            if (!model.containsAttribute("usuario")) {
                model.addAttribute("usuario", usuarioDto);
            }
            model.addAttribute("hidden", usuarioDto);
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("foto", customUserDetails.getAvatar());
        model.addAttribute("id", customUserDetails.getUuid());
        model.addAttribute("vista", "pages/usuario");
        return "layouts/plantilla";
    }

    @PostMapping("/{uuid}/update")
    public String updateUsuario(@PathVariable String uuid, @ModelAttribute("usuario") @Valid UsuarioUpdateDto usuario,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usuario", bindingResult);
            redirectAttributes.addFlashAttribute("usuario", usuario);
            System.out.println(bindingResult.hasErrors());
            System.out.println(bindingResult);
        } else if (validarCampos(usuario, customUserDetails, redirectAttributes)) {
            updateUsuario(usuario, redirectAttributes, authentication, customUserDetails, uuid);
        }
        return "redirect:/usuario/{uuid}";
    }

    @GetMapping("/{uuid}/mazos")
    public String showMazos(@PathVariable String uuid, Model model, Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Usuario usuario = usuarioService.getUsuarioByUuid(uuid).orElseThrow();
        UsuarioUpdateDto usuarioDto = usuarioMapper.toUsuarioDto(usuario);
        List<MazoDto> listaMazos = new ArrayList<>();

        usuario.getListaMazo().forEach(mazo -> listaMazos.add(mazoMapper.toMazoDto(mazo)));
        model.addAttribute("listaMazos", listaMazos.reversed());
        model.addAttribute("usuario", usuarioDto);
        model.addAttribute("foto", customUserDetails.getAvatar());
        model.addAttribute("id", customUserDetails.getUuid());
        model.addAttribute("vista", "pages/mazos_usuario");
        return "layouts/plantilla";
    }

    private void updateUsuario(UsuarioUpdateDto usuarioDto, RedirectAttributes redirectAttributes,
                               Authentication authentication, CustomUserDetails customUserDetails, String uuid) {
        Usuario usuario = usuarioService.getReferenciaByUuid(uuid);
        try {
            if (!usuario.getUsername().equals(usuarioDto.getUsername()))
                usuario.setUsername(usuarioDto.getUsername());
            if (!usuario.getCorreo().equals(usuarioDto.getCorreo()))
                usuario.setCorreo(usuarioDto.getCorreo());
            if (!passwordEncoder.matches(usuarioDto.getPassword(), usuario.getPassword()))
                usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
            if (!usuarioDto.getFotoPerfil().isEmpty()){
                String publicId = cloudinaryService.uploadImage(usuarioDto.getFotoPerfil(), uuid);
                usuario.setFoto(publicId);
            }
            // Actualizar el usuario en la base de datos
            Usuario updatedUsuario = usuarioService.updateUsuario(usuario);
            // Actualizar datos de la sesión
            actualizarSesion(updatedUsuario, authentication, customUserDetails);
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorImg", e.getMessage());
        }
    }

    private void actualizarSesion(Usuario updatedUsuario, Authentication authentication, CustomUserDetails currentUserDetails) {
        CustomUserDetails updatedUserDetails = new CustomUserDetails(
                updatedUsuario,
                currentUserDetails.getAuthorities(),
                cloudinaryService
        );

        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                updatedUserDetails,
                authentication.getCredentials(),
                authentication.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
    }

    private boolean validarCampos(UsuarioUpdateDto usuarioDto, CustomUserDetails customUserDetails,
                                  RedirectAttributes redirectAttributes) {
        boolean valido = true;
        String username = customUserDetails.getUsername();
        if (!usuarioDto.getFotoPerfil().isEmpty() && !validarImagen(usuarioDto.getFotoPerfil())) {
            valido = false;
            redirectAttributes.addFlashAttribute("errorImg","La imagen tener un tamaño máximo de 2MB.<br>" +
                    "Debe estar en formato .jpg, .jpeg, .png o .webp.");
        }
        if (usuarioService.getUsuarioByUsername(usuarioDto.getUsername()).isPresent() && !username.equals(usuarioDto.getUsername())) {
            valido = false;
            redirectAttributes.addFlashAttribute("errorUsr","El nombre de usuario no está disponible");
        }
        return valido;
    }

    private boolean validarImagen(MultipartFile imagen) {
        return FileUploadUtil.validarExtension(imagen) && FileUploadUtil.validarSize(imagen);
    }
}
