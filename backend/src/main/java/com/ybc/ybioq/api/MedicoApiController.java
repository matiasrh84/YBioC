package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.EspecialidadSimpleDto;
import com.ybc.ybioq.api.dto.MedicoDto;
import com.ybc.ybioq.entity.local.Medico;
import com.ybc.ybioq.entity.local.MedicoTieneEspecialidad;
import com.ybc.ybioq.service.MedicoService;
import com.ybc.ybioq.service.MedicoTieneEspecialidadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoApiController {

    private static final Logger log = LoggerFactory.getLogger(MedicoApiController.class);
    private final MedicoService medicoService;
    private final MedicoTieneEspecialidadService medicoTieneEspecialidadService;

    public MedicoApiController(MedicoService medicoService,
                               MedicoTieneEspecialidadService medicoTieneEspecialidadService) {
        this.medicoService = medicoService;
        this.medicoTieneEspecialidadService = medicoTieneEspecialidadService;
    }

    @GetMapping
    public List<MedicoDto> findAll() {
        List<Medico> raw = medicoService.obtenerMedicosConEspecialidades();
        log.info("GET /api/medicos → {} médicos encontrados", raw.size());
        raw.forEach(m -> log.debug("  Médico id={} apellido={} especialidades={}",
                m.getId(), m.getApellido(),
                m.getMedicoTieneEspecialidades() == null ? 0 : m.getMedicoTieneEspecialidades().size()));
        return raw.stream()
                .sorted(Comparator.comparing(Medico::getApellido, Comparator.nullsLast(String::compareToIgnoreCase))
                        .thenComparing(Medico::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                .map(this::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public MedicoDto findById(@PathVariable Integer id) {
        return medicoService.findByIdConEspecialidades(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado."));
    }

    @PostMapping
    public MedicoDto save(@RequestBody MedicoDto request) {
        String apellido = text(request.apellido());
        String nombre   = text(request.nombre());
        if (apellido.isBlank() || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingrese apellido y nombre.");
        }

        Medico medico = request.id() == null
                ? new Medico()
                : medicoService.findById(request.id())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado."));

        medico.setApellido(apellido);
        medico.setNombre(nombre);
        medico.setMatricula(request.matricula());
        medico.setMail(text(request.mail()));
        medico.setTelefono(request.telefono());
        medico.setObservaciones(text(request.observaciones()));
        medico.setEstado(request.estado() == null ? 1 : request.estado());

        return toDto(medicoService.save(medico));
    }

    @PutMapping("/{id}/especialidades")
    public void actualizarEspecialidades(@PathVariable Integer id, @RequestBody List<Integer> idEspecialidades) {
        Medico medico = medicoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Médico no encontrado."));
        medicoTieneEspecialidadService.actualizarEspecialidades(medico, idEspecialidades);
    }

    private MedicoDto toDto(Medico medico) {
        List<EspecialidadSimpleDto> especialidades = medico.getMedicoTieneEspecialidades() == null
                ? Collections.emptyList()
                : medico.getMedicoTieneEspecialidades().stream()
                        .map(MedicoTieneEspecialidad::getIdEspecialidades)
                        .filter(e -> e != null)
                        .map(e -> new EspecialidadSimpleDto(e.getId(), e.getNombre()))
                        .sorted(Comparator.comparing(EspecialidadSimpleDto::nombre,
                                Comparator.nullsLast(String::compareToIgnoreCase)))
                        .toList();

        return new MedicoDto(
                medico.getId(),
                medico.getApellido(),
                medico.getNombre(),
                medico.getMatricula(),
                medico.getMail(),
                medico.getTelefono(),
                medico.getObservaciones(),
                medico.getEstado(),
                especialidades
        );
    }

    private String text(String value) {
        return value == null ? "" : value.trim();
    }
}
