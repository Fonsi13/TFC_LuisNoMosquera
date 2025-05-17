package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.CustomUserDetails;
import com.luisnomosquera.snaplabs.service.MazoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mazos")
public class MazoController {

    @Autowired
    private MazoService mazoService;

    @GetMapping("")
    public String showMazo(Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("foto", customUserDetails.getAvatar());
            model.addAttribute("id", customUserDetails.getUuid());
        }
        model.addAttribute("listaMazos", mazoService.getListaMazosDto());
        model.addAttribute("vista", "pages/mazo");
        return "layouts/plantilla";
    }
}
