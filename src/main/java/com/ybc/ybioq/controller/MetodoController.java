package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Metodo;
import com.ybc.ybioq.service.MetodoService;
import org.springframework.stereotype.Controller;

@Controller
public class MetodoController extends AbstractCrudController<Metodo, Integer> {

    public MetodoController(MetodoService service) {
        super(service);
    }
}
