package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbu;
import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbuId;
import com.ybc.ybioq.service.ObraSocialTienePracticaNbuService;
import org.springframework.stereotype.Controller;

@Controller
public class ObraSocialTienePracticaNbuController extends AbstractCrudController<ObraSocialTienePracticaNbu, ObraSocialTienePracticaNbuId> {

    public ObraSocialTienePracticaNbuController(ObraSocialTienePracticaNbuService service) {
        super(service);
    }
}
