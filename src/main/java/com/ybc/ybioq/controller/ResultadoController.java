package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Resultado;
import com.ybc.ybioq.entity.local.ResultadoId;
import com.ybc.ybioq.service.ResultadoService;
import org.springframework.stereotype.Controller;

@Controller
public class ResultadoController extends AbstractCrudController<Resultado, ResultadoId> {

    public ResultadoController(ResultadoService service) {
        super(service);
    }
}
