package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Derivacion;
import com.ybc.ybioq.repository.local.DerivacionRepository;
import org.springframework.stereotype.Service;

@Service
public class DerivacionService extends AbstractCrudService<Derivacion, Integer> {

    public DerivacionService(DerivacionRepository repository) {
        super(repository);
    }
}
