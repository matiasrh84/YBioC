package com.ybc.ybioq.fx.client.dto;

import java.math.BigDecimal;

public record AnticipoDto(
        Integer id,
        BigDecimal anticipo,
        Integer idOrden,
        Integer estado,
        String fecha,
        String observacion) {
}
