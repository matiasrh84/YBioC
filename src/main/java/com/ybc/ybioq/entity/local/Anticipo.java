package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "anticipo", schema = "bioquimicos")
public class Anticipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal anticipo;

    @Column(name = "id_orden")
    private Integer idOrden;

    @Column(name = "estado")
    private Integer estado;

    @Column(name = "fecha")
    private java.time.LocalDate fecha;

    @Column(name = "observacion")
    private String observacion;

    @Transient
    private Integer idHistoriaClinica;
}
