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
@Table(name = "titulos", schema = "bioquimicos")
public class Titulo {

    @Id
    @Column(name = "Id_titulo", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "prioridad", nullable = false)
    private Integer prioridad;
}