package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "pacientes_tienen_obrasociales", schema = "bioquimicos")
public class PacienteTieneObraSocial {
    @EmbeddedId
    private PacienteTieneObraSocialId id;

    @MapsId("idPacientes")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_Pacientes", nullable = false)
    private Paciente idPacientes;

    @MapsId("idObrasocial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_obrasocial", nullable = false)
    private ObraSocial idObrasocial;

    @Column(name = "numero_afiliado", length = 20)
    private String numeroAfiliado;

}