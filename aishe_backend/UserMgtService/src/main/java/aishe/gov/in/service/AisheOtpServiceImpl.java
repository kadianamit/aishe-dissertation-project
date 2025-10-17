package aishe.gov.in.service;

import aishe.gov.in.dao.AisheOtpDao;
import aishe.gov.in.masterseo.OtpDetailsEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AisheOtpServiceImpl implements AisheOtpService {

    @Autowired
   private AisheOtpDao aisheUserRequestDao;


	@Override
	public Integer saveEmailOtp(OtpDetailsEO forgot) {
		return aisheUserRequestDao.saveEmailOtp(forgot);
	}

	@Override
	public Integer saveMobileOtp(OtpDetailsEO forgot) {
		return aisheUserRequestDao.saveMobileOtp(forgot);
	}

	@Override
	public Boolean verifyEmailOtp(String email, String otp) {
		return aisheUserRequestDao.verifyEmailOtp(email,otp);
	}

	@Override
	public Boolean verifyMobileOtp(String mobile, String otp) {
		return aisheUserRequestDao.verifyMobileOtp(mobile,otp);
	}


	@Override
	public Integer findByEmailIdForCount(String email) {
		return aisheUserRequestDao.findByEmailIdForCount(email);
	}

	@Override
	public Integer findByMobileNumberForCount(String mobileNumber) {
		return aisheUserRequestDao.findByMobileNumberForCount(mobileNumber);
	}
}