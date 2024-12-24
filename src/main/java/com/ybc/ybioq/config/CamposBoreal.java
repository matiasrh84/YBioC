package com.ybc.ybioq.config;

public class CamposBoreal {

    String codigo, coseguro;

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCoseguro(String coseguro) {
        this.coseguro = coseguro;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCoseguro() {
        return coseguro;
    }

    public CamposBoreal(String codigo, String coseguro) {
        this.codigo = codigo;
        this.coseguro = coseguro;
    }


}
