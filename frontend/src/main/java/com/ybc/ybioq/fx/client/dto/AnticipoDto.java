package com.ybc.ybioq.fx.client.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AnticipoDto {
    private Integer id;
    private BigDecimal anticipo;
    private Integer idOrden;
    private Integer estado;
    private LocalDate fecha;
    private String observacion;
    private Integer idHistoriaClinica;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getAnticipo() {
        return anticipo;
    }

    public void setAnticipo(BigDecimal anticipo) {
        this.anticipo = anticipo;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getIdHistoriaClinica() {
        return idHistoriaClinica;
    }

    public void setIdHistoriaClinica(Integer idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }
}
