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

    private Integer idHistoriaClinica;
}