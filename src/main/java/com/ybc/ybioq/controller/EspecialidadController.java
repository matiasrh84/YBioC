package com.ybc.ybioq.controller;

import com.ybc.ybioq.entity.local.Especialidad;
import com.ybc.ybioq.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class EspecialidadController {

    @Autowired
    private EspecialidadService especialidadService;

    public List<Especialidad> getEspecialidadesByNombre(String valor) {
        return especialidadService.findAll(valor);
    }

    public List<Especialidad> getEspecialidades() {
        return especialidadService.findAll();
    }

    public Especialidad addEspecialidad(Especialidad especialidad) {
        return especialidadService.addEspecialidad(especialidad);
    }

    public Optional<Especialidad> getEspecialidadById(int id) {
        return especialidadService.findEspecialidadById(id);
    }
}
