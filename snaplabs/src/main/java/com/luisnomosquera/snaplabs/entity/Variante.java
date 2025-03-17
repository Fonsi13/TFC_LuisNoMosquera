package com.luisnomosquera.snaplabs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Variante {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String descripcion;
    private String enlace;
    private String personaje;
    @Column(name = "total_likes")
    private Long numLikes;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
}
