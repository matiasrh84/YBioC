package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Paciente;
import com.ybc.ybioq.entity.local.Persona;
import com.ybc.ybioq.repository.local.PacienteRepository;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteService extends AbstractCrudService<Paciente, Integer> {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository repository) {
        super(repository);
        this.pacienteRepository = repository;
    }

    public Page<Paciente> findPaginated(int page, int size, String q) {
        Specification<Paciente> spec = (root, query, cb) -> {
            if (q == null || q.isBlank()) return cb.conjunction();
            var persona = root.join("personasDni", JoinType.INNER);
            String trimmed = q.trim();
            try {
                Integer dni = Integer.parseInt(trimmed);
                return cb.equal(persona.get("dni"), dni);
            } catch (NumberFormatException e) {
                String lower = trimmed.toLowerCase();
                return cb.or(
                        cb.like(cb.lower(persona.get("apellido")), "%" + lower + "%"),
                        cb.like(cb.lower(persona.get("nombre")), "%" + lower + "%"));
            }
        };
        return pacienteRepository.findAll(spec,
                PageRequest.of(page, size,
                        Sort.by("personasDni.apellido").ascending()
                                .and(Sort.by("personasDni.nombre").ascending())));
    }

    public Optional<Paciente> findByPersona(Integer idPersona) {
        return pacienteRepository.findByPersonasDniId(idPersona);
    }

    public Paciente crearDesdePersona(Persona persona) {
        Paciente p = new Paciente();
        p.setPersonasDni(persona);
        p.setEstado(1);
        return pacienteRepository.save(p);
    }
}
