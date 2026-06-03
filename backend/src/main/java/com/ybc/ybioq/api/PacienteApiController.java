package com.ybc.ybioq.api;

import com.ybc.ybioq.api.dto.ActualizarPacienteRequest;
import com.ybc.ybioq.api.dto.AsignarObraSocialRequest;
import com.ybc.ybioq.api.dto.ObraSocialPacienteDto;
import com.ybc.ybioq.api.dto.PacienteDto;
import com.ybc.ybioq.entity.local.ObraSocial;
import com.ybc.ybioq.entity.local.Paciente;
import com.ybc.ybioq.entity.local.PacienteTieneObraSocial;
import com.ybc.ybioq.entity.local.PacienteTieneObraSocialId;
import com.ybc.ybioq.entity.local.Persona;
import com.ybc.ybioq.service.ObraSocialService;
import com.ybc.ybioq.service.PacienteService;
import com.ybc.ybioq.service.PacienteTieneObraSocialService;
import com.ybc.ybioq.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteApiController {

    private final PacienteService pacienteService;
    private final PersonaService personaService;
    private final PacienteTieneObraSocialService pacienteTieneObraSocialService;
    private final ObraSocialService obraSocialService;

    public PacienteApiController(PacienteService pacienteService,
                                 PersonaService personaService,
                                 PacienteTieneObraSocialService pacienteTieneObraSocialService,
                                 ObraSocialService obraSocialService) {
        this.pacienteService = pacienteService;
        this.personaService  = personaService;
        this.pacienteTieneObraSocialService = pacienteTieneObraSocialService;
        this.obraSocialService = obraSocialService;
    }

    @GetMapping("/{id}")
    public PacienteDto findById(@PathVariable Integer id) {
        return pacienteService.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado."));
    }

    @GetMapping("/por-persona")
    public PacienteDto porPersona(@RequestParam Integer idPersona) {
        return pacienteService.findByPersona(idPersona)
                .map(this::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "La persona no tiene ficha de paciente."));
    }

    @PostMapping("/desde-persona")
    @ResponseStatus(HttpStatus.CREATED)
    public PacienteDto crearDesdePersona(@RequestParam Integer idPersona) {
        if (pacienteService.findByPersona(idPersona).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un paciente para esa persona.");
        }
        Persona persona = personaService.findById(idPersona)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada."));
        return toDto(pacienteService.crearDesdePersona(persona));
    }

    @PutMapping("/{id}/datos")
    public PacienteDto actualizarDatos(@PathVariable Integer id,
                                       @RequestBody ActualizarPacienteRequest req) {
        Paciente p = pacienteService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado."));
        if (req.fechaNacimiento() != null) p.setFechaNacimiento(req.fechaNacimiento());
        if (req.telefono() != null && !req.telefono().isBlank()) p.setTelefono(req.telefono());
        if (req.mail()     != null && !req.mail().isBlank())     p.setMail(req.mail());
        return toDto(pacienteService.save(p));
    }

    @GetMapping("/{id}/obras-sociales")
    public List<ObraSocialPacienteDto> obrasSociales(@PathVariable Integer id) {
        return pacienteTieneObraSocialService.findByPaciente(id).stream()
                .map(pts -> new ObraSocialPacienteDto(
                        pts.getIdObrasocial().getId(),
                        pts.getIdObrasocial().getNombre(),
                        pts.getNumeroAfiliado()))
                .toList();
    }

    /** Asigna una obra social a un paciente. Si ya existe la combinación, actualiza el número de afiliado. */
    @PostMapping("/{id}/obras-sociales")
    @ResponseStatus(HttpStatus.CREATED)
    public ObraSocialPacienteDto asignarObraSocial(@PathVariable Integer id,
                                                    @RequestBody AsignarObraSocialRequest req) {
        Paciente paciente = pacienteService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado."));
        ObraSocial os = obraSocialService.findById(req.idObraSocial())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Obra social no encontrada."));

        PacienteTieneObraSocialId compositeId = new PacienteTieneObraSocialId();
        compositeId.setIdPacientes(id);
        compositeId.setIdObrasocial(req.idObraSocial());

        PacienteTieneObraSocial link = new PacienteTieneObraSocial();
        link.setId(compositeId);
        link.setIdPacientes(paciente);
        link.setIdObrasocial(os);
        link.setNumeroAfiliado(req.numeroAfiliado() != null ? req.numeroAfiliado().trim() : "");

        pacienteTieneObraSocialService.save(link);
        return new ObraSocialPacienteDto(os.getId(), os.getNombre(), link.getNumeroAfiliado());
    }

    private PacienteDto toDto(Paciente p) {
        Persona per = p.getPersonasDni();
        return new PacienteDto(
                p.getId(),
                per != null ? per.getId() : null,
                per != null ? per.getDni() : null,
                per != null ? per.getApellido() : null,
                per != null ? per.getNombre() : null,
                p.getFechaNacimiento(),
                p.getTelefono(),
                p.getCelular(),
                p.getMail()
        );
    }
}
