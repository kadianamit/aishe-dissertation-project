package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ref_programme")
public class RefProgramme {

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "programme")
	private String programme;
	@Column(name = "course_level_id")
	private String courseLevelId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public String getCourseLevelId() {
		return courseLevelId;
	}
	public void setCourseLevelId(String courseLevelId) {
		this.courseLevelId = courseLevelId;
	}
	
	
}
