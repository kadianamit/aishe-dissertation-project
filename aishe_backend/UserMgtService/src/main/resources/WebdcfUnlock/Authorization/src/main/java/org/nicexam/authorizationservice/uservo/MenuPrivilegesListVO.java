package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class MenuPrivilegesListVO implements Serializable {


	private static final long serialVersionUID = 4555500169920051687L;

	private Integer id;

	private String name;

	private String action;
	
	private Boolean hasChecked=true;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Boolean getHasChecked() {
		return hasChecked;
	}

	public void setHasChecked(Boolean hasChecked) {
		this.hasChecked = hasChecked;
	}
}
