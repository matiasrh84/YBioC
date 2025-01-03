package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @OneToMany(mappedBy = "idMedicos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MedicoTieneEspecialidad> medicoTieneEspecialidades;
}