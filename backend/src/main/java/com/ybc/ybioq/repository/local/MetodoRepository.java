package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Metodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoRepository extends JpaRepository<Metodo, Integer> {
}
