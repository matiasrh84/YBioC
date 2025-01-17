package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadRepository extends JpaRepository<Unidad, Integer> {
}
