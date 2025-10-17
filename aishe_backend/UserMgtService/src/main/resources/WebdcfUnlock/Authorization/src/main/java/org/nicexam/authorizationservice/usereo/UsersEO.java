package org.nicexam.authorizationservice.usereo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.user_master")
public class UsersEO implements Serializable {

	private static final long serialVersionUID = 3407886935652866895L;

	@Id
	@Column(name = "user_id")
    private String loginId;
	
	@Column(name = "first_name")
	private String name;
	
	@Column(name = "bcrypt_password")
	private String password;
	
	//@Column(name = "alternate_password")
	//private String alternatePassword;
	
	@Column(name = "is_approved")
	private Integer isApproved;
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

	public Integer getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}
	
	
}