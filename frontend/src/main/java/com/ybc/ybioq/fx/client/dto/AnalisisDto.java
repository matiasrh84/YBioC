package com.ybc.ybioq.fx.client.dto;

public record AnalisisDto(
        Integer id,
        String nombre,
        String codigoInterno,
        String tipoResultado,
        String unidad,
        String unidadExtra,
        String valoresReferencia,
        Integer prioridad,
        boolean estadoTitulo,
        boolean estado,
        Integer idPractica,
        Integer idMetodo,
        String nombreMetodo,
        Integer idTitulo,
        String nombreTitulo
) {
}
