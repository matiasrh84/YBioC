package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
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

    @ColumnDefault("000000")
    @Column(name = "codigo_practica", nullable = false)
    private Integer codigoPractica;

    @ColumnDefault("''")
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

    @ColumnDefault("0")
    @Column(name = "estado_deriva", nullable = false)
    private Integer estadoDeriva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_seccion")
    private Seccion idSeccion;

    @Column(name = "prioridad")
    private Integer prioridad;

    @ColumnDefault("1")
    @Column(name = "tiempo_procesamiento", nullable = false)
    private Integer tiempoProcesamiento;
    @ColumnDefault("0")
    @Column(name = "tipo_informe", nullable = false)
    private Integer tipoInforme;

/*
 TODO [Reverse Engineering] create field to map the 'precio1' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("0")
    @Column(name = "precio1", columnDefinition = "float UNSIGNED")
    private Object precio1;
*/
/*
 TODO [Reverse Engineering] create field to map the 'precio2' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("0")
    @Column(name = "precio2", columnDefinition = "float UNSIGNED")
    private Object precio2;
*/
/*
 TODO [Reverse Engineering] create field to map the 'precio3' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("0")
    @Column(name = "precio3", columnDefinition = "float UNSIGNED")
    private Object precio3;
*/
/*
 TODO [Reverse Engineering] create field to map the 'precio4' column
 Available actions: Define target Java type | Uncomment as is | Remove column mapping
    @ColumnDefault("0")
    @Column(name = "precio4", columnDefinition = "float UNSIGNED")
    private Object precio4;
*/
}