package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExaminationResultDTO {
	
	@JsonProperty
	private String level;
	@JsonProperty
	private String programme;
	@JsonProperty
	private String broadDisciplineGroup;
	@JsonProperty
	private String discipline;		
	@JsonProperty
	private Integer	appearedTotal;
	@JsonProperty
	private Integer appearedFemale;
	@JsonProperty
	private Integer passedTotal;
	@JsonProperty
	private Integer passedFemale;
	@JsonProperty
	private Integer firstClassPassedTotal;
	@JsonProperty
	private Integer firstClassPassedFemale;
	@JsonProperty
	private String courseMode;
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getProgramme() {
		return programme;
	}
	public void setProgramme(String programme) {
		this.programme = programme;
	}
	public String getBroadDisciplineGroup() {
		return broadDisciplineGroup;
	}
	public void setBroadDisciplineGroup(String broadDisciplineGroup) {
		this.broadDisciplineGroup = broadDisciplineGroup;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public Integer getAppearedTotal() {
		return appearedTotal;
	}
	public void setAppearedTotal(Integer appearedTotal) {
		this.appearedTotal = appearedTotal;
	}
	public Integer getAppearedFemale() {
		return appearedFemale;
	}
	public void setAppearedFemale(Integer appearedFemale) {
		this.appearedFemale = appearedFemale;
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
	public Integer getFirstClassPassedTotal() {
		return firstClassPassedTotal;
	}
	public void setFirstClassPassedTotal(Integer firstClassPassedTotal) {
		this.firstClassPassedTotal = firstClassPassedTotal;
	}
	public Integer getFirstClassPassedFemale() {
		return firstClassPassedFemale;
	}
	public void setFirstClassPassedFemale(Integer firstClassPassedFemale) {
		this.firstClassPassedFemale = firstClassPassedFemale;
	}
	public String getCourseMode() {
		return courseMode;
	}
	public void setCourseMode(String courseMode) {
		this.courseMode = courseMode;
	}	
	
	
}
