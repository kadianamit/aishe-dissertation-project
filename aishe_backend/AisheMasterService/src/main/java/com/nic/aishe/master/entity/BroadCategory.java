package com.nic.aishe.master.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_broad_discipline_group_category")
public class BroadCategory{

	@Id
	private String id;

	@Column(name="discipline_group_category")
	private String category;
		
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
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BroadCategory)) {
			return false;
		}
		BroadCategory other = (BroadCategory) obj;
		return Objects.equals(id, other.id);
	}

	

	
	
}
