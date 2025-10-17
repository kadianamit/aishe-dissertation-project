package aishe.gov.in.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import aishe.gov.in.enums.Constant;
import aishe.gov.in.exception.InvalidInputException;
import aishe.gov.in.masterseo.DataSharingApiUserInformationEO;
import aishe.gov.in.masterseo.DataSharingUserMasterEO;
import aishe.gov.in.masterseo.OtpDetailsMouEO;
import aishe.gov.in.masterseo.RefDataTypeRequiredEO;
import aishe.gov.in.masterseo.RefOrganizationCategoryEO;
import aishe.gov.in.masterseo.RefStatusEO;
import aishe.gov.in.masterseo.RefUserCategoryEO;
import aishe.gov.in.mastersvo.ChangePasswordDTO;
import aishe.gov.in.mastersvo.DataSharingApiUserInformationVO;
import aishe.gov.in.mastersvo.DataSharingApiUserInformationWithStatus;
import aishe.gov.in.mastersvo.DataSharingApiUserStatusUpdate;
import aishe.gov.in.mastersvo.ForgotPasswordDto;
import aishe.gov.in.mastersvo.ResetPasswordDTO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.DataSharingService;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.CommonUtils;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.EncryptionDecryptionUtil;
import aishe.gov.in.utility.IpAddressProvider;
import aishe.gov.in.utility.PdfOperation;
import aishe.gov.in.utility.ReturnResponse;
import aishe.gov.in.utility.ReturnResponseNew;

@RestController
@RequestMapping("/data-sharing")
public class DataSharingController {

    @Autowired
    private DataSharingService dataSharingService;

