package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.AnticipoDto;
import com.ybc.ybioq.entity.local.Anticipo;
import com.ybc.ybioq.service.AnticipoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/anticipos")
public class AnticipoApiController {

    private final AnticipoService anticipoService;

    public AnticipoApiController(AnticipoService anticipoService) {
        this.anticipoService = anticipoService;
    }

    @GetMapping
    public List<AnticipoDto> findByOrden(@RequestParam Integer idOrden) {
        return anticipoService.findByOrden(idOrden).stream()
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    public AnticipoDto save(@RequestBody AnticipoDto request) {
        if (request.anticipo() == null || request.anticipo().signum() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El monto del anticipo debe ser mayor a cero.");
        }

        Anticipo anticipo = new Anticipo();
        anticipo.setAnticipo(request.anticipo());
        anticipo.setIdOrden(request.idOrden());
        anticipo.setEstado(request.estado() != null ? request.estado() : 1);
        anticipo.setFecha(request.fecha());
        anticipo.setObservacion(request.observacion());

        return toDto(anticipoService.save(anticipo));
    }

    private AnticipoDto toDto(Anticipo a) {
        return new AnticipoDto(
                a.getId(),
                a.getAnticipo(),
                a.getIdOrden(),
                a.getEstado(),
                a.getFecha() != null ? a.getFecha() : null,
                a.getObservacion()
        );
    }
}
