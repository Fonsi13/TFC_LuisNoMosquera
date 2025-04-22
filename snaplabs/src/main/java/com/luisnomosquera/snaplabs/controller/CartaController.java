package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.response.CartaResponseDto;
import com.luisnomosquera.snaplabs.service.CartaService;
import com.luisnomosquera.snaplabs.service.MarvelSnapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cartas")
public class CartaController {

    @Autowired
    private CartaService cartaService;

    @Autowired
    private MarvelSnapApiService marvelSnapApiService;

    @GetMapping("")
    public String showListaCartas(Model model) {
        model.addAttribute("vista", "pages/menu_cartas");
        model.addAttribute("listaCartas", cartaService.findAllCartas());
        return "layouts/plantilla";
    }

    @GetMapping("/update")
    public String updateAllCartas() {
        List<CartaResponseDto> cartas = marvelSnapApiService.getArrayCartas();
        cartaService.saveAllCartas(cartas);
        return "redirect:/cartas";
    }


}
