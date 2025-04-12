package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.request.UsuarioRequestDto;
import com.luisnomosquera.snaplabs.entity.Usuario;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @PostMapping ("/guardar")
    public String guardarUsuario(@ModelAttribute("usuarioDto") @Valid UsuarioRequestDto usuarioDto,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        final String vista;
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.usuarioDto", bindingResult);
            redirectAttributes.addFlashAttribute("usuarioDto", usuarioDto);
            vista = "redirect:/registro";
        } else {
            final String uuid = UUID.randomUUID().toString();
            vista = "redirect:/";
        }

        return vista;
    }
}
