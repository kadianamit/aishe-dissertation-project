package aishe.gov.in.service;

import aishe.gov.in.masterseo.OtpDetailsEO;

public interface AisheOtpService {



	Integer saveEmailOtp(OtpDetailsEO forgot);

	Integer saveMobileOtp(OtpDetailsEO forgot);

	Boolean verifyEmailOtp(String email, String otp);

	Boolean verifyMobileOtp(String mobile, String otp);
	Integer findByEmailIdForCount(String email);

	Integer findByMobileNumberForCount(String mobileNumber);

}
