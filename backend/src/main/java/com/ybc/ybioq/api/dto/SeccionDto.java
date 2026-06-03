package com.ybc.ybioq.api.dto;

public record SeccionDto(
        Integer id,
        String nombre,
        Integer prioridad,
        boolean estado
) {}
