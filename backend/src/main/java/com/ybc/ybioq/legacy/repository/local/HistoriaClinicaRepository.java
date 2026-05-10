package com.ybc.ybioq.legacy.repository.local;

import com.ybc.ybioq.legacy.entity.local.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Integer> {
}
