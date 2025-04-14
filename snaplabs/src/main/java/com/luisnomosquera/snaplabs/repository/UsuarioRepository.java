package com.luisnomosquera.snaplabs.repository;

import com.luisnomosquera.snaplabs.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByUsername(String username);
}
