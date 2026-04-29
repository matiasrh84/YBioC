package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Provincia;
import com.ybc.ybioq.repository.local.ProvinciaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService extends AbstractCrudService<Provincia, Integer> {

    public ProvinciaService(ProvinciaRepository repository) {
        super(repository);
    }
}
