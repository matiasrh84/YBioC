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
@Table(name = "materiales", schema = "bioquimicos")
public class Material {

    @Id
    @Column(name = "id_materiales", nullable = false)
    private Integer id;

    @Column(name = "nombre_mat", length = 45)
    private String nombreMat;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "stock")
    private Integer stock;
}