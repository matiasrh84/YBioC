package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.PacienteTieneObraSocial;
import com.ybc.ybioq.entity.local.PacienteTieneObraSocialId;
import com.ybc.ybioq.repository.local.PacienteTieneObraSocialRepository;
import org.springframework.stereotype.Service;

@Service
public class PacienteTieneObraSocialService extends AbstractCrudService<PacienteTieneObraSocial, PacienteTieneObraSocialId> {

    public PacienteTieneObraSocialService(PacienteTieneObraSocialRepository repository) {
        super(repository);
    }
}
