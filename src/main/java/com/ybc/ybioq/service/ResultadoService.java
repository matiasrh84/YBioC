package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Resultado;
import com.ybc.ybioq.entity.local.ResultadoId;
import com.ybc.ybioq.repository.local.ResultadoRepository;
import org.springframework.stereotype.Service;

@Service
public class ResultadoService extends AbstractCrudService<Resultado, ResultadoId> {

    public ResultadoService(ResultadoRepository repository) {
        super(repository);
    }
}
