package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enrolled_distance_student_university_count")
public class EnrolleDistanceStudentUniversityCountEO {
	
	@Id
	@Column(name = "enrolled_distance_student_university_id")
	private Integer enrolledDistanceStudentUniversityId;
	@Column(name = "enrolled_student_count_id")
	private Integer enrolledStudentCountId;
	public Integer getEnrolledDistanceStudentUniversityId() {
		return enrolledDistanceStudentUniversityId;
	}
	public void setEnrolledDistanceStudentUniversityId(Integer enrolledDistanceStudentUniversityId) {
		this.enrolledDistanceStudentUniversityId = enrolledDistanceStudentUniversityId;
	}
	public Integer getEnrolledStudentCountId() {
		return enrolledStudentCountId;
	}
	public void setEnrolledStudentCountId(Integer enrolledStudentCountId) {
		this.enrolledStudentCountId = enrolledStudentCountId;
	}
	

}
