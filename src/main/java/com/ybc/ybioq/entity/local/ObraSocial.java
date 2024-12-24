package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
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
    private String intCodigoObrasocial;

    @Column(name = "celularreferente_obrasocial", length = 45)
    private String celularreferenteObrasocial;

    @Column(name = "importeunidaddearancel_obrasocial")
    private Double importeunidaddearancelObrasocial;

    @Column(name = "razonsocial_obrasocial", length = 45)
    private String razonsocialObrasocial;

    @Column(name = "importeunidaddegasto_obrasocial", length = 45)
    private String importeunidaddegastoObrasocial;

    @Column(name = "codigofacturacion_obrasocial", length = 45)
    private String codigofacturacionObrasocial;

    @Column(name = "codigofacliq_obrasocial", length = 45)
    private String codigofacliqObrasocial;

    @Column(name = "codigo_obrasocial", length = 45)
    private String codigoObrasocial;

    @Column(name = "codigopostal_obrasocial", length = 45)
    private String codigopostalObrasocial;

    @Column(name = "cuit_obrasocial", length = 45)
    private String cuitObrasocial;

    @Column(name = "direccion_obrasocial", length = 45)
    private String direccionObrasocial;

    @Column(name = "facturaaltacomplejidad", length = 2)
    private String facturaaltacomplejidad;

    @Column(name = "facturanonomenclados", length = 2)
    private String facturanonomenclados;

    @Column(name = "facturapor", length = 45)
    private String facturapor;

    @Column(name = "facturaporpaciente", length = 2)
    private String facturaporpaciente;

    @Column(name = "fax_obrasocial", length = 45)
    private String faxObrasocial;

    @Column(name = "fechadealta_obrasocial")
    private LocalDate fechadealtaObrasocial;

    @Column(name = "fechadebaja_obrasocial")
    private LocalDate fechadebajaObrasocial;

    @Column(name = "http_obrasocial", length = 45)
    private String httpObrasocial;

    @Column(name = "imprimedobleinforme", length = 45)
    private String imprimedobleinforme;

    @Column(name = "d998", length = 45)
    private String d998;

    @Column(name = "localidad_obrasocial", length = 45)
    private String localidadObrasocial;

    @Column(name = "mail1_obrasocial", length = 45)
    private String mail1Obrasocial;

    @Column(name = "mail2_obrasocial", length = 45)
    private String mail2Obrasocial;

    @Column(name = "mail3_obrasocial", length = 45)
    private String mail3Obrasocial;

    @Column(name = "nombrereferente_obrasocial", length = 45)
    private String nombrereferenteObrasocial;

    @Column(name = "nombre_obrasocial", length = 45)
    private String nombreObrasocial;

    @Column(name = "nresolucioningreso_obrasocial", length = 45)
    private String nresolucioningresoObrasocial;

    @Column(name = "ntablaaranceles_obrasocial", length = 45)
    private String ntablaarancelesObrasocial;

    @Column(name = "porcentajeafiliado_obrasocial", length = 45)
    private String porcentajeafiliadoObrasocial;

    @Column(name = "porcentajedescuento_obrasocial", length = 45)
    private String porcentajedescuentoObrasocial;

    @Column(name = "provincia_obrasocial", length = 45)
    private String provinciaObrasocial;

    @Column(name = "subtotalporpaciente", length = 2)
    private String subtotalporpaciente;

    @Column(name = "telefono_obrasocial", length = 45)
    private String telefonoObrasocial;

    @Column(name = "tienecategorizacion", length = 2)
    private String tienecategorizacion;

    @Column(name = "tipodefacturacion", length = 45)
    private String tipodefacturacion;

    @Column(name = "tipodefacturaciondirectaocolegio", length = 45)
    private String tipodefacturaciondirectaocolegio;

    @Column(name = "tipoiva", length = 45)
    private String tipoiva;

    @Column(name = "`añonbu`", length = 4)
    private String añonbu;

    @Column(name = "estado_obrasocial")
    private Integer estadoObrasocial;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ColumnDefault("2358")
    @JoinColumn(name = "id_localidad", nullable = false)
    private Localidad idLocalidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ColumnDefault("25")
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia idProvincia;

}