package com.ybc.ybioq.api.dto;

public record RecepcionFilaDto(
        Integer idPaciente,
        Integer idPersona,
        Integer dni,
        String apellido,
        String nombre,
        boolean esPaciente
) {}
