package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Analisis;
import com.ybc.ybioq.repository.local.AnalisisRepository;
import org.springframework.stereotype.Service;

@Service
public class AnalisisService extends AbstractCrudService<Analisis, Integer> {

    public AnalisisService(AnalisisRepository repository) {
        super(repository);
    }
}
