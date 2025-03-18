package com.luisnomosquera.snaplabs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "variante")
public class Variante {
    @Id
    private String uuid;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private String enlace;

    @Column(nullable = false)
    private String personaje;

    @Column(name = "total_likes")
    private Long numLikes;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
