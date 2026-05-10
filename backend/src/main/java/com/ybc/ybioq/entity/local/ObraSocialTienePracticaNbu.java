package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "obrasocial_tiene_practicas_nbu", schema = "bioquimicos")
public class ObraSocialTienePracticaNbu {

    @EmbeddedId
    private ObraSocialTienePracticaNbuId id;

    @MapsId("idObrasocial")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_obra_social", nullable = false)
    private ObraSocial idObrasocial;

    @Column(name = "codigo_fac_practicas_obra_social")
    private Integer codigoFacPracticasObrasocial;

    @Column(name = "unidad_bioquimica")
    private Double unidadbioquimica;

    @Column(name = "importe_unidad_de_arancel_obra_social")
    private String importeunidaddearancelObrasocial;

    @Column(name = "precio_fijo")
    private String preciofijo;

    @Column(name = "precio_total")
    private Double preciototal;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuarios")
    private Usuario idUsuarios;

    @Column(name = "estado")
    private Integer estado;
}
