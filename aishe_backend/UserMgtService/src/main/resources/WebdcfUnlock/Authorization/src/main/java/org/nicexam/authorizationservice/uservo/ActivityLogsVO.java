package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ActivityLogsVO implements Serializable {

	private static final long serialVersionUID = -435346665286094405L;

	private String activity;
	
	private String type;
	
	private String source;
	
	private String username;
	
	private String ipAddress;
	
	private LocalDateTime created;

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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