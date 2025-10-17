package org.nicexam.authorizationservice.requestDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class PenDetailStatusReqDTO {
	private Long teacherId;

	@Pattern(regexp = "[1-9][0-9]{9}", message = "Please enter valid mobile number")
	private String mobile;

	@Email
	private String email;

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
