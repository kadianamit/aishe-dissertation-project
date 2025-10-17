package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "teaching_staff_sanctioned_strength")
public class TeachingStaffSanctionedStrength {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "designation_id")
	private String designationId;
	@Column(name = "sanctioned_strength")
	private Integer sanctionedStrength;
	@Column(name = "in_position")
	private Integer inPosition;
	@Column(name = "in_position_direct")
	private Integer inPositionDirect;
	@Column(name = "in_position_cas")
	private Integer inPositionCas;
	@Column(name = "no_of_phd_teachers")
	private Integer noOfPhdTeachers;
	@Column(name = "in_vocational_course")
	private Integer inVocationalCourse;
	@Transient
	private String universityId;
	@Transient
	private Integer surveyYear;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesignationId() {
		return designationId;
	}
	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}
	public Integer getSanctionedStrength() {
		return sanctionedStrength;
	}
	public void setSanctionedStrength(Integer sanctionedStrength) {
		this.sanctionedStrength = sanctionedStrength;
	}
	public Integer getInPosition() {
		return inPosition;
	}
	public void setInPosition(Integer inPosition) {
		this.inPosition = inPosition;
	}
	public Integer getInPositionDirect() {
		return inPositionDirect;
	}
	public void setInPositionDirect(Integer inPositionDirect) {
		this.inPositionDirect = inPositionDirect;
	}
	public Integer getInPositionCas() {
		return inPositionCas;
	}
	public void setInPositionCas(Integer inPositionCas) {
		this.inPositionCas = inPositionCas;
	}
	public Integer getNoOfPhdTeachers() {
		return noOfPhdTeachers;
	}
	public void setNoOfPhdTeachers(Integer noOfPhdTeachers) {
		this.noOfPhdTeachers = noOfPhdTeachers;
	}
	public Integer getInVocationalCourse() {
		return inVocationalCourse;
	}
	public void setInVocationalCourse(Integer inVocationalCourse) {
		this.inVocationalCourse = inVocationalCourse;
	}
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	
	
}
