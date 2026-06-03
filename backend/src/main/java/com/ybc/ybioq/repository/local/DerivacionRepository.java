package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Derivacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DerivacionRepository extends JpaRepository<Derivacion, Integer> {

    Optional<Derivacion> findByNombreIgnoreCase(String nombre);
}
