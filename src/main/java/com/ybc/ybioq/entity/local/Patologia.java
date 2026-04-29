package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "patologias")
public class Patologia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private String nombre;
    private String descripcion;
    private String tipo;
    private LocalDate fechaInicio;
    private Boolean estado;
}