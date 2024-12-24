package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ordenes", schema = "bioquimicos")
public class Orden {
    @Id
    @Column(name = "id_ordenes", nullable = false)
    private Integer id;

    @Column(name = "periodo", nullable = false)
    private Integer periodo;

    @Column(name = "numero_orden", nullable = false, length = 20)
    private String numeroOrden;

    @Column(name = "total", nullable = false)
    private Float total;

    @Column(name = "estado_orden", nullable = false)
    private Integer estadoOrden;

    @Column(name = "fecha", length = 25)
    private String fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_Usuarios", nullable = false)
    private Usuario idUsuarios;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_medicos", nullable = false)
    private Medico idMedicos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_especialidades", nullable = false)
    private Especialidad idEspecialidades;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_Pacientes", nullable = false)
    private Paciente idPacientes;

    @Column(name = "servicio", length = 45)
    private String servicio;

    @Column(name = "cama")
    private Integer cama;

    @Column(name = "tipo_orden", length = 45)
    private String tipoOrden;

    @Column(name = "nro_de_autorizacion")
    private Long nroDeAutorizacion;

    @Column(name = "fecha_de_autorizacion", length = 25)
    private String fechaDeAutorizacion;

    @Column(name = "fecha_de_coseguro")
    private LocalDate fechaDeCoseguro;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ColumnDefault("0")
    @JoinColumn(name = "id_obrasocial", nullable = false)
    private ObraSocial idObrasocial;

    @Column(name = "precio_coseguro")
    private Float precioCoseguro;

    @Column(name = "nombre_recien_nacido", length = 100)
    private String nombreRecienNacido;

    @ColumnDefault("0")
    @Column(name = "estado_enviado", nullable = false)
    private Integer estadoEnviado;

    @ColumnDefault("''")
    @Column(name = "hora", length = 10)
    private String hora;

    @Column(name = "`seña`")
    private Float seña;

}