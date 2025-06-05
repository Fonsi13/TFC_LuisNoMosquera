package com.luisnomosquera.snaplabs.repository;

import com.luisnomosquera.snaplabs.entity.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartaRepository extends JpaRepository<Carta, String> {

    List<Carta> findBySerie(String serie);

    Optional<Carta> findByNombre(String nombre);

    @Query("SELECT c.nombre FROM Carta c")
    List<String> getAllNombres();
}
