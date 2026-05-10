package com.ybc.ybioq.config;


public class ClaseAnalisis {
    private int idAnalisis;
    private String nombre;

    public ClaseAnalisis(int idAnalisis, String nombre) {
        this.idAnalisis = idAnalisis;
        this.nombre = nombre;
    }

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}