package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DistanceProgrammDetailVO {
	
	@JsonProperty
	private String level;
	@JsonProperty
	private String nameOfProgram;
	@JsonProperty
	private String disciplineSubject;
	@JsonProperty
	private String broadDisciplineGroupCategory;
	@JsonProperty
	private String broadDisciplineGroupName;
	@JsonProperty
	private Integer courseDurationYear;
	@JsonProperty
	private Integer courseDurationMonth;
	@JsonProperty
	private String type;
	
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getNameOfProgram() {
		return nameOfProgram;
	}
	public void setNameOfProgram(String nameOfProgram) {
		this.nameOfProgram = nameOfProgram;
	}
	public String getDisciplineSubject() {
		return disciplineSubject;
	}
	public void setDisciplineSubject(String disciplineSubject) {
		this.disciplineSubject = disciplineSubject;
	}
	public String getBroadDisciplineGroupCategory() {
		return broadDisciplineGroupCategory;
	}
	public void setBroadDisciplineGroupCategory(String broadDisciplineGroupCategory) {
		this.broadDisciplineGroupCategory = broadDisciplineGroupCategory;
	}
	public String getBroadDisciplineGroupName() {
		return broadDisciplineGroupName;
	}
	public void setBroadDisciplineGroupName(String broadDisciplineGroupName) {
		this.broadDisciplineGroupName = broadDisciplineGroupName;
	}
	public Integer getCourseDurationYear() {
		return courseDurationYear;
	}
	public void setCourseDurationYear(Integer courseDurationYear) {
		this.courseDurationYear = courseDurationYear;
	}
	public Integer getCourseDurationMonth() {
		return courseDurationMonth;
	}
	public void setCourseDurationMonth(Integer courseDurationMonth) {
		this.courseDurationMonth = courseDurationMonth;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
