package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Titulo;
import com.ybc.ybioq.repository.local.TituloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TituloService extends AbstractCrudService<Titulo, Integer> {

    private final TituloRepository tituloRepository;

    public TituloService(TituloRepository repository) {
        super(repository);
        this.tituloRepository = repository;
    }

    @Transactional
    public Titulo guardar(Titulo titulo) {
        tituloRepository.findByNombreIgnoreCase(titulo.getNombre())
                .filter(existente -> !existente.getId().equals(titulo.getId()))
                .ifPresent(__ -> {
                    throw new IllegalArgumentException("Ya existe un título con ese nombre.");
                });
        if (titulo.getPrioridad() == null) {
            titulo.setPrioridad(siguientePrioridad());
        }
        return tituloRepository.save(titulo);
    }

    private int siguientePrioridad() {
        return tituloRepository.findAll().stream()
                .map(Titulo::getPrioridad)
                .filter(p -> p != null)
                .max(Integer::compareTo)
                .orElse(0) + 1;
    }
}
