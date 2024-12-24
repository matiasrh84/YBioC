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
    @JoinColumn(name = "id_obrasocial", nullable = false)
    private ObraSocial idObrasocial;

    @Column(name = "codigo_fac_practicas_obrasocial")
    private Integer codigoFacPracticasObrasocial;

    @Column(name = "unidadbioquimica")
    private Double unidadbioquimica;

    @Column(name = "importeunidaddearancel_obrasocial", length = 15)
    private String importeunidaddearancelObrasocial;

    @Column(name = "preciofijo", length = 15)
    private String preciofijo;

    @Column(name = "preciototal")
    private Double preciototal;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_Usuarios")
    private Usuario idUsuarios;

    @Column(name = "estado")
    private Integer estado;
}