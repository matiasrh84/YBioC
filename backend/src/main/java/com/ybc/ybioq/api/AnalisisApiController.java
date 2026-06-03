package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.AnalisisDto;
import com.ybc.ybioq.entity.local.Analisis;
import com.ybc.ybioq.entity.local.Metodo;
import com.ybc.ybioq.entity.local.Practica;
import com.ybc.ybioq.entity.local.Titulo;
import com.ybc.ybioq.service.AnalisisService;
import com.ybc.ybioq.service.MetodoService;
import com.ybc.ybioq.service.PracticaService;
import com.ybc.ybioq.service.TituloService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/analisis")
public class AnalisisApiController {

    private final AnalisisService analisisService;
    private final PracticaService practicaService;
    private final MetodoService metodoService;
    private final TituloService tituloService;

    public AnalisisApiController(AnalisisService analisisService,
                                 PracticaService practicaService,
                                 MetodoService metodoService,
                                 TituloService tituloService) {
        this.analisisService = analisisService;
        this.practicaService = practicaService;
        this.metodoService = metodoService;
        this.tituloService = tituloService;
    }

    @GetMapping
    public List<AnalisisDto> findByPractica(@RequestParam Integer idPractica) {
        return analisisService.findByPracticaId(idPractica).stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public AnalisisDto findById(@PathVariable Integer id) {
        return analisisService.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Analisis no encontrado."));
    }

    @PostMapping
    public AnalisisDto save(@RequestBody AnalisisDto request) {
        if (request.idPractica() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idPractica es obligatorio.");
        }
        if (request.nombre() == null || request.nombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del analisis es obligatorio.");
        }

        Analisis analisis = new Analisis();
        applyFields(analisis, request);
        return toDto(analisisService.save(analisis));
    }

    @PutMapping("/{id}")
    public AnalisisDto update(@PathVariable Integer id, @RequestBody AnalisisDto request) {
        Analisis analisis = analisisService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Analisis no encontrado."));
        applyFields(analisis, request);
        return toDto(analisisService.save(analisis));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        if (analisisService.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Analisis no encontrado.");
        }
        analisisService.deleteById(id);
    }

    private void applyFields(Analisis analisis, AnalisisDto dto) {
        analisis.setNombre(text(dto.nombre()));
        analisis.setCodigoInterno(text(dto.codigoInterno()));
        analisis.setTipoResultado(dto.tipoResultado() != null ? dto.tipoResultado() : "Numerico");
        analisis.setUnidad(dto.unidad());
        analisis.setUnidadExtra(dto.unidadExtra());
        analisis.setValoresReferencia(dto.valoresReferencia());
        analisis.setPrioridad(dto.prioridad());
        analisis.setEstadoTitulo(dto.estadoTitulo());
        analisis.setEstado(dto.estado());

        Practica practica = practicaService.findById(dto.idPractica())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Practica con id " + dto.idPractica() + " no encontrada."));
        analisis.setPractica(practica);

        if (dto.idMetodo() != null) {
            Metodo metodo = metodoService.findById(dto.idMetodo())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Metodo con id " + dto.idMetodo() + " no encontrado."));
            analisis.setMetodo(metodo);
        } else {
            analisis.setMetodo(null);
        }

        if (dto.idTitulo() != null) {
            Titulo titulo = tituloService.findById(dto.idTitulo())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Titulo con id " + dto.idTitulo() + " no encontrado."));
            analisis.setTitulo(titulo);
        } else {
            analisis.setTitulo(null);
        }
    }

    private AnalisisDto toDto(Analisis a) {
        return new AnalisisDto(
                a.getId(),
                a.getNombre(),
                a.getCodigoInterno(),
                a.getTipoResultado(),
                a.getUnidad(),
                a.getUnidadExtra(),
                a.getValoresReferencia(),
                a.getPrioridad(),
                a.isEstadoTitulo(),
                a.isEstado(),
                a.getPractica() != null ? a.getPractica().getId() : null,
                a.getMetodo() != null ? a.getMetodo().getId() : null,
                a.getMetodo() != null ? a.getMetodo().getNombre() : null,
                a.getTitulo() != null ? a.getTitulo().getId() : null,
                a.getTitulo() != null ? a.getTitulo().getNombre() : null
        );
    }

    private String text(String value) {
        return value == null ? "" : value.trim();
    }
}
