package com.ybc.ybioq.fx.client.dto;

public record UsuarioSession(
        Integer id,
        String usuario,
        String nombre,
        String apellido,
        Integer datos,
        Integer informes,
        Integer facturacion
) {
}
