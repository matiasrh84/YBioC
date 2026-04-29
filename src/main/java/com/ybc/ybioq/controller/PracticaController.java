package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Practica;
import com.ybc.ybioq.service.PracticaService;
import org.springframework.stereotype.Controller;

@Controller
public class PracticaController extends AbstractCrudController<Practica, Integer> {

    public PracticaController(PracticaService service) {
        super(service);
    }
}
