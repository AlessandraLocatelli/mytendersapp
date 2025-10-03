package com.bolzanoprovince.tendering.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
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
public class Vendor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(nullable = false, unique = true)
private Long Id;

@Column(length = 100, nullable = false)
private String name;

@Column(nullable = false, unique = true)
private String vatNumber;

@ManyToMany(mappedBy = "vendors")
private Set<Tender> tenders = new HashSet<>();


}
