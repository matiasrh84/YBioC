package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Patologia;
import com.ybc.ybioq.service.PatologiaService;
import org.springframework.stereotype.Controller;

@Controller
public class PatologiaController extends AbstractCrudController<Patologia, Integer> {

    public PatologiaController(PatologiaService service) {
        super(service);
    }
}
