package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.DerivacionDto;
import com.ybc.ybioq.entity.local.Derivacion;
import com.ybc.ybioq.service.DerivacionService;
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
@RequestMapping("/api/derivaciones")
public class DerivacionApiController {

    private final DerivacionService derivacionService;

    public DerivacionApiController(DerivacionService derivacionService) {
        this.derivacionService = derivacionService;
    }

    @GetMapping
    public List<DerivacionDto> findAll() {
        return derivacionService.findAll().stream()
                .sorted(Comparator.comparing(Derivacion::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    public DerivacionDto save(@RequestBody DerivacionDto request) {
        String nombre = request.nombre() == null ? "" : request.nombre().trim();
        if (nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de la derivación es obligatorio.");
        }

        Derivacion derivacion = request.id() == null
                ? new Derivacion()
                : derivacionService.findById(request.id())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Derivación no encontrada."));

        derivacion.setNombre(nombre);
        derivacion.setDireccion(blank(request.direccion()));
        derivacion.setTelefono(request.telefono());
        derivacion.setMail(blank(request.mail()));
        derivacion.setObservaciones(blank(request.observaciones()));

        try {
            return toDto(derivacionService.guardar(derivacion));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    private String blank(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }

    private DerivacionDto toDto(Derivacion d) {
        return new DerivacionDto(d.getId(), d.getNombre(), d.getDireccion(), d.getTelefono(), d.getMail(), d.getObservaciones());
    }
}
