package com.nic.aishe.master.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@Entity
@Table(name = "enrolled_student_count")
public class StudentEnrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name="course_mode_id")
	private CourseMode courseMode;
	
	@ManyToOne
	@JoinColumn(name="level_id",referencedColumnName = "id")
	private CourseLevel levelId;
	
	@ManyToOne
	@JoinColumn(name="programme_id",referencedColumnName = "id")
	private ProgramName programmeId;
	
	@Column(name = "discipline")
	private String discipline;
	
	@ManyToOne
	@JoinColumn(name="course_type_id",referencedColumnName = "id")
	private CourseType typeId;
	
	@Column(name = "year")
	private String year;
	
	@ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="broad_discipline_group_id",referencedColumnName = "id")
	private BroadName broadGroupId;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="count_by_category_id")
	private PersonCategoryCount personCategoryCount;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "enrolled_student_count_other_minority_persons_count_by_category", joinColumns = 
			@JoinColumn(name = "enrolled_student_count_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "other_minority_persons_count_by_category_id", referencedColumnName = "id"))
	private List<OtherMinorityCategoryCount> otherMinorityCategoryCounts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CourseMode getCourseMode() {
		return courseMode;
	}

	public void setCourseMode(CourseMode courseMode) {
		this.courseMode = courseMode;
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

	public CourseType getTypeId() {
		return typeId;
	}

	public void setTypeId(CourseType typeId) {
		this.typeId = typeId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BroadName getBroadGroupId() {
		return broadGroupId;
	}

	public void setBroadGroupId(BroadName broadGroupId) {
		this.broadGroupId = broadGroupId;
	}

	public PersonCategoryCount getPersonCategoryCount() {
		return personCategoryCount;
	}

	public void setPersonCategoryCount(PersonCategoryCount personCategoryCount) {
		this.personCategoryCount = personCategoryCount;
	}

	public List<OtherMinorityCategoryCount> listOtherMinorityCategoryCounts() {
		return otherMinorityCategoryCounts;
	}

	public void addOtherMinorityCategoryCounts(List<OtherMinorityCategoryCount> otherMinorityCategoryCounts) {
		this.otherMinorityCategoryCounts = otherMinorityCategoryCounts;
	}

//	public List<OtherMinorityCategoryCount> getOtherMinorityCategoryCounts() {
//		return otherMinorityCategoryCounts;
//	}

	public void setOtherMinorityCategoryCounts(List<OtherMinorityCategoryCount> otherMinorityCategoryCounts) {
		this.otherMinorityCategoryCounts = otherMinorityCategoryCounts;
	}
	
	
	
	
	
}

