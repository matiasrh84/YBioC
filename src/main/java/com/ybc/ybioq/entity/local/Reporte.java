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
@Table(name = "reportes", schema = "bioquimicos")
public class Reporte {
    @Id
    @Column(name = "id_reportes", nullable = false)
    private Integer id;

    @ColumnDefault("''")
    @Column(name = "`diseño`", nullable = false, length = 20)
    private String diseño;

    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_horizontal1")
    private Integer logoHorizontal1;

    @Column(name = "logo_horizontal2")
    private Integer logoHorizontal2;

    @Column(name = "logo_horizontal3")
    private Integer logoHorizontal3;

    @Column(name = "logo_vertical1")
    private Integer logoVertical1;

    @Column(name = "logo_vertical2")
    private Integer logoVertical2;

    @Column(name = "logo_vertical3")
    private Integer logoVertical3;

    @Column(name = "lab_horizontal1")
    private Integer labHorizontal1;

    @Column(name = "lab_horizontal2")
    private Integer labHorizontal2;

    @Column(name = "lab_horizontal3")
    private Integer labHorizontal3;

    @Column(name = "lab_vertical1")
    private Integer labVertical1;

    @Column(name = "lab_vertical2")
    private Integer labVertical2;

    @Column(name = "lab_vertical3")
    private Integer labVertical3;

    @Column(name = "protocolo_horizontal1")
    private Integer protocoloHorizontal1;

    @Column(name = "protocolo_horizontal2")
    private Integer protocoloHorizontal2;

    @Column(name = "protocolo_horizontal3")
    private Integer protocoloHorizontal3;

    @Column(name = "protocolo_vertical1")
    private Integer protocoloVertical1;

    @Column(name = "protocolo_vertical2")
    private Integer protocoloVertical2;

    @Column(name = "protocolo_vertical3")
    private Integer protocoloVertical3;

    @Column(name = "paciente_horizontal1")
    private Integer pacienteHorizontal1;

    @Column(name = "paciente_horizontal2")
    private Integer pacienteHorizontal2;

    @Column(name = "paciente_horizontal3")
    private Integer pacienteHorizontal3;

    @Column(name = "paciente_vertical1")
    private Integer pacienteVertical1;

    @Column(name = "paciente_vertical2")
    private Integer pacienteVertical2;

    @Column(name = "paciente_vertical3")
    private Integer pacienteVertical3;

    @Column(name = "medico_horizontal1")
    private Integer medicoHorizontal1;

    @Column(name = "medico_horizontal2")
    private Integer medicoHorizontal2;

    @Column(name = "medico_horizontal3")
    private Integer medicoHorizontal3;

    @Column(name = "medico_verical1")
    private Integer medicoVerical1;

    @Column(name = "medico_verical2")
    private Integer medicoVerical2;

    @Column(name = "medico_verical3")
    private Integer medicoVerical3;

    @Column(name = "motivo_horizontal1")
    private Integer motivoHorizontal1;

    @Column(name = "motivo_horizontal2")
    private Integer motivoHorizontal2;

    @Column(name = "motivo_horizontal3")
    private Integer motivoHorizontal3;

    @Column(name = "motivo_vertical1")
    private Integer motivoVertical1;

    @Column(name = "motivo_vertical2")
    private Integer motivoVertical2;

    @Column(name = "motivo_vertical3")
    private Integer motivoVertical3;

    @Column(name = "fecha_horizontal1")
    private Integer fechaHorizontal1;

    @Column(name = "fecha_horizontal2")
    private Integer fechaHorizontal2;

    @Column(name = "fecha_horizontal3")
    private Integer fechaHorizontal3;

    @Column(name = "fecha_vertical1")
    private Integer fechaVertical1;

    @Column(name = "fecha_vertical2")
    private Integer fechaVertical2;

    @Column(name = "fecha_vertical3")
    private Integer fechaVertical3;

    @Column(name = "hora_horizontal1")
    private Integer horaHorizontal1;

    @Column(name = "hora_horizontal2")
    private Integer horaHorizontal2;

    @Column(name = "hora_horizontal3")
    private Integer horaHorizontal3;

    @Column(name = "hora_vertical1")
    private Integer horaVertical1;

    @Column(name = "hora_vertical2")
    private Integer horaVertical2;

    @Column(name = "hora_vertical3")
    private Integer horaVertical3;

    @ColumnDefault("''")
    @Column(name = "horientacion", length = 20)
    private String horientacion;

    @Column(name = "formato", length = 20)
    private String formato;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "mail")
    private String mail;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "observacion2")
    private String observacion2;

}