package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ref_non_teaching_staff_type")
public class StaffTypes {
	@Id
	private String id;
	
	@Column(name="staff_type")
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Types [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
