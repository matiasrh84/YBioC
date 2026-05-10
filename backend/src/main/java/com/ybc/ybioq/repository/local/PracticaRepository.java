package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Practica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Integer> {
}
