package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.PacienteTieneObraSocial;
import com.ybc.ybioq.entity.local.PacienteTieneObraSocialId;
import com.ybc.ybioq.repository.local.PacienteTieneObraSocialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteTieneObraSocialService extends AbstractCrudService<PacienteTieneObraSocial, PacienteTieneObraSocialId> {

    private final PacienteTieneObraSocialRepository repo;

    public PacienteTieneObraSocialService(PacienteTieneObraSocialRepository repository) {
        super(repository);
        this.repo = repository;
    }

    public List<PacienteTieneObraSocial> findByPaciente(Integer idPaciente) {
        return repo.findByIdPacientesId(idPaciente);
    }
}
