package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.PracticaTieneMaterial;
import com.ybc.ybioq.entity.local.PracticaTieneMaterialId;
import com.ybc.ybioq.repository.local.PracticaTieneMaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class PracticaTieneMaterialService extends AbstractCrudService<PracticaTieneMaterial, PracticaTieneMaterialId> {

    public PracticaTieneMaterialService(PracticaTieneMaterialRepository repository) {
        super(repository);
    }
}
