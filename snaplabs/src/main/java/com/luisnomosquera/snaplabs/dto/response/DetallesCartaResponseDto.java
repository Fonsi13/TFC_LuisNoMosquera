package com.luisnomosquera.snaplabs.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class DetallesCartaResponseDto {

    @JsonProperty("card_key")
    private String clave;

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("description")
    private String descripcion;

    @JsonProperty("Image")
    private String imagen;

    @JsonProperty("series_key")
    private String serieClave;

    @JsonProperty("series_label")
    private String serieNombre;

    @JsonProperty("cost")
    private int coste;

    @JsonProperty("power")
    private int poder;

    @JsonProperty("is_collectable")
    private boolean coleccionable;

    @JsonProperty("is_released")
    private boolean publicado;

    @JsonProperty("card_abilities")
    private List<HabilidadCartaResponseDto> habilidades;
}
