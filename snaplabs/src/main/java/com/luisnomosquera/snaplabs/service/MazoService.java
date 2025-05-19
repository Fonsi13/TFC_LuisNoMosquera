package com.luisnomosquera.snaplabs.service;

import com.luisnomosquera.snaplabs.dto.response.MazoResponseDto;
import com.luisnomosquera.snaplabs.entity.Mazo;
import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.mapper.MazoMapper;
import com.luisnomosquera.snaplabs.repository.MazoRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Integer.parseInt;

@Service
public class MazoService {

    @Autowired
    private MazoRepository mazoRepository;

    @Autowired
    private MazoMapper mazoMapper;

    @Autowired
    private UsuarioService usuarioService;

    public Optional<Mazo> findById(int id) { return mazoRepository.findById(id); }

    @Transactional
    public Mazo saveNewMazo(Mazo mazo) { return mazoRepository.save(mazo); }

    public List<MazoResponseDto> getListaMazosDto() {
        List<MazoResponseDto> lista = new ArrayList<>();
        mazoRepository.findAll().forEach(mazo -> lista.add(mazoMapper.toMazoResponseDto(mazo)));
        return lista;
    }

    public Mazo getReferenciaById(int id) {
        return mazoRepository.getReferenceById(id);
    }

    @Transactional
    public void addLikedMazo(String uuid, String mazoId) {
        Usuario usuario = usuarioService.getUsuarioByUuid(uuid).orElseThrow();
        Mazo mazo = getReferenciaById(parseInt(mazoId));
        usuario.getLikedMazos().add(mazo);
    }

    @Transactional
    public void deleteLikedMazo(String uuid, String mazoId) {
        Usuario usuario = usuarioService.getUsuarioByUuid(uuid).orElseThrow();
        Mazo mazo = getReferenciaById(parseInt(mazoId));
        usuario.getLikedMazos().remove(mazo);
    }
}
