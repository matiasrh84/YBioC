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
@Table(name = "nbu", schema = "bioquimicos")
public class Nbu {

    @Id
    @Column(name = "id_nbu", nullable = false)
    private Integer id;

    @Column(name = "periodo_nbu")
    private Integer periodoNbu;
}