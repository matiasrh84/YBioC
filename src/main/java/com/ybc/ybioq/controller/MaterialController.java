package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Material;
import com.ybc.ybioq.service.MaterialService;
import org.springframework.stereotype.Controller;

@Controller
public class MaterialController extends AbstractCrudController<Material, Integer> {

    public MaterialController(MaterialService service) {
        super(service);
    }
}
