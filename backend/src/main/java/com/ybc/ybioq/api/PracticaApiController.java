package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.PracticaDto;
import com.ybc.ybioq.api.dto.PrecioConsultaDto;
import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbu;
import com.ybc.ybioq.entity.local.Practica;
import com.ybc.ybioq.entity.local.Derivacion;
import com.ybc.ybioq.entity.local.Seccion;
import com.ybc.ybioq.entity.local.TipoInforme;
import com.ybc.ybioq.service.DerivacionService;
import com.ybc.ybioq.service.ObraSocialTienePracticaNbuService;
import com.ybc.ybioq.service.PracticaService;
import com.ybc.ybioq.service.SeccionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/practicas")
public class PracticaApiController {

    private final PracticaService practicaService;
    private final SeccionService seccionService;
    private final DerivacionService derivacionService;
    private final ObraSocialTienePracticaNbuService obraSocialNbuService;

    public PracticaApiController(PracticaService practicaService,
                                 SeccionService seccionService,
                                 DerivacionService derivacionService,
                                 ObraSocialTienePracticaNbuService obraSocialNbuService) {
        this.practicaService    = practicaService;
        this.seccionService     = seccionService;
        this.derivacionService  = derivacionService;
        this.obraSocialNbuService = obraSocialNbuService;
    }

    @GetMapping("/{id}/precio")
    public PrecioConsultaDto consultarPrecio(@PathVariable Integer id,
                                             @RequestParam Integer idObraSocial) {
        Practica practica = practicaService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Práctica no encontrada."));

        List<ObraSocialTienePracticaNbu> precios =
                obraSocialNbuService.findByObrasocialAndPractica(idObraSocial, id);

        if (!precios.isEmpty()) {
            ObraSocialTienePracticaNbu p = precios.get(0);
            BigDecimal precio;
            String codFac = p.getCodigoFacPracticasObrasocial() != null
                    ? String.valueOf(p.getCodigoFacPracticasObrasocial()) : "";
            // Precio fijo tiene prioridad
            if (p.getPreciofijo() != null && !p.getPreciofijo().isBlank()) {
                try { precio = new BigDecimal(p.getPreciofijo().trim()); }
                catch (NumberFormatException ex) { precio = BigDecimal.valueOf(p.getPreciototal() != null ? p.getPreciototal() : 0); }
            } else {
                precio = BigDecimal.valueOf(p.getPreciototal() != null ? p.getPreciototal() : 0);
            }
            return new PrecioConsultaDto(id, precio, codFac, true);
        }

        // Fallback: precio1 de la práctica
        BigDecimal precio1 = practica.getPrecio1() != null ? practica.getPrecio1() : BigDecimal.ZERO;
        return new PrecioConsultaDto(id, precio1, "", false);
    }

    @GetMapping
    public List<PracticaDto> findAll() {
        return practicaService.findAllOrdenadas().stream()
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public PracticaDto findById(@PathVariable Integer id) {
        return practicaService.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Práctica no encontrada."));
    }

    @PostMapping
    public PracticaDto save(@RequestBody PracticaDto request) {
        validar(request, null);
        Practica practica = new Practica();
        applyFields(practica, request);
        return toDto(practicaService.save(practica));
    }

    @PutMapping("/{id}")
    public PracticaDto update(@PathVariable Integer id, @RequestBody PracticaDto request) {
        Practica practica = practicaService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Práctica no encontrada."));
        validar(request, id);
        applyFields(practica, request);
        return toDto(practicaService.save(practica));
    }

    // ── helpers ───────────────────────────────────────────────────

    private void validar(PracticaDto dto, Integer idActual) {
        if (dto.codigoPractica() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El código es obligatorio.");
        }
        if (dto.determinacion() == null || dto.determinacion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio.");
        }
        practicaService.findByCodigo(dto.codigoPractica()).ifPresent(existente -> {
            if (!existente.getId().equals(idActual)) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Ya existe una práctica con el código " + dto.codigoPractica() + ".");
            }
        });
    }

    private void applyFields(Practica p, PracticaDto dto) {
        p.setCodigo(dto.codigoPractica());
        p.setDeterminacion(dto.determinacion() != null ? dto.determinacion().toUpperCase().trim() : "");
        p.setInstrucciones(dto.instrucciones());
        p.setPrioridad(dto.prioridad() != null ? dto.prioridad() : 0);
        p.setTiempoProcesamiento(dto.tiempoProcesamiento() != null ? dto.tiempoProcesamiento() : 1);
        p.setTipoInforme(parseTipoInforme(dto.tipoInforme()));
        p.setEstadoDeriva(dto.estadoDeriva() != null ? dto.estadoDeriva() : 0);
        p.setPrecio1(dto.precio1() != null ? dto.precio1() : BigDecimal.ZERO);
        p.setPrecio2(dto.precio2() != null ? dto.precio2() : BigDecimal.ZERO);
        p.setPrecio3(dto.precio3() != null ? dto.precio3() : BigDecimal.ZERO);
        p.setPrecio4(dto.precio4() != null ? dto.precio4() : BigDecimal.ZERO);

        if (dto.idSeccion() != null) {
            Seccion seccion = seccionService.findById(dto.idSeccion())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Sección no encontrada: " + dto.idSeccion()));
            p.setIdSeccion(seccion);
        } else {
            p.setIdSeccion(null);
        }

        if (dto.estadoDeriva() != null && dto.estadoDeriva() == 1 && dto.idDerivacion() != null) {
            Derivacion derivacion = derivacionService.findById(dto.idDerivacion())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Derivación no encontrada: " + dto.idDerivacion()));
            p.setIdDerivaciones(derivacion);
        } else {
            p.setIdDerivaciones(null);
        }
    }

    private TipoInforme parseTipoInforme(String valor) {
        if (valor == null || valor.isBlank()) return TipoInforme.FILAS;
        try {
            return TipoInforme.valueOf(valor.toUpperCase());
        } catch (IllegalArgumentException ex) {
            return TipoInforme.FILAS;
        }
    }

    private PracticaDto toDto(Practica p) {
        return new PracticaDto(
                p.getId(),
                p.getCodigo(),
                p.getDeterminacion(),
                p.getInstrucciones(),
                p.getIdSeccion() != null ? p.getIdSeccion().getId() : null,
                p.getIdSeccion() != null ? p.getIdSeccion().getNombre() : null,
                p.getEstadoDeriva(),
                p.getIdDerivaciones() != null ? p.getIdDerivaciones().getId() : null,
                p.getIdDerivaciones() != null ? p.getIdDerivaciones().getNombre() : null,
                p.getPrioridad(),
                p.getTiempoProcesamiento(),
                p.getTipoInforme() != null ? p.getTipoInforme().name() : TipoInforme.FILAS.name(),
                p.getPrecio1(),
                p.getPrecio2(),
                p.getPrecio3(),
                p.getPrecio4()
        );
    }
}
