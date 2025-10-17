package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_sub_district")
public class RefSubDistrictEO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "lgd_district_code")
	private String lgdDistrictCode;

	@Column(name = "lgd_state_code")
	private String lgdStateCode;
	
	@Column(name = "lgd_sub_district_code")
	private String lgdSubDistrictCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLgdDistrictCode() {
		return lgdDistrictCode;
	}

	public void setLgdDistrictCode(String lgdDistrictCode) {
		this.lgdDistrictCode = lgdDistrictCode;
	}

	public String getLgdStateCode() {
		return lgdStateCode;
	}

	public void setLgdStateCode(String lgdStateCode) {
		this.lgdStateCode = lgdStateCode;
	}

	public String getLgdSubDistrictCode() {
		return lgdSubDistrictCode;
	}

	public void setLgdSubDistrictCode(String lgdSubDistrictCode) {
		this.lgdSubDistrictCode = lgdSubDistrictCode;
	}
}