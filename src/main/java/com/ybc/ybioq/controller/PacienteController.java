package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Paciente;
import com.ybc.ybioq.service.PacienteService;
import org.springframework.stereotype.Controller;

@Controller
public class PacienteController extends AbstractCrudController<Paciente, Integer> {

    public PacienteController(PacienteService service) {
        super(service);
    }
}
