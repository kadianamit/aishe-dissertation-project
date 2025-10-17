package aishe.gov.in.masterseo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_token")
public class UserToken {
	
	@Id
	@Column(name = "user_id")
	private String userId;
	@Column(name = "password")
	private String password;
	@Column(name = "token")
	private String token;
	@Column(name = "generated_datetime")
	private Date generatedDateTime;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getGeneratedDateTime() {
		return generatedDateTime;
	}
	public void setGeneratedDateTime(Date generatedDateTime) {
		this.generatedDateTime = generatedDateTime;
	}
	
	

}
