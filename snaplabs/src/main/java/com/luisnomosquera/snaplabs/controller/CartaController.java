package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.response.CartaResponseDto;
import com.luisnomosquera.snaplabs.service.CartaService;
import com.luisnomosquera.snaplabs.service.MarvelSnapApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/{serie}")
    public String showSerieCartas(Model model, @PathVariable String serie) {
        model.addAttribute("vista", "pages/menu_cartas");
        model.addAttribute("listaCartas", cartaService.findBySerie(serie));
        model.addAttribute("titulo", setTituloSerie(serie));
        return "layouts/plantilla";
    }

    private String setTituloSerie(String serie) {
        return switch (serie) {
            case "Recruit" -> "Temporada de Reclutamiento";
            case "Starter" -> "Iniciales";
            case "SeasonPass" -> "Pase de Temporada";
            case "Series0" -> "Serie 0 (Nivel de Coleccion 1-14)";
            case "Series1" -> "Serie 1";
            case "Series2" -> "Serie 2";
            case "Series3" -> "Serie 3";
            case "Series4" -> "Serie 4";
            case "Series5" -> "Serie 5";
            default -> "";
        };
    }
}