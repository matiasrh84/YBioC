package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Derivacion;
import com.ybc.ybioq.repository.local.DerivacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DerivacionService extends AbstractCrudService<Derivacion, Integer> {

    private final DerivacionRepository derivacionRepository;

    public DerivacionService(DerivacionRepository repository) {
        super(repository);
        this.derivacionRepository = repository;
    }

    @Transactional
    public Derivacion guardar(Derivacion derivacion) {
        derivacionRepository.findByNombreIgnoreCase(derivacion.getNombre())
                .filter(existente -> !existente.getId().equals(derivacion.getId()))
                .ifPresent(__ -> {
                    throw new IllegalArgumentException("Ya existe una derivación con ese nombre.");
                });
        return derivacionRepository.save(derivacion);
    }
}
