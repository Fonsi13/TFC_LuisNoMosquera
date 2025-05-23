package com.luisnomosquera.snaplabs.mapper;

import com.luisnomosquera.snaplabs.dto.request.VarianteRequestDto;
import com.luisnomosquera.snaplabs.dto.response.VarianteResponseDto;
import com.luisnomosquera.snaplabs.entity.Variante;
import org.springframework.stereotype.Component;

@Component
public class VarianteMapper {

    public Variante toVariante(VarianteRequestDto varianteRequestDto) {
        Variante variante = new Variante();
        variante.setUuid(varianteRequestDto.getUuid());
        variante.setNombre(varianteRequestDto.getNombre());
        variante.setDescripcion(varianteRequestDto.getDescripcion());
        variante.setUrlImagen(varianteRequestDto.getUrlFoto());
        variante.setPersonaje(varianteRequestDto.getPersonaje());
        variante.setUsuario(varianteRequestDto.getUsuario());
        return variante;
    }

    public VarianteResponseDto toVarianteDto(Variante variante) {
        VarianteResponseDto varianteDto = new VarianteResponseDto();
        varianteDto.setUuid(variante.getUuid());
        varianteDto.setNombre(variante.getNombre());
        varianteDto.setDescripcion(variante.getDescripcion());
        varianteDto.setUrlImagen(variante.getUrlImagen());
        varianteDto.setPersonaje(variante.getPersonaje());
        varianteDto.setFechaCreacion(variante.getFechaCreacion());
        varianteDto.setUsuario(variante.getUsuario());
        varianteDto.setLikes(variante.getLikes());
        return varianteDto;
    }
}
