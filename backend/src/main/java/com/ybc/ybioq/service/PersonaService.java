package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Persona;
import com.ybc.ybioq.repository.local.PersonaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService extends AbstractCrudService<Persona, Integer> {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        super(personaRepository);
        this.personaRepository = personaRepository;
    }

    public Page<Persona> findPaginated(int page, int size, String q) {
        Specification<Persona> spec = (root, query, cb) -> {
            if (q == null || q.isBlank()) return cb.conjunction();
            String trimmed = q.trim();
            try {
                Integer dni = Integer.parseInt(trimmed);
                return cb.equal(root.get("dni"), dni);
            } catch (NumberFormatException e) {
                String lower = trimmed.toLowerCase();
                return cb.or(
                        cb.like(cb.lower(root.get("apellido")), "%" + lower + "%"),
                        cb.like(cb.lower(root.get("nombre")), "%" + lower + "%"));
            }
        };
        return personaRepository.findAll(spec,
                PageRequest.of(page, size,
                        Sort.by("apellido").ascending().and(Sort.by("nombre").ascending())));
    }

    /** Busca por DNI (si q es numérico) o por apellido/nombre (máx. 50 resultados). */
    public List<Persona> buscar(String q) {
        try {
            Integer dni = Integer.parseInt(q.trim());
            return personaRepository.findByDni(dni).stream().toList();
        } catch (NumberFormatException e) {
            return personaRepository
                    .findTop50ByApellidoContainingIgnoreCaseOrNombreContainingIgnoreCaseOrderByApellidoAscNombreAsc(
                            q.trim(), q.trim());
        }
    }

    public Optional<Persona> findByDni(Integer dni) {
        return personaRepository.findByDni(dni);
    }

    public Persona guardarPersona(Persona persona) {
        return save(persona);
    }

    public int obtenerMaxId() {
        return personaRepository.findMaxId();
    }
}
