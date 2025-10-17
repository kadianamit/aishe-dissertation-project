package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ref_block" ,schema = "public")
public class RefBlockEO{
  
    @Id
    @Column(name="id")
	private Integer Id;
    
	@Column(name="block_name")
	private String blockName;
	
	@Column(name="district_code")
	private String districtCode;
	
	@Column(name="lgd_block_code")
	private Integer lgdBlockCode;

	@Column(name="lgd_district_code")
	private Integer lgdDistrictCode;
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public Integer getLgdBlockCode() {
		return lgdBlockCode;
	}

	public void setLgdBlockCode(Integer lgdBlockCode) {
		this.lgdBlockCode = lgdBlockCode;
	}

	public Integer getLgdDistrictCode() {
		return lgdDistrictCode;
	}

	public void setLgdDistrictCode(Integer lgdDistrictCode) {
		this.lgdDistrictCode = lgdDistrictCode;
	}
}