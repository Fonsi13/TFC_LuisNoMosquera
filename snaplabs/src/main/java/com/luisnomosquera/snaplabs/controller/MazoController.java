package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.CustomUserDetails;
import com.luisnomosquera.snaplabs.dto.response.MazoDto;
import com.luisnomosquera.snaplabs.dto.response.SimpleCartaResponseDto;
import com.luisnomosquera.snaplabs.entity.Mazo;
import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.mapper.MazoMapper;
import com.luisnomosquera.snaplabs.service.MazoService;
import com.luisnomosquera.snaplabs.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/mazos")
public class MazoController {

    @Autowired
    private MazoService mazoService;

    @Autowired
    private MazoMapper mazoMapper;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public String showMazos(Model model, Authentication authentication) {
        List<Integer> likedMazos = new ArrayList<>();
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("foto", customUserDetails.getAvatar());
            model.addAttribute("id", customUserDetails.getUuid());
            Optional<Usuario> usuario = usuarioService.getUsuarioByUuid(customUserDetails.getUuid());
            usuario.get().getLikedMazos().forEach(mazo -> likedMazos.add(mazo.getId()));
        }
        model.addAttribute("likedMazos", likedMazos);
        model.addAttribute("listaMazos", mazoService.getListaMazosDto().reversed());
        model.addAttribute("vista", "pages/menu_mazos");
        return "layouts/plantilla";
    }

    @GetMapping("/{id}")
    public String showMazo(@PathVariable String id, Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("foto", customUserDetails.getAvatar());
            model.addAttribute("id", customUserDetails.getUuid());
        }
        Optional<Mazo> mazo = mazoService.findById(parseInt(id));
        if (mazo.isPresent()) {
            MazoDto mazoDto =  mazoMapper.toMazoDto(mazo.get());
            List<SimpleCartaResponseDto> cartas = mazoDto.getCartas();
            model.addAttribute("mazo", mazoDto);
            model.addAttribute("coste", getMedias(cartas, "coste"));
            model.addAttribute("poder", getMedias(cartas, "poder"));
        }
        model.addAttribute("vista", "pages/mazo");
        return "layouts/plantilla";
    }

    @GetMapping("/{id}/like")
    public String likeMazo(@PathVariable String id, Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            mazoService.addLikedMazo(customUserDetails.getUuid(), id);
        }
        return "redirect:/mazos";
    }

    @GetMapping("/{id}/dislike")
    public String dislikeMazo(@PathVariable String id, Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            mazoService.deleteLikedMazo(customUserDetails.getUuid(), id);
        }
        return "redirect:/mazos";
    }

    private Float getMedias(List<SimpleCartaResponseDto> cartas, String categoria) {
        float media = 0;
        for (SimpleCartaResponseDto carta : cartas) {
            if (categoria.equals("coste"))
                media += carta.getCoste();
            else
                media += carta.getPoder();
        }
        return media/cartas.size();
    }
}
