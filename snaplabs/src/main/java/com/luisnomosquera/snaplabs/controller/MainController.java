package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.CustomUserDetails;
import com.luisnomosquera.snaplabs.service.CloudinaryService;
import com.luisnomosquera.snaplabs.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/")
    public String showIndex(Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            String id = customUserDetails.getUuid();
            String url = customUserDetails.getAvatar();
            model.addAttribute("foto", url);
            model.addAttribute("id", id);
        }
        model.addAttribute("vista", "pages/index");
        return "layouts/plantilla";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("vista", "pages/login");
        return "layouts/plantilla";
    }
}
