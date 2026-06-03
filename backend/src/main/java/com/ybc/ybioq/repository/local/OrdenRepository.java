package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Orden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer>, JpaSpecificationExecutor<Orden> {

    List<Orden> findTop20ByIdPacientesIdOrderByFechaDesc(Integer idPaciente);

    Optional<Orden> findFirstByNumeroOrdenOrderByFechaDesc(String numeroOrden);

    List<Orden> findTop30ByOrderByFechaDesc();

    Page<Orden> findByIdPacientesId(Integer idPaciente, Pageable pageable);
}
