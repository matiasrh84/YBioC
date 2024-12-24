/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ybc.ybioq.config;

public class CamposInformes2 {

    String practica, titulos, analisis, titulos_1, analisis_1, resultados, resultados_1, valor, valor_1, unidad, unidad_1, observacion;

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public void setUnidad_1(String unidad_1) {
        this.unidad_1 = unidad_1;
    }

    public String getUnidad() {
        return unidad;
    }

    public String getUnidad_1() {
        return unidad_1;
    }


    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setValor_1(String valor_1) {
        this.valor_1 = valor_1;
    }

    public String getValor() {
        return valor;
    }

    public String getValor_1() {
        return valor_1;
    }

    public void setPractica(String practica) {
        this.practica = practica;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public void setResultados_1(String resultados_1) {
        this.resultados_1 = resultados_1;
    }

    public String getResultados_1() {
        return resultados_1;
    }

    public String getPractica() {
        return practica;
    }


    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    public void setTitulos_1(String titulos_1) {
        this.titulos_1 = titulos_1;
    }

    public void setAnalisis_1(String analisis_1) {
        this.analisis_1 = analisis_1;
    }

    public String getTitulos() {
        return titulos;
    }

    public String getAnalisis() {
        return analisis;
    }

    public String getTitulos_1() {
        return titulos_1;
    }

    public String getAnalisis_1() {
        return analisis_1;
    }


    public CamposInformes2(String practica, String titulos, String analisis, String titulos_1, String analisis_1, String resultados, String resultados_1, String valor, String valor_1, String unidad, String unidad_1, String observacion) {
        this.titulos = titulos;
        this.analisis = analisis;
        this.titulos_1 = titulos_1;
        this.analisis_1 = analisis_1;
        this.practica = practica;
        this.resultados = resultados;
        this.resultados_1 = resultados_1;
        this.valor = valor;
        this.valor_1 = valor_1;
        this.unidad = unidad;
        this.unidad_1 = unidad_1;
        this.observacion = observacion;
    }
}
