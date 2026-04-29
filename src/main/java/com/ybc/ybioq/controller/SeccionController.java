package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Seccion;
import com.ybc.ybioq.service.SeccionService;
import org.springframework.stereotype.Controller;

@Controller
public class SeccionController extends AbstractCrudController<Seccion, Integer> {

    public SeccionController(SeccionService service) {
        super(service);
    }
}
