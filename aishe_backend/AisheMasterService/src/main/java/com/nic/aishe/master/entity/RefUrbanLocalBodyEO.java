package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ref_urban_local_body" ,schema = "public")
public class RefUrbanLocalBodyEO{
  
    @Id
    @Column(name="id")
	private Integer Id;
    
	@Column(name="ulb_name")
	private String ulbName;
	
	@Column(name="district_code")
	private String districtCode;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getUlbName() {
		return ulbName;
	}

	public void setUlbName(String ulbName) {
		this.ulbName = ulbName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}	
}