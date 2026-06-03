package com.ybc.ybioq.api.dto;

import java.time.LocalDate;

public record PacienteDto(
        Integer id,
        Integer idPersona,
        Integer dni,
        String apellido,
        String nombre,
        LocalDate fechaNacimiento,
        String telefono,
        String celular,
        String mail
) {}
