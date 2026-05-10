package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbu;
import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbuId;
import com.ybc.ybioq.repository.local.ObraSocialTienePracticaNbuRepository;
import org.springframework.stereotype.Service;

@Service
public class ObraSocialTienePracticaNbuService extends AbstractCrudService<ObraSocialTienePracticaNbu, ObraSocialTienePracticaNbuId> {

    public ObraSocialTienePracticaNbuService(ObraSocialTienePracticaNbuRepository repository) {
        super(repository);
    }
}
