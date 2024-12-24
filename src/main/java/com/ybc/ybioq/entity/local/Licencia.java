package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "licencia", schema = "bioquimicos")
public class Licencia {
    @Id
    @Column(name = "id_licencia", nullable = false)
    private Integer id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "contador")
    private Integer contador;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "nombre_lab")
    private String nombreLab;

    @Column(name = "lugar_lab", length = 100)
    private String lugarLab;

    @Column(name = "Horario_inicio", length = 6)
    private String horarioInicio;

    @Column(name = "horario_fin", length = 6)
    private String horarioFin;

    @Column(name = "dias", length = 50)
    private String dias;

}