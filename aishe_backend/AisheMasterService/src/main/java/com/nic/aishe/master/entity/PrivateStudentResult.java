package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "private_students_result")
public class PrivateStudentResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "level_id", referencedColumnName = "id",nullable = false)
	private CourseLevel levelId;

	@ManyToOne
	@JoinColumn(name = "programme_id", referencedColumnName = "id",nullable = false)
	private ProgramName programmeId;

	@Column(name = "discipline",nullable = false)
	private String discipline;

	@ManyToOne
	@JoinColumn(name = "broad_discipline_group_id", referencedColumnName = "id",nullable = false)
	private BroadName broadGroupId;
	
	@ManyToOne
	@JoinColumn(name = "broad_discipline_group_category_id", referencedColumnName = "id",nullable = false)
	private BroadCategory broadCategory;

	@Column(name = "appeared_total")
	private Integer appearTotal;

	@Column(name = "appeared_female")
	private Integer appearFemale;

	@Column(name = "passed_total",nullable = false)
	private Integer passedTotal;

	@Column(name = "passed_female",nullable = false)
	private Integer passedFemale;

	@Column(name = "first_class_passed_total")
	private Integer firstTotal;

	@Column(name = "first_class_passed_female")
	private Integer firstFemale;

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

	public Integer getAppearTotal() {
		return appearTotal;
	}

	public void setAppearTotal(Integer appearTotal) {
		this.appearTotal = appearTotal;
	}

	public Integer getAppearFemale() {
		return appearFemale;
	}

	public void setAppearFemale(Integer appearFemale) {
		this.appearFemale = appearFemale;
	}

	public Integer getPassedTotal() {
		return passedTotal;
	}

	public void setPassedTotal(Integer passedTotal) {
		this.passedTotal = passedTotal;
	}

	public Integer getPassedFemale() {
		return passedFemale;
	}

	public void setPassedFemale(Integer passedFemale) {
		this.passedFemale = passedFemale;
	}

	public Integer getFirstTotal() {
		return firstTotal;
	}

	public void setFirstTotal(Integer firstTotal) {
		this.firstTotal = firstTotal;
	}

	public Integer getFirstFemale() {
		return firstFemale;
	}

	public void setFirstFemale(Integer firstFemale) {
		this.firstFemale = firstFemale;
	}
	
	

}