package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "caja", schema = "bioquimicos")
public class Caja {

    @Id
    @Column(name = "idCaja", nullable = false)
    private Integer id;

    @Column(name = "monto")
    private Double monto;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "idHistoriaClinica")
    private Integer idHistoriaClinica;
}