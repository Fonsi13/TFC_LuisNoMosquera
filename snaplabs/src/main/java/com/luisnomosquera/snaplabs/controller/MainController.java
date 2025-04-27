package com.luisnomosquera.snaplabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("vista", "pages/index");
        return "layouts/plantilla";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("vista", "pages/login");
        return "layouts/plantilla";
    }
}
