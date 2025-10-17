package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "infrastructure")
public class InfrastructureEO {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "no_of_playgrounds")
	private Integer no_of_playgrounds;
	@Column(name = "no_of_auditoriums")
	private Integer no_of_auditoriums;
	@Column(name = "no_of_theatres")
	private Integer no_of_theatres;
	@Column(name = "no_of_libraries")
	private Integer no_of_libraries;
	@Column(name = "no_of_laboratories")
	private Integer no_of_laboratories;
	@Column(name = "no_of_conference_halls")
	private Integer no_of_conference_halls;
	@Column(name = "no_of_health_centers")
	private Integer no_of_health_centers;
	@Column(name = "no_of_gymnasim_fitness_centers")
	private Integer no_of_gymnasim_fitness_centers;
	@Column(name = "no_of_indoor_stadiums")
	private Integer no_of_indoor_stadiums;
	@Column(name = "no_of_common_rooms")
	private Integer no_of_common_rooms;
	@Column(name = "no_of_computer_centers")
	private Integer no_of_computer_centers; 
	@Column(name = "no_of_cafeteria")
	private Integer no_of_cafeteria;
	@Column(name = "no_of_guest_houses")
	private Integer no_of_guest_houses;
	@Column(name = "separate_room_for_girls")
	private Integer separate_room_for_girls;
	@Column(name = "no_of_books")
	private Integer no_of_books; 
	@Column(name = "no_of_journals")
	private Integer no_of_journals;
	@Column(name = "playground")
	private Boolean playground;
	@Column(name = "auditorium")
	private Boolean auditorium;
	@Column(name = "theatre")
	private Boolean theatre; 
	@Column(name = "library")
	private Boolean library;
	@Column(name = "laboratory")
	private Boolean laboratory;
	@Column(name = "conference_hall")
	private Boolean conference_hall;
	@Column(name = "health_center")
	private Boolean health_center;
	@Column(name = "gymnasium_fitness_center")
	private Boolean gymnasium_fitness_center;
	@Column(name = "indoor_stadium")
	private Boolean indoor_stadium;
	@Column(name = "common_room")
	private Boolean common_room;
	@Column(name = "computer_center")
	private Boolean computer_center;
	@Column(name = "cafeteria")
	private Boolean cafeteria;
	@Column(name = "guest_house")
	private Boolean guest_house;
	@Column(name = "no_of_separate_rooms_for_girls")
	private Boolean no_of_separate_rooms_for_girls;
	
	
	@Column(name = "solar_power_generation")
	private Boolean solar_power_generation;
	@Column(name = "connectivity_nkn")
	private Boolean connectivity_nkn;
	@Column(name = "connectivity_nmeict")
	private Boolean connectivity_nmeict;
	@Column(name = "campus_friendly")
	private Boolean campus_friendly;
	@Column(name = "grievance_redressal_mechanism")
	private Boolean grievance_redressal_mechanism;
	@Column(name = "vigilance_cell")
	private Boolean vigilance_cell;
	@Column(name = "opportunity_cell")
	private Boolean opportunity_cell;
	@Column(name = "separate_toilet_for_disabled_female")
	private Boolean separate_toilet_for_disabled_female;
	@Column(name = "ramp_attached_to_classroom_library")
	private Boolean ramp_attached_to_classroom_library;
	@Column(name = "sexual_harassment_cell")
	private Boolean sexual_harassment_cell;
	@Column(name = "counselors_for_students")
	private Boolean counselors_for_students;
	@Column(name = "clinic_first_aid_room")
	private Boolean clinic_first_aid_room;
	@Column(name = "separate_toilet_for_girls")
	private Boolean separate_toilet_for_girls;
	@Column(name = "skill_development_centre")
	private Boolean skill_development_centre;
	@Column(name = "self_defence_class_for_females")
	private Boolean self_defence_class_for_females;
	@Column(name = "institution_disaster_management_facilities")
	private Boolean institution_disaster_management_facilities;
	@Column(name = "capacity_building_and_training_aware_programme_conducted")
	private Boolean capacity_building_and_training_aware_programme_conducted;
	@Column(name = "vulnerability_assess_checks_made_during_year")
	private Boolean vulnerability_assess_checks_made_during_year;
	@Column(name = "any_mock_drill_rehearsal_programme_conducted")
	private Boolean any_mock_drill_rehearsal_programme_conducted;
	@Column(name = "anti_ragging_cell")
	private Boolean anti_ragging_cell;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean isPlayground() {
		return playground;
	}
	public void setPlayground(Boolean playground) {
		this.playground = playground;
	}
	public Boolean isAuditorium() {
		return auditorium;
	}
	public void setAuditorium(Boolean auditorium) {
		this.auditorium = auditorium;
	}
	public Boolean isTheatre() {
		return theatre;
	}
	public void setTheatre(Boolean theatre) {
		this.theatre = theatre;
	}
	public Boolean isLibrary() {
		return library;
	}
	public void setLibrary(Boolean library) {
		this.library = library;
	}
	public Boolean isLaboratory() {
		return laboratory;
	}
	public void setLaboratory(Boolean laboratory) {
		this.laboratory = laboratory;
	}
	public Boolean isConference_hall() {
		return conference_hall;
	}
	public void setConference_hall(Boolean conference_hall) {
		this.conference_hall = conference_hall;
	}
	public Boolean isHealth_center() {
		return health_center;
	}
	public void setHealth_center(Boolean health_center) {
		this.health_center = health_center;
	}
	public Boolean isGymnasium_fitness_center() {
		return gymnasium_fitness_center;
	}
	public void setGymnasium_fitness_center(Boolean gymnasium_fitness_center) {
		this.gymnasium_fitness_center = gymnasium_fitness_center;
	}
	public Boolean isIndoor_stadium() {
		return indoor_stadium;
	}
	public void setIndoor_stadium(Boolean indoor_stadium) {
		this.indoor_stadium = indoor_stadium;
	}
	public Boolean isCommon_room() {
		return common_room;
	}
	public void setCommon_room(Boolean common_room) {
		this.common_room = common_room;
	}
	public Boolean isComputer_center() {
		return computer_center;
	}
	public void setComputer_center(Boolean computer_center) {
		this.computer_center = computer_center;
	}
	public Boolean isCafeteria() {
		return cafeteria;
	}
	public void setCafeteria(Boolean cafeteria) {
		this.cafeteria = cafeteria;
	}
	public Boolean isGuest_house() {
		return guest_house;
	}
	public void setGuest_house(Boolean guest_house) {
		this.guest_house = guest_house;
	}
	public Integer getNo_of_playgrounds() {
		return no_of_playgrounds;
	}
	public void setNo_of_playgrounds(Integer no_of_playgrounds) {
		this.no_of_playgrounds = no_of_playgrounds;
	}
	public Integer getNo_of_auditoriums() {
		return no_of_auditoriums;
	}
	public void setNo_of_auditoriums(Integer no_of_auditoriums) {
		this.no_of_auditoriums = no_of_auditoriums;
	}
	public Integer getNo_of_theatres() {
		return no_of_theatres;
	}
	public void setNo_of_theatres(Integer no_of_theatres) {
		this.no_of_theatres = no_of_theatres;
	}
	public Integer getNo_of_libraries() {
		return no_of_libraries;
	}
	public void setNo_of_libraries(Integer no_of_libraries) {
		this.no_of_libraries = no_of_libraries;
	}
	public Integer getNo_of_laboratories() {
		return no_of_laboratories;
	}
	public void setNo_of_laboratories(Integer no_of_laboratories) {
		this.no_of_laboratories = no_of_laboratories;
	}
	public Integer getNo_of_conference_halls() {
		return no_of_conference_halls;
	}
	public void setNo_of_conference_halls(Integer no_of_conference_halls) {
		this.no_of_conference_halls = no_of_conference_halls;
	}
	public Integer getNo_of_health_centers() {
		return no_of_health_centers;
	}
	public void setNo_of_health_centers(Integer no_of_health_centers) {
		this.no_of_health_centers = no_of_health_centers;
	}
	public Integer getNo_of_gymnasim_fitness_centers() {
		return no_of_gymnasim_fitness_centers;
	}
	public void setNo_of_gymnasim_fitness_centers(Integer no_of_gymnasim_fitness_centers) {
		this.no_of_gymnasim_fitness_centers = no_of_gymnasim_fitness_centers;
	}
	public Integer getNo_of_indoor_stadiums() {
		return no_of_indoor_stadiums;
	}
	public void setNo_of_indoor_stadiums(Integer no_of_indoor_stadiums) {
		this.no_of_indoor_stadiums = no_of_indoor_stadiums;
	}
	public Integer getNo_of_common_rooms() {
		return no_of_common_rooms;
	}
	public void setNo_of_common_rooms(Integer no_of_common_rooms) {
		this.no_of_common_rooms = no_of_common_rooms;
	}
	public Integer getNo_of_computer_centers() {
		return no_of_computer_centers;
	}
	public void setNo_of_computer_centers(Integer no_of_computer_centers) {
		this.no_of_computer_centers = no_of_computer_centers;
	}
	public Integer getNo_of_cafeteria() {
		return no_of_cafeteria;
	}
	public void setNo_of_cafeteria(Integer no_of_cafeteria) {
		this.no_of_cafeteria = no_of_cafeteria;
	}
	public Integer getNo_of_guest_houses() {
		return no_of_guest_houses;
	}
	public void setNo_of_guest_houses(Integer no_of_guest_houses) {
		this.no_of_guest_houses = no_of_guest_houses;
	}
	public Integer getSeparate_room_for_girls() {
		return separate_room_for_girls;
	}
	public void setSeparate_room_for_girls(Integer separate_room_for_girls) {
		this.separate_room_for_girls = separate_room_for_girls;
	}
	public Boolean isNo_of_separate_rooms_for_girls() {
		return no_of_separate_rooms_for_girls;
	}
	public void setNo_of_separate_rooms_for_girls(Boolean no_of_separate_rooms_for_girls) {
		this.no_of_separate_rooms_for_girls = no_of_separate_rooms_for_girls;
	}
	public Boolean isSolar_power_generation() {
		return solar_power_generation;
	}
	public void setSolar_power_generation(Boolean solar_power_generation) {
		this.solar_power_generation = solar_power_generation;
	}
	public Boolean isConnectivity_nkn() {
		return connectivity_nkn;
	}
	public void setConnectivity_nkn(Boolean connectivity_nkn) {
		this.connectivity_nkn = connectivity_nkn;
	}
	public Boolean isConnectivity_nmeict() {
		return connectivity_nmeict;
	}
	public void setConnectivity_nmeict(Boolean connectivity_nmeict) {
		this.connectivity_nmeict = connectivity_nmeict;
	}
	public Integer getNo_of_books() {
		return no_of_books;
	}
	public void setNo_of_books(Integer no_of_books) {
		this.no_of_books = no_of_books;
	}
	public Integer getNo_of_journals() {
		return no_of_journals;
	}
	public void setNo_of_journals(Integer no_of_journals) {
		this.no_of_journals = no_of_journals;
	}
	public Boolean isCampus_friendly() {
		return campus_friendly;
	}
	public void setCampus_friendly(Boolean campus_friendly) {
		this.campus_friendly = campus_friendly;
	}
	public Boolean isGrievance_redressal_mechanism() {
		return grievance_redressal_mechanism;
	}
	public void setGrievance_redressal_mechanism(Boolean grievance_redressal_mechanism) {
		this.grievance_redressal_mechanism = grievance_redressal_mechanism;
	}
	public Boolean isVigilance_cell() {
		return vigilance_cell;
	}
	public void setVigilance_cell(Boolean vigilance_cell) {
		this.vigilance_cell = vigilance_cell;
	}
	public Boolean isOpportunity_cell() {
		return opportunity_cell;
	}
	public void setOpportunity_cell(Boolean opportunity_cell) {
		this.opportunity_cell = opportunity_cell;
	}
	public Boolean isSeparate_toilet_for_disabled_female() {
		return separate_toilet_for_disabled_female;
	}
	public void setSeparate_toilet_for_disabled_female(Boolean separate_toilet_for_disabled_female) {
		this.separate_toilet_for_disabled_female = separate_toilet_for_disabled_female;
	}
	public Boolean isRamp_attached_to_classroom_library() {
		return ramp_attached_to_classroom_library;
	}
	public void setRamp_attached_to_classroom_library(Boolean ramp_attached_to_classroom_library) {
		this.ramp_attached_to_classroom_library = ramp_attached_to_classroom_library;
	}
	public Boolean isSexual_harassment_cell() {
		return sexual_harassment_cell;
	}
	public void setSexual_harassment_cell(Boolean sexual_harassment_cell) {
		this.sexual_harassment_cell = sexual_harassment_cell;
	}
	public Boolean isCounselors_for_students() {
		return counselors_for_students;
	}
	public void setCounselors_for_students(Boolean counselors_for_students) {
		this.counselors_for_students = counselors_for_students;
	}
	public Boolean isClinic_first_aid_room() {
		return clinic_first_aid_room;
	}
	public void setClinic_first_aid_room(Boolean clinic_first_aid_room) {
		this.clinic_first_aid_room = clinic_first_aid_room;
	}
	public Boolean isSeparate_toilet_for_girls() {
		return separate_toilet_for_girls;
	}
	public void setSeparate_toilet_for_girls(Boolean separate_toilet_for_girls) {
		this.separate_toilet_for_girls = separate_toilet_for_girls;
	}
	public Boolean isSkill_development_centre() {
		return skill_development_centre;
	}
	public void setSkill_development_centre(Boolean skill_development_centre) {
		this.skill_development_centre = skill_development_centre;
	}
	public Boolean isSelf_defence_class_for_females() {
		return self_defence_class_for_females;
	}
	public void setSelf_defence_class_for_females(Boolean self_defence_class_for_females) {
		this.self_defence_class_for_females = self_defence_class_for_females;
	}
	public Boolean isInstitution_disaster_management_facilities() {
		return institution_disaster_management_facilities;
	}
	public void setInstitution_disaster_management_facilities(Boolean institution_disaster_management_facilities) {
		this.institution_disaster_management_facilities = institution_disaster_management_facilities;
	}
	public Boolean isCapacity_building_and_training_aware_programme_conducted() {
		return capacity_building_and_training_aware_programme_conducted;
	}
	public void setCapacity_building_and_training_aware_programme_conducted(
			Boolean capacity_building_and_training_aware_programme_conducted) {
		this.capacity_building_and_training_aware_programme_conducted = capacity_building_and_training_aware_programme_conducted;
	}
	public Boolean isVulnerability_assess_checks_made_during_year() {
		return vulnerability_assess_checks_made_during_year;
	}
	public void setVulnerability_assess_checks_made_during_year(Boolean vulnerability_assess_checks_made_during_year) {
		this.vulnerability_assess_checks_made_during_year = vulnerability_assess_checks_made_during_year;
	}
	public Boolean isAny_mock_drill_rehearsal_programme_conducted() {
		return any_mock_drill_rehearsal_programme_conducted;
	}
	public void setAny_mock_drill_rehearsal_programme_conducted(Boolean any_mock_drill_rehearsal_programme_conducted) {
		this.any_mock_drill_rehearsal_programme_conducted = any_mock_drill_rehearsal_programme_conducted;
	}
	public Boolean isAnti_ragging_cell() {
		return anti_ragging_cell;
	}
	public void setAnti_ragging_cell(Boolean anti_ragging_cell) {
		this.anti_ragging_cell = anti_ragging_cell;
	}
	
}
