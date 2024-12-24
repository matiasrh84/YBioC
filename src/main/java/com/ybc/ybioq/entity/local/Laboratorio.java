package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "laboratorios", schema = "bioquimicos")
public class Laboratorio {

    @Id
    @Column(name = "id_laboratorios", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "apellido", length = 100)
    private String apellido;

    @Column(name = "direccion", length = 45)
    private String direccion;

    @Column(name = "telefono")
    private Long telefono;

    @Column(name = "lugar", length = 100)
    private String lugar;

    @Column(name = "cuit", nullable = false, length = 20)
    private String cuit;

    @Column(name = "matricula", nullable = false)
    private Integer matricula;

    @Column(name = "celular")
    private Long celular;

    @Column(name = "dni")
    private Integer dni;

    @Column(name = "usuario", nullable = false, length = 11)
    private String usuario;

    @Column(name = "clave", nullable = false, length = 20)
    private String clave;

    @Column(name = "mail_direccion")
    private String mailDireccion;

    @Column(name = "mail_clave")
    private String mailClave;

    @Column(name = "id_colegiado")
    private Integer idColegiado;
}