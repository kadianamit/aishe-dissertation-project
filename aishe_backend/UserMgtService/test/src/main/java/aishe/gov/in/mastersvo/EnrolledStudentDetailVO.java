package aishe.gov.in.mastersvo;

import java.util.List;

public class EnrolledStudentDetailVO {
	
	private String level;
	private String programme;
	private String broadDisciplineGroupName;
	private String discipline;
	private String type;
	private String mode;
	private Integer year;
	private List<CategoryVO> category;
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
	public String getBroadDisciplineGroupName() {
		return broadDisciplineGroupName;
	}
	public void setBroadDisciplineGroupName(String broadDisciplineGroupName) {
		this.broadDisciplineGroupName = broadDisciplineGroupName;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public List<CategoryVO> getCategory() {
		return category;
	}
	public void setCategory(List<CategoryVO> category) {
		this.category = category;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	
}
