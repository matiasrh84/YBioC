package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.ObraSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Integer> {
}
