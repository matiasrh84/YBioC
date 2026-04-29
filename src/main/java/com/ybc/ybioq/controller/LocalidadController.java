package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Localidad;
import com.ybc.ybioq.service.LocalidadService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LocalidadController extends AbstractCrudController<Localidad, Integer> {

    public LocalidadController(LocalidadService localidadService) {
        super(localidadService);
    }

    public List<Localidad> getLocalidades() {
        return findAll();
    }
}
