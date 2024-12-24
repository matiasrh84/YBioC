package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ResultadoId implements java.io.Serializable {

    private static final long serialVersionUID = -5198164330804052168L;

    @Column(name = "id_analisis", nullable = false)
    private Integer idAnalisis;

    @Column(name = "id_practicas", nullable = false)
    private Integer idPracticas;

    @Column(name = "id_ordenes", nullable = false)
    private Integer idOrdenes;

    @Column(name = "id_Usuarios", nullable = false)
    private Integer idUsuarios;

    @Column(name = "id_medicos", nullable = false)
    private Integer idMedicos;

    @Column(name = "id_especialidades", nullable = false)
    private Integer idEspecialidades;

    @Column(name = "id_Pacientes", nullable = false)
    private Integer idPacientes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ResultadoId entity = (ResultadoId) o;
        return Objects.equals(this.idOrdenes, entity.idOrdenes) &&
                Objects.equals(this.idPracticas, entity.idPracticas) &&
                Objects.equals(this.idAnalisis, entity.idAnalisis) &&
                Objects.equals(this.idMedicos, entity.idMedicos) &&
                Objects.equals(this.idEspecialidades, entity.idEspecialidades) &&
                Objects.equals(this.idPacientes, entity.idPacientes) &&
                Objects.equals(this.idUsuarios, entity.idUsuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdenes, idPracticas, idAnalisis, idMedicos, idEspecialidades, idPacientes, idUsuarios);
    }
}