package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class RegistrationSiteDetailsVO implements Serializable {


	private static final long serialVersionUID = -3776794634703839340L;

	private Long Id;
	
	private String name;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
		
}