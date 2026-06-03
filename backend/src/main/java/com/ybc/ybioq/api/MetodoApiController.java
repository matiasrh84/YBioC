package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.MetodoDto;
import com.ybc.ybioq.entity.local.Metodo;
import com.ybc.ybioq.service.MetodoService;
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
@RequestMapping("/api/metodos")
public class MetodoApiController {

    private final MetodoService metodoService;

    public MetodoApiController(MetodoService metodoService) {
        this.metodoService = metodoService;
    }

    @GetMapping
    public List<MetodoDto> findAll() {
        return metodoService.findAll().stream()
                .sorted(Comparator.comparing(Metodo::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    public MetodoDto save(@RequestBody MetodoDto request) {
        String nombre = request.nombre() == null ? "" : request.nombre().trim();
        if (nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del método es obligatorio.");
        }

        Metodo metodo = request.id() == null
                ? new Metodo()
                : metodoService.findById(request.id())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Método no encontrado."));
        metodo.setNombre(nombre);
        metodo.setEstado(request.estado());

        try {
            return toDto(metodoService.guardar(metodo));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    private MetodoDto toDto(Metodo m) {
        return new MetodoDto(m.getId(), m.getNombre(), m.isEstado());
    }
}
