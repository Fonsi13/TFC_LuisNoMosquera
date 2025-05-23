package com.luisnomosquera.snaplabs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "variante")
public class Variante {
    @Id
    @Column(name = "uuid", columnDefinition = "CHAR(36)")
    private String uuid;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private String urlImagen;

    @Column(nullable = false)
    private String personaje;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", columnDefinition = "CHAR(36)")
    private Usuario usuario;

    @ManyToMany(mappedBy = "likedVariantes")
    private Set<Usuario> likes;
}
