package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Informe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformeRepository extends JpaRepository<Informe, Integer> {
}
