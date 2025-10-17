package com.nic.aishe.master.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="course_examination_result")
public class CourseExamResult {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToOne
	@JoinColumn(name="examination_result_id")
	private ExamResult examinationInfo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ExamResult getExaminationInfo() {
		return examinationInfo;
	}

	public void setExaminationInfo(ExamResult examinationInfo) {
		this.examinationInfo = examinationInfo;
	}

	
	
	
	
}