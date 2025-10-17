package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_speciality")
public class RefSpecialityEO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id") 
	private String id;
	@Column(name = "speciality") 
	private String speciality;
	@Column(name = "valid_for_form1") 
	private Boolean valid_for_form1;
	@Column(name = "valid_for_form2") 
	private Boolean valid_for_form2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Boolean getValid_for_form1() {
		return valid_for_form1;
	}

	public void setValid_for_form1(Boolean valid_for_form1) {
		this.valid_for_form1 = valid_for_form1;
	}

	public Boolean getValid_for_form2() {
		return valid_for_form2;
	}

	public void setValid_for_form2(Boolean valid_for_form2) {
		this.valid_for_form2 = valid_for_form2;
	}

}
