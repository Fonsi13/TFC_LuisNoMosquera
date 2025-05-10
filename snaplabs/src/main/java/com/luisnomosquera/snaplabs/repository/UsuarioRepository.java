package com.luisnomosquera.snaplabs.repository;

import com.luisnomosquera.snaplabs.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByUsername(String username);

    @Query("SELECT u.foto FROM Usuario u WHERE u.uuid = ?1")
    String getFotoById(String uuid);
}
