package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Anticipo;
import com.ybc.ybioq.service.AnticipoService;
import org.springframework.stereotype.Controller;

@Controller
public class AnticipoController extends AbstractCrudController<Anticipo, Integer> {

    public AnticipoController(AnticipoService service) {
        super(service);
    }
}
