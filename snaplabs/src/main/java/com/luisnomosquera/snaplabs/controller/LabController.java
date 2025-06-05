package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.CustomUserDetails;
import com.luisnomosquera.snaplabs.dto.request.MazoRequestDto;
import com.luisnomosquera.snaplabs.entity.Mazo;
import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.mapper.MazoMapper;
import com.luisnomosquera.snaplabs.service.CartaService;
import com.luisnomosquera.snaplabs.service.MazoService;
import com.luisnomosquera.snaplabs.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/lab")
public class LabController {

    @Autowired
    private CartaService cartaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MazoMapper mazoMapper;

    @Autowired
    private MazoService mazoService;

    @GetMapping("")
    public String showLab(Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("foto", customUserDetails.getAvatar());
            model.addAttribute("id", customUserDetails.getUuid());
        }
        if (!model.containsAttribute("mazoDto")) {
            MazoRequestDto mazoDto = new  MazoRequestDto();
            model.addAttribute("mazoDto", mazoDto);
        }
        model.addAttribute("listaCartas", cartaService.findAllCartas());
        model.addAttribute("vista", "pages/lab");
        return "layouts/plantilla";
    }

    @PostMapping("/{uuid}/crear")
    public String saveMazo(@PathVariable String uuid, @ModelAttribute("mazoDto") @Valid MazoRequestDto mazoDto,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String vista;
        try {
            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mazoDto", bindingResult);
                redirectAttributes.addFlashAttribute("mazoDto", mazoDto);
                System.out.println(bindingResult);
                vista = "redirect:/lab";
            } else {
                Optional<Usuario> usuario = usuarioService.getUsuarioByUuid(uuid);
                Mazo mazo = mazoMapper.toMazo(mazoDto, usuario.get());
                mazoService.saveNewMazo(mazo);
                vista = "redirect:/lab?exito";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el mazo.");
            vista = "redirect:/lab";
        }

        return vista;
    }
}
