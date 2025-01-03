package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Patologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatologiaRepository extends JpaRepository<Patologia, Integer> {
}
