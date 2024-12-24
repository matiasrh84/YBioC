package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Resultado;
import com.ybc.ybioq.entity.local.ResultadoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultadoRepository extends JpaRepository<Resultado, ResultadoId> {
}
