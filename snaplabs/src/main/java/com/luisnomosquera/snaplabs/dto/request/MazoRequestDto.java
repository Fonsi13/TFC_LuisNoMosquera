package com.luisnomosquera.snaplabs.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MazoRequestDto {

    @NotBlank(message = "El mazo debe estar completo.")
    private String contenido;

    @NotBlank(message = "El nombre es obligatorio.")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "El mazo tiene que tener un arquetipo.")
    private String arquetipo;

    private Boolean publico = false;
}
