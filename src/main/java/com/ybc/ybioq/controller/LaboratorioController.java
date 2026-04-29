package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Laboratorio;
import com.ybc.ybioq.service.LaboratorioService;
import org.springframework.stereotype.Controller;

@Controller
public class LaboratorioController extends AbstractCrudController<Laboratorio, Integer> {

    public LaboratorioController(LaboratorioService service) {
        super(service);
    }
}
