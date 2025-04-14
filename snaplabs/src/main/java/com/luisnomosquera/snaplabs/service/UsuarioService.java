package com.luisnomosquera.snaplabs.service;

import com.luisnomosquera.snaplabs.entity.Usuario;
import com.luisnomosquera.snaplabs.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario saveNewUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
