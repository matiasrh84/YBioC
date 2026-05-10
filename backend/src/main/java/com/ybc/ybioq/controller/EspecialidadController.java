package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Especialidad;
import com.ybc.ybioq.service.EspecialidadService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class EspecialidadController extends AbstractCrudController<Especialidad, Integer> {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        super(especialidadService);
        this.especialidadService = especialidadService;
    }

    public List<Especialidad> getEspecialidadesByNombre(String valor) {
        return especialidadService.findAll(valor);
    }

    public List<Especialidad> getEspecialidades() {
        return findAll();
    }

    public Especialidad addEspecialidad(Especialidad especialidad) {
        return save(especialidad);
    }

    public Optional<Especialidad> getEspecialidadById(int id) {
        return findById(id);
    }
}
