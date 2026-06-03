package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer>, JpaSpecificationExecutor<Persona> {

    Optional<Persona> findByDni(Integer dni);

    List<Persona> findTop50ByApellidoContainingIgnoreCaseOrNombreContainingIgnoreCaseOrderByApellidoAscNombreAsc(
            String apellido, String nombre);

    @Query("SELECT COALESCE(MAX(p.id), 0) FROM Persona p")
    int findMaxId();
}
