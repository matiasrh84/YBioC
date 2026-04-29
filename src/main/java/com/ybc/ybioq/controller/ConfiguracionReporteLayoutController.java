package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.ConfiguracionReporteLayout;
import com.ybc.ybioq.service.ConfiguracionReporteLayoutService;
import org.springframework.stereotype.Controller;

@Controller
public class ConfiguracionReporteLayoutController extends AbstractCrudController<ConfiguracionReporteLayout, Integer> {

    public ConfiguracionReporteLayoutController(ConfiguracionReporteLayoutService service) {
        super(service);
    }
}
