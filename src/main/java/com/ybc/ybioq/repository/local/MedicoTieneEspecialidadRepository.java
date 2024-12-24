package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.MedicoTieneEspecialidad;
import com.ybc.ybioq.entity.local.MedicoTieneEspecialidadId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoTieneEspecialidadRepository extends JpaRepository<MedicoTieneEspecialidad, MedicoTieneEspecialidadId> {
}
