package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unidades", schema = "bioquimicos")
public class Unidad {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "estado", nullable = false, columnDefinition = "tinyint(1) DEFAULT 1")
    private boolean estado = true;
}
