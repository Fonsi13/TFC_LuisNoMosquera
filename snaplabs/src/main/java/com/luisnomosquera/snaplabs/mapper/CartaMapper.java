package com.luisnomosquera.snaplabs.mapper;

import com.luisnomosquera.snaplabs.dto.response.SimpleCartaResponseDto;
import com.luisnomosquera.snaplabs.entity.Carta;
import org.springframework.stereotype.Component;

@Component
public class CartaMapper {

    public Carta toCarta(SimpleCartaResponseDto simpleCartaResponseDto) {
        Carta carta = new Carta();
        carta.setClave(simpleCartaResponseDto.getClave());
        carta.setNombre(simpleCartaResponseDto.getNombre());
        carta.setDescripcion(simpleCartaResponseDto.getDescripcion());
        carta.setImagen(simpleCartaResponseDto.getImagen());
        carta.setSerie(simpleCartaResponseDto.getSerie());
        carta.setCoste(simpleCartaResponseDto.getCoste());
        carta.setPoder(simpleCartaResponseDto.getPoder());
        carta.setHabilidades(simpleCartaResponseDto.getHabilidades());
        carta.setIdSnap("En proceso");
        return carta;
    }

    public SimpleCartaResponseDto toCartaDto(Carta carta) {
        SimpleCartaResponseDto simpleCartaResponseDto = new SimpleCartaResponseDto();
        simpleCartaResponseDto.setClave(carta.getClave());
        simpleCartaResponseDto.setNombre(carta.getNombre());
        simpleCartaResponseDto.setDescripcion(carta.getDescripcion());
        simpleCartaResponseDto.setImagen(carta.getImagen());
        simpleCartaResponseDto.setSerie(carta.getSerie());
        simpleCartaResponseDto.setCoste(carta.getCoste());
        simpleCartaResponseDto.setPoder(carta.getPoder());
        simpleCartaResponseDto.setHabilidades(carta.getHabilidades());
        return simpleCartaResponseDto;
    }
}
