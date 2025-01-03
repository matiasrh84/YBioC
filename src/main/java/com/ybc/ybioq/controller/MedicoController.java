package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Especialidad;
import com.ybc.ybioq.entity.local.Medico;
import com.ybc.ybioq.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    public List<Especialidad> listarEspecialidades() {
        return medicoService.listarEspecialidades();
    }

    public List<Medico> listarMedicos() {
        return medicoService.obtenerMedicosConEspecialidades();
    }

    public Medico guardarMedico(Medico medico) {
        return medicoService.guardarMedico(medico);
    }

    public int obtenerMaxId() {
        return medicoService.obtenerMaxId();
    }
}
