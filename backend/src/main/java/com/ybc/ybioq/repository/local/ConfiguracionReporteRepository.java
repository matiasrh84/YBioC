package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.ConfiguracionReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionReporteRepository extends JpaRepository<ConfiguracionReporte, Integer> {
}
