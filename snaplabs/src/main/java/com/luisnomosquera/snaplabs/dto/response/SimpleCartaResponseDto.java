package com.luisnomosquera.snaplabs.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class SimpleCartaResponseDto {

    @JsonProperty("card_key")
    private String clave;

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("description")
    private String descripcion;

    @JsonProperty("Image")
    private String imagen;

    private String serie;

    private int coste;

    private int poder;

    private String habilidades;
}
