package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "personas", schema = "bioquimicos")
public class Persona {
    @Id
    @Column(name = "dni", nullable = false)
    private Integer id;

    @Column(name = "apellido", length = 45)
    private String apellido;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "domicilio", length = 45)
    private String domicilio;

    @Column(name = "sexo", length = 1)
    private String sexo;

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