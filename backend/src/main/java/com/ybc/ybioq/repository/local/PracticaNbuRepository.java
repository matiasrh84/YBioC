package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.PracticaNbu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticaNbuRepository extends JpaRepository<PracticaNbu, Integer> {

    List<PracticaNbu> findByIdPracticasId(Integer idPractica);
}
