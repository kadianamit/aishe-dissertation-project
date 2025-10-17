package aishe.gov.in.masterseo;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "public.user_master")
public class UserMasterDetailStateEO {

	@Id
	@Column(name = "user_id")
	private String userId;
	@Column(name = "role_id")
	private Integer roleId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@Column(name = "last_name")
	private String lastName;
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToOne
	@JoinColumn(name = "state_code" ,nullable  = true)
	private RefState state;
	@Column(name = "is_approved")
	private Integer isApproved;
	/*@ManyToOne
	@JoinColumn(name = "state_level_body")
	@Where(clause = "stateLevelBody.surveyYear=(select max(survey_year) from ref_university)")
	private RefUniversity stateLevelBody;*/
	@Column(name = "state_level_body")
	private String stateLevelBody;

	@Column(name = "state_level_body_institute")
	private String stateLevelBodyInstitute;
	@Column(name = "status_id")
	private Integer statusId;
	@Column(name = "deo_user_type")
	private Integer deoUserType;
	@Column(name = "aishe_code")
	private String aisheCode;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RefState getState() {
		return state;
	}

	public void setState(RefState state) {
		this.state = state;
	}

	public Integer getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}

	public String getStateLevelBody() {
		return stateLevelBody;
	}

	public void setStateLevelBody(String stateLevelBody) {
		this.stateLevelBody = stateLevelBody;
	}

	public String getStateLevelBodyInstitute() {
		return stateLevelBodyInstitute;
	}

	public void setStateLevelBodyInstitute(String stateLevelBodyInstitute) {
		this.stateLevelBodyInstitute = stateLevelBodyInstitute;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getDeoUserType() {
		return deoUserType;
	}

	public void setDeoUserType(Integer deoUserType) {
		this.deoUserType = deoUserType;
	}

	public String getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
}