package com.nic.aishe.master.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_course_Type")
public class CourseType {
	
	@Id
	private String id;

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