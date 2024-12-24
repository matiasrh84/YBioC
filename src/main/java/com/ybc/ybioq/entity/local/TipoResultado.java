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
@Table(name = "tipo_resultados", schema = "bioquimicos")
public class TipoResultado {
    @Id
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ColumnDefault("''")
    @Column(name = "nombre", nullable = false, length = 500)
    private String nombre;

    @Column(name = "id_practicas")
    private Integer idPracticas;

    @ColumnDefault("1")
    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "id_analisis")
    private Integer idAnalisis;

}