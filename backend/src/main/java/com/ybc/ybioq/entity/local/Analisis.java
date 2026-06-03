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

    @Column(name = "valores_referencia", length = 500)
    private String valoresReferencia;

    @Column(name = "unidad", length = 100)
    private String unidad;

    @Column(name = "codigo_interno", nullable = false, length = 15)
    private String codigoInterno;

    @Column(name = "estado_titulo", nullable = false, columnDefinition = "tinyint(1) DEFAULT 0")
    private boolean estadoTitulo;

    @Column(name = "tipo_resultado", nullable = false, length = 25)
    private String tipoResultado;

    @Column(name = "unidad_extra", length = 100)
    private String unidadExtra;

    @Column(name = "prioridad")
    private Integer prioridad;

    @Column(nullable = false, columnDefinition = "tinyint(1) DEFAULT 1")
    private boolean estado = true;

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
