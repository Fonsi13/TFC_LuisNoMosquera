package com.luisnomosquera.snaplabs.service;

import com.luisnomosquera.snaplabs.dto.response.VarianteResponseDto;
import com.luisnomosquera.snaplabs.entity.Variante;
import com.luisnomosquera.snaplabs.mapper.VarianteMapper;
import com.luisnomosquera.snaplabs.repository.VarianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VarianteService {

    @Autowired
    private VarianteRepository varianteRepository;

    @Autowired
    private VarianteMapper varianteMapper;

    @Transactional
    public void saveNewVariante(Variante variante){ varianteRepository.save(variante); }

    public List<VarianteResponseDto> getListVarianteDto() {
        List<VarianteResponseDto> lista = new ArrayList<>();
        varianteRepository.findAll().forEach(variante -> lista.add(varianteMapper.toVarianteDto(variante)));
        return lista;
    }
}
