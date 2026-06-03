package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Analisis;
import com.ybc.ybioq.repository.local.AnalisisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalisisService extends AbstractCrudService<Analisis, Integer> {

    private final AnalisisRepository analisisRepository;

    public AnalisisService(AnalisisRepository repository) {
        super(repository);
        this.analisisRepository = repository;
    }

    public List<Analisis> findByPracticaId(Integer practicaId) {
        return analisisRepository.findByPracticaIdConRelaciones(practicaId);
    }
}
