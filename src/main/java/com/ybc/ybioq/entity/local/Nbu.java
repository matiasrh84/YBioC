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
    @Column(name = "id_nbu", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "periodo_nbu")
    private Integer periodoNbu;
}