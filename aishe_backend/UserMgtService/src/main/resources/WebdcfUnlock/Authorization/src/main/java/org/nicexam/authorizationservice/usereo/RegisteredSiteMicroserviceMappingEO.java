package org.nicexam.authorizationservice.usereo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "registered_site_microservices_mapping")
public class RegisteredSiteMicroserviceMappingEO implements Serializable {


	private static final long serialVersionUID = 4898045845659243242L;

	@Id
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "microservice_name")
	private String microserviceName;
	
	@Column(name = "microservice_urls")
	private String microserviceUrls;
	
	@Column(name = "fk_registration_site_id")
	private Long registrationSiteId;
	
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

	public String getMicroserviceName() {
		return microserviceName;
	}

	public void setMicroserviceName(String microserviceName) {
		this.microserviceName = microserviceName;
	}

	public String getMicroserviceUrls() {
		return microserviceUrls;
	}

	public void setMicroserviceUrls(String microserviceUrls) {
		this.microserviceUrls = microserviceUrls;
	}

	public Long getRegistrationSiteId() {
		return registrationSiteId;
	}

	public void setRegistrationSiteId(Long registrationSiteId) {
		this.registrationSiteId = registrationSiteId;
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