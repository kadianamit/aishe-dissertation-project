package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.time.LocalDateTime;


public class ChangeLogsVO implements Serializable {

	private static final long serialVersionUID = -5870784342271826223L;
	
	private String activity;
	
	private String action;
	
	private String type;
	
	private String username;
	
	private String ipAddress;
	
	private LocalDateTime created;

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
}