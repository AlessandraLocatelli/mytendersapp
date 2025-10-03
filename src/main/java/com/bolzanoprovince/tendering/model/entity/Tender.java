package com.bolzanoprovince.tendering.model.entity;

import com.bolzanoprovince.tendering.model.enums.TenderType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Tender {

@Id
@Column(nullable = false,unique = true)
private String protocolId;

@Column(length = 10, nullable = false)
private String description;

@Column(nullable = false)
private BigDecimal amount;

private Boolean isThreshold = false;

@Column(nullable = false)
private Boolean publicVisibility;

@Column(nullable = false)
private LocalDateTime docAvailableUntilQualification;

@Column(nullable = false)
@Enumerated(EnumType.STRING)
private TenderType tenderType;

@OneToMany(mappedBy = "tender",cascade = CascadeType.ALL )
private List<Lot> lots;


@ManyToOne
@JoinColumn(name = "responsible_id", nullable = false)
private Responsible responsible;


@ManyToMany
@JoinTable(
        name = "tender_vendor",
        joinColumns = @JoinColumn(name = "tender_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "vendor_id", nullable = false)
    )
private Set<Vendor> vendors = new HashSet<>();



}
