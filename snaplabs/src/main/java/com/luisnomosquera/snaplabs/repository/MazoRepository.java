package com.luisnomosquera.snaplabs.repository;

import com.luisnomosquera.snaplabs.entity.Mazo;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MazoRepository extends JpaRepository<Mazo, Integer> {

    Optional<Mazo> findById(int id);

    @NotNull
    Mazo getReferenceById(@NotNull int id);
}
