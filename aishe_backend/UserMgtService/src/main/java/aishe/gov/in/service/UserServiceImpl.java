package aishe.gov.in.service;

import aishe.gov.in.dao.UserDao;
import aishe.gov.in.masterseo.OtpDetailsMouEO;
import aishe.gov.in.mastersvo.ChangePasswordDTO;
import aishe.gov.in.mastersvo.ForgotPasswordDto;
import aishe.gov.in.mastersvo.ResetPasswordDTO;
import aishe.gov.in.security.UserInfo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public String findByEmailIdIgnoreCase(String email) {
        return userDao.findByEmailIdIgnoreCase(email);
    }


    @Override
    public boolean saveForgotPassword(OtpDetailsMouEO forgot) {
        return userDao.saveForgotPassword(forgot);
    }


    @Override
    public String verifyOtpForogtPassword(ForgotPasswordDto forgotPassword) {
        return userDao.verifyOtpForogtPassword(forgotPassword);
    }


	@Override
	public String findByAisheCodeIgnoreCase(String aisheCode) {
		 return userDao.findByAisheCodeIgnoreCase(aisheCode);
	}


	@Override
	public boolean updateUserPassword(String userId, String oldPassword, String confirmPassword, String newPassword) {
		 return userDao.updateUserPassword(userId,oldPassword,confirmPassword,newPassword);
	}
    @Override
    public boolean resetPassword(ResetPasswordDTO resetPassword,HttpServletRequest request,UserInfo userInfo) {
        return userDao.resetPassword(resetPassword,request,userInfo);
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePassword,HttpServletRequest request,UserInfo userInfo) {
        return userDao.changePassword(changePassword,request,userInfo);
    }
}