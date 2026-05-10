package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.OrdenTienePractica;
import com.ybc.ybioq.entity.local.OrdenTienePracticaId;
import com.ybc.ybioq.service.OrdenTienePracticaService;
import org.springframework.stereotype.Controller;

@Controller
public class OrdenTienePracticaController extends AbstractCrudController<OrdenTienePractica, OrdenTienePracticaId> {

    public OrdenTienePracticaController(OrdenTienePracticaService service) {
        super(service);
    }
}
