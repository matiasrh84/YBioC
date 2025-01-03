package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Persona;
import com.ybc.ybioq.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    public List<Persona> buscarPersonas(String valor) {
        return personaService.buscarPersonas(valor);
    }

    public Persona guardarPersona(Persona persona) {
        return personaService.guardarPersona(persona);
    }

    public int obtenerMaxId() {
        return personaService.obtenerMaxId();
    }
}
