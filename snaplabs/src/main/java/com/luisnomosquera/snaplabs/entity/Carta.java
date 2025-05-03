package com.luisnomosquera.snaplabs.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "carta")
public class Carta {
    @Id
    private String clave;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int coste;

    @Column(nullable = false)
    private int poder;

    @Column(nullable = false)
    private String serie;

    @Column(nullable = false)
    private String habilidades;

    @Column(nullable = false)
    private String imagen;

    @Column(name = "id_snap", nullable = false)
    private String idSnap;
}
