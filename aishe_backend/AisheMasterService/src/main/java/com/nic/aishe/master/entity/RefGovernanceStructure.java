package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ref_governance_structure")
public class RefGovernanceStructure{

	@Id
	private Integer id;

	@Column(name="name")
	private String name;

	
}