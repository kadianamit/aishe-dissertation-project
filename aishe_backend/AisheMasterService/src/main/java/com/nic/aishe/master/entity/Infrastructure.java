package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity
@Table(name = "infrastructure")
public class Infrastructure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/*	@Transient
	private Integer surveyYear;
	@Transient
	private Integer aisheCode; */

	@Column(name="survey_year")
	private Integer surveyYear;
	@Column(name="aishe_code")
	private Integer aisheCode;
	@Column(name="institute_type")
	private String instituteType;
	@Column(name="playground")
	private Boolean playGround=false;
	@Column(name="auditorium")
	private Boolean auditorium=false;
	@Column(name="theatre")
	private Boolean theatre=false;
	@Column(name="library")
	private Boolean library=false;
	@Column(name="laboratory")
	private Boolean laboratory=false;
	@Column(name="conference_hall")
	private Boolean conferenceHall=false;
	@Column(name="health_center")
	private Boolean healthCenter=false;
	@Column(name="gymnasium_fitness_center")
	private Boolean gymCenter=false;
	@Column(name="indoor_stadium")
	private Boolean indoorStadium=false;
	@Column(name="common_room")
	private Boolean commonRoom=false;
	@Column(name="separate_room_for_girls")
	private Boolean girlsCommonRoom=false;
	@Column(name="computer_center")
	private Boolean compCenter=false;
	@Column(name="cafeteria")
	private Boolean cafeteria=false;
	@Column(name="guest_house")
	private Boolean guestHouse=false;
	@Column(name="no_of_playgrounds")
	private Integer playgroundCount=0;
	@Column(name="no_of_auditoriums")
	private Integer auditoriumCount=0;
	@Column(name="no_of_theatres")
	private Integer theatresCount=0;
	@Column(name="no_of_libraries")
	private Integer librarayCount=0;
	@Column(name="no_of_laboratories")
	private Integer laboratoryCount=0;
	@Column(name="no_of_conference_halls")
	private Integer conferenceHallCount=0;
	@Column(name="no_of_health_centers")
	private Integer healthCenterCount=0;
	@Column(name="no_of_gymnasim_fitness_centers")
	private Integer gymCount=0;
	@Column(name="no_of_indoor_stadiums")
	private Integer indoorStadiumCount=0;
	@Column(name="no_of_common_rooms")
	private Integer commonRoomCount=0; 
	@Column(name="no_of_computer_centers")
	private Integer computerCenterCount=0;
	@Column(name="no_of_cafeteria")
	private Integer cafeCount=0;
	@Column(name="no_of_guest_houses")
	private Integer guestHouseCount=0;
	@Column(name="no_of_separate_rooms_for_girls")
	private Integer girlRoomCount=0;
	@Column(name="solar_power_generation")
	private Boolean solarPower=false;  
	@Column(name="connectivity_nkn")
	private Boolean connectivityNkn=false;  
	@Column(name="connectivity_nmeict")
	private Boolean connectivityNmeict=false;  
	@Column(name="no_of_books")
	private Integer bookCount=0;
	@Column(name="no_of_journals")
	private Integer journalCount=0;
	@Column(name="campus_friendly")
	private Boolean campusFriendly=false; 
	@Column(name="grievance_redressal_mechanism")
	private Boolean grievanceRedressal=false; 
	@Column(name="vigilance_cell")
	private Boolean vigilanceCell=false; 
	@Column(name="opportunity_cell")
	private Boolean oppportunityCell=false; 
	@Column(name="separate_toilet_for_disabled_female")
	private Boolean disabledGirlToilet=false; 
	@Column(name="ramp_attached_to_classroom_library")
	private Boolean attachedRampClassLibrary=false; 
	@Column(name="sexual_harassment_cell")
	private Boolean sexualHarrassmentCell=false; 
	@Column(name="counselors_for_students")
	private Boolean studentCounselor=false; 
	@Column(name="clinic_first_aid_room")
	private Boolean firstAidRoom=false; 
	@Column(name="separate_toilet_for_girls")
	private Boolean girlsToilet=false; 
	@Column(name="skill_development_centre")
	private Boolean skillCenter=false; 
	@Column(name="self_defence_class_for_females")
	private Boolean girlSelfDefenceClass=false; 
	@Column(name="institution_disaster_management_facilities")
	private Boolean disasterMgmt=false; 
	@Column(name="capacity_building_and_training_aware_programme_conducted")
	private Boolean capacityBuilding=false; 
	@Column(name="vulnerability_assess_checks_made_during_year")
	private Boolean vulnerableAssessChecks=false; 
	@Column(name="any_mock_drill_rehearsal_programme_conducted")
	private Boolean mockDrillPrograms=false; 
	@Column(name="anti_ragging_cell")
	private Boolean antiRaggingCell=false;
	@Column(name = "hand_rails")
	private Boolean handRails;
	@Column(name = "total_classrooms_and_seminar_halls")
	private Integer totalClassroomAndSeminarHalls;
	@Column(name = "total_computers_for_academic_work")
	private Integer totalComputersForAcademicWork;
	@Column(name = "incubation_centres")
	private Integer incubationCentres;
	@Column(name = "no_of_toilets_total")
	private Integer noOfToiletsTotal;
	@Column(name = "no_of_toilets_girls")
	private Integer noOfToiletsGirls;
	@Column(name = "no_of_toilets_disabled_females")
	private Integer noOfToiletsDisabledFemales;
	@Column(name = "whether_institution_has_iqac")
	private Boolean whetherInstitutionHasIqac;
	@Column(name = "date_of_iqac_establishment")
	private Date date_of_iqac_establishment;
	@Column(name = "iqac_contact_details")
	private String iqacContactDetails;
	@Column(name = "no_of_toilets_disabled_males")
	private Integer noOfToiletsDisabledMales;
	@Transient
	private String dateOfIqacEstablishment;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getPlayGround() {
		return playGround;
	}
	public void setPlayGround(Boolean playGround) {
		this.playGround = playGround;
	}
	public Boolean getAuditorium() {
		return auditorium;
	}
	public void setAuditorium(Boolean auditorium) {
		this.auditorium = auditorium;
	}
	public Boolean getTheatre() {
		return theatre;
	}
	public void setTheatre(Boolean theatre) {
		this.theatre = theatre;
	}
	public Boolean getLibrary() {
		return library;
	}
	public void setLibrary(Boolean library) {
		this.library = library;
	}
	public Boolean getLaboratory() {
		return laboratory;
	}
	public void setLaboratory(Boolean laboratory) {
		this.laboratory = laboratory;
	}
	public Boolean getConferenceHall() {
		return conferenceHall;
	}
	public void setConferenceHall(Boolean conferenceHall) {
		this.conferenceHall = conferenceHall;
	}
	public Boolean getHealthCenter() {
		return healthCenter;
	}
	public void setHealthCenter(Boolean healthCenter) {
		this.healthCenter = healthCenter;
	}
	public Boolean getGymCenter() {
		return gymCenter;
	}
	public void setGymCenter(Boolean gymCenter) {
		this.gymCenter = gymCenter;
	}
	public Boolean getIndoorStadium() {
		return indoorStadium;
	}
	public void setIndoorStadium(Boolean indoorStadium) {
		this.indoorStadium = indoorStadium;
	}
	public Boolean getCommonRoom() {
		return commonRoom;
	}
	public void setCommonRoom(Boolean commonRoom) {
		this.commonRoom = commonRoom;
	}
	public Boolean getCompCenter() {
		return compCenter;
	}
	public void setCompCenter(Boolean compCenter) {
		this.compCenter = compCenter;
	}
	public Boolean getCafeteria() {
		return cafeteria;
	}
	public void setCafeteria(Boolean cafeteria) {
		this.cafeteria = cafeteria;
	}
	public Boolean getGuestHouse() {
		return guestHouse;
	}
	public void setGuestHouse(Boolean guestHouse) {
		this.guestHouse = guestHouse;
	}
	public Integer getPlaygroundCount() {
		return playgroundCount;
	}
	public void setPlaygroundCount(Integer playgroundCount) {
		this.playgroundCount = playgroundCount;
	}
	public Integer getAuditoriumCount() {
		return auditoriumCount;
	}
	public void setAuditoriumCount(Integer auditoriumCount) {
		this.auditoriumCount = auditoriumCount;
	}
	public Integer getTheatresCount() {
		return theatresCount;
	}
	public void setTheatresCount(Integer theatresCount) {
		this.theatresCount = theatresCount;
	}
	public Integer getLibrarayCount() {
		return librarayCount;
	}
	public void setLibrarayCount(Integer librarayCount) {
		this.librarayCount = librarayCount;
	}
	public Integer getLaboratoryCount() {
		return laboratoryCount;
	}
	public void setLaboratoryCount(Integer laboratoryCount) {
		this.laboratoryCount = laboratoryCount;
	}
	public Integer getConferenceHallCount() {
		return conferenceHallCount;
	}
	public void setConferenceHallCount(Integer conferenceHallCount) {
		this.conferenceHallCount = conferenceHallCount;
	}
	public Integer getHealthCenterCount() {
		return healthCenterCount;
	}
	public void setHealthCenterCount(Integer healthCenterCount) {
		this.healthCenterCount = healthCenterCount;
	}
	public Integer getGymCount() {
		return gymCount;
	}
	public void setGymCount(Integer gymCount) {
		this.gymCount = gymCount;
	}
	public Integer getIndoorStadiumCount() {
		return indoorStadiumCount;
	}
	public void setIndoorStadiumCount(Integer indoorStadiumCount) {
		this.indoorStadiumCount = indoorStadiumCount;
	}
	public Integer getCommonRoomCount() {
		return commonRoomCount;
	}
	public void setCommonRoomCount(Integer commonRoomCount) {
		this.commonRoomCount = commonRoomCount;
	}
	public Integer getComputerCenterCount() {
		return computerCenterCount;
	}
	public void setComputerCenterCount(Integer computerCenterCount) {
		this.computerCenterCount = computerCenterCount;
	}
	public Integer getCafeCount() {
		return cafeCount;
	}
	public void setCafeCount(Integer cafeCount) {
		this.cafeCount = cafeCount;
	}
	public Integer getGuestHouseCount() {
		return guestHouseCount;
	}
	public void setGuestHouseCount(Integer guestHouseCount) {
		this.guestHouseCount = guestHouseCount;
	}
	public Integer getGirlRoomCount() {
		return girlRoomCount;
	}
	public void setGirlRoomCount(Integer girlRoomCount) {
		this.girlRoomCount = girlRoomCount;
	}
	public Boolean getSolarPower() {
		return solarPower;
	}
	public void setSolarPower(Boolean solarPower) {
		this.solarPower = solarPower;
	}
	public Boolean getConnectivityNkn() {
		return connectivityNkn;
	}
	public void setConnectivityNkn(Boolean connectivityNkn) {
		this.connectivityNkn = connectivityNkn;
	}
	public Boolean getConnectivityNmeict() {
		return connectivityNmeict;
	}
	public void setConnectivityNmeict(Boolean connectivityNmeict) {
		this.connectivityNmeict = connectivityNmeict;
	}
	public Integer getBookCount() {
		return bookCount;
	}
	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}
	
	public Boolean getCampusFriendly() {
		return campusFriendly;
	}
	public void setCampusFriendly(Boolean campusFriendly) {
		this.campusFriendly = campusFriendly;
	}
	public Boolean getGrievanceRedressal() {
		return grievanceRedressal;
	}
	public void setGrievanceRedressal(Boolean grievanceRedressal) {
		this.grievanceRedressal = grievanceRedressal;
	}
	public Boolean getVigilanceCell() {
		return vigilanceCell;
	}
	public void setVigilanceCell(Boolean vigilanceCell) {
		this.vigilanceCell = vigilanceCell;
	}
	public Boolean getOppportunityCell() {
		return oppportunityCell;
	}
	public void setOppportunityCell(Boolean oppportunityCell) {
		this.oppportunityCell = oppportunityCell;
	}
	public Boolean getDisabledGirlToilet() {
		return disabledGirlToilet;
	}
	public void setDisabledGirlToilet(Boolean disabledGirlToilet) {
		this.disabledGirlToilet = disabledGirlToilet;
	}
	public Boolean getAttachedRampClassLibrary() {
		return attachedRampClassLibrary;
	}
	public void setAttachedRampClassLibrary(Boolean attachedRampClassLibrary) {
		this.attachedRampClassLibrary = attachedRampClassLibrary;
	}
	public Boolean getSexualHarrassmentCell() {
		return sexualHarrassmentCell;
	}
	public void setSexualHarrassmentCell(Boolean sexualHarrassmentCell) {
		this.sexualHarrassmentCell = sexualHarrassmentCell;
	}
	public Boolean getStudentCounselor() {
		return studentCounselor;
	}
	public void setStudentCounselor(Boolean studentCounselor) {
		this.studentCounselor = studentCounselor;
	}
	public Boolean getFirstAidRoom() {
		return firstAidRoom;
	}
	public void setFirstAidRoom(Boolean firstAidRoom) {
		this.firstAidRoom = firstAidRoom;
	}
	public Boolean getGirlsToilet() {
		return girlsToilet;
	}
	public void setGirlsToilet(Boolean girlsToilet) {
		this.girlsToilet = girlsToilet;
	}
	public Boolean getSkillCenter() {
		return skillCenter;
	}
	public void setSkillCenter(Boolean skillCenter) {
		this.skillCenter = skillCenter;
	}
	public Boolean getGirlSelfDefenceClass() {
		return girlSelfDefenceClass;
	}
	public void setGirlSelfDefenceClass(Boolean girlSelfDefenceClass) {
		this.girlSelfDefenceClass = girlSelfDefenceClass;
	}
	public Boolean getDisasterMgmt() {
		return disasterMgmt;
	}
	public void setDisasterMgmt(Boolean disasterMgmt) {
		this.disasterMgmt = disasterMgmt;
	}
	public Boolean getCapacityBuilding() {
		return capacityBuilding;
	}
	public void setCapacityBuilding(Boolean capacityBuilding) {
		this.capacityBuilding = capacityBuilding;
	}
	public Boolean getVulnerableAssessChecks() {
		return vulnerableAssessChecks;
	}
	public void setVulnerableAssessChecks(Boolean vulnerableAssessChecks) {
		this.vulnerableAssessChecks = vulnerableAssessChecks;
	}
	public Boolean getMockDrillPrograms() {
		return mockDrillPrograms;
	}
	public void setMockDrillPrograms(Boolean mockDrillPrograms) {
		this.mockDrillPrograms = mockDrillPrograms;
	}
	public Boolean getAntiRaggingCell() {
		return antiRaggingCell;
	}
	public void setAntiRaggingCell(Boolean antiRaggingCell) {
		this.antiRaggingCell = antiRaggingCell;
	}
	public Boolean getGirlsCommonRoom() {
		return girlsCommonRoom;
	}
	public void setGirlsCommonRoom(Boolean girlsCommonRoom) {
		this.girlsCommonRoom = girlsCommonRoom;
	}
	public Integer getJournalCount() {
		return journalCount;
	}
	public void setJournalCount(Integer journalCount) {
		this.journalCount = journalCount;
	}
	public Integer getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(Integer aisheCode) {
		this.aisheCode = aisheCode;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

	public Boolean getHandRails() {
		return handRails;
	}

	public void setHandRails(Boolean handRails) {
		this.handRails = handRails;
	}

	public Integer getTotalClassroomAndSeminarHalls() {
		return totalClassroomAndSeminarHalls;
	}

	public void setTotalClassroomAndSeminarHalls(Integer totalClassroomAndSeminarHalls) {
		this.totalClassroomAndSeminarHalls = totalClassroomAndSeminarHalls;
	}

	public Integer getTotalComputersForAcademicWork() {
		return totalComputersForAcademicWork;
	}

	public void setTotalComputersForAcademicWork(Integer totalComputersForAcademicWork) {
		this.totalComputersForAcademicWork = totalComputersForAcademicWork;
	}

	public Integer getIncubationCentres() {
		return incubationCentres;
	}

	public void setIncubationCentres(Integer incubationCentres) {
		this.incubationCentres = incubationCentres;
	}

	public Integer getNoOfToiletsTotal() {
		return noOfToiletsTotal;
	}

	public void setNoOfToiletsTotal(Integer noOfToiletsTotal) {
		this.noOfToiletsTotal = noOfToiletsTotal;
	}

	public Integer getNoOfToiletsGirls() {
		return noOfToiletsGirls;
	}

	public void setNoOfToiletsGirls(Integer noOfToiletsGirls) {
		this.noOfToiletsGirls = noOfToiletsGirls;
	}

	public Integer getNoOfToiletsDisabledFemales() {
		return noOfToiletsDisabledFemales;
	}

	public void setNoOfToiletsDisabledFemales(Integer noOfToiletsDisabledFemales) {
		this.noOfToiletsDisabledFemales = noOfToiletsDisabledFemales;
	}

	public Boolean getWhetherInstitutionHasIqac() {
		return whetherInstitutionHasIqac;
	}

	public void setWhetherInstitutionHasIqac(Boolean whetherInstitutionHasIqac) {
		this.whetherInstitutionHasIqac = whetherInstitutionHasIqac;
	}

	public Date getDate_of_iqac_establishment() {
		return date_of_iqac_establishment;
	}

	public void setDate_of_iqac_establishment(Date date_of_iqac_establishment) {
		this.date_of_iqac_establishment = date_of_iqac_establishment;
	}

	public String getIqacContactDetails() {
		return iqacContactDetails;
	}

	public void setIqacContactDetails(String iqacContactDetails) {
		this.iqacContactDetails = iqacContactDetails;
	}

	public String getDateOfIqacEstablishment() {
		return dateOfIqacEstablishment;
	}
	public void setDateOfIqacEstablishment(String dateOfIqacEstablishment) {
		this.dateOfIqacEstablishment = dateOfIqacEstablishment;
	}
	
	public Integer getNoOfToiletsDisabledMales() {
		return noOfToiletsDisabledMales;
	}
	public void setNoOfToiletsDisabledMales(Integer noOfToiletsDisabledMales) {
		this.noOfToiletsDisabledMales = noOfToiletsDisabledMales;
	}
	public String getInstituteType() {
		return instituteType;
	}
	public void setInstituteType(String instituteType) {
		this.instituteType = instituteType;
	}
	@Override
	public String toString() {
		return "Infrastructure [id=" + id + ", surveyYear=" + surveyYear + ", aisheCode=" + aisheCode + ", playGround="
				+ playGround + ", auditorium=" + auditorium + ", theatre=" + theatre + ", library=" + library
				+ ", laboratory=" + laboratory + ", conferenceHall=" + conferenceHall + ", healthCenter=" + healthCenter
				+ ", gymCenter=" + gymCenter + ", indoorStadium=" + indoorStadium + ", commonRoom=" + commonRoom
				+ ", girlsCommonRoom=" + girlsCommonRoom + ", compCenter=" + compCenter + ", cafeteria=" + cafeteria
				+ ", guestHouse=" + guestHouse + ", playgroundCount=" + playgroundCount + ", auditoriumCount="
				+ auditoriumCount + ", theatresCount=" + theatresCount + ", librarayCount=" + librarayCount
				+ ", laboratoryCount=" + laboratoryCount + ", conferenceHallCount=" + conferenceHallCount
				+ ", healthCenterCount=" + healthCenterCount + ", gymCount=" + gymCount + ", indoorStadiumCount="
				+ indoorStadiumCount + ", commonRoomCount=" + commonRoomCount + ", computerCenterCount="
				+ computerCenterCount + ", cafeCount=" + cafeCount + ", guestHouseCount=" + guestHouseCount
				+ ", girlRoomCount=" + girlRoomCount + ", solarPower=" + solarPower + ", connectivityNkn="
				+ connectivityNkn + ", connectivityNmeict=" + connectivityNmeict + ", bookCount=" + bookCount
				+ ", journalCount=" + journalCount + ", campusFriendly=" + campusFriendly + ", grievanceRedressal="
				+ grievanceRedressal + ", vigilanceCell=" + vigilanceCell + ", oppportunityCell=" + oppportunityCell
				+ ", disabledGirlToilet=" + disabledGirlToilet + ", attachedRampClassLibrary="
				+ attachedRampClassLibrary + ", sexualHarrassmentCell=" + sexualHarrassmentCell + ", studentCounselor="
				+ studentCounselor + ", firstAidRoom=" + firstAidRoom + ", girlsToilet=" + girlsToilet
				+ ", skillCenter=" + skillCenter + ", girlSelfDefenceClass=" + girlSelfDefenceClass + ", disasterMgmt="
				+ disasterMgmt + ", capacityBuilding=" + capacityBuilding + ", vulnerableAssessChecks="
				+ vulnerableAssessChecks + ", mockDrillPrograms=" + mockDrillPrograms + ", antiRaggingCell="
				+ antiRaggingCell + "]";
	}

	
	
	
}