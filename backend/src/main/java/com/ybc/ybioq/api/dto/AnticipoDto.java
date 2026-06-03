package com.ybc.ybioq.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AnticipoDto(
        Integer id,
        BigDecimal anticipo,
        Integer idOrden,
        Integer estado,
        LocalDate fecha,
        String observacion
) {}
