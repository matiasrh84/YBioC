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
public class ObraSocialTienePracticaNbuId implements java.io.Serializable {

    private static final long serialVersionUID = -514024944330224026L;

    @Column(name = "id_obrasocial", nullable = false)
    private Integer idObrasocial;

    @Column(name = "id_practicasnbu", nullable = false)
    private Integer idPracticasnbu;

    @Column(name = "id_nbu", nullable = false)
    private Integer idNbu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ObraSocialTienePracticaNbuId entity = (ObraSocialTienePracticaNbuId) o;
        return Objects.equals(this.idNbu, entity.idNbu) &&
                Objects.equals(this.idObrasocial, entity.idObrasocial) &&
                Objects.equals(this.idPracticasnbu, entity.idPracticasnbu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNbu, idObrasocial, idPracticasnbu);
    }
}