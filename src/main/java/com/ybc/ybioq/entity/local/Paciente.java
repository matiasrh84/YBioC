package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "pacientes", schema = "bioquimicos")
public class Paciente {

    @Id
    @Column(name = "id_Pacientes", nullable = false)
    private Integer id;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "celular", length = 20)
    private String celular;

    @Column(name = "mail", length = 45)
    private String mail;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "personas_dni", nullable = false)
    private Persona personasDni;

    @Column(name = "estado", nullable = false)
    private Integer estado;
}