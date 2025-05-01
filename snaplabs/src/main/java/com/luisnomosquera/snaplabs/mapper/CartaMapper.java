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
        carta.setIdSnap("En proceso");
        return carta;
    }
}
