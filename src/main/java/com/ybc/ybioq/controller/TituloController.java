package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Titulo;
import com.ybc.ybioq.service.TituloService;
import org.springframework.stereotype.Controller;

@Controller
public class TituloController extends AbstractCrudController<Titulo, Integer> {

    public TituloController(TituloService service) {
        super(service);
    }
}
