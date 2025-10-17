package aishe.gov.in.masterseo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="teaching_staff_information_details")
public class TeachingStaffDetailsEO {
	@Id
	private Integer id;
	@Column(name = "country_name")
   	private String countryName;
    @Column(name ="passport_number")
    private String passportNumber;
    @Column(name ="school")
    private String school;
    @Column(name ="department_name")
    private String departmentName;
    @Column(name ="name_of_the_employee")
    private String nameOfTheEmployee;
    @Column(name ="designation")
    private String designation;
    @Column(name ="gender")
    private String gender;
    @Column(name ="dob")
    private Date dob;
    @Column(name ="social_category")
    private String socialCategory;
    @Column(name ="religous_community")
    private String religousCommunity;
    @Column(name ="pwd")
    private String pwd;
    @Column(name ="nature_of_appointment")
   private String natureOfAppointment;
    @Column(name ="selection_modet")
    private String selectionMode;
   @Column(name ="doj")
    private Date doj;
    @Column(name ="date_of_joinfteaching_profession")
    private String dateOfJoinfteachingProfession;
    @Column(name ="highest_qualification")
    private String highestQualification;
    @Column(name ="eligibility_qualification")
    private String eligibilityQualification;
    @Column(name ="broad_discipline_category")
    private String broadDisciplineCategory;
    @Column(name ="Broad_discipline_group")
    private String broadDisciplineGroup;
    @Column(name ="year_exclusivily_inother")
    private String yearExclusivilyInother;
    @Column(name ="job_status")
    private String jobStatus;
    @Column(name ="date_of_change_status")
    private String dateOfChangeStatus;
    
}
