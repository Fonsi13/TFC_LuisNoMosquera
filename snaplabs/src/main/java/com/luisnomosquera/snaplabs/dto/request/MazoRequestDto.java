package com.luisnomosquera.snaplabs.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MazoRequestDto {

    @NotBlank(message = "El mazo debe estar completo.")
    private String contenido;

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(min = 3, max = 25, message = "El nombre debe tener entre 3 y 25 letras")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "El mazo tiene que tener un arquetipo.")
    private String arquetipo;

    private Boolean publico = false;
}
