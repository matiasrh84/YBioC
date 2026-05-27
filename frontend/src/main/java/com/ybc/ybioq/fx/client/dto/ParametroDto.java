package com.ybc.ybioq.fx.client.dto;

public class ParametroDto {
    private Integer id;
    private String nombre;
    private String valorReferencia;
    private String unidad; // Aquí usarías el ID de la unidad que hicimos antes
    private String sexo;

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

    public String getValorReferencia() {
        return valorReferencia;
    }

    public void setValorReferencia(String valorReferencia) {
        this.valorReferencia = valorReferencia;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
