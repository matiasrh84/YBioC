package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "analisis", schema = "bioquimicos")
public class Analisis {
    @Id
    @Column(name = "id_analisis", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "valoresreferencia", length = 1000)
    private String valoresreferencia;

    @Column(name = "unidad", length = 100)
    private String unidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_practicas", nullable = false)
    private Practica idPracticas;

    @ColumnDefault("''")
    @Column(name = "codigo_interno", nullable = false, length = 15)
    private String codigoInterno;

    @ColumnDefault("0")
    @Column(name = "estado_titulo", nullable = false)
    private Integer estadoTitulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_titulo")
    private Titulo idTitulo;

    @ColumnDefault("''")
    @Column(name = "tipo_resultado", nullable = false, length = 25)
    private String tipoResultado;

    @Column(name = "unidad_extra", length = 100)
    private String unidadExtra;

    @Column(name = "prioridad")
    private Integer prioridad;

    @ColumnDefault("1")
    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "idMetodos")
    private Integer idMetodos;

}