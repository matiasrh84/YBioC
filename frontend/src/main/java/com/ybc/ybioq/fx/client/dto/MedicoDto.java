package com.ybc.ybioq.fx.client.dto;

import java.util.List;

public record MedicoDto(
        Integer id,
        String apellido,
        String nombre,
        Integer matricula,
        String mail,
        Long telefono,
        String observaciones,
        Integer estado,
        List<EspecialidadDto> especialidades) {
}
