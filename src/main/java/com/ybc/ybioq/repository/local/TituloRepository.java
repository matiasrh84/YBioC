package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Integer> {
}
