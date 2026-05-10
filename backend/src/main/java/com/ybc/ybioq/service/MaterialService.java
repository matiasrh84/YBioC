package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Material;
import com.ybc.ybioq.repository.local.MaterialRepository;
import org.springframework.stereotype.Service;

@Service
public class MaterialService extends AbstractCrudService<Material, Integer> {

    public MaterialService(MaterialRepository repository) {
        super(repository);
    }
}
