package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.OrdenTienePractica;
import com.ybc.ybioq.entity.local.OrdenTienePracticaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenTienePracticaRepository extends JpaRepository<OrdenTienePractica, OrdenTienePracticaId> {

    List<OrdenTienePractica> findByIdIdOrdenes(Integer idOrdenes);

    @org.springframework.data.jpa.repository.Modifying
    @org.springframework.data.jpa.repository.Query("DELETE FROM OrdenTienePractica otp WHERE otp.id.idOrdenes = :idOrden")
    void deleteByOrden(@org.springframework.data.repository.query.Param("idOrden") Integer idOrden);
}
