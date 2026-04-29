package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.TipoResultado;
import com.ybc.ybioq.repository.local.TipoResultadoRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoResultadoService extends AbstractCrudService<TipoResultado, Integer> {

    public TipoResultadoService(TipoResultadoRepository repository) {
        super(repository);
    }
}
