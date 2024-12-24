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
public class OrdenTienePracticaId implements java.io.Serializable {
    private static final long serialVersionUID = 4300559632188232136L;
    @Column(name = "id_practicas", nullable = false)
    private Integer idPracticas;

    @Column(name = "id_ordenes", nullable = false)
    private Integer idOrdenes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrdenTienePracticaId entity = (OrdenTienePracticaId) o;
        return Objects.equals(this.idOrdenes, entity.idOrdenes) &&
                Objects.equals(this.idPracticas, entity.idPracticas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdenes, idPracticas);
    }

}