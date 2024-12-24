package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "practicas", schema = "bioquimicos")
public class Practica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_practicas", nullable = false)
    private Integer id;

    @Column(name = "codigo_practica", nullable = false)
    private Integer codigoPractica;

    @Column(name = "determinacion_practica")
    private String determinacionPractica;

    @Column(name = "instrucciones")
    private String instrucciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_derivaciones")
    private Derivacion idDerivaciones;

    @Column(name = "metodo")
    private String metodo;

    @Column(name = "estado_deriva", nullable = false)
    private Integer estadoDeriva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seccion")
    private Seccion idSeccion;

    @Column(name = "prioridad")
    private Integer prioridad;

    @Column(name = "tiempo_procesamiento", nullable = false)
    private Integer tiempoProcesamiento;
    @Column(name = "tipo_informe", nullable = false)
    private Integer tipoInforme;

    @Column(name = "precio1", columnDefinition = "float UNSIGNED")
    private double precio1;

    @Column(name = "precio2", columnDefinition = "float UNSIGNED")
    private double precio2;

    @Column(name = "precio3", columnDefinition = "float UNSIGNED")
    private double precio3;

    @Column(name = "precio4", columnDefinition = "float UNSIGNED")
    private double precio4;
}