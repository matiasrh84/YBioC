package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Practica;
import com.ybc.ybioq.repository.local.PracticaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PracticaService extends AbstractCrudService<Practica, Integer> {

    private final PracticaRepository practicaRepository;

    public PracticaService(PracticaRepository repository) {
        super(repository);
        this.practicaRepository = repository;
    }

    public List<Practica> findAllOrdenadas() {
        return practicaRepository.findAllByOrderByDeterminacionAsc();
    }

    public Optional<Practica> findByCodigo(Integer codigo) {
        return practicaRepository.findByCodigo(codigo);
    }
}
