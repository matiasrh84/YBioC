package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Analisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalisisRepository extends JpaRepository<Analisis, Integer> {

    @Query("SELECT a FROM Analisis a " +
           "LEFT JOIN FETCH a.metodo " +
           "LEFT JOIN FETCH a.titulo " +
           "WHERE a.practica.id = :practicaId " +
           "ORDER BY a.prioridad ASC, a.nombre ASC")
    List<Analisis> findByPracticaIdConRelaciones(@Param("practicaId") Integer practicaId);
}
