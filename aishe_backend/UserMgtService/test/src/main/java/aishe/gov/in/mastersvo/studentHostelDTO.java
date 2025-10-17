package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class studentHostelDTO {

	
	@JsonProperty
	private Integer intakeCapacity;
	@JsonProperty
	private String name;
	@JsonProperty
	private Integer studentsResiding;	
	@JsonProperty
	private RefStudentHostelType refStudentHostelType;
}
