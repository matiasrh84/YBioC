package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "medicos", schema = "bioquimicos")
public class Medico {
    @Id
    @Column(name = "id_medicos", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "apellido", length = 100)
    private String apellido;

    @Column(name = "mail", length = 100)
    private String mail;

    @Column(name = "telefono")
    private Long telefono;

    @Column(name = "matricula")
    private Integer matricula;

    @Column(name = "observaciones")
    private String observaciones;

    @ColumnDefault("1")
    @Column(name = "estado", nullable = false)
    private Integer estado;

}