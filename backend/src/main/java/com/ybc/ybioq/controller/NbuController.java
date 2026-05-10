package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Nbu;
import com.ybc.ybioq.service.NbuService;
import org.springframework.stereotype.Controller;

@Controller
public class NbuController extends AbstractCrudController<Nbu, Integer> {

    public NbuController(NbuService service) {
        super(service);
    }
}
