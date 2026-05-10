package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Seccion;
import com.ybc.ybioq.repository.local.SeccionRepository;
import org.springframework.stereotype.Service;

@Service
public class SeccionService extends AbstractCrudService<Seccion, Integer> {

    public SeccionService(SeccionRepository repository) {
        super(repository);
    }
}
