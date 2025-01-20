package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "provincia", schema = "bioquimicos")
public class Provincia {

    @Id
    @Column(name = "id_provincia", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_provincia", nullable = false, length = 20)
    private String nombreProvincia;
}