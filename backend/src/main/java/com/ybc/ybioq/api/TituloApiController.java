package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.TituloDto;
import com.ybc.ybioq.entity.local.Titulo;
import com.ybc.ybioq.service.TituloService;
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
@RequestMapping("/api/titulos")
public class TituloApiController {

    private final TituloService tituloService;

    public TituloApiController(TituloService tituloService) {
        this.tituloService = tituloService;
    }

    @GetMapping
    public List<TituloDto> findAll() {
        return tituloService.findAll().stream()
                .sorted(Comparator.comparing(Titulo::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    public TituloDto save(@RequestBody TituloDto request) {
        String nombre = request.nombre() == null ? "" : request.nombre().trim();
        if (nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del título es obligatorio.");
        }

        Titulo titulo = request.id() == null
                ? new Titulo()
                : tituloService.findById(request.id())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Título no encontrado."));
        titulo.setNombre(nombre);
        titulo.setEstado(request.estado());

        try {
            return toDto(tituloService.guardar(titulo));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    private TituloDto toDto(Titulo titulo) {
        return new TituloDto(titulo.getId(), titulo.getNombre(), titulo.isEstado(), titulo.getPrioridad());
    }
}
