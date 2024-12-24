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
@Table(name = "provincia", schema = "bioquimicos")
public class Provincia {
    @Id
    @ColumnDefault("0")
    @Column(name = "id_provincia", nullable = false)
    private Integer id;

    @Column(name = "nombre_provincia", nullable = false, length = 20)
    private String nombreProvincia;

}