package com.luisnomosquera.snaplabs.service;

import com.luisnomosquera.snaplabs.dto.request.MazoRequestDto;
import com.luisnomosquera.snaplabs.dto.response.MazoResponseDto;
import com.luisnomosquera.snaplabs.entity.Mazo;
import com.luisnomosquera.snaplabs.mapper.MazoMapper;
import com.luisnomosquera.snaplabs.repository.MazoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MazoService {

    @Autowired
    private MazoRepository mazoRepository;

    @Autowired
    private MazoMapper mazoMapper;

    @Transactional
    public Mazo saveNewMazo(Mazo mazo) { return mazoRepository.save(mazo); }

    public List<MazoResponseDto> getListaMazosDto() {
        List<MazoResponseDto> lista = new ArrayList<>();
        mazoRepository.findAll().forEach(mazo -> lista.add(mazoMapper.toMazoResponseDto(mazo)));
        return lista;
    }
}
