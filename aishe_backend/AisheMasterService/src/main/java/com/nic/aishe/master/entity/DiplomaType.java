package com.nic.aishe.master.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_diploma_course")
public class DiplomaType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String type;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public DiplomaType(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	public DiplomaType() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DiplomaType)) {
			return false;
		}
		DiplomaType other = (DiplomaType) obj;
		return Objects.equals(id, other.id);
	}
	@Override
	public String toString() {
		return "DiplomaType [id=" + id + ", type=" + type + "]";
	}
	
	
	

}
