package com.nic.aishe.master.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="department")
public class Department implements Serializable{

	private static final long serialVersionUID = -2115939166588784421L;
	@Id	
	private Integer id;
	private String name;
	
	@Transient
	private Integer aisheCode;
	@Transient
	private Integer surveyYear;
	
//	@OneToMany(fetch = FetchType.LAZY)
//	@NotFound(action = NotFoundAction.IGNORE)
//	@JoinTable(name="department_teaching_staff_count",
//	joinColumns =@JoinColumn(name="department_id",referencedColumnName = "id"),
//	inverseJoinColumns = @JoinColumn(name="teaching_staff_count_id",referencedColumnName = "id"))
//	private List<TeachingStaffCount> teachingStaffCounts;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinTable(name = "college_institution_department", joinColumns = {
			@JoinColumn(name = "department_id", referencedColumnName = "id")}, 
	inverseJoinColumns = {
	@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
	@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") })
	private CollegeInfo collegeInfo;
	
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
//	public List<TeachingStaffCount> listTeachingStaffCounts() {
//		return teachingStaffCounts;
//	}
//	public void addTeachingStaffCounts(List<TeachingStaffCount> teachingStaffCounts) {
//		this.teachingStaffCounts = teachingStaffCounts;
//	}
	public Integer getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(Integer aisheCode) {
		this.aisheCode = aisheCode;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", aisheCode=" + aisheCode + ", surveyYear=" + surveyYear
				+ ", collegeInfo=" + collegeInfo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (id == null) {
			return other.id == null;
		} else return id.equals(other.id);
	}
	public CollegeInfo findCollegeInfo() {
		return collegeInfo;
	}
	public void addCollegeInfo(CollegeInfo collegeInfo) {
		this.collegeInfo = collegeInfo;
	}
	
	
}
