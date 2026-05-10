package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Laboratorio;
import com.ybc.ybioq.repository.local.LaboratorioRepository;
import org.springframework.stereotype.Service;

@Service
public class LaboratorioService extends AbstractCrudService<Laboratorio, Integer> {

    public LaboratorioService(LaboratorioRepository repository) {
        super(repository);
    }
}
