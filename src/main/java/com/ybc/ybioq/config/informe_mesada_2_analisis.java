/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ybc.ybioq.config;

/**
 * @author Fernando ****infomes de mesadas*****
 */
public class informe_mesada_2_analisis {
    String seccion, titulos, nombre_analisis1, nombre_analisis2;

    public informe_mesada_2_analisis(String seccion, String titulos, String nombre_analisis1, String nombre_analisis2) {
        this.seccion = seccion;
        this.titulos = titulos;
        this.nombre_analisis1 = nombre_analisis1;
        this.nombre_analisis2 = nombre_analisis2;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getTitulos() {
        return titulos;
    }

    public void setTitulos(String titulos) {
        this.titulos = titulos;
    }

    public String getNombre_analisis1() {
        return nombre_analisis1;
    }

    public void setNombre_analisis1(String nombre_analisis1) {
        this.nombre_analisis1 = nombre_analisis1;
    }

    public String getNombre_analisis2() {
        return nombre_analisis2;
    }

    public void setNombre_analisis2(String nombre_analisis2) {
        this.nombre_analisis2 = nombre_analisis2;
    }


}
