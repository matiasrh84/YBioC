package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer>, JpaSpecificationExecutor<Paciente> {

    Optional<Paciente> findByPersonasDniId(Integer idPersona);
}
