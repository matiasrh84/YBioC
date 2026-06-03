package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.UnidadDto;
import com.ybc.ybioq.entity.local.Unidad;
import com.ybc.ybioq.service.UnidadService;
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
@RequestMapping("/api/unidades")
public class UnidadApiController {

    private final UnidadService unidadService;

    public UnidadApiController(UnidadService unidadService) {
        this.unidadService = unidadService;
    }

    @GetMapping
    public List<UnidadDto> findAll() {
        return unidadService.findAll().stream()
                .sorted(Comparator.comparing(Unidad::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    public UnidadDto save(@RequestBody UnidadDto request) {
        String nombre = request.nombre() == null ? "" : request.nombre().trim();
        if (nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la unidad es obligatorio.");
        }

        Unidad unidad = request.id() == null
                ? new Unidad()
                : unidadService.findById(request.id())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidad no encontrada."));
        unidad.setNombre(nombre);
        unidad.setEstado(request.estado());

        try {
            return toDto(unidadService.guardar(unidad));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    private UnidadDto toDto(Unidad unidad) {
        return new UnidadDto(unidad.getId(), unidad.getNombre(), unidad.isEstado());
    }
}
