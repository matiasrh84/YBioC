package com.ybc.ybioq.fx.client.dto;

public record DerivacionDto(
        Integer id,
        String nombre,
        String direccion,
        Long telefono,
        String mail,
        String observaciones) {
}
