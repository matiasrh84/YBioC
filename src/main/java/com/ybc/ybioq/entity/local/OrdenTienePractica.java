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
@Table(name = "ordenes_tienen_practicas")
public class OrdenTienePractica {

    @EmbeddedId
    private OrdenTienePracticaId id;

    @ColumnDefault("''")
    @Column(name = "precio_practica", nullable = false, length = 9)
    private String precioPractica;

    @ColumnDefault("''")
    @Column(name = "cod_practica_fac", nullable = false, length = 6)
    private String codPracticaFac;

    @ColumnDefault("0")
    @Column(name = "factura", nullable = false)
    private Integer factura;

    @ColumnDefault("1")
    @Column(name = "estado", nullable = false)
    private Integer estado;

}