package aishe.gov.in.masterseo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "public.activity_master_log")
public class ActivityMasterLogsEO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "activity_master_id")
	private Integer activityMasterId;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "action_id")
	private Integer actionId;
	@Column(name = "survey_year")
	private Integer surveyYear;
	@Column(name = "start_date")
	private LocalDateTime startDate;
	@Column(name = "end_date")
	private LocalDateTime endDate;
	@Column(name = "prev_start_date")
	private LocalDateTime prevStartDate;
	@Column(name = "prev_end_date")
	private LocalDateTime prevEndDate;
	@Column(name = "action_date")
	private LocalDateTime actionDate;

	private String remarks;


}