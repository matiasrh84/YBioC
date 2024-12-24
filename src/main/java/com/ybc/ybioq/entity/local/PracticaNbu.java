package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "practicas_nbu", schema = "bioquimicos")
public class PracticaNbu {

    @Id
    @Column(name = "id_practicasnbu", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_practicas", nullable = false)
    private Practica idPracticas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_nbu", nullable = false)
    private Nbu idNbu;

    @Column(name = "unidadbioquimica_practica", nullable = false, precision = 10, scale = 2)
    private BigDecimal unidadbioquimicaPractica;

    @Column(name = "fercuencia_practica", length = 10)
    private String fercuenciaPractica;
}