package com.nic.aishe.master.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.nic.aishe.master.dto.ApplicationTypeNameListVO;

@Entity 
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="ref_state") 
//@TypeDef(name = "JsonUserType", typeClass = JsonUserType.class)
public class State {
	
	@Id
	@Column(name="st_code")
	private String stateCode;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "lgd_code")
	private Integer lgdCode;
	
    @Transient
    private List<District> district;
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

	public Integer getLgdCode() {
		return lgdCode;
	}

	public void setLgdCode(Integer lgdCode) {
		this.lgdCode = lgdCode;
	}

	public List<District> getDistrict() {
		return district;
	}

	public void setDistrict(List<District> district) {
		this.district = district;
	}

}
