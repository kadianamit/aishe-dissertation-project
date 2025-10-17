package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_non_teaching_staff_type")
public class RefNonTeachingStaffType implements Serializable {

	@Id
	@Column(name = "id")
	private String staffTypeId;
	@Column(name = "staff_type")
	private String staffType;

	public String getStaffTypeId() {
		return staffTypeId;
	}
	public void setStaffTypeId(String staffTypeId) {
		this.staffTypeId = staffTypeId;
	}
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
}
