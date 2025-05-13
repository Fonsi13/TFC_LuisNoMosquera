package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.CustomUserDetails;
import com.luisnomosquera.snaplabs.service.MarvelSnapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meta")
public class MetaController {

    @Autowired
    private MarvelSnapApiService marvelSnapApiService;

    @GetMapping("")
    public String showMeta(Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("foto", customUserDetails.getAvatar());
        }
        model.addAttribute("vista", "pages/meta");
        model.addAttribute("listaMazos", marvelSnapApiService.getMazosMeta());
        return "layouts/plantilla";
    }
}
