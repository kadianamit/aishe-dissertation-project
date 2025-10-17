package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.time.LocalDateTime;
public class OtpDetailsVO implements Serializable {
	
	private static final long serialVersionUID = -8578007676602733380L;

	private Long Id;
	
	private Long userId;
	
	private String type;
	
	private String otp;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime expiresOn;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(LocalDateTime expiresOn) {
		this.expiresOn = expiresOn;
	}

	
	
	
}