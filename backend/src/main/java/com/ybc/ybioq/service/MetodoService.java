package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Metodo;
import com.ybc.ybioq.repository.local.MetodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MetodoService extends AbstractCrudService<Metodo, Integer> {

    private final MetodoRepository metodoRepository;

    public MetodoService(MetodoRepository repository) {
        super(repository);
        this.metodoRepository = repository;
    }

    @Transactional
    public Metodo guardar(Metodo metodo) {
        metodoRepository.findByNombreIgnoreCase(metodo.getNombre())
                .filter(existente -> !existente.getId().equals(metodo.getId()))
                .ifPresent(__ -> {
                    throw new IllegalArgumentException("Ya existe un método con ese nombre.");
                });
        return metodoRepository.save(metodo);
    }
}
