package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.PracticaNbu;
import com.ybc.ybioq.service.PracticaNbuService;
import org.springframework.stereotype.Controller;

@Controller
public class PracticaNbuController extends AbstractCrudController<PracticaNbu, Integer> {

    public PracticaNbuController(PracticaNbuService service) {
        super(service);
    }
}
