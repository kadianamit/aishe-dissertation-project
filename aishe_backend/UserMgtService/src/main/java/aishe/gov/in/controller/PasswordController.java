package aishe.gov.in.controller;

import aishe.gov.in.enums.Constant;
import aishe.gov.in.masterseo.OtpDetailsMouEO;
import aishe.gov.in.mastersvo.ChangePasswordDTO;
import aishe.gov.in.mastersvo.ForgotPasswordDto;
import aishe.gov.in.mastersvo.ResetPasswordDTO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.UserService;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.CommonUtils;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.EncryptionDecryptionUtil;
import aishe.gov.in.utility.ReturnResponse;
import aishe.gov.in.utility.ReturnResponseNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api")
@RestController
public class PasswordController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/forgot-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnResponseNew> forgotUserPassword(@RequestParam String email//,@WithUser UserInfo userInfo
    ) {
        String existingUser = userService.findByEmailIdIgnoreCase(EncryptionDecryptionUtil.getDecryptedString(email));
        if (existingUser != null) {
            String otp = CommonUtils.getRandomNumberString();
            OtpDetailsMouEO forgot = new OtpDetailsMouEO();
            forgot.setUserId(existingUser);
            forgot.setType("EMAIL");
            forgot.setOtp(otp);
            forgot.setCreatedDate(DateUtils.obtainCurrentTimeStamp());
            forgot.setExpiresOn(DateUtils.obtainCurrentTimeStamp().plusMinutes(15));
            boolean isSave = userService.saveForgotPassword(forgot);
            if (isSave) {
                String subject = "Aishe Verify Otp Forgot Password";
                // Util.sendEmail(email, subject, "Your Otp To Verify Your Identity For Aishe Is " + otp);
                return new ResponseEntity<>(new ReturnResponseNew(EncryptionDecryptionUtil.getEncryptedString(String.valueOf(HttpStatus.OK.value())), EncryptionDecryptionUtil.getEncryptedString("Link Has Been Sent to Your Mail!!")), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ReturnResponseNew(EncryptionDecryptionUtil.getEncryptedString(String.valueOf(HttpStatus.BAD_REQUEST.value())), EncryptionDecryptionUtil.getEncryptedString("Some Error Occurred!!")), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new ReturnResponseNew(EncryptionDecryptionUtil.getEncryptedString(String.valueOf(HttpStatus.BAD_REQUEST.value())), EncryptionDecryptionUtil.getEncryptedString("This email address does not exist!")), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/verifyforgotpassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnResponse> verifyOtpForgotPassword(@RequestBody ForgotPasswordDto forgotPassword// ,@WithUser UserInfo userInfo
    ) {
        ForgotPasswordDto.decript(forgotPassword);
        String verifystatus = userService.verifyOtpForogtPassword(forgotPassword);
        if (verifystatus.equalsIgnoreCase("success")) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "OTP has been Verified successfully!!"), HttpStatus.OK);
        } else {
            String message = "";
            switch (verifystatus) {
                case "otpnotfound":
                    message = "OTP is not found.please resend it again!!";
                    break;
                case "invalidtype":
                    message = "OTP Type is not found.please resend it again!!";
                    break;
                case "invalidmobileotp":
                    message = "Invalid mobile/email otp";
                    break;
                case "expmobileotp":
                    message = "Expired mobile/email otp";
                    break;
                case "invalidemailotp":
                    message = "Invalid email/mobile otp";
                    break;
                case "expemailotp":
                    message = "Expired email/mobile otp";
                    break;
                case "Enter Otp For Mobile and Email Both":
                    message = "Enter Otp For Mobile and Email Both";
                    break;
                default:
                    message = "Request Cannot Be Processed. Please Try Again.";
                    break;
            }
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), message), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnResponseNew> resetUserPassword(@RequestBody ResetPasswordDTO resetPassword, HttpServletRequest request
            , @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        boolean isReset = userService.resetPassword(resetPassword, request, userInfo);
        if (isReset) {
            return new ResponseEntity<>(new ReturnResponseNew(EncryptionDecryptionUtil.getEncryptedString(String.valueOf(HttpStatus.OK.value())),
                    EncryptionDecryptionUtil.getEncryptedString("Password successfully reset. User can now log in with the new credentials.")), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponseNew(EncryptionDecryptionUtil.getEncryptedString(String.valueOf(HttpStatus.BAD_REQUEST.value())),
                    EncryptionDecryptionUtil.getEncryptedString("The link is invalid or broken!")), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/changepassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnResponseNew> updateUserPassword(@RequestBody ChangePasswordDTO changePassword, HttpServletRequest request, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        boolean isUpdate = userService.changePassword(changePassword, request, userInfo);

        if (isUpdate) {
            return new ResponseEntity<>(new ReturnResponseNew(EncryptionDecryptionUtil.getEncryptedString(String.valueOf(HttpStatus.OK.value())),
                    EncryptionDecryptionUtil.getEncryptedString("Password successfully Changed. You can now log in with the new credentials.")), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponseNew(
                    EncryptionDecryptionUtil.getEncryptedString(String.valueOf(HttpStatus.BAD_REQUEST.value())),
                    EncryptionDecryptionUtil.getEncryptedString(Constant.REQUEST_CANNOT_BE_PROCESSED)), HttpStatus.BAD_REQUEST);
        }

    }

}
