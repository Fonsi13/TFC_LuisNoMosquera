package com.luisnomosquera.snaplabs.mapper;

import com.luisnomosquera.snaplabs.dto.response.CartaResponseDto;
import com.luisnomosquera.snaplabs.entity.Carta;
import org.springframework.stereotype.Component;

@Component
public class CartaMapper {

    public Carta toCarta(CartaResponseDto cartaResponseDto) {
        Carta carta = new Carta();
        carta.setClave(cartaResponseDto.getClave());
        carta.setNombre(cartaResponseDto.getNombre());
        carta.setDescripcion(cartaResponseDto.getDescripcion());
        carta.setImagen(cartaResponseDto.getImagen());
        carta.setSeries(cartaResponseDto.getSeries());
        carta.setIdSnap("En proceso");
        return carta;
    }
}
