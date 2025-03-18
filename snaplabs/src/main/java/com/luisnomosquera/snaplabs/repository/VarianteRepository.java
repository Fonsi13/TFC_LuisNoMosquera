package com.luisnomosquera.snaplabs.repository;

import com.luisnomosquera.snaplabs.entity.Variante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarianteRepository extends JpaRepository<Variante, Integer> {
}
