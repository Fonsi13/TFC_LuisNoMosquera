package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.request.UsuarioRequestDto;
import com.luisnomosquera.snaplabs.mapper.UsuarioMapper;
import com.luisnomosquera.snaplabs.service.CloudinaryService;
import com.luisnomosquera.snaplabs.service.UsuarioService;
import com.luisnomosquera.snaplabs.util.FileUploadUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/registro")
    public String showRegistro(Model model) {
        if (!model.containsAttribute("usuarioDto")) {
            model.addAttribute("usuarioDto", new UsuarioRequestDto());
        }
        model.addAttribute("vista", "pages/registro");
        return "layouts/plantilla";
    }

    @PostMapping ("/registro")
    public String guardarUsuario(@ModelAttribute("usuarioDto") @Valid UsuarioRequestDto usuarioDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        final String vista;
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usuarioDto", bindingResult);
            redirectAttributes.addFlashAttribute("usuarioDto", usuarioDto);
            vista = "redirect:/registro";
        } else if (validarCampos(usuarioDto, redirectAttributes)) {
            vista = crearUsuario(usuarioDto, redirectAttributes, request);
        } else {
            redirectAttributes.addFlashAttribute("usuarioDto", usuarioDto);
            vista = "redirect:/registro";
        }

        return vista;
    }

    private String crearUsuario(UsuarioRequestDto usuarioDto, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        String vista;
        try {
            final String uuid = UUID.randomUUID().toString();
            usuarioDto.setUuid(uuid);
            // Hash contraseña
            usuarioDto.setHashPassword(passwordEncoder.encode(usuarioDto.getPassword()));
            // Guardar la imagen en la nube y actualizar la url del usuario
            usuarioDto.setUrlFoto(cloudinaryService.uploadImage(usuarioDto.getFotoPerfil(), uuid));
            // Guardar el usuario en la base de datos
            usuarioService.saveNewUsuario(usuarioMapper.toUsuario(usuarioDto));
            vista = "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("usuarioDto", usuarioDto);
            redirectAttributes.addFlashAttribute("errorImg", e.getMessage());
            vista = "redirect:/registro";
        }
        return vista;
    }

    private boolean validarCampos(UsuarioRequestDto usuarioDto, RedirectAttributes redirectAttributes) {
        boolean valido = true;
        if (!usuarioDto.getFotoPerfil().isEmpty() && !validarImagen(usuarioDto.getFotoPerfil())) {
            valido = false;
            redirectAttributes.addFlashAttribute("errorImg","La imagen tener un tamaño máximo de 2MB.<br>" +
                    "Debe estar en formato .jpg, .jpeg, .png o .webp.");
        }
        if (!usuarioDto.getPassword().equals(usuarioDto.getConfirmarPassword())) {
            valido = false;
            redirectAttributes.addFlashAttribute("errorPwd","La contraseña no coincide");
        }
        if (usuarioService.getUsuarioByUsername(usuarioDto.getUsername()).isPresent()) {
            valido = false;
            redirectAttributes.addFlashAttribute("errorUsr","El nombre de usuario no está disponible");
        }
        return valido;
    }

    private boolean validarImagen(MultipartFile imagen) {
        return FileUploadUtil.validarExtension(imagen) && FileUploadUtil.validarSize(imagen);
    }
}