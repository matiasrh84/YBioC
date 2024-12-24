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
@Table(name = "secciones", schema = "bioquimicos")
public class Seccion {

    @Id
    @Column(name = "id_secciones", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "prioridad")
    private Integer prioridad;

    @Column(name = "estado")
    private Integer estado;
}