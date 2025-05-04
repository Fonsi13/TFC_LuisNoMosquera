package com.luisnomosquera.snaplabs.service;

import com.luisnomosquera.snaplabs.dto.response.SimpleCartaResponseDto;
import com.luisnomosquera.snaplabs.entity.Carta;
import com.luisnomosquera.snaplabs.mapper.CartaMapper;
import com.luisnomosquera.snaplabs.repository.CartaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartaService {

    @Autowired
    private CartaRepository cartaRepository;

    @Autowired
    private CartaMapper cartaMapper;

    @Transactional
    public void saveAllCartas(List<SimpleCartaResponseDto> listaCartasDto) {
        List<Carta> cartas = new ArrayList<>();
        for (SimpleCartaResponseDto carta: listaCartasDto) {
            cartas.add(cartaMapper.toCarta(carta));
        }
        cartaRepository.saveAll(cartas);
    }

    public List<Carta> findAllCartas() {
        return cartaRepository.findAll();
    }

    public List<Carta> findBySerie(String serie) {
        return cartaRepository.findBySerie(serie);
    }
}
