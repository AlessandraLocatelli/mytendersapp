package com.bolzanoprovince.tendering.repository;

import com.bolzanoprovince.tendering.model.entity.Tender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TenderRepository extends JpaRepository<Tender,String> {

    Tender findTopByOrderByProtocolIdDesc();
}
