package com.luisnomosquera.snaplabs.repository;

import com.luisnomosquera.snaplabs.entity.Usuario;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByUsername(String username);

    Optional<Usuario> findByUuid(String uuid);

    @NotNull Usuario getReferenceById(@NotNull String uuid);
}
