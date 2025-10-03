package com.bolzanoprovince.tendering.repository;


import com.bolzanoprovince.tendering.model.entity.Responsible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibleRepository extends JpaRepository<Responsible,Long> {
}
