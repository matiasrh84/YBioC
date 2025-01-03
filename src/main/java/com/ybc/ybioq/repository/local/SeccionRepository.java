package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, Integer> {
}
