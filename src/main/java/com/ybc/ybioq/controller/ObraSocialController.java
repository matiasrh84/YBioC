package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.ObraSocial;
import com.ybc.ybioq.service.ObraSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ObraSocialController {

    @Autowired
    private ObraSocialService obraSocialService;

    public List<ObraSocial> getAllObraSocial() {
        return obraSocialService.getAllObraSocial();
    }

    public Optional<ObraSocial> getObraSocialByCodigoAndNombre(String codigo, String nombre) {
        return obraSocialService.getObraSocialByCodigoAndNombre(codigo, nombre);
    }
}
