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
@Table(name = "especialidades", schema = "bioquimicos")
public class Especialidad {

    @Id
    @Column(name = "id_especialidades", nullable = false)
    private Integer id;

    @Column(name = "nombre_esp", length = 45)
    private String nombreEsp;

    @Column(name = "estado", nullable = false)
    private Integer estado;
}