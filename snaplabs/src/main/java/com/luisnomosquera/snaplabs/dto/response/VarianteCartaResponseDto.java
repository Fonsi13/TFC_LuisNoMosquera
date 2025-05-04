package com.luisnomosquera.snaplabs.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class VarianteCartaResponseDto {

    @JsonProperty("variant_label")
    private String nombre;

    @JsonProperty("Image")
    private String imagen;

    @JsonProperty("rarity_label")
    private String rareza;

    @JsonProperty("artist")
    private List<ArtistaCartaResponseDto> artistas;
}
