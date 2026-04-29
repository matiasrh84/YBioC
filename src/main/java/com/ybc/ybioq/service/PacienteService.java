package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Paciente;
import com.ybc.ybioq.repository.local.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class PacienteService extends AbstractCrudService<Paciente, Integer> {

    public PacienteService(PacienteRepository repository) {
        super(repository);
    }
}
