package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
public class OtpDetailsVerifyVO implements Serializable {
	
	private static final long serialVersionUID = 1579617613051481054L;
	
	private String e_Otp;
	
	private String m_Otp;

	public String getE_Otp() {
		return e_Otp;
	}

	public void setE_Otp(String e_Otp) {
		this.e_Otp = e_Otp;
	}

	public String getM_Otp() {
		return m_Otp;
	}

	public void setM_Otp(String m_Otp) {
		this.m_Otp = m_Otp;
	}
}