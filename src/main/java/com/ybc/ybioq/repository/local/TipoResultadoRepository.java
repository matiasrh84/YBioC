package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.TipoResultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoResultadoRepository extends JpaRepository<TipoResultado, Integer> {
}
