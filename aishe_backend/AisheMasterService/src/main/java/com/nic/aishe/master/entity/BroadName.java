package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
//@Cacheable
//@Cacheable 
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="ref_broad_discipline_group")
public class BroadName {
	
	@Id
	private String id;

	@Column(name="discipline_group")
	private String discipline;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id",referencedColumnName = "id")
	private BroadCategory categoryId;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public BroadCategory listCategoryId() {
		return categoryId;
	}
	public void addCategoryId(BroadCategory categoryId) {
		this.categoryId = categoryId;
	}
	
	
	

}
