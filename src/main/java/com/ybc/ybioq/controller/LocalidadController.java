package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Localidad;
import com.ybc.ybioq.service.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LocalidadController {

    @Autowired
    private LocalidadService localidadService;

    public List<Localidad> getLocalidades() {
        return localidadService.getAllLocalidades();
    }
}
