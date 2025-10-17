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
import javax.persistence.Transient;

@Entity
@Table(name = "examination_result")
public class ExamResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
	private Integer year;

	@ManyToOne
	@JoinColumn(name = "course_mode_id", referencedColumnName = "id")
	private CourseMode modeId;

	@ManyToOne
	@JoinColumn(name = "course_level_id", referencedColumnName = "id")
	private CourseLevel levelId;

	@ManyToOne
	@JoinColumn(name = "programme_id", referencedColumnName = "id")
	private ProgramName programmeId;

	@Column(name = "discipline")
	private String discipline;

	@ManyToOne
	@JoinColumn(name = "broad_discipline_group_id", referencedColumnName = "id")
	private BroadName broadGroupId;

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
	
	//new changes
		@Column(name = "appeared_count_by_category_id")
		private Integer appeared_count_by_category_id;
		@Column(name = "passed_count_by_category_id")
		private Integer passed_count_by_category_id;
		@Column(name = "first_class_passed_count_by_category_id")
		private Integer first_class_passed_count_by_category_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CourseMode getModeId() {
		return modeId;
	}

	public void setModeId(CourseMode modeId) {
		this.modeId = modeId;
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ExamResult)) {
			return false;
		}
		ExamResult other = (ExamResult) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getAppeared_count_by_category_id() {
		return appeared_count_by_category_id;
	}

	public void setAppeared_count_by_category_id(Integer appeared_count_by_category_id) {
		this.appeared_count_by_category_id = appeared_count_by_category_id;
	}

	public Integer getPassed_count_by_category_id() {
		return passed_count_by_category_id;
	}

	public void setPassed_count_by_category_id(Integer passed_count_by_category_id) {
		this.passed_count_by_category_id = passed_count_by_category_id;
	}

	public Integer getFirst_class_passed_count_by_category_id() {
		return first_class_passed_count_by_category_id;
	}

	public void setFirst_class_passed_count_by_category_id(Integer first_class_passed_count_by_category_id) {
		this.first_class_passed_count_by_category_id = first_class_passed_count_by_category_id;
	}
	
	

}