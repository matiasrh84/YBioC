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
public class PacienteTieneObraSocialId implements java.io.Serializable {
    private static final long serialVersionUID = -5375647456019327026L;
    @Column(name = "id_Pacientes", nullable = false)
    private Integer idPacientes;

    @Column(name = "id_obrasocial", nullable = false)
    private Integer idObrasocial;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PacienteTieneObraSocialId entity = (PacienteTieneObraSocialId) o;
        return Objects.equals(this.idObrasocial, entity.idObrasocial) &&
                Objects.equals(this.idPacientes, entity.idPacientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idObrasocial, idPacientes);
    }

}