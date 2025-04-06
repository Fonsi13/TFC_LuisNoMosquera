package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.entity.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuarios")
public class UserController {

    @PostMapping ("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario,
                                 @RequestParam("foto_perfil") MultipartFile fotoPerfil) {
        return "redirect:/";
    }
}
