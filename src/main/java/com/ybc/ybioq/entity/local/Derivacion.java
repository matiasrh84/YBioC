package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "derivaciones", schema = "bioquimicos")
public class Derivacion {

    @Id
    @Column(name = "id_derivaciones", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 150)
    private String nombre;

    @Column(name = "direccion", length = 150)
    private String direccion;

    @Column(name = "telefono")
    private Long telefono;

    @Column(name = "mail", length = 150)
    private String mail;

    @Column(name = "observaciones")
    private String observaciones;
}