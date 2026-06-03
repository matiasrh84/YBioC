package com.ybc.ybioq.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrdenDto(
        Integer id,
        String numeroOrden,
        Integer periodo,
        LocalDateTime fecha,
        Integer idPaciente,
        String nombrePaciente,
        Integer dniPaciente,
        Integer idMedico,
        String nombreMedico,
        Integer idObraSocial,
        String nombreObraSocial,
        String tipoOrden,
        BigDecimal total,
        Integer estadoOrden,
        String estadoResultados
) {}
