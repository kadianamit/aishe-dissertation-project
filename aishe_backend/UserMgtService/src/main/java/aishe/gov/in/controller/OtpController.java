/*
package aishe.gov.in.controller;

import aishe.gov.in.masterseo.OtpDetailsEO;
import aishe.gov.in.service.AisheOtpService;
import aishe.gov.in.utility.CommonUtils;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.EmailUtil;
import aishe.gov.in.utility.ReturnResponse;
import aishe.gov.in.utility.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class OtpController {
    @Autowired
    private AisheOtpService aisheUserRequestService;

    @PostMapping(value = "/send-otp-to-email")
    public ResponseEntity<ReturnResponse> sendOtpToEmail(@RequestParam(required = false) Integer latestId, @RequestParam String email) {
        Integer countAlreadyExist = aisheUserRequestService.findByEmailIdForCount(email);
        if (countAlreadyExist >= 1) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Please Try After 1 Minutes", null), HttpStatus.BAD_REQUEST);
        }
        String otp = CommonUtils.getRandomNumberString();
        OtpDetailsEO forgot = new OtpDetailsEO();
        forgot.setSno(latestId);
        forgot.setEmailId(email);
        forgot.setEmailOtp(otp);
        forgot.setEmailOtpDatetime(DateUtils.obtainCurrentTimeStamp());
        forgot.setIsEmailIdVerified(false);
        Integer isSave = aisheUserRequestService.saveEmailOtp(forgot);
        if (isSave != 0 || isSave != null) {
            String mail = email;
            String subject = "OTP from Aishe-Portal";
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, 15);
            String message = "OTP for email verification is (" + otp + "). Valid till " + cal.getTime() + ". Do not share the OTP with anyone for security reasons.<br><br>Regards,<br>Team AISHE";
            EmailUtil.sendEmail(mail, subject, message);
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "OTP Has Been Sent to Your Mail!!", isSave), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "This email address does not exist!", null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/send-otp-to-mobile")
    public ResponseEntity<ReturnResponse> sendOtpToMobile(@RequestParam(required = false) Integer latestId, @RequestParam String mobileNumber) {
        Integer countAlreadyExist = aisheUserRequestService.findByMobileNumberForCount(mobileNumber);
        if (countAlreadyExist >= 1) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Please Try After 1 Minutes", null), HttpStatus.BAD_REQUEST);
        }
        String otp = CommonUtils.getRandomNumberString();
        //Integer mobileOTP = Integer.valueOf(otp);
        OtpDetailsEO forgot = new OtpDetailsEO();
        forgot.setSno(latestId);
        forgot.setMobileNumber(mobileNumber);
        forgot.setMobileOtp(otp);
        forgot.setMobileOtpDatetime(DateUtils.obtainCurrentTimeStamp());
        forgot.setIsMobilenoVerified(false);
        Integer isSave = aisheUserRequestService.saveMobileOtp(forgot);
        if (isSave != 0 || isSave != null) {
            String mail = mobileNumber;
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, 15);
            SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date date = cal.getTime();

            String date1 = format1.format(date);
            String message = "OTP for mobile number verification is " + otp + ".Valid till " + date1 + ".Do not share the OTP with anyone for security reasons.Regards,Team AISHE";
            SMSUtil.sendMessageWithTemplatedId(mail, message, "1007161224200505533");  //template id for otp message

            return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "OTP Has Been Sent to Your Mail!!", isSave), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "This email address does not exist!", null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/verify-mobile-otp")
    public ResponseEntity<ReturnResponse> verifyMobileOtp(@RequestParam String mobile, @RequestParam String otp) {
        Boolean isSave = aisheUserRequestService.verifyMobileOtp(mobile, otp);
        if (isSave) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "OTP Has Been Verified Successfully!!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Problem Occurred!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/verify-email-otp")
    public ResponseEntity<ReturnResponse> verifyEmailOtp(@RequestParam String email, @RequestParam String otp) {
        Boolean isSave = aisheUserRequestService.verifyEmailOtp(email, otp);
        if (isSave) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "OTP Has Been Verified Successfully!!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Problem Occurred!"), HttpStatus.BAD_REQUEST);
        }
    }
}
*/
