package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "programme_bdc" ,schema = "webdcf")
public class WebDcfBroadCategoryEO{
  
    @Id
    @Column(name="programme_id")
	private String programmeId;
    
	@Column(name="programme")
	private String programmeName;
	
	@Column(name="group_category_id")
	private String id;
	
	@Column(name="discipline_group_category")
	private String category;

	public String getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}

	public String getProgrammeName() {
		return programmeName;
	}

	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


}
