package org.nicexam.authorizationservice.usereo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.users_contacts")
public class UsersContactsEO implements Serializable {

	private static final long serialVersionUID = -2216216161596846870L;

	@Id
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "fk_user_id")
	private Long userId;
	
	@Column(name = "primary_email_address")
	private String primaryEmailAddress;
	
	@Column(name = "recovery_email_address")
	private String recoveryEmailAddress;
	
	@Column(name = "primary_mobile_number")
	private String primaryMobileNumber;
	
	@Column(name = "recovery_mobile_number")
	private String recoveryMobileNumber;
	
	@Column(name = "primary_email_address_verified_on")
	private LocalDateTime primaryEmailVerifiedOn;
	
	@Column(name = "recovery_email_address_verified_on")
	private LocalDateTime recoveryEmailVerifiedOn;
	
	@Column(name = "primary_mobile_number_verified_on")
	private LocalDateTime primaryMobileVerifiedOn;
	
	@Column(name = "recovery_mobile_number_verified_on")
	private LocalDateTime recoveryMobileVerifiedOn;
	
	@Column(name = "is_primary_email_address_verified")
	private boolean isPrimaryEmailVerified;
	
	@Column(name = "is_recovery_email_address_verified")
	private boolean isRecoveryEmailVerified;
	
	@Column(name = "is_primary_mobile_number_verified")
	private boolean isPrimaryMobileVerified;
	
	@Column(name = "is_recovery_mobile_number_verified")
	private boolean isRecoveryMobileverified;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	
	@Column(name = "is_active")
	private boolean isActive;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPrimaryEmailAddress() {
		return primaryEmailAddress;
	}

	public void setPrimaryEmailAddress(String primaryEmailAddress) {
		this.primaryEmailAddress = primaryEmailAddress;
	}

	public String getRecoveryEmailAddress() {
		return recoveryEmailAddress;
	}

	public void setRecoveryEmailAddress(String recoveryEmailAddress) {
		this.recoveryEmailAddress = recoveryEmailAddress;
	}

	public String getPrimaryMobileNumber() {
		return primaryMobileNumber;
	}

	public void setPrimaryMobileNumber(String primaryMobileNumber) {
		this.primaryMobileNumber = primaryMobileNumber;
	}

	public String getRecoveryMobileNumber() {
		return recoveryMobileNumber;
	}

	public void setRecoveryMobileNumber(String recoveryMobileNumber) {
		this.recoveryMobileNumber = recoveryMobileNumber;
	}

	public LocalDateTime getPrimaryEmailVerifiedOn() {
		return primaryEmailVerifiedOn;
	}

	public void setPrimaryEmailVerifiedOn(LocalDateTime primaryEmailVerifiedOn) {
		this.primaryEmailVerifiedOn = primaryEmailVerifiedOn;
	}

	public LocalDateTime getRecoveryEmailVerifiedOn() {
		return recoveryEmailVerifiedOn;
	}

	public void setRecoveryEmailVerifiedOn(LocalDateTime recoveryEmailVerifiedOn) {
		this.recoveryEmailVerifiedOn = recoveryEmailVerifiedOn;
	}

	public LocalDateTime getPrimaryMobileVerifiedOn() {
		return primaryMobileVerifiedOn;
	}

	public void setPrimaryMobileVerifiedOn(LocalDateTime primaryMobileVerifiedOn) {
		this.primaryMobileVerifiedOn = primaryMobileVerifiedOn;
	}

	public LocalDateTime getRecoveryMobileVerifiedOn() {
		return recoveryMobileVerifiedOn;
	}

	public void setRecoveryMobileVerifiedOn(LocalDateTime recoveryMobileVerifiedOn) {
		this.recoveryMobileVerifiedOn = recoveryMobileVerifiedOn;
	}

	public boolean isPrimaryEmailVerified() {
		return isPrimaryEmailVerified;
	}

	public void setPrimaryEmailVerified(boolean isPrimaryEmailVerified) {
		this.isPrimaryEmailVerified = isPrimaryEmailVerified;
	}

	public boolean isRecoveryEmailVerified() {
		return isRecoveryEmailVerified;
	}

	public void setRecoveryEmailVerified(boolean isRecoveryEmailVerified) {
		this.isRecoveryEmailVerified = isRecoveryEmailVerified;
	}

	public boolean isPrimaryMobileVerified() {
		return isPrimaryMobileVerified;
	}

	public void setPrimaryMobileVerified(boolean isPrimaryMobileVerified) {
		this.isPrimaryMobileVerified = isPrimaryMobileVerified;
	}

	public boolean isRecoveryMobileverified() {
		return isRecoveryMobileverified;
	}

	public void setRecoveryMobileverified(boolean isRecoveryMobileverified) {
		this.isRecoveryMobileverified = isRecoveryMobileverified;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	
	
	
	
}