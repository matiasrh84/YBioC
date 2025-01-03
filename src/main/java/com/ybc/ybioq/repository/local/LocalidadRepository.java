package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Integer> {
}
