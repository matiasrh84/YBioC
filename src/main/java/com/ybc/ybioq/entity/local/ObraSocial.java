package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "obrasocial", schema = "bioquimicos")
public class ObraSocial {

    @Id
    @Column(name = "id_obrasocial", nullable = false)
    private Integer id;

    @Column(name = "Int_codigo_obrasocial", length = 50)
    private String intCodigoObraSocial;

    @Column(name = "celularreferente_obrasocial", length = 45)
    private String celularReferenteObrasocial;

    @Column(name = "importeunidaddearancel_obrasocial")
    private Double importeUnidadDeArancelObraSocial;

    @Column(name = "razonsocial_obrasocial", length = 45)
    private String razonSocialObraSocial;

    @Column(name = "importeunidaddegasto_obrasocial", length = 45)
    private String importeUnidadDeGastoObraSocial;

    @Column(name = "codigofacturacion_obrasocial", length = 45)
    private String codigoFacturacionObraSocial;

    @Column(name = "codigofacliq_obrasocial", length = 45)
    private String codigoFacLiqObraSocial;

    @Column(name = "codigo_obrasocial", length = 45)
    private String codigoObraSocial;

    @Column(name = "codigopostal_obrasocial", length = 45)
    private String codigoPostalObraSocial;

    @Column(name = "cuit_obrasocial", length = 45)
    private String cuitObraSocial;

    @Column(name = "direccion_obrasocial", length = 45)
    private String direccionObraSocial;

    @Column(name = "facturaaltacomplejidad", length = 2)
    private String facturaAltaComplejidad;

    @Column(name = "facturanonomenclados", length = 2)
    private String facturaNoNomenclados;

    @Column(name = "facturapor", length = 45)
    private String facturaPor;

    @Column(name = "facturaporpaciente", length = 2)
    private String facturaPorPaciente;

    @Column(name = "fax_obrasocial", length = 45)
    private String faxObraSocial;

    @Column(name = "fechadealta_obrasocial")
    private LocalDate fechaDeAltaObraSocial;

    @Column(name = "fechadebaja_obrasocial")
    private LocalDate fechaDeBajaObraSocial;

    @Column(name = "http_obrasocial", length = 45)
    private String httpObraSocial;

    @Column(name = "imprimedobleinforme", length = 45)
    private String imprimeDobleInforme;

    @Column(name = "d998", length = 45)
    private String d998;

    @Column(name = "localidad_obrasocial", length = 45)
    private String localidadObraSocial;

    @Column(name = "mail1_obrasocial", length = 45)
    private String mail1ObraSocial;

    @Column(name = "mail2_obrasocial", length = 45)
    private String mail2ObraSocial;

    @Column(name = "mail3_obrasocial", length = 45)
    private String mail3ObraSocial;

    @Column(name = "nombrereferente_obrasocial", length = 45)
    private String nombreReferenteObraSocial;

    @Column(name = "nombre_obrasocial", length = 45)
    private String nombreObraSocial;

    @Column(name = "nresolucioningreso_obrasocial", length = 45)
    private String nResolucionIngresoObraSocial;

    @Column(name = "ntablaaranceles_obrasocial", length = 45)
    private String nTablaArancelesObraSocial;

    @Column(name = "porcentajeafiliado_obrasocial", length = 45)
    private String porcentajeAfiliadoObraSocial;

    @Column(name = "porcentajedescuento_obrasocial", length = 45)
    private String porcentajeDescuentoObraSocial;

    @Column(name = "provincia_obrasocial", length = 45)
    private String provinciaObraSocial;

    @Column(name = "subtotalporpaciente", length = 2)
    private String subtotalPorPaciente;

    @Column(name = "telefono_obrasocial", length = 45)
    private String telefonoObraSocial;

    @Column(name = "tienecategorizacion", length = 2)
    private String tieneCategorizacion;

    @Column(name = "tipodefacturacion", length = 45)
    private String tipoDeFacturacion;

    @Column(name = "tipodefacturaciondirectaocolegio", length = 45)
    private String tipoDeFacturacionDirectaOColegio;

    @Column(name = "tipoiva", length = 45)
    private String tipoIva;

    @Column(name = "periodo_nbu", length = 4)
    private String periodoNbu;

    @Column(name = "estado_obrasocial")
    private Integer estadoObraSocial;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_localidad", nullable = false)
    private Localidad idLocalidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia idProvincia;
}