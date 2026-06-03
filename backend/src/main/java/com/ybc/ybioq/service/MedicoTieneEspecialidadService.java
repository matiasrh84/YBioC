package com.ybc.ybioq.service;

import com.ybc.ybioq.entity.local.Especialidad;
import com.ybc.ybioq.entity.local.Medico;
import com.ybc.ybioq.entity.local.MedicoTieneEspecialidad;
import com.ybc.ybioq.entity.local.MedicoTieneEspecialidadId;
import com.ybc.ybioq.repository.local.EspecialidadRepository;
import com.ybc.ybioq.repository.local.MedicoTieneEspecialidadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoTieneEspecialidadService extends AbstractCrudService<MedicoTieneEspecialidad, MedicoTieneEspecialidadId> {

    private final MedicoTieneEspecialidadRepository medicoTieneEspecialidadRepository;
    private final EspecialidadRepository especialidadRepository;

    public MedicoTieneEspecialidadService(MedicoTieneEspecialidadRepository repository,
                                          EspecialidadRepository especialidadRepository) {
        super(repository);
        this.medicoTieneEspecialidadRepository = repository;
        this.especialidadRepository = especialidadRepository;
    }

    @Transactional
    public void actualizarEspecialidades(Medico medico, List<Integer> idEspecialidades) {
        medicoTieneEspecialidadRepository.deleteByIdMedicosId(medico.getId());
        for (Integer idEsp : idEspecialidades) {
            Especialidad esp = especialidadRepository.findById(idEsp)
                    .orElseThrow(() -> new IllegalArgumentException("Especialidad no encontrada: " + idEsp));
            MedicoTieneEspecialidadId mteId = new MedicoTieneEspecialidadId();
            mteId.setIdMedicos(medico.getId());
            mteId.setIdEspecialidades(idEsp);
            MedicoTieneEspecialidad mte = new MedicoTieneEspecialidad();
            mte.setId(mteId);
            mte.setIdMedicos(medico);
            mte.setIdEspecialidades(esp);
            medicoTieneEspecialidadRepository.save(mte);
        }
    }
}
