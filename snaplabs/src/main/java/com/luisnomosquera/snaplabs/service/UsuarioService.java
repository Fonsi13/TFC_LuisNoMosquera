package com.luisnomosquera.snaplabs.service;

import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Transactional
    public Usuario saveNewUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
