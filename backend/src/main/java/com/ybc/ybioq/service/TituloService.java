package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Titulo;
import com.ybc.ybioq.repository.local.TituloRepository;
import org.springframework.stereotype.Service;

@Service
public class TituloService extends AbstractCrudService<Titulo, Integer> {

    public TituloService(TituloRepository repository) {
        super(repository);
    }
}
