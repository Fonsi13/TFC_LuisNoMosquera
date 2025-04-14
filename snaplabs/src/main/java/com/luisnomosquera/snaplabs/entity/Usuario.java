package com.luisnomosquera.snaplabs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "uuid", columnDefinition = "CHAR(36)")
    private String uuid;

    @Column(nullable = false)
    private String correo;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String foto;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Mazo> listaMazo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Variante> listaVariante;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_favorito_mazo",
            joinColumns = { @JoinColumn(name = "id_usuario", columnDefinition = "CHAR(36)") },
            inverseJoinColumns = { @JoinColumn(name = "id_mazo") }
    )
    private Set<Mazo> likedMazos;
}
