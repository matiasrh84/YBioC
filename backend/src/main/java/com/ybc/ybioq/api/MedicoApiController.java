package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.MedicoDto;
import com.ybc.ybioq.entity.local.Medico;
import com.ybc.ybioq.service.MedicoService;
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
@RequestMapping("/api/medicos")
public class MedicoApiController {

    private final MedicoService medicoService;

    public MedicoApiController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @GetMapping
    public List<MedicoDto> findAll() {
        return medicoService.findAll().stream()
                .sorted(Comparator.comparing(Medico::getApellido, Comparator.nullsLast(String::compareToIgnoreCase))
                        .thenComparing(Medico::getNombre, Comparator.nullsLast(String::compareToIgnoreCase)))
                .map(this::toDto)
                .toList();
    }

    @PostMapping
    public MedicoDto save(@RequestBody MedicoDto request) {
        String apellido = text(request.apellido());
        String nombre = text(request.nombre());
        if (apellido.isBlank() || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ingrese apellido y nombre.");
        }

        Medico medico = request.id() == null
                ? new Medico()
                : medicoService.findById(request.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Medico no encontrado."));

        medico.setApellido(apellido);
        medico.setNombre(nombre);
        medico.setMatricula(request.matricula());
        medico.setMail(text(request.mail()));
        medico.setTelefono(request.telefono());
        medico.setObservaciones(text(request.observaciones()));
        medico.setEstado(request.estado() == null ? 1 : request.estado());

        return toDto(medicoService.save(medico));
    }

    private MedicoDto toDto(Medico medico) {
        return new MedicoDto(
                medico.getId(),
                medico.getApellido(),
                medico.getNombre(),
                medico.getMatricula(),
                medico.getMail(),
                medico.getTelefono(),
                medico.getObservaciones(),
                medico.getEstado()
        );
    }

    private String text(String value) {
        return value == null ? "" : value.trim();
    }
}
