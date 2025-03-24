package com.luisnomosquera.snaplabs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("vista", "index");
        return "plantilla";
    }
}
