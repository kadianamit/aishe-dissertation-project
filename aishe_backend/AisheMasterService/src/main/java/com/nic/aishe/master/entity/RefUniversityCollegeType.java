package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_university_college_type")
public class RefUniversityCollegeType {
	
	@Id
	@Column(name="id")
	private String id;
    @Column(name="type")
	private String type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String Type) {
		this.type = Type;
	}
	
}
