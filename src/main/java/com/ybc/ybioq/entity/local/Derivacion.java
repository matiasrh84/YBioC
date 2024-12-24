package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "derivaciones", schema = "bioquimicos")
public class Derivacion {
    @Id
    @Column(name = "id_derivaciones", nullable = false)
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