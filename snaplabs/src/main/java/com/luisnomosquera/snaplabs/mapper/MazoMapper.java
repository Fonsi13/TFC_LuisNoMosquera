package com.luisnomosquera.snaplabs.mapper;

import com.luisnomosquera.snaplabs.dto.request.MazoRequestDto;
import com.luisnomosquera.snaplabs.dto.response.MazoDto;
import com.luisnomosquera.snaplabs.dto.response.SimpleCartaResponseDto;
import com.luisnomosquera.snaplabs.entity.Carta;
import com.luisnomosquera.snaplabs.entity.Mazo;
import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.service.CartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    public MazoDto toMazoDto(Mazo mazo) {
        MazoDto mazoDto = new MazoDto();
        mazoDto.setId(mazo.getId());
        mazoDto.setNombre(mazo.getNombre());
        mazoDto.setDescripcion(mazo.getDescripcion());
        mazoDto.setArquetipo(mazo.getArquetipo());
        mazoDto.setNombreArquetipo(getNombreArquetipo(mazo.getArquetipo()));
        mazoDto.setPublico(mazo.getPublico());
        mazoDto.setFechaCreacion(mazo.getFechaCreacion());
        mazoDto.setTiempoTranscurrido(getDiferenciaFecha(mazo.getFechaCreacion()));
        mazoDto.setUsuario(mazo.getUsuario());
        List<SimpleCartaResponseDto> listaCartas = new ArrayList<>();
        String[] cartas = mazo.getContenido().split(",");
        for (String nombre : cartas) {
            Carta carta = cartaService.findByNombre(nombre).get();
            listaCartas.add(cartaMapper.toCartaDto(carta));
        }
        mazoDto.setCartas(listaCartas);
        return mazoDto;
    }

    private String getDiferenciaFecha(LocalDate fechaCreacion) {
        String diferencia = "";
        LocalDate today = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(fechaCreacion, today);
        long meses = ChronoUnit.MONTHS.between(fechaCreacion, today);
        long years = ChronoUnit.YEARS.between(fechaCreacion, today);

        if (dias == 0) {
            diferencia = "hoy";
        } else if (meses < 1) {
            diferencia = "hace " + dias + "d";
        } else if (meses >= 1 && meses <= 12) {
            diferencia = "hace " + meses + "m";
        } else {
            diferencia = "hace " + years + "y";
        }
        return diferencia;
    }

    private String getNombreArquetipo(String arquetipo) {
        return switch (arquetipo) {
            case "destroy" -> "Destruir";
            case "discard" -> "Descarte";
            case "move" -> "Movimiento";
            case "reveal" -> "Al Revelarse";
            case "ongoing" -> "Continuo";
            default -> "";
        };
    }
}
