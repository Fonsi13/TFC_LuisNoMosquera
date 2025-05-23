package com.luisnomosquera.snaplabs.service;

import com.luisnomosquera.snaplabs.entity.Variante;
import com.luisnomosquera.snaplabs.repository.VarianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VarianteService {

    @Autowired
    private VarianteRepository varianteRepository;

    @Transactional
    public void saveNewVariante(Variante variante){ varianteRepository.save(variante); }
}
