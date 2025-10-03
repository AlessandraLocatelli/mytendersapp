package com.bolzanoprovince.tendering.repository;

import com.bolzanoprovince.tendering.model.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface LotRepository extends JpaRepository<Lot,String> {

    @Query("SELECT SUM(l.amount) FROM Lot l WHERE l.tender.protocolId = :protocolId")
    BigDecimal sumByTenderId(@Param("protocolId") String protocolId);


}
