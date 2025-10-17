package aishe.gov.in.dao;

import javax.servlet.http.HttpServletRequest;

import aishe.gov.in.masterseo.OtpDetailsMouEO;
import aishe.gov.in.masterseo.CaptchaValidationEO;
import aishe.gov.in.mastersvo.ChangePasswordDTO;
import aishe.gov.in.mastersvo.ForgotPasswordDto;
import aishe.gov.in.mastersvo.ResetPasswordDTO;
import aishe.gov.in.security.UserInfo;


public interface UserDao {

    String findByEmailIdIgnoreCase(String email);

    boolean saveForgotPassword(OtpDetailsMouEO forgot);

    String verifyOtpForogtPassword(ForgotPasswordDto forgotPassword);

	String findByAisheCodeIgnoreCase(String aisheCode);

	boolean updateUserPassword(String userId, String oldPassword,String confirmPassword, String newPassword);
    boolean resetPassword(ResetPasswordDTO resetPassword, HttpServletRequest request, UserInfo userInfo);

    boolean changePassword(ChangePasswordDTO changePassword, HttpServletRequest request, UserInfo userInfo);

    Boolean getCaptcha(String captcha);

    void saveCaptcha(CaptchaValidationEO validationEO);

	boolean fetchTokenNotExpired(String authorizationHeader);


}