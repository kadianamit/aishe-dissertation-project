package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "public.otp_details")
public class OtpDetailsEO {

	@Id
	@Column(name = "sno")
	private Integer sno;
	@Column(name = "mobile_no")
	private String mobileNumber;
	@Column(name = "mobile_otp")
	private String mobileOtp;
	@Column(name = "mobile_otp_datetime")
	private LocalDateTime mobileOtpDatetime;
	@Column(name = "is_mobileno_verified")
	private Boolean isMobilenoVerified;
	@Column(name = "mobileno_verified_datetime")
	private LocalDateTime mobileNoVerifiedDatetime;
	
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "email_otp")
	private String emailOtp;
	@Column(name = "email_otp_datetime")
	private LocalDateTime emailOtpDatetime;
	@Column(name = "is_emailid_verified")
	private Boolean isEmailIdVerified;
	@Column(name = "emailid_verified_datetime")
	private LocalDateTime emailIdVerifiedDatetime;
	public Integer getSno() {
		return sno;
	}
	public void setSno(Integer sno) {
		this.sno = sno;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getMobileOtp() {
		return mobileOtp;
	}
	public void setMobileOtp(String mobileOtp) {
		this.mobileOtp = mobileOtp;
	}
	public LocalDateTime getMobileOtpDatetime() {
		return mobileOtpDatetime;
	}
	public void setMobileOtpDatetime(LocalDateTime mobileOtpDatetime) {
		this.mobileOtpDatetime = mobileOtpDatetime;
	}
	public Boolean getIsMobilenoVerified() {
		return isMobilenoVerified;
	}
	public void setIsMobilenoVerified(Boolean isMobilenoVerified) {
		this.isMobilenoVerified = isMobilenoVerified;
	}
	public LocalDateTime getMobileNoVerifiedDatetime() {
		return mobileNoVerifiedDatetime;
	}
	public void setMobileNoVerifiedDatetime(LocalDateTime mobileNoVerifiedDatetime) {
		this.mobileNoVerifiedDatetime = mobileNoVerifiedDatetime;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEmailOtp() {
		return emailOtp;
	}
	public void setEmailOtp(String emailOtp) {
		this.emailOtp = emailOtp;
	}
	public LocalDateTime getEmailOtpDatetime() {
		return emailOtpDatetime;
	}
	public void setEmailOtpDatetime(LocalDateTime emailOtpDatetime) {
		this.emailOtpDatetime = emailOtpDatetime;
	}
	public Boolean getIsEmailIdVerified() {
		return isEmailIdVerified;
	}
	public void setIsEmailIdVerified(Boolean isEmailIdVerified) {
		this.isEmailIdVerified = isEmailIdVerified;
	}
	public LocalDateTime getEmailIdVerifiedDatetime() {
		return emailIdVerifiedDatetime;
	}
	public void setEmailIdVerifiedDatetime(LocalDateTime emailIdVerifiedDatetime) {
		this.emailIdVerifiedDatetime = emailIdVerifiedDatetime;
	}
}