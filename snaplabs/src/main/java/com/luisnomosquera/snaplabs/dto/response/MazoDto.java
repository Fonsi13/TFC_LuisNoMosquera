package com.luisnomosquera.snaplabs.dto.response;

import com.luisnomosquera.snaplabs.entity.Usuario;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MazoDto {

    private int id;

    private String nombre;

    private String descripcion;

    private String arquetipo;

    private String nombreArquetipo;

    private Boolean publico;

    private LocalDate fechaCreacion;

    private String tiempoTranscurrido;

    private Usuario usuario;

    private List<SimpleCartaResponseDto> cartas;
}
