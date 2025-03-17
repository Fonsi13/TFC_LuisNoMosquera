package com.luisnomosquera.snaplabs.repository;

import com.luisnomosquera.snaplabs.entity.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends JpaRepository<Carta, String> {
}
