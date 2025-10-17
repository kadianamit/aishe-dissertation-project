package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class RegisteredSiteMicroserviceMappingVO implements Serializable {

	private static final long serialVersionUID = -1474115293709246509L;

	private Long Id;
	
	private String microserviceName;
	
	private String microserviceUrls;
	
	private Long registrationSiteId;

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
	
	
	
	
}