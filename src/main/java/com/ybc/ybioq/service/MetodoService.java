package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Metodo;
import com.ybc.ybioq.repository.local.MetodoRepository;
import org.springframework.stereotype.Service;

@Service
public class MetodoService extends AbstractCrudService<Metodo, Integer> {

    public MetodoService(MetodoRepository repository) {
        super(repository);
    }
}
