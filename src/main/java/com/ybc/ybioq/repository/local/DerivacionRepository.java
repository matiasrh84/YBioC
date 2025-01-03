package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Derivacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DerivacionRepository extends JpaRepository<Derivacion, Integer> {
}
