package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.PracticaTieneMaterial;
import com.ybc.ybioq.entity.local.PracticaTieneMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticaTieneMaterialRepository extends JpaRepository<PracticaTieneMaterial, PracticaTieneMaterialId> {
}
