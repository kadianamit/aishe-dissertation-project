package com.nic.aishe.master.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity 
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="ref_district") 
public class District {
	
	@Id
	@Column(name="dist_code")
	private String distCode;
	
	@Column(name="st_code")
	private String stateCode;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="sno")
	private int sno;
	
	@Column(name = "lgd_district_code")
	private Integer lgdDistCode;

	public String getDistCode() {
		return distCode;
	}

	public void setDistCode(String distCode) {
		this.distCode = distCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public Integer getLgdDistCode() {
		return lgdDistCode;
	}

	public void setLgdDistCode(Integer lgdDistCode) {
		this.lgdDistCode = lgdDistCode;
	}

}
