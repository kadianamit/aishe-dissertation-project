package org.nicexam.authorizationservice.usereo;

import java.io.Serializable;

public class UsersVONew implements Serializable {

	/**
	 * Virendra Gupta
	 */
	private static final long serialVersionUID = -3210702227927586158L;

	private String loginId;

	private String name;

	private String password;

	private String email;

	private String mobile;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
