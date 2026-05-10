package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios", schema = "bioquimicos")
public class Usuario {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 25)
    private String nombre;

    @Column(name = "apellido", length = 25)
    private String apellido;

    @Column(name = "usuario", length = 15)
    private String usuario;

    @Column(name = "clave", length = 60)
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
