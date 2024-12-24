package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MedicoTieneEspecialidadId implements java.io.Serializable {

    private static final long serialVersionUID = 1849866691624802230L;
    @Column(name = "id_medicos", nullable = false)
    private Integer idMedicos;

    @ColumnDefault("0")
    @Column(name = "id_especialidades", nullable = false)
    private Integer idEspecialidades;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MedicoTieneEspecialidadId entity = (MedicoTieneEspecialidadId) o;
        return Objects.equals(this.idMedicos, entity.idMedicos) &&
                Objects.equals(this.idEspecialidades, entity.idEspecialidades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMedicos, idEspecialidades);
    }

}