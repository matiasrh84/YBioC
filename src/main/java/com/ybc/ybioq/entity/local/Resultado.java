package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "resultados")
public class Resultado {
    @EmbeddedId
    private ResultadoId id;

    @Column(name = "resultado", length = 500)
    private String resultado;

    @Column(name = "observacion")
    private String observacion;

    @ColumnDefault("0")
    @Column(name = "estado_imprime")
    private Integer estadoImprime;

    @ColumnDefault("0")
    @Column(name = "imprimir_nombre", nullable = false)
    private Integer imprimirNombre;

}