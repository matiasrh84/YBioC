package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Practica;
import com.ybc.ybioq.repository.local.PracticaRepository;
import org.springframework.stereotype.Service;

@Service
public class PracticaService extends AbstractCrudService<Practica, Integer> {

    public PracticaService(PracticaRepository repository) {
        super(repository);
    }
}
