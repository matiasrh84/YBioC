package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Integer> {

    List<Seccion> findByEstadoOrderByPrioridadAscNombreAsc(Integer estado);

    Optional<Seccion> findByNombreIgnoreCase(String nombre);
}
