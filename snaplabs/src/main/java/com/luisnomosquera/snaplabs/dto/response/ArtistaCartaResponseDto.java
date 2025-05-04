package com.luisnomosquera.snaplabs.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class ArtistaCartaResponseDto {

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("artist_type")
    private String tipo;
}
