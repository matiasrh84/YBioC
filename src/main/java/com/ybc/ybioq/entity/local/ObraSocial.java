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
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "int_codigo", length = 50)
    private String intCodigo;

    @Column(name = "celular_referente", length = 45)
    private String celularReferente;

    @Column(name = "importe_unidad_de_arancel")
    private Double importeUnidadDeArancel;

    @Column(name = "razon_social", length = 45)
    private String razonSocial;

    @Column(name = "importe_unidad_de_gasto", length = 45)
    private String importeUnidadDeGasto;

    @Column(name = "codigo_facturacion", length = 45)
    private String codigoFacturacion;

    @Column(name = "codigo_fac_liq", length = 45)
    private String codigoFacLiq;

    @Column(name = "codigo", length = 45)
    private String codigo;

    @Column(name = "cuit", length = 45)
    private String cuit;

    @Column(name = "direccion", length = 45)
    private String direccion;

    @Column(name = "factura_alta_complejidad", length = 2)
    private String facturaAltaComplejidad;

    @Column(name = "factura_no_nomenclados", length = 2)
    private String facturaNoNomenclados;

    @Column(name = "factura_por", length = 45)
    private String facturaPor;

    @Column(name = "factura_por_paciente", length = 2)
    private String facturaPorPaciente;

    @Column(name = "fax", length = 45)
    private String fax;

    @Column(name = "fecha_de_alta")
    private LocalDate fechaDeAlta;

    @Column(name = "fecha_de_baja")
    private LocalDate fechaDeBaja;

    @Column(name = "web", length = 45)
    private String web;

    @Column(name = "imprime_doble_informe", length = 45)
    private String imprimeDobleInforme;

    @Column(name = "d998", length = 45)
    private String d998;

    @Column(name = "mail1", length = 45)
    private String mail1;

    @Column(name = "mail2", length = 45)
    private String mail2;

    @Column(name = "mail3", length = 45)
    private String mail3;

    @Column(name = "nombre_referente", length = 45)
    private String nombreReferente;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "numero_resolucion_ingreso", length = 45)
    private String numeroResolucionIngreso;

    @Column(name = "n_tabla_aranceles", length = 45)
    private String nTablaAranceles;

    @Column(name = "porcentaje_afiliado", length = 45)
    private String porcentajeAfiliado;

    @Column(name = "porcentaje_descuento", length = 45)
    private String porcentajeDescuento;

    @Column(name = "subtotal_por_paciente", length = 2)
    private String subtotalPorPaciente;

    @Column(name = "telefono", length = 45)
    private String telefono;

    @Column(name = "tiene_categorizacion", length = 2)
    private String tieneCategorizacion;

    @Column(name = "tipo_de_facturacion", length = 45)
    private String tipoDeFacturacion;

    @Column(name = "tipo_de_facturacion_directa_o_colegio", length = 45)
    private String tipoDeFacturacionDirectaOColegio;

    @Column(name = "tipo_iva", length = 45)
    private String tipoIva;

    @Column(name = "periodo_nbu", length = 4)
    private String periodoNbu;

    @Column(name = "estado")
    private Integer estado;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_localidad", nullable = false)
    private Localidad localidad;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia provincia;
}