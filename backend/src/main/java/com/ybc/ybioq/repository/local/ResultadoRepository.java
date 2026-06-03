package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.Resultado;
import com.ybc.ybioq.entity.local.ResultadoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, ResultadoId> {

    @Query("SELECT r FROM Resultado r WHERE r.id.idOrdenes = :idOrden ORDER BY r.id.idPracticas ASC, r.id.idAnalisis ASC")
    List<Resultado> findByOrdenId(@Param("idOrden") Integer idOrden);

    @Query("SELECT COUNT(r) FROM Resultado r WHERE r.id.idOrdenes = :idOrden")
    long countByOrden(@Param("idOrden") Integer idOrden);

    @Query("SELECT COUNT(r) FROM Resultado r WHERE r.id.idOrdenes = :idOrden AND r.resultado <> '-'")
    long countInformadosByOrden(@Param("idOrden") Integer idOrden);

    @Modifying
    @Query("DELETE FROM Resultado r WHERE r.id.idOrdenes = :idOrden")
    void deleteByOrden(@Param("idOrden") Integer idOrden);
}
