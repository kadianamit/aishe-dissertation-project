package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "course_enrolled_student_count")
public class CourseEnrolledStudentCountEO {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	@ManyToOne
	@JoinColumn(name = "enrolled_student_count_id")
	private EnrolledStudentCountEO enrolledStudentCount;
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
	public EnrolledStudentCountEO getEnrolledStudentCount() {
		return enrolledStudentCount;
	}
	public void setEnrolledStudentCount(EnrolledStudentCountEO enrolledStudentCount) {
		this.enrolledStudentCount = enrolledStudentCount;
	}
	
	

}
