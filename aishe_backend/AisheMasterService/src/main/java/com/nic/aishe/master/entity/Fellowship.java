package com.nic.aishe.master.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="fellowship_info",schema = "institutionprofile")
public class Fellowship implements Serializable {

	private static final long serialVersionUID = -2880026960468495375L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fellowship_id")
	private Integer id;
	@Column(name = "aishe_code")
	private String aisheCode;
	@Column(name = "program_id")
	private Integer programId;
	@Column(name = "program_year")
	private Integer programYear;
	@Column(name = "total")
	private Integer total;
	@Column(name = "total_female")
	private Integer totalFemale;
	
	@OneToOne
	@JoinColumn(name="remarks_id",referencedColumnName = "id")
	private Remarks remarksId;
	
	private boolean isFellowship;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "fellowship_social_category_info", schema = "institutionprofile", joinColumns = @JoinColumn(name = "fellowship_id"), inverseJoinColumns = @JoinColumn(name = "SOCIAL_CATEGORY_INFO_DETAILS_ID"))
	private Set<SocialCategoryInfo> socialCategoryInfo = new HashSet<SocialCategoryInfo>(0);

	@OneToMany(cascade = CascadeType.ALL)

	@JoinTable(name = "fellowship_special_category_info", schema = "institutionprofile", joinColumns = @JoinColumn(name = "fellowship_id"), inverseJoinColumns = @JoinColumn(name = "SPECIAL_CATEGORY_INFO_DETAILS_ID"))
	private Set<SpecialCategoryInfo> specialCategoryInfo = new HashSet<SpecialCategoryInfo>(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}

	public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public Integer getProgramYear() {
		return programYear;
	}

	public void setProgramYear(Integer programYear) {
		this.programYear = programYear;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalFemale() {
		return totalFemale;
	}

	public void setTotalFemale(Integer totalFemale) {
		this.totalFemale = totalFemale;
	}

	public Remarks getRemarksId() {
		return remarksId;
	}

	public void setRemarksId(Remarks remarksId) {
		this.remarksId = remarksId;
	}


	public boolean isFellowship() {
		return isFellowship;
	}

	public void setFellowship(boolean isFellowship) {
		this.isFellowship = isFellowship;
	}

	public Set<SocialCategoryInfo> getSocialCategoryInfo() {
		return socialCategoryInfo;
	}

	public void setSocialCategoryInfo(Set<SocialCategoryInfo> socialCategoryInfo) {
		this.socialCategoryInfo = socialCategoryInfo;
	}

	public Set<SpecialCategoryInfo> getSpecialCategoryInfo() {
		return specialCategoryInfo;
	}

	public void setSpecialCategoryInfo(Set<SpecialCategoryInfo> specialCategoryInfo) {
		this.specialCategoryInfo = specialCategoryInfo;
	}

	
	
	}