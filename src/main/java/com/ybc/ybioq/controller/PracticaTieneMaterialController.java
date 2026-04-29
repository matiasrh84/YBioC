package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.PracticaTieneMaterial;
import com.ybc.ybioq.entity.local.PracticaTieneMaterialId;
import com.ybc.ybioq.service.PracticaTieneMaterialService;
import org.springframework.stereotype.Controller;

@Controller
public class PracticaTieneMaterialController extends AbstractCrudController<PracticaTieneMaterial, PracticaTieneMaterialId> {

    public PracticaTieneMaterialController(PracticaTieneMaterialService service) {
        super(service);
    }
}
