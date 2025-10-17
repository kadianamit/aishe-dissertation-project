package com.nic.aishe.master.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="ref_course_level")
public class CourseLevelNew {
	
	@Id
	private String id;

	private String name;
	private String level;
	@Transient
	private List<ProgramNameNew> programme;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List<ProgramNameNew> getProgramme() {
		return programme;
	}
	public void setProgramme(List<ProgramNameNew> programme) {
		this.programme = programme;
	}
	
	
}
