package com.nic.aishe.master.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "enrolled_foreign_student_count")
public class ForeignStudentEnrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="country_id",referencedColumnName = "country_id")
	private Country country;
	
	@ManyToOne
	@JoinColumn(name="level_id",referencedColumnName = "id")
	private CourseLevel levelId;

	@ManyToOne
	@JoinColumn(name="programme_id",referencedColumnName = "id")
	private ProgramName programmeId;

	@Column(name = "discipline")
	private String discipline;

	@ManyToOne
	@JoinColumn(name="broad_discipline_group_id",referencedColumnName = "id")
	private BroadName broadGroupId;
		
	@Column(name = "total")
	private Integer total;
	
	@Column(name = "girls")
	private Integer totalFemale;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CourseLevel getLevelId() {
		return levelId;
	}

	public void setLevelId(CourseLevel levelId) {
		this.levelId = levelId;
	}

	public ProgramName getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(ProgramName programmeId) {
		this.programmeId = programmeId;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public BroadName getBroadGroupId() {
		return broadGroupId;
	}

	public void setBroadGroupId(BroadName broadGroupId) {
		this.broadGroupId = broadGroupId;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalFemale() {
		return totalFemale;
	}

	public void setTotalFemale(Integer totalFemale) {
		this.totalFemale = totalFemale;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ForeignStudentEnrollment)) {
			return false;
		}
		ForeignStudentEnrollment other = (ForeignStudentEnrollment) obj;
		return Objects.equals(id, other.id);
	}


}