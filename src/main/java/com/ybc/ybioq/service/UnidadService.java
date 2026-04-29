package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Unidad;
import com.ybc.ybioq.repository.local.UnidadRepository;
import org.springframework.stereotype.Service;

@Service
public class UnidadService extends AbstractCrudService<Unidad, Integer> {

    public UnidadService(UnidadRepository repository) {
        super(repository);
    }
}
