package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "localidad", schema = "bioquimicos")
public class Localidad {

    @Id
    @Column(name = "id_localidad", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_provincia", nullable = false)
    private Provincia idProvincia;

    @Column(name = "nombre_localidad", nullable = false, length = 45)
    private String nombreLocalidad;
}