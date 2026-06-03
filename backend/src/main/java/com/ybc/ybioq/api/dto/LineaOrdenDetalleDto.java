package com.ybc.ybioq.api.dto;

import java.math.BigDecimal;

public record LineaOrdenDetalleDto(
        Integer idPractica,
        Integer codigoPractica,
        String nombre,
        BigDecimal precio,
        String codFac
) {}
