package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.PageResponse;
import com.ybc.ybioq.api.dto.RecepcionFilaDto;
import com.ybc.ybioq.entity.local.Paciente;
import com.ybc.ybioq.entity.local.Persona;
import com.ybc.ybioq.service.PacienteService;
import com.ybc.ybioq.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/recepcion")
public class RecepcionApiController {

    private final PersonaService personaService;
    private final PacienteService pacienteService;

    public RecepcionApiController(PersonaService personaService, PacienteService pacienteService) {
        this.personaService  = personaService;
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public PageResponse<RecepcionFilaDto> findPaginated(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false)    String q) {

        // Sin búsqueda: lista de pacientes paginada
        if (q == null || q.isBlank()) {
            var pacientes = pacienteService.findPaginated(page, size, null);
            var content = pacientes.getContent().stream().map(this::pacienteToFila).toList();
            return new PageResponse<>(content, pacientes.getTotalPages(),
                    pacientes.getTotalElements(), pacientes.getNumber(),
                    pacientes.isFirst(), pacientes.isLast());
        }

        // Con búsqueda: pacientes primero
        var pacientes = pacienteService.findPaginated(page, size, q);
        if (pacientes.getTotalElements() > 0) {
            var content = pacientes.getContent().stream().map(this::pacienteToFila).toList();
            return new PageResponse<>(content, pacientes.getTotalPages(),
                    pacientes.getTotalElements(), pacientes.getNumber(),
                    pacientes.isFirst(), pacientes.isLast());
        }

        // Sin pacientes: fallback a personas (para el flujo de dar de alta)
        var personas = personaService.findPaginated(page, size, q);
        var content = personas.getContent().stream().map(p -> {
            var pac = pacienteService.findByPersona(p.getId());
            return new RecepcionFilaDto(
                    pac.map(Paciente::getId).orElse(null),
                    p.getId(), p.getDni(), p.getApellido(), p.getNombre(),
                    pac.isPresent());
        }).toList();
        return new PageResponse<>(content, personas.getTotalPages(),
                personas.getTotalElements(), personas.getNumber(),
                personas.isFirst(), personas.isLast());
    }

    private RecepcionFilaDto pacienteToFila(Paciente p) {
        Persona per = p.getPersonasDni();
        return new RecepcionFilaDto(
                p.getId(),
                per != null ? per.getId()       : null,
                per != null ? per.getDni()       : null,
                per != null ? per.getApellido()  : null,
                per != null ? per.getNombre()    : null,
                true);
    }

    @GetMapping("/buscar")
    public List<RecepcionFilaDto> buscar(@RequestParam String q) {
        if (q == null || q.trim().length() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Ingrese al menos 2 caracteres.");
        }
        List<Persona> personas = personaService.buscar(q.trim());
        return personas.stream().map(p -> {
            var paciente = pacienteService.findByPersona(p.getId());
            return new RecepcionFilaDto(
                    paciente.map(pac -> pac.getId()).orElse(null),
                    p.getId(),
                    p.getDni(),
                    p.getApellido(),
                    p.getNombre(),
                    paciente.isPresent()
            );
        }).toList();
    }
}
