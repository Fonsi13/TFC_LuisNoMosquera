package com.luisnomosquera.snaplabs.repository;

import com.luisnomosquera.snaplabs.entity.Variante;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VarianteRepository extends JpaRepository<Variante, Integer> {

    List<Variante> findAllByOrderByFechaCreacionDesc();

    @NotNull Variante getReferenceByUuid(@NotNull String uuid);
}
