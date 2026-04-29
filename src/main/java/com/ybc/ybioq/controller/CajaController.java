package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Caja;
import com.ybc.ybioq.service.CajaService;
import org.springframework.stereotype.Controller;

@Controller
public class CajaController extends AbstractCrudController<Caja, Integer> {

    public CajaController(CajaService service) {
        super(service);
    }
}
