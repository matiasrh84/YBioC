package com.ybc.ybioq.legacy.repository.local;

import com.ybc.ybioq.legacy.entity.local.Arancel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArancelRepository extends JpaRepository<Arancel, Integer> {
}
