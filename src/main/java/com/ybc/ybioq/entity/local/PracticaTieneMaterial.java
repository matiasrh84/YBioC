package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "practicas_tienen_materiales", schema = "bioquimicos")
public class PracticaTieneMaterial {
    @EmbeddedId
    private PracticaTieneMaterialId id;

    @MapsId("idMateriales")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_materiales", nullable = false)
    private Material idMateriales;

    @MapsId("idPracticas")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ColumnDefault("0")
    @JoinColumn(name = "id_practicas", nullable = false)
    private Practica idPracticas;

    @ColumnDefault("0")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

}