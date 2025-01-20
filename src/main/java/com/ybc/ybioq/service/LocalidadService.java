package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Localidad;
import com.ybc.ybioq.repository.local.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadService {

    @Autowired
    private LocalidadRepository localidadRepository;

    public List<Localidad> getAllLocalidades(){
        return localidadRepository.findAll();
    }
}
