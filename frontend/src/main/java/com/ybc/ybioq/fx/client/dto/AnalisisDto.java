package com.ybc.ybioq.fx.client.dto;

import java.util.ArrayList;
import java.util.List;

public class AnalisisDto {
    private Integer id;
    private String nombre; // La "Práctica"
    private String nombreInforme;
    private String metodo;
    private String instrucciones;
    private boolean derivacion;
    private String formatoMesada;
    private List<ParametroDto> parametros = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreInforme() {
        return nombreInforme;
    }

    public void setNombreInforme(String nombreInforme) {
        this.nombreInforme = nombreInforme;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public boolean isDerivacion() {
        return derivacion;
    }

    public void setDerivacion(boolean derivacion) {
        this.derivacion = derivacion;
    }

    public String getFormatoMesada() {
        return formatoMesada;
    }

    public void setFormatoMesada(String formatoMesada) {
        this.formatoMesada = formatoMesada;
    }

    public List<ParametroDto> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroDto> parametros) {
        this.parametros = parametros;
    }
}
