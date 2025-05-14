package com.luisnomosquera.snaplabs.mapper;

import com.luisnomosquera.snaplabs.dto.request.MazoRequestDto;
import com.luisnomosquera.snaplabs.entity.Mazo;
import com.luisnomosquera.snaplabs.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class MazoMapper {

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
}
