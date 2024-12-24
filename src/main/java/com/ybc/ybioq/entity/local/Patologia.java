package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "patologias", schema = "bioquimicos")
public class Patologia {
    @Id
    @Column(name = "id_patologia", nullable = false)
    private Integer id;

    @ColumnDefault("''")
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("0")
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente idPaciente;

}