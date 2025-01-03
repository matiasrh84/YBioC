package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaRepository extends JpaRepository<Caja, Integer> {
}
