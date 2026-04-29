package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Localidad;
import com.ybc.ybioq.repository.local.LocalidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadService extends AbstractCrudService<Localidad, Integer> {

    public LocalidadService(LocalidadRepository localidadRepository) {
        super(localidadRepository);
    }

    public List<Localidad> getAllLocalidades() {
        return findAll();
    }
}
