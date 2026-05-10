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
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "valores_referencia")
    private String valoresReferencia;

    @Column(name = "unidad", length = 100)
    private String unidad;

    @Column(name = "codigo_interno", nullable = false)
    private String codigoInterno;

    @Column(name = "estado_titulo", nullable = false)
    private Integer estadoTitulo;

    @Column(name = "tipo_resultado", nullable = false)
    private String tipoResultado;

    @Column(name = "unidad_extra")
    private String unidadExtra;

    @Column(name = "prioridad")
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
