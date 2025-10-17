package com.nic.aishe.master.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="course_enrolled_foreign_student_count")
public class CourseEnrolledForeignStudentCount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Transient
	private Integer year;
	
	@OneToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@OneToOne
	@JoinColumn(name="enrolled_foreign_student_count_id")
	private ForeignStudentEnrollment foreignStudentEnrollment;

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

	public ForeignStudentEnrollment getForeignStudentEnrollment() {
		return foreignStudentEnrollment;
	}

	public void setForeignStudentEnrollment(ForeignStudentEnrollment foreignStudentEnrollment) {
		this.foreignStudentEnrollment = foreignStudentEnrollment;
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
		if (!(obj instanceof CourseEnrolledForeignStudentCount)) {
			return false;
		}
		CourseEnrolledForeignStudentCount other = (CourseEnrolledForeignStudentCount) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	
	
	
	
}