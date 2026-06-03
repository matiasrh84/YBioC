package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Unidad;
import com.ybc.ybioq.repository.local.UnidadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnidadService extends AbstractCrudService<Unidad, Integer> {

    private final UnidadRepository unidadRepository;

    public UnidadService(UnidadRepository repository) {
        super(repository);
        this.unidadRepository = repository;
    }

    @Transactional
    public Unidad guardar(Unidad unidad) {
        unidadRepository.findByNombreIgnoreCase(unidad.getNombre())
                .filter(existente -> !existente.getId().equals(unidad.getId()))
                .ifPresent(__ -> {
                    throw new IllegalArgumentException("Ya existe una unidad con ese nombre.");
                });
        return unidadRepository.save(unidad);
    }
}
