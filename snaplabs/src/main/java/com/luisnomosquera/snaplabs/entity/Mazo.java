package com.luisnomosquera.snaplabs.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "mazo")
public class Mazo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String contenido;

    @Column(nullable = false)
    private String nombre;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String arquetipo;

    @Column(nullable = false)
    private Boolean publico;

    @CreationTimestamp
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", columnDefinition = "CHAR(36)")
    private Usuario usuario;

    @ManyToMany(mappedBy = "likedMazos")
    private Set<Usuario> likes;
}
