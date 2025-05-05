package com.luisnomosquera.snaplabs.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiCartaResponseDto {
    private int total_pages;
    private Integer next;
    private List<SimpleCartaResponseDto> data;
}
