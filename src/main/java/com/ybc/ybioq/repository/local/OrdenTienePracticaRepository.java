package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.OrdenTienePractica;
import com.ybc.ybioq.entity.local.OrdenTienePracticaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenTienePracticaRepository extends JpaRepository<OrdenTienePractica, OrdenTienePracticaId> {
}
