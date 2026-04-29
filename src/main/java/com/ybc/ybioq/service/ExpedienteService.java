package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Expediente;
import com.ybc.ybioq.repository.local.ExpedienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpedienteService extends AbstractCrudService<Expediente, Integer> {

    public ExpedienteService(ExpedienteRepository repository) {
        super(repository);
    }
}
