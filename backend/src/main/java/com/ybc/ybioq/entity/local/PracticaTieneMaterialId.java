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
public class PracticaTieneMaterialId implements java.io.Serializable {

    private static final long serialVersionUID = 6149723643185917412L;

    @Column(name = "id_materiales", nullable = false)
    private Integer idMateriales;

    @ColumnDefault("0")
    @Column(name = "id_practicas", nullable = false)
    private Integer idPracticas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PracticaTieneMaterialId entity = (PracticaTieneMaterialId) o;
        return Objects.equals(this.idMateriales, entity.idMateriales) &&
                Objects.equals(this.idPracticas, entity.idPracticas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMateriales, idPracticas);
    }
}