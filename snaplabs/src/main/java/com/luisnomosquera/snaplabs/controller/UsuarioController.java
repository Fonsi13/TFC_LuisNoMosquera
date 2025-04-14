package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.request.UsuarioRequestDto;
import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.mapper.UsuarioMapper;
import com.luisnomosquera.snaplabs.service.CloudinaryService;
import com.luisnomosquera.snaplabs.service.UsuarioService;
import com.luisnomosquera.snaplabs.util.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping ("/guardar")
    public String guardarUsuario(@ModelAttribute("usuarioDto") @Valid UsuarioRequestDto usuarioDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        final String vista;
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usuarioDto", bindingResult);
            redirectAttributes.addFlashAttribute("usuarioDto", usuarioDto);
            vista = "redirect:/registro";
        } else if (validarCampos(usuarioDto, redirectAttributes)) {
            vista = crearUsuario(usuarioDto, redirectAttributes);
        } else {
            redirectAttributes.addFlashAttribute("usuarioDto", usuarioDto);
            vista = "redirect:/registro";
        }

        return vista;
    }

    private String crearUsuario(UsuarioRequestDto usuarioDto, RedirectAttributes redirectAttributes) {
        String vista;
        try {
            final String uuid = UUID.randomUUID().toString();
            usuarioDto.setUuid(uuid);
            // Hash contraseña
            // Guardo la imagen en la nube y actualizo la url del usuario
            usuarioDto.setUrlFoto(cloudinaryService.uploadImage(usuarioDto.getFotoPerfil(), uuid));
            Usuario usuario = usuarioService.saveNewUsuario(usuarioMapper.toUsuario(usuarioDto));
            // Variables de sesion
            vista = "redirect:/";
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
        if (usuarioService.getUsuarioByUsername(usuarioDto.getUsername()) == null) {
            valido = false;
            redirectAttributes.addFlashAttribute("errorUsr","El nombre de usuario no está disponible");
        }
        return valido;
    }

    private boolean validarImagen(MultipartFile imagen) {
        return FileUploadUtil.validarExtension(imagen) && FileUploadUtil.validarSize(imagen);
    }
}