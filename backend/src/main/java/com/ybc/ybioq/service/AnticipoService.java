package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Anticipo;
import com.ybc.ybioq.repository.local.AnticipoRepository;
import org.springframework.stereotype.Service;

@Service
public class AnticipoService extends AbstractCrudService<Anticipo, Integer> {

    public AnticipoService(AnticipoRepository repository) {
        super(repository);
    }
}
