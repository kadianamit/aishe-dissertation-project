package aishe.gov.in.mastersvo;

import aishe.gov.in.utility.EncryptionDecryptionUtil;

import java.io.Serializable;

public class ForgotPasswordDto implements Serializable {

    private static final long serialVersionUID = 3157808092872607072L;

    private String loginId;

    private String password;

    private String confirmPassword;

    private String mobileOtp;

    private String emailOtp;

    private String mobile;

    private String email;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getMobileOtp() {
        return mobileOtp;
    }

    public void setMobileOtp(String mobileOtp) {
        this.mobileOtp = mobileOtp;
    }

    public String getEmailOtp() {
        return emailOtp;
    }

    public void setEmailOtp(String emailOtp) {
        this.emailOtp = emailOtp;
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


    public static void decript(ForgotPasswordDto dto) {
        if (null != dto.getMobileOtp())
            dto.setMobileOtp(EncryptionDecryptionUtil.getDecryptedString(dto.getMobileOtp()));
        if (null != dto.getMobile())
            dto.setMobile(EncryptionDecryptionUtil.getDecryptedString(dto.getMobile()));
        if (null != dto.getEmail())
            dto.setEmail(EncryptionDecryptionUtil.getDecryptedString(dto.getEmail()));
        if (null != dto.getEmailOtp())
            dto.setEmailOtp(EncryptionDecryptionUtil.getDecryptedString(dto.getEmailOtp()));
    }
}