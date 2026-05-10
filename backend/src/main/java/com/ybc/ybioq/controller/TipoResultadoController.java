package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.TipoResultado;
import com.ybc.ybioq.service.TipoResultadoService;
import org.springframework.stereotype.Controller;

@Controller
public class TipoResultadoController extends AbstractCrudController<TipoResultado, Integer> {

    public TipoResultadoController(TipoResultadoService service) {
        super(service);
    }
}
