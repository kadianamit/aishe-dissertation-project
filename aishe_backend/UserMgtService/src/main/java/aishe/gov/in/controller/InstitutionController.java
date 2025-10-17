package aishe.gov.in.controller;

import aishe.gov.in.mastersvo.AisheInstituteRequestCheckNameVO;
import aishe.gov.in.mastersvo.NewInstituteRequestCheckNameVO;
import aishe.gov.in.mastersvo.RequestForAddInstitute;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.AisheUserRequestService;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.EmailUtil;
import aishe.gov.in.utility.EncryptionDecryptionUtil;
import aishe.gov.in.utility.ReturnResponse;
import aishe.gov.in.utility.SMSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

@RestController
//@CrossOrigin("*")
public class InstitutionController {
    private static final Logger logger = LoggerFactory.getLogger(InstitutionController.class);
    @Autowired(required = true)
    private AisheUserRequestService aisheUserRequestService;

    @PostMapping(value="/requestforaddinstitute")
    public ResponseEntity<ReturnResponse> saveRequestForAddInstitute(HttpServletRequest request, @RequestParam("file") MultipartFile file,
                                                                     @RequestParam(required = false) String instituteTypeCorS, @RequestParam(required = false) Integer surveyYear,
                                                                     @RequestParam(required = false) String instituteName, @RequestParam(required = false) String stateCode,
                                                                     @RequestParam(required = false) String districtCode, @RequestParam(required = false) String collegeTypeId,
                                                                     @RequestParam(required = false) String universityId, @RequestParam(required = false) String locationId,
                                                                     @RequestParam(required = false) String managementId, @RequestParam(required = false) String admissionYear,
                                                                     @RequestParam(required = false) String isAffiliatedToOtherUniversity, @RequestParam(required = false) String otherAffiliatedUniversityName,
                                                                     @RequestParam(required = false) String admissionProcessCompleted, @RequestParam(required = false) Integer type,
                                                                     @RequestParam(required = false) Boolean isDeclarationAccepted, @RequestParam(required = false) String userId,
                                                                     @RequestParam(required = false) Integer roleId, @RequestParam(required = false) String stdCode,
                                                                     @RequestParam(required = false) String bcryptPassword, @RequestParam(required = false) String directorName,
                                                                     @RequestParam(required = false) String directorTelephone, @RequestParam(required = false) String directorEmail,
                                                                     @RequestParam(required = false) String directorDesignation, @RequestParam(required = false) String directorMobile,
                                                                     @RequestParam(required = false) Integer directorGender, @RequestParam(required = false) String personName,
                                                                     @RequestParam(required = false) String personDesignation, @RequestParam(required = false) String personEmail,
                                                                     @RequestParam(required = false) String personMobile, @RequestParam(required = false) String personLandline,
                                                                     @RequestParam(required = false) Integer personGender, @RequestParam(required = false) String personLine1,
                                                                     @RequestParam(required = false) String personLine2, @RequestParam(required = false) String personCity,
                                                                     @RequestParam(required = false) String personStateCode, @RequestParam(required = false) String personDistrictCode,
                                                                     @RequestParam(required = false) String personPincode, @RequestParam(required = false) String firstName,
                                                                     @RequestParam(required = false) String middleName, @RequestParam(required = false) String lastName,
                                                                     @RequestParam(required = false) Integer stateBodyId,@WithUser UserInfo userInfo) throws IOException {
        CommanObjectOperation.usernameValidate(userInfo);
        logger.info("university TeachingStaff controller : saveOrUpdateUniversityTeaching staff method invoked : {}");
        RequestForAddInstitute requestForAdd = new RequestForAddInstitute();

        //MediaType mediaType = MediaType.parseMediaType(file.getContentType());
        if (!file.getContentType().equals("application/pdf")) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
        }
        String passwordb = bcryptPassword;
        String decrypt = EncryptionDecryptionUtil.getDecryptedString(passwordb);
        requestForAdd.setInstituteTypeCorS(instituteTypeCorS);
        requestForAdd.setSurveyYear(surveyYear);
        requestForAdd.setInstituteName(instituteName);
        requestForAdd.setStateCode(stateCode);
        requestForAdd.setDistrictCode(districtCode);
        requestForAdd.setCollegeTypeId(collegeTypeId);
        requestForAdd.setUniversityId(universityId);
        requestForAdd.setLocationId(locationId);
        requestForAdd.setManagementId(managementId);
        requestForAdd.setAdmissionYear(admissionYear);
        requestForAdd.setIsAffiliatedToOtherUniversity(isAffiliatedToOtherUniversity);
        requestForAdd.setOtherAffiliatedUniversityName(otherAffiliatedUniversityName);
        requestForAdd.setAdmissionProcessCompleted(admissionProcessCompleted);
        requestForAdd.setType(type);
        requestForAdd.setIsDeclarationAccepted(isDeclarationAccepted);
        requestForAdd.setUserId(userId);
        requestForAdd.setRoleId(roleId);
        requestForAdd.setStdCode(stdCode);
        requestForAdd.setIsAffiliatedToOtherUniversity(isAffiliatedToOtherUniversity);
        requestForAdd.setBcryptPassword(decrypt);
        requestForAdd.setDirectorName(directorName);
        requestForAdd.setDirectorDesignation(directorDesignation);
        requestForAdd.setDirectorTelephone(directorTelephone);
        requestForAdd.setDirectorEmail(directorEmail);//director telephone
        requestForAdd.setDirectorMobile(directorMobile);
        requestForAdd.setDirectorGender(directorGender);
        requestForAdd.setPersonName(personName);
        requestForAdd.setPersonDesignation(personDesignation);
        requestForAdd.setPersonEmail(personEmail);
        requestForAdd.setPersonMobile(personMobile);
        requestForAdd.setPersonLandline(personLandline);
        requestForAdd.setPersonGender(personGender);
        requestForAdd.setPersonLine1(personLine1);
        requestForAdd.setPersonLine2(personLine2);
        requestForAdd.setPersonCity(personCity);
        requestForAdd.setPersonStateCode(personStateCode);
        requestForAdd.setPersonDistrictCode(personDistrictCode);
        requestForAdd.setPersonPincode(personPincode);    //person pincode
        requestForAdd.setFirstName(firstName);
        requestForAdd.setMiddleName(middleName);
        requestForAdd.setLastName(lastName);
        if (stateBodyId == 0) {
            requestForAdd.setStateBodyId(null);
        }
        requestForAdd.setStateBodyId(stateBodyId);
        byte[] byteArr = file.getBytes();
        boolean isPd = isPdf(byteArr);
        if (!isPd) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
        }
        InputStream inputStream = new ByteArrayInputStream(byteArr);
        inputStream.read();
        inputStream.close();
        requestForAdd.setDocumentPdf(byteArr);
        requestForAdd.setIpAddress(request.getHeader("X-Forwarded-For"));
        Integer isSaved = aisheUserRequestService.saveRequestForAddInstitute(requestForAdd);
        String message = "Dear User,<br /><br />\r\n" +
                "\r\n" +
                "Request for addition of  Institutions <b>'" + instituteName + "'</b> has been submitted successfully on AISHE Portal.<br/>\r\n" +
                "\r\n" +
                "Please save your Request Id <b>'" + isSaved + "'</b> for future reference.To know the current status of your Request Id click on the Link <a href=\"http://www.aishe.gov.in/aishe/addInstituteRequestStatus\">Track your Request Id</a>.<br/>\r\n" +
                "\r\n" +
                "Contact the State approving authority for consideration of your Request Id. The details of the Nodal Officers are mentioned in <a href=\"https://aishe.gov.in/aishe/contactDirectory\">Know Your Approving Authority</a>.<br/>\r\n" +
                "\r\n" +
                "<b>*Approval of Request Id at MoE level for addition of College/Institution may take 7 to 15 working days at MoE level only if it is approved by concerned AISHE Nodal Officer at State Level.</b>\r\n" +
                "\r\n" +
                "\r\n" +
                "Regards,<br/>\r\n" +
                "Team AISHE";
        EmailUtil.sendEmail(requestForAdd.getPersonEmail(), "Your Request For Adding Institution in Aishe Portal Is Successful", message);

        String smsContent = "Dear User,Request submitted with request ID " + isSaved + " on AISHE portal. Please Track your request ID on AISHE portal to get the status.Regards,Team AISHE";

        String templateId = "1007160733191284268";

        if (personMobile != null) {
            SMSUtil.sendMessageWithTemplatedId(personMobile, smsContent, templateId);
        }
        if (isSaved != null) {
            return new ResponseEntity<>(
                    new ReturnResponse(HttpStatus.OK.value(), "Request For Add Institute Details has been  Successfully Saved!!", isSaved),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Request Cannot Be Processed. Please Try Again.", null), HttpStatus.BAD_REQUEST);
        }
    }

    public boolean isPdf(byte[] data) {
        if (data == null || data.length < 5) return false;
        // %PDF-
        if (data[0] == 0x25 && data[1] == 0x50 && data[2] == 0x44 && data[3] == 0x46 && data[4] == 0x2D) {
            int offset = data.length - 8, count = 0; // check last 8 bytes for %%EOF with optional white-space
            boolean hasSpace = false, hasCr = false, hasLf = false;
            while (offset < data.length) {
                if (count == 0 && data[offset] == 0x25) count++; // %
                if (count == 1 && data[offset] == 0x25) count++; // %
                if (count == 2 && data[offset] == 0x45) count++; // E
                if (count == 3 && data[offset] == 0x4F) count++; // O
                if (count == 4 && data[offset] == 0x46) count++; // F
                // Optional flags for meta info
                if (count == 5 && data[offset] == 0x20) hasSpace = true; // SPACE
                if (count == 5 && data[offset] == 0x0D) hasCr = true; // CR
                if (count == 5 && data[offset] == 0x0A) hasLf = true; // LF / EOL
                offset++;
            }

            if (count == 5) {
                String version = data.length > 13 ? String.format("%s%s%s", (char) data[5], (char) data[6], (char) data[7]) : "?";
                logger.info("Version : %s | Space : %b | CR : %b | LF : %b%n", version, hasSpace, hasCr, hasLf);
                return true;
            }
        }

        return false;
    }

   // @GetMapping(value = "/institutenamecheck")
    public List<NewInstituteRequestCheckNameVO> InstituteNameCheck(@RequestParam(required = false) String typeId, @RequestParam(required = false) String districtCode,
                                                                   @RequestParam(required = false) String stateCode
            , @RequestParam(required = false) String instituteName) throws ParseException {
        logger.info("university controller : fetchCollegeCourse method invoked : {}");
        List<NewInstituteRequestCheckNameVO> collegeCourse = aisheUserRequestService.InstituteNameCheck(typeId, districtCode, stateCode, instituteName);
        return collegeCourse;
    }

    @GetMapping(value = "/institutenameexistinaishe")
    public List<AisheInstituteRequestCheckNameVO> InstituteNameExistInAishe(@RequestParam(required = false) String districtCode, @RequestParam(required = false) String instituteName) throws ParseException {
        logger.info("university controller : fetchCollegeCourse method invoked : {}");
        List<AisheInstituteRequestCheckNameVO> collegeCourse = aisheUserRequestService.InstituteNameExistInAishe(districtCode, instituteName.toLowerCase());
        return collegeCourse;
    }
}
