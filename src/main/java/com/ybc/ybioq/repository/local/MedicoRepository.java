package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query("SELECT COALESCE(MAX(m.id), 0) FROM Medico m")
    int findMaxId();

    @Query("SELECT m FROM Medico m LEFT JOIN FETCH m.medicoTieneEspecialidades mt LEFT JOIN FETCH mt.idEspecialidades WHERE m.estado = 1")
    List<Medico> findMedicosConEspecialidades();

    Optional<Medico> findByMatriculaAndEstado(Integer matricula, Integer estado);
}
