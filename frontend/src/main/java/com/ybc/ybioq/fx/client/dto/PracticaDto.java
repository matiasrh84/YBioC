package com.ybc.ybioq.fx.client.dto;

import java.math.BigDecimal;

public record PracticaDto(
        Integer id,
        Integer codigoPractica,
        String determinacion,
        String instrucciones,
        Integer idSeccion,
        String nombreSeccion,
        Integer estadoDeriva,
        Integer idDerivacion,
        String nombreDerivacion,
        Integer prioridad,
        Integer tiempoProcesamiento,
        String tipoInforme,
        BigDecimal precio1,
        BigDecimal precio2,
        BigDecimal precio3,
        BigDecimal precio4
) {}
