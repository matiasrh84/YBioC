package com.ybc.ybioq.api.dto;

import com.ybc.ybioq.entity.local.Usuario;

public record UsuarioResponse(
        Integer id,
        String usuario,
        String nombre,
        String apellido,
        Integer datos,
        Integer informes,
        Integer facturacion
) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDatos(),
                usuario.getInformes(),
                usuario.getFacturacion()
        );
    }
}
