package com.ybc.ybioq.api.dto;

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
        List<EspecialidadSimpleDto> especialidades
) {}
