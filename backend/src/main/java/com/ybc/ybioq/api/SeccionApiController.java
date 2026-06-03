package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.SeccionDto;
import com.ybc.ybioq.entity.local.Seccion;
import com.ybc.ybioq.service.SeccionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/secciones")
public class SeccionApiController {

    private final SeccionService seccionService;

    public SeccionApiController(SeccionService seccionService) {
        this.seccionService = seccionService;
    }

    @GetMapping
    public List<SeccionDto> findAll() {
        return seccionService.findAllActivas().stream()
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    public SeccionDto save(@RequestBody SeccionDto request) {
        String nombre = upper(request.nombre());
        if (nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la sección es obligatorio.");
        }
        Seccion seccion = new Seccion();
        seccion.setNombre(nombre);
        seccion.setPrioridad(request.prioridad() != null ? request.prioridad() : 0);
        seccion.setEstado(1);
        try {
            return toDto(seccionService.guardar(seccion));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public SeccionDto update(@PathVariable Integer id, @RequestBody SeccionDto request) {
        Seccion seccion = seccionService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sección no encontrada."));
        String nombre = upper(request.nombre());
        if (nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la sección es obligatorio.");
        }
        seccion.setNombre(nombre);
        seccion.setPrioridad(request.prioridad() != null ? request.prioridad() : seccion.getPrioridad());
        try {
            return toDto(seccionService.guardar(seccion));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Seccion seccion = seccionService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sección no encontrada."));
        seccion.setEstado(0);
        seccionService.save(seccion);
    }

    private SeccionDto toDto(Seccion s) {
        return new SeccionDto(s.getId(), s.getNombre(), s.getPrioridad(), s.getEstado() != null && s.getEstado() == 1);
    }

    private String upper(String value) {
        return value == null ? "" : value.trim().toUpperCase();
    }
}
