package com.ybc.ybioq.config;

public class camposinformes {

    String titulos, analisis, resultados, unidades, referencias, observacion, metodo;

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public camposinformes(String observacion) {
        this.observacion = observacion;
    }

    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getTitulos() {
        return titulos;
    }

    public String getAnalisis() {
        return analisis;
    }

    public String getResultados() {
        return resultados;
    }

    public String getUnidades() {
        return unidades;
    }

    public String getReferencias() {
        return referencias;
    }

    public camposinformes(String titulos, String analisis, String resultados, String unidades, String referencias, String observacion, String metodo) {
        this.titulos = titulos;
        this.analisis = analisis;
        this.resultados = resultados;
        this.unidades = unidades;
        this.referencias = referencias;
        this.observacion = observacion;
        this.metodo = metodo;
    }


}
