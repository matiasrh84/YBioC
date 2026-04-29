package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(
        name = "configuracion_reporte_layout",
        schema = "bioquimicos",
        uniqueConstraints = @UniqueConstraint(name = "uk_layout", columnNames = {"id_laboratorio", "elemento", "instancia"})
)
public class ConfiguracionReporteLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_laboratorio", nullable = false)
    private ConfiguracionReporte configuracionReporte;

    @Column(name = "elemento", nullable = false, length = 30)
    private String elemento;

    @Column(name = "instancia", nullable = false)
    private Integer instancia;

    @Column(name = "col")
    private Integer col;

    @Column(name = "fila")
    private Integer fila;
}
