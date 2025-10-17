package aishe.gov.in.mastersvo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeachingStaffDetailsVO {
	@JsonProperty
   	private String countryName;
   	@JsonProperty
    private String passportNumber;
    @JsonProperty
    private String school;
    @JsonProperty
    private String departmentName;
    @JsonProperty
    private String nameOfTheEmployee;
    @JsonProperty
    private String designation;
    @JsonProperty
    private String gender;
    @JsonProperty
    private Date dob;
    @JsonProperty
    private String socialCategory;
    @JsonProperty
    private String religousCommunity;
    @JsonProperty
    private String pwd;
    @JsonProperty
   private String natureOfAppointment;
   @JsonProperty
    private String selectionMode;
    @JsonProperty
    private Date doj;
    @JsonProperty
    private String dateOfJoinfteachingProfession;
    @JsonProperty
    private String highestQualification;
    @JsonProperty
    private String eligibilityQualification;
    @JsonProperty
    private String broadDisciplineCategory;
    @JsonProperty
    private String broadDisciplineGroup;
    @JsonProperty
    private String yearExclusivilyInother;
    @JsonProperty
    private String jobStatus;
    @JsonProperty
    private String dateOfChangeStatus;
}
