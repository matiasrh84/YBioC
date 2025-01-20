package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Especialidad;
import com.ybc.ybioq.repository.local.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;


    public List<Especialidad> findAll(String valor) {
        return especialidadRepository.findEspecialidadesByNombreContaining(valor);
    }

    public List<Especialidad> findAll() {
        return especialidadRepository.findAll();
    }

    public Especialidad addEspecialidad(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public Optional<Especialidad> findEspecialidadById(int id) {
        return especialidadRepository.findById(id);
    }
}
