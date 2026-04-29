package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.MedicoTieneEspecialidad;
import com.ybc.ybioq.entity.local.MedicoTieneEspecialidadId;
import com.ybc.ybioq.service.MedicoTieneEspecialidadService;
import org.springframework.stereotype.Controller;

@Controller
public class MedicoTieneEspecialidadController extends AbstractCrudController<MedicoTieneEspecialidad, MedicoTieneEspecialidadId> {

    public MedicoTieneEspecialidadController(MedicoTieneEspecialidadService service) {
        super(service);
    }
}
