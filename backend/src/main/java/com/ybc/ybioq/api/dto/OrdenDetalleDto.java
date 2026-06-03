package com.ybc.ybioq.api.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrdenDetalleDto(
        Integer id,
        String numeroOrden,
        Integer periodo,
        LocalDateTime fecha,
        Integer idPaciente,
        String nombrePaciente,
        Integer dniPaciente,
        Integer idMedico,
        String nombreMedico,
        Integer idEspecialidad,
        String nombreEspecialidad,
        Integer idObraSocial,
        String nombreObraSocial,
        String tipoOrden,
        BigDecimal total,
        Integer estadoOrden,
        String estadoResultados,
        List<LineaOrdenDetalleDto> practicas
) {}
