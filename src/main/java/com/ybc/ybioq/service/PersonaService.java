package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Persona;
import com.ybc.ybioq.repository.local.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> buscarPersonas(String valor) {
        try {
            Integer dni = Integer.valueOf(valor);
            return personaRepository.findById(dni).stream().toList();
        } catch (NumberFormatException e) {
            return personaRepository.findByApellidoContainingIgnoreCaseOrNombreContainingIgnoreCase(valor, valor);
        }
    }

    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public int obtenerMaxId() {
        return personaRepository.findMaxId();
    }
}
