package com.luisnomosquera.snaplabs.mapper;

import com.luisnomosquera.snaplabs.dto.request.MazoRequestDto;
import com.luisnomosquera.snaplabs.dto.response.MazoResponseDto;
import com.luisnomosquera.snaplabs.dto.response.SimpleCartaResponseDto;
import com.luisnomosquera.snaplabs.entity.Carta;
import com.luisnomosquera.snaplabs.entity.Mazo;
import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.service.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MazoMapper {

    @Autowired
    private CartaService cartaService;

    @Autowired
    private CartaMapper cartaMapper;

    public Mazo toMazo(MazoRequestDto mazoRequestDto, Usuario usuario) {
        Mazo mazo = new Mazo();
        mazo.setNombre(mazoRequestDto.getNombre());
        mazo.setContenido(mazoRequestDto.getContenido());
        mazo.setDescripcion(mazoRequestDto.getDescripcion());
        mazo.setArquetipo(mazoRequestDto.getArquetipo());
        mazo.setPublico(mazoRequestDto.getPublico());
        mazo.setUsuario(usuario);
        return mazo;
    }

    public MazoResponseDto toMazoResponseDto(Mazo mazo) {
        MazoResponseDto mazoResponseDto = new MazoResponseDto();
        mazoResponseDto.setNombre(mazo.getNombre());
        mazoResponseDto.setUsuario(mazo.getUsuario().getUsername());
        mazoResponseDto.setPublico(mazo.getPublico());
        List<SimpleCartaResponseDto> listaCartas = new ArrayList<>();
        String[] cartas = mazo.getContenido().split(",");
        for (String nombre : cartas) {
            Carta carta = cartaService.findByNombre(nombre).get();
            listaCartas.add(cartaMapper.toCartaDto(carta));
        }
        mazoResponseDto.setCartas(listaCartas);
        return mazoResponseDto;
    }
}
