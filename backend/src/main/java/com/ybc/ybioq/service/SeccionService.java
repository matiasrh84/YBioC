package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Seccion;
import com.ybc.ybioq.repository.local.SeccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeccionService extends AbstractCrudService<Seccion, Integer> {

    private final SeccionRepository seccionRepository;

    public SeccionService(SeccionRepository repository) {
        super(repository);
        this.seccionRepository = repository;
    }

    public List<Seccion> findAllActivas() {
        return seccionRepository.findByEstadoOrderByPrioridadAscNombreAsc(1);
    }

    public Seccion guardar(Seccion seccion) {
        seccionRepository.findByNombreIgnoreCase(seccion.getNombre()).ifPresent(existente -> {
            if (!existente.getId().equals(seccion.getId())) {
                throw new IllegalArgumentException("Ya existe una sección con ese nombre.");
            }
        });
        return save(seccion);
    }
}
