package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Licencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Integer> {
}
