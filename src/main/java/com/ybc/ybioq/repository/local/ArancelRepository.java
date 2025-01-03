package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Arancel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArancelRepository extends JpaRepository<Arancel, Integer> {
}
