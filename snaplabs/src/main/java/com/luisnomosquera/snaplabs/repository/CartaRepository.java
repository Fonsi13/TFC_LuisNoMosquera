package com.luisnomosquera.snaplabs.repository;

import com.luisnomosquera.snaplabs.entity.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaRepository extends JpaRepository<Carta, String> {
    List<Carta> findBySerie(String serie);
}
