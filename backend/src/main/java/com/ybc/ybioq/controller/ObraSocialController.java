package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.ObraSocial;
import com.ybc.ybioq.service.ObraSocialService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ObraSocialController extends AbstractCrudController<ObraSocial, Integer> {

    private final ObraSocialService obraSocialService;

    public ObraSocialController(ObraSocialService obraSocialService) {
        super(obraSocialService);
        this.obraSocialService = obraSocialService;
    }

    public List<ObraSocial> getAllObraSocial() {
        return findAll();
    }

    public Optional<ObraSocial> getObraSocialByCodigoAndNombre(String codigo, String nombre) {
        return obraSocialService.getObraSocialByCodigoAndNombre(codigo, nombre);
    }

    public ObraSocial addObraSocial(ObraSocial obraSocial) {
        return save(obraSocial);
    }
}
