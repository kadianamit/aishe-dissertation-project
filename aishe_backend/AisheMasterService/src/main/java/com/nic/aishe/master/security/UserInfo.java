package com.nic.aishe.master.security;

public class UserInfo {
	private String username;
	private Integer userId;

	public UserInfo(String username,Integer userid) {
		super();
		this.username = username;
		this.userId=userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
