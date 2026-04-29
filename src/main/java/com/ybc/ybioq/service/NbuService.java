package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Nbu;
import com.ybc.ybioq.repository.local.NbuRepository;
import org.springframework.stereotype.Service;

@Service
public class NbuService extends AbstractCrudService<Nbu, Integer> {

    public NbuService(NbuRepository repository) {
        super(repository);
    }
}
