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
@Table(name = "metodos", schema = "bioquimicos")
public class Metodo {

    @Id
    @Column(name = "idMetodos", nullable = false)
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;
}