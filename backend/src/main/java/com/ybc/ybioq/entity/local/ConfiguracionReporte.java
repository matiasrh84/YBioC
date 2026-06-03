package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "configuracion_reporte", schema = "bioquimicos")
public class ConfiguracionReporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "mail")
    private String mail;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "observacion2")
    private String observacion2;

    @Lob
    @Column(name = "logo", columnDefinition = "LONGBLOB")
    private byte[] logo;

    @Lob
    @Column(name = "firma", columnDefinition = "LONGBLOB")
    private byte[] firma;

    @Lob
    @Column(name = "portada", columnDefinition = "LONGBLOB")
    private byte[] portada;

    @Lob
    @Column(name = "membrete", columnDefinition = "LONGBLOB")
    private byte[] membrete;

    @Column(name = "formato", length = 20)
    private String formato;

    @Column(name = "orientacion", length = 20)
    private String orientacion;

    @Column(name = "diseno", length = 20)
    private String diseno;
}
