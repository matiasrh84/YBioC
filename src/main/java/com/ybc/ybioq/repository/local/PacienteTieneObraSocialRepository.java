package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.PacienteTieneObraSocial;
import com.ybc.ybioq.entity.local.PacienteTieneObraSocialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteTieneObraSocialRepository extends JpaRepository<PacienteTieneObraSocial, PacienteTieneObraSocialId> {
}
