package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Orden;
import com.ybc.ybioq.service.OrdenService;
import org.springframework.stereotype.Controller;

@Controller
public class OrdenController extends AbstractCrudController<Orden, Integer> {

    public OrdenController(OrdenService service) {
        super(service);
    }
}
