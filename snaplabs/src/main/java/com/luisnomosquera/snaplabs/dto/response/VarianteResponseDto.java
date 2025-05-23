package com.luisnomosquera.snaplabs.dto.response;

import com.luisnomosquera.snaplabs.entity.Usuario;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class VarianteResponseDto {

    private String uuid;

    private String nombre;

    private String descripcion;

    private String urlImagen;

    private String personaje;

    private LocalDate fechaCreacion;

    private Usuario usuario;

    private Set<Usuario> likes;
}
