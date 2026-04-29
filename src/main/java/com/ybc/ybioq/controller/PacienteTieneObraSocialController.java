package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.PacienteTieneObraSocial;
import com.ybc.ybioq.entity.local.PacienteTieneObraSocialId;
import com.ybc.ybioq.service.PacienteTieneObraSocialService;
import org.springframework.stereotype.Controller;

@Controller
public class PacienteTieneObraSocialController extends AbstractCrudController<PacienteTieneObraSocial, PacienteTieneObraSocialId> {

    public PacienteTieneObraSocialController(PacienteTieneObraSocialService service) {
        super(service);
    }
}
