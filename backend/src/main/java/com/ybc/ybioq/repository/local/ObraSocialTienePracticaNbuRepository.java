package com.ybc.ybioq.repository.local;

import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbu;
import com.ybc.ybioq.entity.local.ObraSocialTienePracticaNbuId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraSocialTienePracticaNbuRepository extends JpaRepository<ObraSocialTienePracticaNbu, ObraSocialTienePracticaNbuId> {

    @Query("SELECT o FROM ObraSocialTienePracticaNbu o " +
           "WHERE o.idObrasocial.id = :idObrasocial " +
           "AND o.id.idPracticasnbu IN " +
           "(SELECT pn.id FROM PracticaNbu pn WHERE pn.idPracticas.id = :idPractica)")
    List<ObraSocialTienePracticaNbu> findByObrasocialAndPractica(
            @Param("idObrasocial") Integer idObrasocial,
            @Param("idPractica") Integer idPractica);
}
