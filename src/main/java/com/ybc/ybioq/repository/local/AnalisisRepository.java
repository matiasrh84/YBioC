package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Analisis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalisisRepository extends JpaRepository<Analisis, Integer> {
}
