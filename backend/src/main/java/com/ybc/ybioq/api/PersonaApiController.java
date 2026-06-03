package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.PersonaCrearRequest;
import com.ybc.ybioq.api.dto.PersonaDto;
import com.ybc.ybioq.entity.local.Persona;
import com.ybc.ybioq.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaApiController {

    private final PersonaService personaService;

    public PersonaApiController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/buscar")
    public List<PersonaDto> buscar(@RequestParam String q) {
        if (q == null || q.trim().length() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ingrese al menos 2 caracteres para buscar.");
        }
        return personaService.buscar(q).stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public PersonaDto findById(@PathVariable Integer id) {
        return personaService.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonaDto crear(@RequestBody PersonaCrearRequest request) {
        String apellido = request.apellido() == null ? "" : request.apellido().trim();
        String nombre   = request.nombre()   == null ? "" : request.nombre().trim();
        if (apellido.isBlank() || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Apellido y nombre son obligatorios.");
        }
        if (request.dni() != null && personaService.findByDni(request.dni()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe una persona con DNI " + request.dni() + ".");
        }
        Persona persona = new Persona();
        persona.setApellido(apellido);
        persona.setNombre(nombre);
        persona.setDni(request.dni());
        return toDto(personaService.save(persona));
    }

    private PersonaDto toDto(Persona p) {
        return new PersonaDto(p.getId(), p.getDni(), p.getApellido(), p.getNombre());
    }
}
