package com.nic.aishe.master.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name="ref_institution_management")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ownership {
	@Id	
	@Column(name="id")
	private String id;
	@Column(name="management")
	private String management;
	@Column(name="active")
	private Boolean active;


	@JsonIgnore
	@Column(name = "ownership_for_college", columnDefinition = "jsonb")
	@Type(type = "jsonb")
	private List<Integer> ownershipForCollege;
	@Transient
	private List<Management> ownershipForClg;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getManagement() {
		return management;
	}
	public void setManagement(String management) {
		this.management = management;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}


	public List<Integer> getOwnershipForCollege() {
		return ownershipForCollege;
	}

	public void setOwnershipForCollege(List<Integer> ownershipForCollege) {
		this.ownershipForCollege = ownershipForCollege;
	}

	public List<Management> getOwnershipForClg() {
		return ownershipForClg;
	}

	public void setOwnershipForClg(List<Management> ownershipForClg) {
		this.ownershipForClg = ownershipForClg;
	}
}
