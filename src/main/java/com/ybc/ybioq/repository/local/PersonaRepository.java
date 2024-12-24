package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
}
