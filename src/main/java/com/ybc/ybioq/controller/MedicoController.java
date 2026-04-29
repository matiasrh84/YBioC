package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Especialidad;
import com.ybc.ybioq.entity.local.Medico;
import com.ybc.ybioq.service.MedicoService;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MedicoController extends AbstractCrudController<Medico, Integer> {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        super(medicoService);
        this.medicoService = medicoService;
    }

    public List<Especialidad> listarEspecialidades() {
        return medicoService.listarEspecialidades();
    }

    public List<Medico> listarMedicos() {
        return medicoService.obtenerMedicosConEspecialidades();
    }

    public Medico guardarMedico(Medico medico) {
        return save(medico);
    }

    public Medico addMedico(String nombre, String apellido, int matricula) throws SQLException {
        return medicoService.addMedico(nombre, apellido, matricula);
    }
}
