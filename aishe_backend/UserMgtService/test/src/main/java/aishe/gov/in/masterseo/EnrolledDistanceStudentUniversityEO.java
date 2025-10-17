package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enrolled_distance_student_university")
public class EnrolledDistanceStudentUniversityEO {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "regional_center_name")
	private String regionalCenterName;
	@ManyToOne
	@JoinColumn(name = "state_code", insertable = false, updatable = false)
	private RefState state;
	@ManyToOne
	@JoinColumn(name = "district_code", insertable = false, updatable = false)
	private RefDistrict district;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRegionalCenterName() {
		return regionalCenterName;
	}
	public void setRegionalCenterName(String regionalCenterName) {
		this.regionalCenterName = regionalCenterName;
	}
	public RefState getState() {
		return state;
	}
	public void setState(RefState state) {
		this.state = state;
	}
	public RefDistrict getDistrict() {
		return district;
	}
	public void setDistrict(RefDistrict district) {
		this.district = district;
	}
		

}
