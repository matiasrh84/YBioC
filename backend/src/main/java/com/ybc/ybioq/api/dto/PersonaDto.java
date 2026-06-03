package com.ybc.ybioq.api.dto;

public record PersonaDto(
        Integer id,
        Integer dni,
        String apellido,
        String nombre
) {}
