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

    @Column(name = "`contraseña`", length = 15)
    private String contraseña;

    @ColumnDefault("0")
    @Column(name = "datos", nullable = false)
    private Integer datos;

    @ColumnDefault("0")
    @Column(name = "cbt", nullable = false)
    private Integer cbt;

    @ColumnDefault("0")
    @Column(name = "informes", nullable = false)
    private Integer informes;

    @ColumnDefault("0")
    @Column(name = "facturacion", nullable = false)
    private Integer facturacion;

}