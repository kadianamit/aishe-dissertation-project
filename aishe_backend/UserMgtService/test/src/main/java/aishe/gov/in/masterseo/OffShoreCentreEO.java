package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "public.off_shore_center")

public class OffShoreCentreEO {

@Id
@Column(name = "id")
private Integer Id;
	
@Column(name = "name")
private String name;

@Column(name = "country_id")
private Integer countryId;

@Column(name = "mode_id")
private Integer studyModeId;

@Column(name = "total_no_of_student")
private Integer totalNoOfStudent;

@Column(name = "girls_no_of_student")
private Integer totalGirl;

public Integer getId() {
	return Id;
}

public void setId(Integer id) {
	Id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Integer getCountryId() {
	return countryId;
}

public void setCountryId(Integer countryId) {
	this.countryId = countryId;
}

public Integer getStudyModeId() {
	return studyModeId;
}

public void setStudyModeId(Integer studyModeId) {
	this.studyModeId = studyModeId;
}

public Integer getTotalNoOfStudent() {
	return totalNoOfStudent;
}

public void setTotalNoOfStudent(Integer totalNoOfStudent) {
	this.totalNoOfStudent = totalNoOfStudent;
}

public Integer getTotalGirl() {
	return totalGirl;
}

public void setTotalGirl(Integer totalGirl) {
	this.totalGirl = totalGirl;
}
}