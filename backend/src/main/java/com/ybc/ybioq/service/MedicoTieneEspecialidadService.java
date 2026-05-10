package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.MedicoTieneEspecialidad;
import com.ybc.ybioq.entity.local.MedicoTieneEspecialidadId;
import com.ybc.ybioq.repository.local.MedicoTieneEspecialidadRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicoTieneEspecialidadService extends AbstractCrudService<MedicoTieneEspecialidad, MedicoTieneEspecialidadId> {

    public MedicoTieneEspecialidadService(MedicoTieneEspecialidadRepository repository) {
        super(repository);
    }
}
