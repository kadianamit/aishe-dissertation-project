package com.nic.aishe.master.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="ref_nirf_discipline_category")
public class RefNirfDisciplineCategoryEO {
	
	@Id
	private Integer id;
	private String name;
	
	
	
	

}
