package com.luisnomosquera.snaplabs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Mazo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String contenido;
    private String nombre;
    private String arquetipo;
    private Boolean publico;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
}
