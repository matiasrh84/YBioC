package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "especialidades", schema = "bioquimicos")
public class Especialidad {

    @Id
    @Column(name = "id_especialidades", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private Integer estado;
}