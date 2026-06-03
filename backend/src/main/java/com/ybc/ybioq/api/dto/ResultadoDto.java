package com.ybc.ybioq.api.dto;

public record ResultadoDto(
        Integer idAnalisis,
        Integer idPracticas,
        Integer idOrdenes,
        Integer idUsuarios,
        String resultado,
        String observacion,
        Integer estadoImprime,
        Integer imprimirNombre,
        String nombreAnalisis,
        String tipoResultado,
        String unidad,
        String valoresReferencia,
        String nombrePractica,
        Integer codigoPractica
) {}
