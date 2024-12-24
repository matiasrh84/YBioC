package com.ybc.ybioq.entity.local;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("''")
    @Column(name = "cuit", nullable = false, length = 20)
    private String cuit;

    @ColumnDefault("0")
    @Column(name = "matricula", nullable = false)
    private Integer matricula;

    @Column(name = "celular")
    private Long celular;

    @Column(name = "dni")
    private Integer dni;

    @ColumnDefault("''")
    @Column(name = "usuario", nullable = false, length = 11)
    private String usuario;

    @ColumnDefault("''")
    @Column(name = "`contraseña`", nullable = false, length = 20)
    private String contraseña;

    @Column(name = "mail_direccion")
    private String mailDireccion;

    @ColumnDefault("''")
    @Column(name = "`mail_contraseña`")
    private String mailContraseña;

    @ColumnDefault("0")
    @Column(name = "id_colegiado")
    private Integer idColegiado;

}