package com.ybc.ybioq.fx.client.dto;

public record ActualizarResultadoRequest(
        String resultado,
        String observacion,
        Integer estadoImprime,
        Integer imprimirNombre
) {}
