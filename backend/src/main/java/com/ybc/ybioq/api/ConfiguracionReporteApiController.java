package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.ConfiguracionReporteDto;
import com.ybc.ybioq.entity.local.ConfiguracionReporte;
import com.ybc.ybioq.service.ConfiguracionReporteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/configuracion-reporte")
public class ConfiguracionReporteApiController {

    private final ConfiguracionReporteService service;

    public ConfiguracionReporteApiController(ConfiguracionReporteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConfiguracionReporteDto> findAll() {
        return service.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ConfiguracionReporteDto findById(@PathVariable Integer id) {
        return service.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Configuración no encontrada."));
    }

    @PostMapping
    public ConfiguracionReporteDto save(@RequestBody ConfiguracionReporteDto request) {
        if (request.nombre() == null || request.nombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del laboratorio es obligatorio.");
        }
        ConfiguracionReporte entity = new ConfiguracionReporte();
        applyFields(entity, request);
        return toDto(service.save(entity));
    }

    @PutMapping("/{id}")
    public ConfiguracionReporteDto update(@PathVariable Integer id,
                                          @RequestBody ConfiguracionReporteDto request) {
        if (request.nombre() == null || request.nombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del laboratorio es obligatorio.");
        }
        ConfiguracionReporte entity = service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Configuración no encontrada."));
        applyFields(entity, request);
        return toDto(service.save(entity));
    }

    private void applyFields(ConfiguracionReporte entity, ConfiguracionReporteDto dto) {
        entity.setNombre(blank(dto.nombre()));
        entity.setDireccion(blank(dto.direccion()));
        entity.setTelefono(blank(dto.telefono()));
        entity.setMail(blank(dto.mail()));
        entity.setObservacion(blank(dto.observacion()));
        entity.setObservacion2(blank(dto.observacion2()));
        entity.setLogo(dto.logo());
        entity.setFirma(dto.firma());
        entity.setPortada(dto.portada());
        entity.setMembrete(dto.membrete());
        entity.setFormato(blank(dto.formato()));
        entity.setOrientacion(blank(dto.orientacion()));
        entity.setDiseno(blank(dto.diseno()));
    }

    private ConfiguracionReporteDto toDto(ConfiguracionReporte e) {
        return new ConfiguracionReporteDto(
                e.getId(),
                e.getNombre(),
                e.getDireccion(),
                e.getTelefono(),
                e.getMail(),
                e.getObservacion(),
                e.getObservacion2(),
                e.getLogo(),
                e.getFirma(),
                e.getPortada(),
                e.getMembrete(),
                e.getFormato(),
                e.getOrientacion(),
                e.getDiseno());
    }

    private String blank(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }
}
