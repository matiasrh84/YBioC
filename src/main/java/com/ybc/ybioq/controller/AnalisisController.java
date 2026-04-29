package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Analisis;
import com.ybc.ybioq.service.AnalisisService;
import org.springframework.stereotype.Controller;

@Controller
public class AnalisisController extends AbstractCrudController<Analisis, Integer> {

    public AnalisisController(AnalisisService service) {
        super(service);
    }
}
