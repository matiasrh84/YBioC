package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Expediente;
import com.ybc.ybioq.service.ExpedienteService;
import org.springframework.stereotype.Controller;

@Controller
public class ExpedienteController extends AbstractCrudController<Expediente, Integer> {

    public ExpedienteController(ExpedienteService service) {
        super(service);
    }
}
