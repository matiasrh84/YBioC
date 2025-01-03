package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {

    List<Especialidad> findAllByOrderByNombreAsc();
}
