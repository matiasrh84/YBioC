package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
