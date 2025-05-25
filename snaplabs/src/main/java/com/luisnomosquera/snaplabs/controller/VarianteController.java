package com.luisnomosquera.snaplabs.controller;

import com.luisnomosquera.snaplabs.dto.CustomUserDetails;
import com.luisnomosquera.snaplabs.dto.request.VarianteRequestDto;
import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.mapper.VarianteMapper;
import com.luisnomosquera.snaplabs.service.CartaService;
import com.luisnomosquera.snaplabs.service.CloudinaryService;
import com.luisnomosquera.snaplabs.service.UsuarioService;
import com.luisnomosquera.snaplabs.service.VarianteService;
import com.luisnomosquera.snaplabs.util.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/variantes")
public class VarianteController {

    @Autowired
    private VarianteService varianteService;

    @Autowired
    private VarianteMapper varianteMapper;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CartaService cartaService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("")
    public String showVariantes(Model model, Authentication authentication) {
        List<String> likedVariantes = new ArrayList<>();
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("foto", customUserDetails.getAvatar());
            model.addAttribute("id", customUserDetails.getUuid());
            Usuario usuario = usuarioService.getUsuarioByUuid(customUserDetails.getUuid()).orElseThrow();
            usuario.getLikedVariantes().forEach(variante -> likedVariantes.add(variante.getUuid()));
        }
        model.addAttribute("likedVariantes", likedVariantes);
        model.addAttribute("listaVariantes", varianteService.getListVarianteDto());
        model.addAttribute("vista", "pages/menu_variantes");
        return "layouts/plantilla";
    }

    @GetMapping("/upload")
    public String showFormVariante(Model model, Authentication authentication) {
        if (authentication != null) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            model.addAttribute("foto", customUserDetails.getAvatar());
            model.addAttribute("id", customUserDetails.getUuid());
        }
        if (!model.containsAttribute("varianteDto")) {
            model.addAttribute("varianteDto", new VarianteRequestDto());
        }
        model.addAttribute("vista", "pages/upload_variante");
        return "layouts/plantilla";
    }

    @PostMapping("/upload")
    public String subirVariante(@ModelAttribute("varianteDto") @Valid VarianteRequestDto varianteDto,
                                BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                Authentication authentication) {
        final String vista;
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.varianteDto", bindingResult);
            redirectAttributes.addFlashAttribute("varianteDto", varianteDto);
            vista = "redirect:/variantes/upload";
        } else if (validarCampos(varianteDto, redirectAttributes)) {
            vista = guardarVariante(varianteDto, redirectAttributes, customUserDetails);
        } else {
            redirectAttributes.addFlashAttribute("varianteDto", varianteDto);
            vista = "redirect:/variantes/upload";
        }
        return vista;
    }

    private boolean validarCampos(VarianteRequestDto varianteDto, RedirectAttributes redirectAttributes) {
        boolean valido = true;
        if (!FileUploadUtil.validarExtension(varianteDto.getImagen())) {
            valido = false;
            redirectAttributes.addFlashAttribute("errorImg","La imagen debe estar en formato .jpg, .jpeg, .png o .webp.");
        }
        if (!cartaService.getAllNombresCartas().contains(varianteDto.getPersonaje())) {
            valido = false;
            redirectAttributes.addFlashAttribute("errorPersonaje","Debes introducir el nombre de un personaje existente.");
        }
        return valido;
    }

    private String guardarVariante(VarianteRequestDto varianteDto, RedirectAttributes redirectAttributes,
                                   CustomUserDetails customUserDetails) {
        String vista;
        try {
            final String uuid = UUID.randomUUID().toString();
            varianteDto.setUuid(uuid);
            // Guardar la imagen en la nube y actualizar la url
            varianteDto.setUrlFoto(cloudinaryService.uploadVariante(varianteDto.getImagen(), uuid));
            // Asignar usuario logueado
            varianteDto.setUsuario(usuarioService.getReferenciaByUuid(customUserDetails.getUuid()));
            // Guardar la variante en la base de datos
            varianteService.saveNewVariante(varianteMapper.toVariante(varianteDto));
            vista = "redirect:/variantes?exito";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("varianteDto", varianteDto);
            redirectAttributes.addFlashAttribute("errorImg", e.getMessage());
            vista = "redirect:/variantes/upload";
        }
        return vista;
    }
}
