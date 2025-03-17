package com.luisnomosquera.snaplabs.repository;

import com.luisnomosquera.snaplabs.entity.Mazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MazoRepository extends JpaRepository<Mazo, Integer> {
}
