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
@Table(name = "informes", schema = "bioquimicos")
public class Informe {
    @Id
    @Column(name = "id_informes", nullable = false)
    private Integer id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "idhistoria_clinica", nullable = false)
    private Integer idhistoriaClinica;

}