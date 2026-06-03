package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.PacienteTieneObraSocial;
import com.ybc.ybioq.entity.local.PacienteTieneObraSocialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteTieneObraSocialRepository extends JpaRepository<PacienteTieneObraSocial, PacienteTieneObraSocialId> {

    List<PacienteTieneObraSocial> findByIdPacientesId(Integer idPaciente);
}
