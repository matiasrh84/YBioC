package com.ybc.ybioq.api.dto;

public record TituloDto(
        Integer id,
        String nombre,
        boolean estado,
        Integer prioridad
) {
}
