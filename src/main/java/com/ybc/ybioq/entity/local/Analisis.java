package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "analisis", schema = "bioquimicos")
public class Analisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String valoresReferencia;

    private String unidad;

    @Column(nullable = false)
    private String codigoInterno;

    @Column(nullable = false)
    private Integer estadoTitulo;

    @Column(nullable = false)
    private String tipoResultado;

    private String unidadExtra;

    private Integer prioridad;

    @Column(nullable = false)
    private Integer estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_metodo")
    private Metodo metodo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_practica", nullable = false)
    private Practica practica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_titulo")
    private Titulo titulo;
}
