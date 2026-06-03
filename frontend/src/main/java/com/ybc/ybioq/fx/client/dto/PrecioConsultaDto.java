package com.ybc.ybioq.fx.client.dto;

import java.math.BigDecimal;

public record PrecioConsultaDto(
        Integer idPractica,
        BigDecimal precio,
        String codFac,
        boolean esPrecioNbu
) {}
