package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Anticipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnticipoRepository extends JpaRepository<Anticipo, Integer> {
}
