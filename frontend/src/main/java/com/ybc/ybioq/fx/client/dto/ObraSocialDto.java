package com.ybc.ybioq.fx.client.dto;

import java.math.BigDecimal;

public record ObraSocialDto(
        Integer id,
        String codigo,
        String razonSocial,
        String nombre,
        String cuit,
        String codigoFacturacion,
        String telefono,
        String mail1,
        String mail2,
        String mail3,
        String web,
        String direccion,
        String fechaDeAlta,
        String nombreReferente,
        String celularReferente,
        String periodoNbu,
        BigDecimal importeUnidadDeArancel,
        String porcentajeAfiliado,
        String porcentajeDescuento,
        boolean facturaAltaComplejidad,
        boolean facturaNoNomenclados,
        String facturaPor,
        boolean facturaPorPaciente,
        String imprimeDobleInforme,
        String d998,
        boolean subtotalPorPaciente,
        boolean tieneCategorizacion,
        String tipoDeFacturacion,
        String tipoDeFacturacionDirectaOColegio,
        String tipoIva,
        boolean estado
) {
}
