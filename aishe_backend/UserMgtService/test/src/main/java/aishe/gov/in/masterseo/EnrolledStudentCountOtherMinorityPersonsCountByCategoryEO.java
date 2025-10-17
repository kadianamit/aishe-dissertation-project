package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.enrolled_student_count_other_minority_persons_count_by_category")
public class EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO {
	
	@Id
	@Column(name = "enrolled_student_count_id")
	private Integer enrolledStudentCountId;
	@Column(name = "other_minority_persons_count_by_category_id")
	private OtherMinorityCategoryCountEO otherMinorityCategoryCountEO;
	
	public Integer getEnrolledStudentCountId() {
		return enrolledStudentCountId;
	}
	public void setEnrolledStudentCountId(Integer enrolledStudentCountId) {
		this.enrolledStudentCountId = enrolledStudentCountId;
	}
	public OtherMinorityCategoryCountEO getOtherMinorityCategoryCountEO() {
		return otherMinorityCategoryCountEO;
	}
	public void setOtherMinorityCategoryCountEO(OtherMinorityCategoryCountEO otherMinorityCategoryCountEO) {
		this.otherMinorityCategoryCountEO = otherMinorityCategoryCountEO;
	}
		

}
