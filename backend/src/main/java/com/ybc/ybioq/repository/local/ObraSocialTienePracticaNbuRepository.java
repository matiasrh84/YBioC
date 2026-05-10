package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbu;
import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbuId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraSocialTienePracticaNbuRepository extends JpaRepository<ObraSocialTienePracticaNbu, ObraSocialTienePracticaNbuId> {
}
