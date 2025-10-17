package org.nicexam.authorizationservice.uservo;

public class UserInfo {
	private String username;
	private Long userId;

	public UserInfo(String username,Long userId) {
		super();
		this.username = username;
		this.userId=userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}	
}
