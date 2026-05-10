package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "nbu", schema = "bioquimicos")
public class Nbu {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "año")
    private Integer periodoNbu;

    @Column(name = "detalle", length = 50)
    private String detalle;
}
