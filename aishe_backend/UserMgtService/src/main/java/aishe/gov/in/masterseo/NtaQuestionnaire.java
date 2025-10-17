package aishe.gov.in.masterseo;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import aishe.gov.in.jsonbutility.JsonUserType;
import lombok.Getter;
import lombok.Setter;

@Entity
@TypeDef(name = "JsonUserType", typeClass = JsonUserType.class)
@Getter
@Setter
@Table(name = "miscellaneous.nta_questionnaire")
public class NtaQuestionnaire {

	@Id
	@GenericGenerator(name="ntaquestionnaire" , strategy="increment")
	@GeneratedValue(generator="ntaquestionnaire")
	@Column(name = "id")
	private Integer id;
	@Column(name = "institute_type")
	private String instituteType;
	@Column(name = "institute_name")
	private String instituteName;
	@Column(name = "aishe_code")
	private String aisheCode;
	@Column(name = "q2")
	private Boolean q2;
	@Column(name = "q3")
	private Boolean q3;
	@Column(name = "q4")
	private Boolean q4;

	@Column(name = "q5_1")
	private String q5_1;

	@Column(name = "q5_2_3")
	@Type(type = "JsonUserType")
    private List<Map<String, String>> common_q5_1;



	@Column(name = "q5_4")
	private Boolean q5_4;

	@Column(name = "q5_5")
	private String q5_5;

	@Column(name = "q5_6")
	private Boolean q5_6;

	@Column(name = "q5_7")
	private Boolean q5_7;

	@Column(name = "q5_8")
	private Boolean q5_8;

	@Column(name = "q5_9")
	private String q5_9;

	@Column(name = "q5_10")
	private Boolean q5_10;

	@Column(name = "q5_11")
	private Boolean q5_11;

	@Column(name = "q5_12")
	private String q5_12;

	@Column(name = "q5_13")
	private String q5_13;

	@Column(name = "q5_14")
	private Boolean q5_14;

	@Column(name = "q5_15")
	private Boolean q5_15;

	@Column(name = "q5_16")
	private Boolean q5_16;

	@Column(name = "q5_17")
	private String q5_17;

	@Column(name = "q5_18")
	private Boolean q5_18;

	@Column(name = "q6_1")
	private Boolean q6_1;

	@Column(name = "q6_2")
	private String q6_2;

	@Column(name = "q6_3")
	private Boolean q6_3;

	@Column(name = "q6_4")
	private String q6_4;

	@Column(name = "q6_5")
	private Boolean q6_5;

	@Column(name = "q6_6")
	private Boolean q6_6;

	@Column(name = "latitude")
	private Double latitude;
	@Column(name = "longitude")
	private Double longitude;
	@Transient
	private boolean isParticipateInSurvey;
	@Transient
	private String message;
}