    @PostMapping(value = "/api-user-information")
    public ResponseEntity<ReturnResponse> apiUserInformation(@RequestPart String requestVo, @RequestPart("file") MultipartFile file, HttpServletRequest request) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        DataSharingApiUserInformationVO vo = objectMapper.readValue(requestVo, DataSharingApiUserInformationVO.class);
        if (PdfOperation.isPdf(file.getBytes())) {
            if (!file.getContentType().equals("application/pdf"))
                throw new InvalidInputException("Incorrect file type, PDF required.");
            PdfOperation.checkFileName(file.getOriginalFilename());
        } else {
            throw new InvalidInputException("Incorrect file type, PDF required.");
        }
        DataSharingApiUserInformationEO eo = new DataSharingApiUserInformationEO();
        BeanUtils.copyProperties(vo, eo);
        eo.setRequestLetter(file.getBytes());
        eo.setRequestLetterName(file.getOriginalFilename());
        eo.setIpAddress(IpAddressProvider.getClientIpAddr(request));
        eo.setSubmissionDate(DateUtils.obtainCurrentTimeStamp());
        String lastFormDate = dataSharingService.lastRequestId();
        eo.setRequiredDataTypeId(vo.getRequiredDataTypeId());
        if (null == lastFormDate) {
            eo.setRequestId(CommonUtils.generateRequestId(1));
        } else {
            String[] lastReqest = lastFormDate.split("-");
            if (lastReqest[1].equals(DateUtils.formatDateWithOutBackslash())) {
                eo.setRequestId(CommonUtils.generateRequestId(Integer.valueOf(lastReqest[2]) + 1));
            } else {
                eo.setRequestId(CommonUtils.generateRequestId(1));
            }
        }
        String aBoolean = dataSharingService.apiUserInformation(eo);
        ReturnResponse returnResponse = ((aBoolean != null) ? new ReturnResponse(HttpStatus.CREATED.value(), "api-user-information has been Successfully Updated!!", aBoolean)
                : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @GetMapping(value = "/api-user-information")
    public ResponseEntity<ReturnResponse> apiUserInformation(@RequestParam(defaultValue = "-1") Integer status, @RequestParam(defaultValue = "-1") Integer orgCategory,
                                                             @RequestParam(defaultValue = "-1") Integer ministryId, @RequestParam(defaultValue = "-1") Integer id) throws IOException {
        List<DataSharingApiUserInformationWithStatus> aBoolean = dataSharingService.apiUserInformationList(status, orgCategory, ministryId, null, id);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "api-user-information has been Successfully Updated!!", aBoolean);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/update-status-api-user-information")
    public ResponseEntity<ReturnResponse> apiUserInformationUpdateStatus(@RequestBody DataSharingApiUserStatusUpdate eo, @WithUser UserInfo userInfo, HttpServletRequest request) {

        Boolean aBoolean = dataSharingService.apiUserInformationUpdateStatus(eo, userInfo, request);
        ReturnResponse returnResponse = ((aBoolean != null) ? new ReturnResponse(HttpStatus.CREATED.value(), "api-user-information-status has been Successfully Updated!!", aBoolean)
                : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/user-master-request")
    public ResponseEntity<ReturnResponse> dataSharingUserMaster(@RequestBody DataSharingUserMasterEO eo, HttpServletRequest request) {

        String aBoolean = dataSharingService.dataSharingUserMaster(eo, request);

        if (aBoolean != null && aBoolean.equals("success")) {
            return new ResponseEntity<>(
                    new ReturnResponse(HttpStatus.OK.value(), "User Successfully Registered!!"),
                    HttpStatus.OK);
        } else if (aBoolean != null && aBoolean.equals("Exist")) {
            return new ResponseEntity<>(
                    new ReturnResponse(HttpStatus.OK.value(), "User Already Exists!!"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Request Cannot Be Processed. Please Try Again.", null), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user-master-request")
    public ResponseEntity<ReturnResponse> userMasterRequest(@RequestParam(required = false) String username) throws IOException {
        List<DataSharingUserMasterEO> listRequest = dataSharingService.userMasterRequest(username);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "User Master Registration Data!!", listRequest);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/ref-user-category")
    public ResponseEntity<ReturnResponse> refUserCategory(@RequestParam(required = false) Integer id) {
        List<RefUserCategoryEO> aBoolean = dataSharingService.refUserCategory(id);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success", aBoolean);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/ref-data-type-required")
    public ResponseEntity<ReturnResponse> refDataTypeRequired(@RequestParam(required = false) Integer id) {
        List<RefDataTypeRequiredEO> aBoolean = dataSharingService.refDataTypeRequired(id);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success", aBoolean);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/ref-organization-category")
    public ResponseEntity<ReturnResponse> refOrgCategory(@RequestParam(required = false) Integer id) {
        List<RefOrganizationCategoryEO> aBoolean = dataSharingService.refOrgCategory(id);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success", aBoolean);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/ref-status")
    public ResponseEntity<ReturnResponse> refStatus(@RequestParam(required = false) Integer id) {
        List<RefStatusEO> aBoolean = dataSharingService.refStatus(id);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success", aBoolean);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @PostMapping(value = "/forgot-password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnResponseNew> forgotUserPassword(@RequestParam String email) {
        String existingUser = dataSharingService.findByEmailIdIgnoreCase(EncryptionDecryptionUtil.getDecryptedString(email));
        if (existingUser != null) {
            String otp = CommonUtils.getRandomNumberString();
            OtpDetailsMouEO forgot = new OtpDetailsMouEO();
            forgot.setUserId(existingUser);
            forgot.setType("EMAIL");
            forgot.setOtp(otp);
            forgot.setCreatedDate(DateUtils.obtainCurrentTimeStamp());
            forgot.setExpiresOn(DateUtils.obtainCurrentTimeStamp().plusMinutes(15));
            boolean isSave = dataSharingService.saveForgotPassword(forgot);
            if (isSave) {
                String subject = "Data Sharing Verify Otp Forgot Password";
                // Util.sendEmail(email, subject, "Your Otp To Verify Your Identity For Aishe Is " + otp);
                return new ResponseEntity<>(new ReturnResponseNew(EncryptionDecryptionUtil.getEncryptedString(String.valueOf(HttpStatus.OK.value())), EncryptionDecryptionUtil.getEncryptedString("Link Has Been Sent to Your Mail!!")), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ReturnResponseNew(EncryptionDecryptionUtil.getEncryptedString(String.valueOf(HttpStatus.BAD_REQUEST.value())), EncryptionDecryptionUtil.getEncryptedString("Some Error Occurred!!")), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new ReturnResponseNew(EncryptionDecryptionUtil.getEncryptedString(String.valueOf(HttpStatus.BAD_REQUEST.value())), EncryptionDecryptionUtil.getEncryptedString("This email address does not exist!")), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/verifyforgotpassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReturnResponse> verifyOtpForgotPassword(@RequestBody ForgotPasswordDto forgotPassword) {
        ForgotPasswordDto.decript(forgotPassword);
        String verifystatus = dataSharingService.verifyOtpForogtPassword(forgotPassword);
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
        boolean isReset = dataSharingService.resetPassword(resetPassword, request, userInfo);
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
        boolean isUpdate = dataSharingService.changePassword(changePassword, request, userInfo);

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
