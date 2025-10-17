package com.nic.aishe.master.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="ref_programme")
//@TypeDef(name = "JsonUserType", typeClass = JsonUserType.class)
public class ProgramName {
	
	@Id
	private String id;
	
	@Column(name="programme")
	private String programme;
	
	@OneToOne
	@JoinColumn(name="course_level_id", referencedColumnName = "id")
	private CourseLevel courseLevel;
	
	@OneToOne
	@JoinColumn(name="teacher_highest_qualification_id", referencedColumnName = "id")
	private RefTeacherHighestQualification teacherHighestQualification;
	
	@Column(name="year", columnDefinition = "jsonb")
    @Type(type = "jsonb")
	private List<Integer> year;
	
	@Column(name="month", columnDefinition = "jsonb")
    @Type(type = "jsonb")
	private List<Integer> month;
	
	@Column(name="is_active")
	private Boolean isActive;
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public CourseLevel findCourseLevel() {
		return courseLevel;
	}
	public void addCourseLevel(CourseLevel courseLevel) {
		this.courseLevel = courseLevel;
	}

	public CourseLevel getCourseLevel() {
		return courseLevel;
	}

	public void setCourseLevel(CourseLevel courseLevel) {
		this.courseLevel = courseLevel;
	}
	public RefTeacherHighestQualification getTeacherHighestQualification() {
		return teacherHighestQualification;
	}
	public void setTeacherHighestQualification(RefTeacherHighestQualification teacherHighestQualification) {
		this.teacherHighestQualification = teacherHighestQualification;
	}
	public List<Integer> getYear() {
		return year;
	}
	public void setYear(List<Integer> year) {
		this.year = year;
	}
	public List<Integer> getMonth() {
		return month;
	}
	public void setMonth(List<Integer> month) {
		this.month = month;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
