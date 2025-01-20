package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {

    List<Especialidad> findAllByEstadoIsTrueOrderByNombreAsc();

    @Query("SELECT e FROM Especialidad e WHERE e.estado = true AND e.nombre LIKE %:valor% ORDER BY e.nombre")
    List<Especialidad> findEspecialidadesByNombreContaining(@Param("valor") String valor);
}
