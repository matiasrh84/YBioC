package com.ybc.ybioq.api.dto;

public record MedicoDto(
        Integer id,
        String apellido,
        String nombre,
        Integer matricula,
        String mail,
        Long telefono,
        String observaciones,
        Integer estado
) {
}
