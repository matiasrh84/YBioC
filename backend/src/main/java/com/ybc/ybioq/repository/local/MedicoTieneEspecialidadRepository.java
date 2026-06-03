package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.MedicoTieneEspecialidad;
import com.ybc.ybioq.entity.local.MedicoTieneEspecialidadId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MedicoTieneEspecialidadRepository extends JpaRepository<MedicoTieneEspecialidad, MedicoTieneEspecialidadId> {

    @Query("SELECT mte FROM MedicoTieneEspecialidad mte " +
           "JOIN FETCH mte.idEspecialidades " +
           "WHERE mte.idMedicos.id = :idMedico")
    List<MedicoTieneEspecialidad> findByIdMedicosId(@Param("idMedico") Integer idMedico);

    @Modifying
    @Transactional
    @Query("DELETE FROM MedicoTieneEspecialidad mte WHERE mte.idMedicos.id = :idMedico")
    void deleteByIdMedicosId(@Param("idMedico") Integer idMedico);
}
