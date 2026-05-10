package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.PracticaNbu;
import com.ybc.ybioq.repository.local.PracticaNbuRepository;
import org.springframework.stereotype.Service;

@Service
public class PracticaNbuService extends AbstractCrudService<PracticaNbu, Integer> {

    public PracticaNbuService(PracticaNbuRepository repository) {
        super(repository);
    }
}
