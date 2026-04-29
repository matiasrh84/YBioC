package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Derivacion;
import com.ybc.ybioq.service.DerivacionService;
import org.springframework.stereotype.Controller;

@Controller
public class DerivacionController extends AbstractCrudController<Derivacion, Integer> {

    public DerivacionController(DerivacionService service) {
        super(service);
    }
}
