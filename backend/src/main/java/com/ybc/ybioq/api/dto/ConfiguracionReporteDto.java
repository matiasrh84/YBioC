package com.ybc.ybioq.api.dto;

public record ConfiguracionReporteDto(
        Integer id,
        String nombre,
        String direccion,
        String telefono,
        String mail,
        String observacion,
        String observacion2,
        byte[] logo,
        byte[] firma,
        byte[] portada,
        byte[] membrete,
        String formato,
        String orientacion,
        String diseno) {}
