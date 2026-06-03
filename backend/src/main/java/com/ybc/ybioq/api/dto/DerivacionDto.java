package com.ybc.ybioq.api.dto;

public record DerivacionDto(
        Integer id,
        String nombre,
        String direccion,
        Long telefono,
        String mail,
        String observaciones
) {
}
