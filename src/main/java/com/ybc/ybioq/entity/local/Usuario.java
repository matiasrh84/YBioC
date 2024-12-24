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
@Table(name = "usuarios", schema = "bioquimicos")
public class Usuario {

    @Id
    @Column(name = "id_Usuarios", nullable = false)
    private Integer id;

    @Column(name = "Nombre", length = 25)
    private String nombre;

    @Column(name = "Apellido", length = 25)
    private String apellido;

    @Column(name = "usuario", length = 15)
    private String usuario;

    @Column(name = "clave", length = 15)
    private String clave;

    @Column(name = "datos", nullable = false)
    private Integer datos;

    @Column(name = "cbt", nullable = false)
    private Integer cbt;

    @Column(name = "informes", nullable = false)
    private Integer informes;

    @Column(name = "facturacion", nullable = false)
    private Integer facturacion;
}