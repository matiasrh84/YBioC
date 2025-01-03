package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Nbu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NbuRepository extends JpaRepository<Nbu, Integer> {
}
