package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.CrearOrdenRequest;
import com.ybc.ybioq.api.dto.ModificarOrdenRequest;
import com.ybc.ybioq.api.dto.OrdenDetalleDto;
import com.ybc.ybioq.api.dto.OrdenDto;
import com.ybc.ybioq.api.dto.PageResponse;
import com.ybc.ybioq.entity.local.Medico;
import com.ybc.ybioq.entity.local.Orden;
import com.ybc.ybioq.service.MedicoService;
import com.ybc.ybioq.service.OrdenService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenApiController {

    private final OrdenService    ordenService;
    private final MedicoService   medicoService;

    public OrdenApiController(OrdenService ordenService, MedicoService medicoService) {
        this.ordenService  = ordenService;
        this.medicoService = medicoService;
    }

    @GetMapping
    public PageResponse<OrdenDto> findPaginated(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false)    Integer idPaciente,
            @RequestParam(required = false)    Integer idObraSocial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
            @RequestParam(required = false)    String q) {
        return toPageResponse(
                ordenService.findPaginated(page, size, idPaciente, idObraSocial, desde, hasta, q));
    }

    @GetMapping("/{id}")
    public OrdenDto findById(@PathVariable Integer id) {
        return ordenService.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada."));
    }

    @GetMapping("/{id}/detalle")
    public OrdenDetalleDto detalle(@PathVariable Integer id) {
        try {
            return ordenService.getDetalle(id);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public OrdenDto modificarCompleta(@PathVariable Integer id,
                                      @RequestBody CrearOrdenRequest request) {
        try {
            return toDto(ordenService.modificarOrdenCompleta(id, request));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        } catch (IllegalStateException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    @GetMapping("/por-numero")
    public OrdenDto porNumero(@RequestParam String numero) {
        return ordenService.findByNumeroOrden(numero.trim())
                .map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe orden con número: " + numero));
    }

    @GetMapping("/recientes")
    public List<OrdenDto> recientes(@RequestParam Integer idPaciente) {
        return ordenService.findByPaciente(idPaciente).stream()
                .map(this::toDto)
                .toList();
    }

    @PatchMapping("/{id}")
    public OrdenDto modificar(@PathVariable Integer id, @RequestBody ModificarOrdenRequest req) {
        Orden orden = ordenService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada."));
        if (req.numeroOrden() != null && !req.numeroOrden().isBlank())
            orden.setNumeroOrden(req.numeroOrden().trim());
        if (req.tipoOrden() != null && !req.tipoOrden().isBlank())
            orden.setTipoOrden(req.tipoOrden());
        if (req.idMedico() != null) {
            Medico medico = medicoService.findById(req.idMedico())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado."));
            orden.setIdMedicos(medico);
        }
        return toDto(ordenService.save(orden));
    }

    @GetMapping("/ultimas")
    public List<OrdenDto> ultimas() {
        return ordenService.findUltimas().stream()
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdenDto crear(@RequestBody CrearOrdenRequest request) {
        if (request.idPaciente() == null)    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idPaciente es obligatorio.");
        if (request.idMedico() == null)      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idMedico es obligatorio.");
        if (request.idEspecialidad() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idEspecialidad es obligatorio.");
        if (request.idObraSocial() == null)  throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idObraSocial es obligatorio.");
        if (request.idUsuario() == null)     throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idUsuario es obligatorio.");
        if (request.practicas() == null || request.practicas().isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Debe incluir al menos una práctica.");
        try {
            return toDto(ordenService.crearOrden(request));
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    private OrdenDto toDto(Orden o) {
        var paciente = o.getIdPacientes();
        var persona  = paciente != null ? paciente.getPersonasDni() : null;
        var medico   = o.getIdMedicos();
        var os       = o.getIdObrasocial();
        return new OrdenDto(
                o.getId(),
                o.getNumeroOrden(),
                o.getPeriodo(),
                o.getFecha(),
                paciente != null ? paciente.getId() : null,
                persona  != null ? persona.getApellido() + ", " + persona.getNombre() : null,
                persona  != null ? persona.getDni() : null,
                medico   != null ? medico.getId() : null,
                medico   != null ? medico.getApellido() + ", " + medico.getNombre() : null,
                os       != null ? os.getId() : null,
                os       != null ? os.getNombre() : null,
                o.getTipoOrden(),
                o.getTotal(),
                o.getEstadoOrden(),
                ordenService.calcularEstadoResultados(o.getId())
        );
    }

    private PageResponse<OrdenDto> toPageResponse(Page<Orden> page) {
        return new PageResponse<>(
                page.getContent().stream().map(this::toDto).toList(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getNumber(),
                page.isFirst(),
                page.isLast()
        );
    }
}
