package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.EspecialidadDto;
import com.ybc.ybioq.entity.local.Especialidad;
import com.ybc.ybioq.service.EspecialidadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadApiController {

    private final EspecialidadService especialidadService;

    public EspecialidadApiController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public List<EspecialidadDto> findAll() {
        return especialidadService.findAll().stream()
                .sorted(Comparator.comparing(Especialidad::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    public EspecialidadDto save(@RequestBody EspecialidadDto request) {
        String nombre = request.nombre() == null ? "" : request.nombre().trim();
        if (nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingrese el nombre de la especialidad.");
        }

        Especialidad especialidad = request.id() == null
                ? new Especialidad()
                : especialidadService.findById(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidad no encontrada."));
        especialidad.setNombre(nombre);
        especialidad.setEstado(request.estado());

        return toDto(especialidadService.save(especialidad));
    }

    private EspecialidadDto toDto(Especialidad especialidad) {
        return new EspecialidadDto(especialidad.getId(), especialidad.getNombre(), especialidad.isEstado());
    }
}
