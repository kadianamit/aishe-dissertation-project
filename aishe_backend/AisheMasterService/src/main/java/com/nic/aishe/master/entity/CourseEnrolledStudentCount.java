package com.nic.aishe.master.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="course_enrolled_student_count")
public class CourseEnrolledStudentCount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
	private Integer year;
	
	@OneToOne
	@JoinColumn(name="course_id",referencedColumnName = "id")
	private Course course;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="enrolled_student_count_id",referencedColumnName = "id")
	private StudentEnrollment studentEnrollment;

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

	public StudentEnrollment getStudentEnrollment() {
		return studentEnrollment;
	}

	public void setStudentEnrollment(StudentEnrollment studentEnrollment) {
		this.studentEnrollment = studentEnrollment;
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
		if (!(obj instanceof CourseEnrolledStudentCount)) {
			return false;
		}
		CourseEnrolledStudentCount other = (CourseEnrolledStudentCount) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	
	
}