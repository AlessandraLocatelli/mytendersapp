package com.bolzanoprovince.tendering.model.entity;

import com.bolzanoprovince.tendering.model.enums.EvaluationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Lot {
@Id
@Column(length = 10, nullable = false,unique = true)
private String cig;

@Column(length = 10, nullable = false)
private String description;

@Column(nullable = false)
private BigDecimal amount;

@Column(nullable = false)
@Enumerated(EnumType.STRING)
private EvaluationType evaluationType;

@ManyToOne(optional = false)
@JoinColumn(name = "tender_id", nullable = false)
private Tender tender;


}
