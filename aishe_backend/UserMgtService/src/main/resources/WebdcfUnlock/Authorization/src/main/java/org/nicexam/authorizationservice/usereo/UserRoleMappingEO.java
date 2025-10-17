package org.nicexam.authorizationservice.usereo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.user_roles_mapping")
public class UserRoleMappingEO implements Serializable {

	private static final long serialVersionUID = 7536392951213538461L;
	
    @Id
	@Column(name = "record_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recordId;
	
	@Column(name = "user_record_id")
	private Long userRecordId;
	
	@Column(name = "roles_record_id")
	private Integer rolesRecordId;
	
	@Column(name = "user_office_id ")
	private Integer userOfficeId;
	
	@Column(name = "user_office_value")
	private String userOfficeValue;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	
	@Column(name = "updated_by")
	private String updatedBy;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Long getUserRecordId() {
		return userRecordId;
	}

	public void setUserRecordId(Long userRecordId) {
		this.userRecordId = userRecordId;
	}

	public Integer getRolesRecordId() {
		return rolesRecordId;
	}

	public void setRolesRecordId(Integer rolesRecordId) {
		this.rolesRecordId = rolesRecordId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getUserOfficeId() {
		return userOfficeId;
	}

	public void setUserOfficeId(Integer userOfficeId) {
		this.userOfficeId = userOfficeId;
	}

	public String getUserOfficeValue() {
		return userOfficeValue;
	}

	public void setUserOfficeValue(String userOfficeValue) {
		this.userOfficeValue = userOfficeValue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}