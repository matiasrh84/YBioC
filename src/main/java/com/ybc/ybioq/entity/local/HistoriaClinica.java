package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "historia_clinica", schema = "bioquimicos")
public class HistoriaClinica {

    @Id
    @Column(name = "idhistoria_clinica", nullable = false)
    private Integer idhistoriaClinica;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "id_ordenes", nullable = false)
    private Integer idOrdenes;

    @Column(name = "fecha_entrega", length = 10)
    private String fechaEntrega;

    @Column(name = "estado", nullable = false)
    private Integer estado;
}