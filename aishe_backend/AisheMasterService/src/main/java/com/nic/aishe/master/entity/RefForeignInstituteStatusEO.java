package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ref_foreign_institute_status" ,schema = "public")
public class RefForeignInstituteStatusEO{
  
    @Id
    @Column(name="id")
	private Integer Id;
    
	@Column(name="name")
	private String Name;
	
	@Column(name="status")
	private boolean status;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
}