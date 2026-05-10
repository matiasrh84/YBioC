package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medicos_tienen_especialidades", schema = "bioquimicos")
public class MedicoTieneEspecialidad {

    @EmbeddedId
    private MedicoTieneEspecialidadId id;

    @MapsId("idMedicos")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_medicos", nullable = false)
    private Medico idMedicos;

    @MapsId("idEspecialidades")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_especialidades", nullable = false)
    private Especialidad idEspecialidades;

    @Column(name = "matricula")
    private Integer matricula;

    @Column(name = "observaciones")
    private String observaciones;

}