package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Especialidad;
import com.ybc.ybioq.repository.local.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService extends AbstractCrudService<Especialidad, Integer> {

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadService(EspecialidadRepository especialidadRepository) {
        super(especialidadRepository);
        this.especialidadRepository = especialidadRepository;
    }

    public List<Especialidad> findAll(String valor) {
        return especialidadRepository.findEspecialidadesByNombreContaining(valor);
    }

    @Override
    public List<Especialidad> findAll() {
        return super.findAll();
    }

    public Especialidad addEspecialidad(Especialidad especialidad) {
        return save(especialidad);
    }

    public Optional<Especialidad> findEspecialidadById(int id) {
        return especialidadRepository.findById(id);
    }
}
