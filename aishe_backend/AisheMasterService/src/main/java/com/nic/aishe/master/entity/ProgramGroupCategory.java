package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_programme_broad_discipline_group_and_category")
public class ProgramGroupCategory {
	
	
	@Column(name="programme_id")
	private String id;
	
//	@OneToOne
//	@JoinColumn(name="broad_discipline_group_id",referencedColumnName = "id")
//	private BroadName broadName;
//	
//	@OneToOne
//	@JoinColumn(name="broad_discipline_group_category_id",referencedColumnName = "id")
//	private BroadCategory broadCategory;
	
	//@OneToOne
	@Column(name="broad_discipline_group_id")
	private String broadName;
//	
//	//@OneToOne
	@Id
	@Column(name="broad_discipline_group_category_id")
	private String broadCategory;
	
	@Column(name="is_applicable")
	private Boolean isApplicable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBroadName() {
		return broadName;
	}

	public void setBroadName(String broadName) {
		this.broadName = broadName;
	}

	public String getBroadCategory() {
		return broadCategory;
	}

	public Boolean getIsApplicable() {
		return isApplicable;
	}

	public void setIsApplicable(Boolean isApplicable) {
		this.isApplicable = isApplicable;
	}

//	public void setBroadCategory(String broadCategory) {
//		this.broadCategory = broadCategory;
//	}

//	public BroadName getBroadName() {
//		return broadName;
//	}
//
//	public void setBroadName(BroadName broadName) {
//		this.broadName = broadName;
//	}
//
//	public BroadCategory getBroadCategory() {
//		return broadCategory;
//	}
//
//	public void setBroadCategory(BroadCategory broadCategory) {
//		this.broadCategory = broadCategory;
//	}
	
	

}
