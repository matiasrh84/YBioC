package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.OrdenTienePractica;
import com.ybc.ybioq.entity.local.OrdenTienePracticaId;
import com.ybc.ybioq.repository.local.OrdenTienePracticaRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdenTienePracticaService extends AbstractCrudService<OrdenTienePractica, OrdenTienePracticaId> {

    public OrdenTienePracticaService(OrdenTienePracticaRepository repository) {
        super(repository);
    }
}
