package com.ybc.ybioq.api.dto;

import java.time.LocalDate;

public record ActualizarPacienteRequest(LocalDate fechaNacimiento, String telefono, String mail) {}
