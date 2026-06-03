package com.ybc.ybioq.fx.client.dto;

import java.math.BigDecimal;

public record LineaOrdenDetalleDto(
        Integer idPractica,
        Integer codigoPractica,
        String nombre,
        BigDecimal precio,
        String codFac
) {}
