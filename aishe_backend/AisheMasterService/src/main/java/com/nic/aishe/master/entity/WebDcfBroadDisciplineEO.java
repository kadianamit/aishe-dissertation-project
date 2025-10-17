package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "programme_bdgc" ,schema = "webdcf")
public class WebDcfBroadDisciplineEO{
	
	@Column(name="group_category_id")
	private String groupCategoryId;
	
	@Column(name="discipline_group_category")
	private String category;
		
	@Id
	@Column(name="discipline_group_id")
	private String id;
	
	@Column(name="discipline_group")
	private String discipline;

	public String getGroupCategoryId() {
		return groupCategoryId;
	}

	public void setGroupCategoryId(String groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

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
}