package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
}
