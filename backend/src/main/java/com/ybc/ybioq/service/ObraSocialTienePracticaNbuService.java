package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbu;
import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbuId;
import com.ybc.ybioq.repository.local.ObraSocialTienePracticaNbuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObraSocialTienePracticaNbuService extends AbstractCrudService<ObraSocialTienePracticaNbu, ObraSocialTienePracticaNbuId> {

    private final ObraSocialTienePracticaNbuRepository repo;

    public ObraSocialTienePracticaNbuService(ObraSocialTienePracticaNbuRepository repository) {
        super(repository);
        this.repo = repository;
    }

    public List<ObraSocialTienePracticaNbu> findByObrasocialAndPractica(Integer idObrasocial, Integer idPractica) {
        return repo.findByObrasocialAndPractica(idObrasocial, idPractica);
    }
}
