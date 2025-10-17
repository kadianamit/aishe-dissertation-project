package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "public.placement_details")
public class PlacementDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "survey_year")
	private Integer survey_year;
	@Column(name = "aishe_code")
	private String  aishe_code;
	@Column(name = "placement_cell_present")
	private Boolean placement_cell_present;
	@Column(name = "no_of_students_placed_male")
	private Integer no_of_students_placed_male;
	@Column(name = "no_of_students_placed_female")
	private Integer no_of_students_placed_female;
	@Column(name = "no_of_students_placed")
	private Integer no_of_students_placed;
	@Column(name = "placed_graduates_median_salary")
	private Integer placed_graduates_median_salary;
	@Column(name = "no_of_students_selected_for_higher_studies_male")
	private Integer no_of_students_selected_for_higher_studies_male;
	@Column(name = "no_of_students_selected_for_higher_studies_female")
	private Integer no_of_students_selected_for_higher_studies_female;
	@Column(name = "no_of_students_selected_for_higher_studies")
	private Integer no_of_students_selected_for_higher_studies;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSurvey_year() {
		return survey_year;
	}
	public void setSurvey_year(Integer survey_year) {
		this.survey_year = survey_year;
	}
	public String getAishe_code() {
		return aishe_code;
	}
	public void setAishe_code(String aishe_code) {
		this.aishe_code = aishe_code;
	}
	public Boolean getPlacement_cell_present() {
		return placement_cell_present;
	}
	public void setPlacement_cell_present(Boolean placement_cell_present) {
		this.placement_cell_present = placement_cell_present;
	}
	public Integer getNo_of_students_placed() {
		return no_of_students_placed;
	}
	public void setNo_of_students_placed(Integer no_of_students_placed) {
		this.no_of_students_placed = no_of_students_placed;
	}
	public Integer getPlaced_graduates_median_salary() {
		return placed_graduates_median_salary;
	}
	public void setPlaced_graduates_median_salary(Integer placed_graduates_median_salary) {
		this.placed_graduates_median_salary = placed_graduates_median_salary;
	}
	public Integer getNo_of_students_selected_for_higher_studies() {
		return no_of_students_selected_for_higher_studies;
	}
	public void setNo_of_students_selected_for_higher_studies(Integer no_of_students_selected_for_higher_studies) {
		this.no_of_students_selected_for_higher_studies = no_of_students_selected_for_higher_studies;
	}
	public Integer getNo_of_students_placed_male() {
		return no_of_students_placed_male;
	}
	public void setNo_of_students_placed_male(Integer no_of_students_placed_male) {
		this.no_of_students_placed_male = no_of_students_placed_male;
	}
	public Integer getNo_of_students_placed_female() {
		return no_of_students_placed_female;
	}
	public void setNo_of_students_placed_female(Integer no_of_students_placed_female) {
		this.no_of_students_placed_female = no_of_students_placed_female;
	}
	public Integer getNo_of_students_selected_for_higher_studies_male() {
		return no_of_students_selected_for_higher_studies_male;
	}
	public void setNo_of_students_selected_for_higher_studies_male(
			Integer no_of_students_selected_for_higher_studies_male) {
		this.no_of_students_selected_for_higher_studies_male = no_of_students_selected_for_higher_studies_male;
	}
	public Integer getNo_of_students_selected_for_higher_studies_female() {
		return no_of_students_selected_for_higher_studies_female;
	}
	public void setNo_of_students_selected_for_higher_studies_female(
			Integer no_of_students_selected_for_higher_studies_female) {
		this.no_of_students_selected_for_higher_studies_female = no_of_students_selected_for_higher_studies_female;
	}
}
