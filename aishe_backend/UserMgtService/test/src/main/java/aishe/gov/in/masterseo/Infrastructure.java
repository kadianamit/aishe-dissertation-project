package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "public.infrastructure")
public class Infrastructure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Transient
	private Integer year;
	@Transient
	private String aisheCode;
	@Column(name="playground")
	private Boolean playGround;
	@Column(name="auditorium")
	private Boolean auditorium;
	@Column(name="theatre")
	private Boolean theatre;
	@Column(name="library")
	private Boolean library;
	@Column(name="laboratory")
	private Boolean laboratory;
	@Column(name="conference_hall")
	private Boolean conferenceHall;
	@Column(name="health_center")
	private Boolean healthCenter;
	@Column(name="gymnasium_fitness_center")
	private Boolean gymCenter;
	@Column(name="indoor_stadium")
	private Boolean indoorStadium;
	@Column(name="common_room")
	private Boolean commonRoom;
	@Column(name="separate_room_for_girls")
	private Boolean girlsCommonRoom;
	@Column(name="computer_center")
	private Boolean compCenter;
	@Column(name="cafeteria")
	private Boolean cafeteria;
	@Column(name="guest_house")
	private Boolean guestHouse;
	@Column(name="no_of_playgrounds")
	private Integer playgroundCount;
	@Column(name="no_of_auditoriums")
	private Integer auditoriumCount;
	@Column(name="no_of_theatres")
	private Integer theatresCount;
	@Column(name="no_of_libraries")
	private Integer librarayCount;
	@Column(name="no_of_laboratories")
	private Integer laboratoryCount;
	@Column(name="no_of_conference_halls")
	private Integer conferenceHallCount;
	@Column(name="no_of_health_centers")
	private Integer healthCenterCount;
	@Column(name="no_of_gymnasim_fitness_centers")
	private Integer gymCount;
	@Column(name="no_of_indoor_stadiums")
	private Integer indoorStadiumCount;
	@Column(name="no_of_common_rooms")
	private Integer commonRoomCount; 
	@Column(name="no_of_computer_centers")
	private Integer computerCenterCount;
	@Column(name="no_of_cafeteria")
	private Integer cafeCount;
	@Column(name="no_of_guest_houses")
	private Integer guestHouseCount;
	@Column(name="no_of_separate_rooms_for_girls")
	private Integer girlRoomCount;
	@Column(name="solar_power_generation")
	private Boolean solarPower;  
	@Column(name="connectivity_nkn")
	private Boolean connectivityNkn;  
	@Column(name="connectivity_nmeict")
	private Boolean connectivityNmeict;  
	@Column(name="no_of_books")
	private Integer bookCount;
	@Column(name="no_of_journals")
	private Integer journalCount;
	@Column(name="campus_friendly")
	private Boolean campusFriendly; 
	@Column(name="grievance_redressal_mechanism")
	private Boolean grievanceRedressal; 
	@Column(name="vigilance_cell")
	private Boolean vigilanceCell; 
	@Column(name="opportunity_cell")
	private Boolean oppportunityCell; 
	@Column(name="separate_toilet_for_disabled_female")
	private Boolean disabledGirlToilet; 
	@Column(name="ramp_attached_to_classroom_library")
	private Boolean attachedRampClassLibrary; 
	@Column(name="sexual_harassment_cell")
	private Boolean sexualHarrassmentCell; 
	@Column(name="counselors_for_students")
	private Boolean studentCounselor; 
	@Column(name="clinic_first_aid_room")
	private Boolean firstAidRoom; 
	@Column(name="separate_toilet_for_girls")
	private Boolean girlsToilet; 
	@Column(name="skill_development_centre")
	private Boolean skillCenter; 
	@Column(name="self_defence_class_for_females")
	private Boolean girlSelfDefenceClass; 
	@Column(name="institution_disaster_management_facilities")
	private Boolean disasterMgmt; 
	@Column(name="capacity_building_and_training_aware_programme_conducted")
	private Boolean capacityBuilding; 
	@Column(name="vulnerability_assess_checks_made_during_year")
	private Boolean vulnerableAssessChecks; 
	@Column(name="any_mock_drill_rehearsal_programme_conducted")
	private Boolean mockDrillPrograms; 
	@Column(name="anti_ragging_cell")
	private Boolean antiRaggingCell;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
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
	public Boolean getGirlsCommonRoom() {
		return girlsCommonRoom;
	}
	public void setGirlsCommonRoom(Boolean girlsCommonRoom) {
		this.girlsCommonRoom = girlsCommonRoom;
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
	public Integer getJournalCount() {
		return journalCount;
	}
	public void setJournalCount(Integer journalCount) {
		this.journalCount = journalCount;
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
}