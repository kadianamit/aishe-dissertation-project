package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_non_teaching_staff_group")
public class RefNonTeachingStaffGroup implements Serializable{


	@Id
	@Column(name = "id")
	private String staffId;
	@Column(name = "staff_group")
	private String staffGroup;

	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStaffGroup() {
		return staffGroup;
	}
	public void setStaffGroup(String staffGroup) {
		this.staffGroup = staffGroup;
	}





}
