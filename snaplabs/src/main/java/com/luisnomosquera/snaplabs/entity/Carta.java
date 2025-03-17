package com.luisnomosquera.snaplabs.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "carta")
public class Carta {
    @Id
    private String nombre;
    @Column(name = "id_snap")
    private String idSnap;
}
