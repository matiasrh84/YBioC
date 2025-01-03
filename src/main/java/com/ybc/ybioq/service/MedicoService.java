package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Especialidad;
import com.ybc.ybioq.entity.local.Medico;
import com.ybc.ybioq.repository.local.EspecialidadRepository;
import com.ybc.ybioq.repository.local.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;

    public List<Especialidad> listarEspecialidades() {
        return especialidadRepository.findAllByOrderByNombreAsc();
    }

    public List<Medico> obtenerMedicosConEspecialidades() {
        return medicoRepository.findMedicosConEspecialidades();
    }

    public Medico guardarMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    public int obtenerMaxId() {
        return medicoRepository.findMaxId();
    }
}
