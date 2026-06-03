package com.ybc.ybioq.fx.client.dto;

public record RecepcionFilaDto(
        Integer idPaciente,
        Integer idPersona,
        Integer dni,
        String apellido,
        String nombre,
        boolean esPaciente
) {}
