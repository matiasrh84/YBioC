package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.ConfiguracionReporte;
import com.ybc.ybioq.service.ConfiguracionReporteService;
import org.springframework.stereotype.Controller;

@Controller
public class ConfiguracionReporteController extends AbstractCrudController<ConfiguracionReporte, Integer> {

    public ConfiguracionReporteController(ConfiguracionReporteService service) {
        super(service);
    }
}
