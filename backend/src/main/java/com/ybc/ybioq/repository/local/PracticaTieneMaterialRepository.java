package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.PracticaTieneMaterial;
import com.ybc.ybioq.entity.local.PracticaTieneMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticaTieneMaterialRepository extends JpaRepository<PracticaTieneMaterial, PracticaTieneMaterialId> {
}
