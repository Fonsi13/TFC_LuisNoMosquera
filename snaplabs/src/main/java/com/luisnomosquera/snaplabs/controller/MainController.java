package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.request.UsuarioRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("vista", "index");
        return "plantilla";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("vista", "login");
        return "plantilla";
    }
}
