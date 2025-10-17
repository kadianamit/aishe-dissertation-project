package aishe.gov.in.masterseo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "enrolled_student_count")
public class EnrolledStudentCountEO {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "discipline")
	private String discipline;
	@Column(name = "year")
	private String year;
	@ManyToOne
	@JoinColumn(name = "course_mode_id", insertable = false, updatable = false)
	private RefCourseMode courseMode;	
	@ManyToOne
	@JoinColumn(name = "level_id", insertable = false, updatable = false)
	private RefCourseLevel levelId;
	@ManyToOne
	@JoinColumn(name = "programme_id", insertable = false, updatable = false)
	private RefProgramme programmeId;	
	@ManyToOne
	@JoinColumn(name = "broad_discipline_group_id", insertable = false, updatable = false)
	private RefBroadDisciplineGroup broadGroupId;	
	@ManyToOne
	@JoinColumn(name = "course_type_id", insertable = false, updatable = false)
	private RefCourseType typeId;
	@ManyToOne
	@JoinColumn(name = "count_by_category_id", insertable = false, updatable = false)
	private PersonsCountByCategory personCategoryCount;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "enrolled_student_count_other_minority_persons_count_by_category", joinColumns = 
			@JoinColumn(name = "enrolled_student_count_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "other_minority_persons_count_by_category_id", referencedColumnName = "id"))
	private List<OtherMinorityCategoryCountEO> otherMinorityCategoryCounts;

	
	public List<OtherMinorityCategoryCountEO> getOtherMinorityCategoryCounts() {
		return otherMinorityCategoryCounts;
	}
	public void setOtherMinorityCategoryCounts(List<OtherMinorityCategoryCountEO> otherMinorityCategoryCounts) {
		this.otherMinorityCategoryCounts = otherMinorityCategoryCounts;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public RefCourseMode getCourseMode() {
		return courseMode;
	}
	public void setCourseMode(RefCourseMode courseMode) {
		this.courseMode = courseMode;
	}
	public RefCourseLevel getLevelId() {
		return levelId;
	}
	public void setLevelId(RefCourseLevel levelId) {
		this.levelId = levelId;
	}
	public RefProgramme getProgrammeId() {
		return programmeId;
	}
	public void setProgrammeId(RefProgramme programmeId) {
		this.programmeId = programmeId;
	}
	public RefBroadDisciplineGroup getBroadGroupId() {
		return broadGroupId;
	}
	public void setBroadGroupId(RefBroadDisciplineGroup broadGroupId) {
		this.broadGroupId = broadGroupId;
	}
	public RefCourseType getTypeId() {
		return typeId;
	}
	public void setTypeId(RefCourseType typeId) {
		this.typeId = typeId;
	}
	public PersonsCountByCategory getPersonCategoryCount() {
		return personCategoryCount;
	}
	public void setPersonCategoryCount(PersonsCountByCategory personCategoryCount) {
		this.personCategoryCount = personCategoryCount;
	}
	
	
}
