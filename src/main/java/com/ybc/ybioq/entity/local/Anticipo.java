package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "anticipo", schema = "bioquimicos")
public class Anticipo {

    @Id
    @Column(name = "`id_anticipo`", nullable = false)
    private Integer id;

    @Column(name = "anticipo")
    private Double anticipo;

    @Column(name = "id_historia_clinica", nullable = false)
    private Integer idHistoriaClinica;
}