package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Unidad;
import com.ybc.ybioq.service.UnidadService;
import org.springframework.stereotype.Controller;

@Controller
public class UnidadController extends AbstractCrudController<Unidad, Integer> {

    public UnidadController(UnidadService service) {
        super(service);
    }
}
