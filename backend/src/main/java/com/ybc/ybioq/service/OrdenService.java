package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Orden;
import com.ybc.ybioq.repository.local.OrdenRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdenService extends AbstractCrudService<Orden, Integer> {

    public OrdenService(OrdenRepository repository) {
        super(repository);
    }
}
