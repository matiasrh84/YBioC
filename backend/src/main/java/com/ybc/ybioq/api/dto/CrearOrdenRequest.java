package com.ybc.ybioq.api.dto;

import java.math.BigDecimal;
import java.util.List;

public record CrearOrdenRequest(
        Integer idPaciente,
        Integer idMedico,
        Integer idEspecialidad,
        Integer idObraSocial,
        Integer idUsuario,
        String numeroOrden,
        String tipoOrden,
        Integer periodo,
        BigDecimal sena,
        List<LineaOrdenRequest> practicas
) {
    public record LineaOrdenRequest(
            Integer idPractica,
            BigDecimal precio,
            String codPracticaFac
    ) {}
}
