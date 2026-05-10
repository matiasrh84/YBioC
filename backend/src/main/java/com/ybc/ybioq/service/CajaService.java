package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Caja;
import com.ybc.ybioq.repository.local.CajaRepository;
import org.springframework.stereotype.Service;

@Service
public class CajaService extends AbstractCrudService<Caja, Integer> {

    public CajaService(CajaRepository repository) {
        super(repository);
    }
}
