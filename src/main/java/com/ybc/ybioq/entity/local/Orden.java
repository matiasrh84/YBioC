package com.ybc.ybioq.entity.local;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "ordenes", schema = "bioquimicos")
public class Orden {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "periodo", nullable = false)
    private Integer periodo;

    @Column(name = "numero_orden", nullable = false, length = 20)
    private String numeroOrden;

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @Column(name = "estado_orden", nullable = false)
    private Integer estadoOrden;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_usuarios", nullable = false)
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
    @JoinColumn(name = "id_pacientes", nullable = false)
    private Paciente idPacientes;

    @Column(name = "servicio", length = 45)
    private String servicio;

    @Column(name = "cama")
    private Integer cama;

    @Column(name = "tipo_orden", length = 45)
    private String tipoOrden;

    @Column(name = "nro_de_autorizacion")
    private Long nroDeAutorizacion;

    @Column(name = "fecha_de_autorizacion")
    private LocalDateTime fechaDeAutorizacion;

    @Column(name = "fecha_de_coseguro")
    private LocalDate fechaDeCoseguro;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_obrasocial", nullable = false)
    private ObraSocial idObrasocial;

    @Column(name = "precio_coseguro")
    private BigDecimal precioCoseguro;

    @Column(name = "nombre_recien_nacido", length = 100)
    private String nombreRecienNacido;

    @Column(name = "estado_enviado", nullable = false)
    private Integer estadoEnviado;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "id_expediente")
    private Integer idExpediente;

    @Transient
    private Float anticipo;
}
