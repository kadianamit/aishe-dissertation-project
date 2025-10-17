package com.nic.aishe.master.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

@Entity
@Table(name="non_teaching_staff_count")
public class StaffCount {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Transient
	private Integer surveyYear;
	@Transient
	private Integer aisheCode;
	
	@OneToOne
	@JoinColumn(name="staff_type_id",nullable = false)
	private StaffTypes staffType;
	
	@OneToOne
	@JoinColumn(name="group_id",nullable = false)
	private StaffGroups staffGroup;
	
	@Column(name="sanctioned_strength",nullable = false)
	private Integer sanctionedStrength;
	
	@Column(name="posts_reserved_for_pwd")
	private Integer pwdPosts;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="count_by_category_id",nullable = false)
	private PersonCategoryCount staffCategCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StaffTypes getStaffType() {
		return staffType;
	}

	public void setStaffType(StaffTypes staffType) {
		this.staffType = staffType;
	}

	public StaffGroups getStaffGroup() {
		return staffGroup;
	}

	public void setStaffGroup(StaffGroups staffGroup) {
		this.staffGroup = staffGroup;
	}

	public Integer getSanctionedStrength() {
		return sanctionedStrength;
	}

	public void setSanctionedStrength(Integer sanctionedStrength) {
		this.sanctionedStrength = sanctionedStrength;
	}

	public Integer getPwdPosts() {
		return pwdPosts;
	}

	public void setPwdPosts(Integer pwdPosts) {
		this.pwdPosts = pwdPosts;
	}

	public PersonCategoryCount getStaffCategCount() {
		return staffCategCount;
	}

	public void setStaffCategCount(PersonCategoryCount staffCategCount) {
		this.staffCategCount = staffCategCount;
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
		if (!(obj instanceof StaffCount)) {
			return false;
		}
		StaffCount other = (StaffCount) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

	public Integer getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(Integer aisheCode) {
		this.aisheCode = aisheCode;
	}

	@Override
	public String toString() {
		return "StaffCount [id=" + id + ", surveyYear=" + surveyYear + ", aisheCode=" + aisheCode + ", staffType="
				+ staffType + ", staffGroup=" + staffGroup + ", sanctionedStrength=" + sanctionedStrength
				+ ", pwdPosts=" + pwdPosts + ", staffCategCount=" + staffCategCount + "]";
	}

	
	
	
	

}
