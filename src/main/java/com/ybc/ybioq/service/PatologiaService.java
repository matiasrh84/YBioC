package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Patologia;
import com.ybc.ybioq.repository.local.PatologiaRepository;
import org.springframework.stereotype.Service;

@Service
public class PatologiaService extends AbstractCrudService<Patologia, Integer> {

    public PatologiaService(PatologiaRepository repository) {
        super(repository);
    }
}
