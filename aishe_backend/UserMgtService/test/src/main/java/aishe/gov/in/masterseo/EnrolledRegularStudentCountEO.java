package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enrolled_regular_student_count")
public class EnrolledRegularStudentCountEO {
	
	@Id
	@Column(name = "enrolled_regular_student_id")
	private Integer enrolledRegularStudentId;
	@Column(name = "enrolled_student_count_id")
	private Integer enrolledStudentCountId;
	public Integer getEnrolledRegularStudentId() {
		return enrolledRegularStudentId;
	}
	public void setEnrolledRegularStudentId(Integer enrolledRegularStudentId) {
		this.enrolledRegularStudentId = enrolledRegularStudentId;
	}
	public Integer getEnrolledStudentCountId() {
		return enrolledStudentCountId;
	}
	public void setEnrolledStudentCountId(Integer enrolledStudentCountId) {
		this.enrolledStudentCountId = enrolledStudentCountId;
	}
	
}
