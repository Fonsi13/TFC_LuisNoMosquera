package com.luisnomosquera.snaplabs.service;

import com.luisnomosquera.snaplabs.entity.Mazo;
import com.luisnomosquera.snaplabs.repository.MazoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MazoService {

    @Autowired
    private MazoRepository mazoRepository;

    @Transactional
    public Mazo saveNewMazo(Mazo mazo) { return mazoRepository.save(mazo); }
}
