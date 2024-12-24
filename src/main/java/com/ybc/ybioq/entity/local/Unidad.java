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
@Table(name = "unidades", schema = "bioquimicos")
public class Unidad {

    @Id
    @Column(name = "id_unidades", nullable = false)
    private Integer id;

    @Column(name = "nombre_uni", length = 45)
    private String nombreUni;
}