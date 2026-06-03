package com.ybc.ybioq.api.dto;

public record ModificarOrdenRequest(
        String  numeroOrden,
        String  tipoOrden,
        Integer idMedico
) {}
