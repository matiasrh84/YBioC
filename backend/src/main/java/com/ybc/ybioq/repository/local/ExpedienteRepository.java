package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Expediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpedienteRepository extends JpaRepository<Expediente, Integer> {
}
