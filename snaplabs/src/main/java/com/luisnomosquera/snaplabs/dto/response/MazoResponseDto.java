package com.luisnomosquera.snaplabs.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MazoResponseDto {

    @JsonProperty("deck_name")
    private String nombre;

    @JsonProperty("win_rate")
    private Float pctVictorias;

    @JsonProperty("avg_cubes")
    private Float mediaCubos;

    @JsonProperty("code")
    private String codigo;

    @JsonProperty("cards_on_this_deck")
    private List<SimpleCartaResponseDto> cartas;
}
