package com.ybc.ybioq.api.dto;

import java.math.BigDecimal;

public record PrecioConsultaDto(
        Integer idPractica,
        BigDecimal precio,
        String codFac,
        boolean esPrecioNbu
) {}
