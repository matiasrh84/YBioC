package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Persona;
import com.ybc.ybioq.service.PersonaService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PersonaController extends AbstractCrudController<Persona, Integer> {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        super(personaService);
        this.personaService = personaService;
    }

    public List<Persona> buscarPersonas(String valor) {
        return personaService.buscar(valor);
    }

    public Persona guardarPersona(Persona persona) {
        return save(persona);
    }

    public int obtenerMaxId() {
        return personaService.obtenerMaxId();
    }
}
