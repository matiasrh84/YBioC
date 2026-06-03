package com.ybc.ybioq.api.dto;

public record ActualizarResultadoRequest(
        String resultado,
        String observacion,
        Integer estadoImprime,
        Integer imprimirNombre
) {}
