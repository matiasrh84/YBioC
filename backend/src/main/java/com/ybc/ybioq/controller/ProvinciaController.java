package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Provincia;
import com.ybc.ybioq.service.ProvinciaService;
import org.springframework.stereotype.Controller;

@Controller
public class ProvinciaController extends AbstractCrudController<Provincia, Integer> {

    public ProvinciaController(ProvinciaService service) {
        super(service);
    }
}
