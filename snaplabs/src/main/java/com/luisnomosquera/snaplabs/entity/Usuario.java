package com.luisnomosquera.snaplabs.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Usuario {
    @Id
    private String uuid;
    private String correo;
    private String username;
    private String password;
    private String foto;
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
}
