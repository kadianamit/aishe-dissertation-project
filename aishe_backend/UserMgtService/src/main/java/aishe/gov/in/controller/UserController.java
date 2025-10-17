package aishe.gov.in.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import aishe.gov.in.enums.FormUploadStatus;
import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.enums.SortBy;
import aishe.gov.in.enums.UserStatus;
import aishe.gov.in.masterseo.ActiveInstituteInactiveUser;
import aishe.gov.in.masterseo.ApproveDisapproveUserDTO;
import aishe.gov.in.masterseo.ApproveUserDTO;
import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.masterseo.RefUserRoleMaster;
import aishe.gov.in.masterseo.UserMasterDetailEO;
import aishe.gov.in.masterseo.UserMasterDetailNewEO;
import aishe.gov.in.masterseo.UserMasterEO;
import aishe.gov.in.masterseo.UserMasterLogDetailEO;
import aishe.gov.in.masterseo.UserMasterNewEO;
import aishe.gov.in.masterseo.UserMasterRequestDetailEO;
import aishe.gov.in.masterseo.UserRegistrationDetailEO;
import aishe.gov.in.masterseo.UserRegistrationUpdatedDetailEO;
import aishe.gov.in.mastersvo.ApprovingAuthorityDTO;
import aishe.gov.in.mastersvo.RegisteredUserDTO;
import aishe.gov.in.mastersvo.UserMasterDTO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.AisheUserRequestService;
import aishe.gov.in.service.UserRegistrationService;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.EncryptionDecryptionUtil;
import aishe.gov.in.utility.NullValueHandler;
import aishe.gov.in.utility.ReturnResponse;

@RestController

public class UserController {
    @Autowired
    private UserRegistrationService institutionService;
    @Autowired(required = true)
    private AisheUserRequestService aisheUserRequestService;
    @Autowired
    private NullValueHandler handler;
    @Autowired
    RestTemplate restTemplate;
    private static final Logger logger1 = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/api/user")
    public ResponseEntity<ReturnResponse> updateUserRegistration(@RequestBody @Valid UserMasterNewEO userMasterEO,
                                                                 BindingResult bindingResult, HttpServletRequest request, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
       // UserInfo userInfo=new UserInfo("anwar.khan",0);
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        if (null != userMasterEO.getStateId())
            userMasterEO.setStateCode(userMasterEO.getStateId());
        String instituteType = null;
        if (userMasterEO.getAisheCode() != null) {
            String[] splitted = userMasterEO.getAisheCode().trim().split("\\s*-\\s*");
            instituteType = splitted[0];
            if (instituteType.equals("C")) {
                userMasterEO.setBodyType("1");
            }
            if (instituteType.equals("S")) {
                userMasterEO.setBodyType("0");
            }
        }
        userMasterEO.setPrivilegeId(3);
        UserMasterNewEO masterEO = institutionService.saveUpdateRegistration(userMasterEO, request, userInfo);
        InstituteDetailEO instituteDetail = institutionService.getInstituteDetail(userMasterEO.getAisheCode());
        if (instituteDetail != null) {
            instituteDetail.setCategoryType(instituteType);
            instituteDetail.setAisheCode(userMasterEO.getAisheCode());
            instituteDetail.setManagementId(userMasterEO.getManagementId());
            instituteDetail.setOwnerShipId(userMasterEO.getOwnerShipId());
            instituteDetail.setLocationId(userMasterEO.getLocationId());
            instituteDetail.setStateId(userMasterEO.getStateCode());
            instituteDetail.setDistrictId(userMasterEO.getDistrictId());
            instituteDetail.setAddressLine1(userMasterEO.getAddressLine1());
            instituteDetail.setAddressLine2(userMasterEO.getAddressLine2());
            instituteDetail.setPinCode(userMasterEO.getPinCode());
            instituteDetail.setLatitude(userMasterEO.getLatitude());
            instituteDetail.setLongitude(userMasterEO.getLongitude());

            instituteDetail.setNodalOfficerName(userMasterEO.getNodalOfficerName());
            instituteDetail.setNodalOfficerGender(userMasterEO.getNodalOfficerGender());
            instituteDetail.setNodalOfficerDesignation(userMasterEO.getNodalOfficerDesignation());
            instituteDetail.setNodalOfficerEmail(userMasterEO.getEmail());
            instituteDetail.setNodalOfficerMobile(userMasterEO.getMobile());
            instituteDetail.setNodalOfficerTelephone(userMasterEO.getPhoneLandline());

            instituteDetail.setHeadOfficerName(userMasterEO.getInstituteHeadName());
            instituteDetail.setHeadOfficerGender(userMasterEO.getHeadOfficerGender());
            instituteDetail.setHeadOfficerDesignation(userMasterEO.getInstituteHeadDesignation());
            instituteDetail.setHeadOfficerEmail(userMasterEO.getInstituteHeadEmail());
            instituteDetail.setHeadOfficerMobile(userMasterEO.getInstituteHeadMobile());
            instituteDetail.setHeadOfficerTelephone(userMasterEO.getInstituteHeadTelephone());
            instituteDetail.setBlockId(userMasterEO.getBlockId());
            instituteDetail.setUlbId(userMasterEO.getUlbId());
            instituteDetail.setPinCode(userMasterEO.getPinCode());
            instituteDetail.setCity(userMasterEO.getCity());
            instituteDetail.setWebsite(userMasterEO.getWebsite());
            instituteDetail.setUniversityId(userMasterEO.getUniversityId());
            instituteDetail.setIsGeospatialDataVerified(userMasterEO.getIsGeospatialDataVerified());

            instituteDetail.setSubDistrictId(userMasterEO.getSubDistrictId());
            instituteDetail.setIslgdDistrictVerified(userMasterEO.getIslgdDistrictVerified());
            instituteDetail.setIsLgdSubDistrictVerified(userMasterEO.getIsLgdSubDistrictVerified());
            instituteDetail.setIslgdStateVerified(userMasterEO.getIsLgdStateVerified());
            instituteDetail.setIsBisagVerified(userMasterEO.getIsBisagVerified());

            instituteDetail.setNodalOfficerTitleId(userMasterEO.getNodalOfficerTitleId());
            instituteDetail.setHeadOfficerTitleId(userMasterEO.getHeadOfficerTitleId());

            instituteDetail.setIsOtherAffiliatingUniversityStatuatoryBody(userMasterEO.getIsOtherAffiliatingUniversityStatuatoryBody());
            instituteDetail.setOtherAffiliatingUniversity(userMasterEO.getOtherAffiliatingUniversity());
            instituteDetail.setStatuatoryBody(userMasterEO.getStatuatoryBody());
            instituteDetail.setMinistryId(userMasterEO.getMinistryId());
            instituteDetail.setIsInstituteDetailSaved(userMasterEO.getIsInstituteDetailSaved());
            if (null != userMasterEO.getConstructedArea())
                instituteDetail.setConstructedArea(userMasterEO.getConstructedArea());
            if (null != userMasterEO.getArea())
                instituteDetail.setArea(userMasterEO.getArea());
            institutionService.updateInfoInstituteDetail(instituteDetail);
        }
        if (masterEO == null) {
            ReturnResponse returnResponse = ((masterEO == null) ? new ReturnResponse(HttpStatus.UNAUTHORIZED.value(), "Please Verify Your Email or Mobile Or Both!!", null) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
            return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
        }
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.CREATED.value(), "User Registration has been Successfully Updated!!", masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/api/user")
    public ResponseEntity<ReturnResponse> getRegisteredUserByUserId(@RequestParam(required = false) String userId, @RequestParam(required = false) Integer currentSurveyYear) {
        UserMasterEO masterEO1 = institutionService.getUserRegistration(userId, currentSurveyYear);
        UserMasterDTO masterEO = null;
        if (null != masterEO1) {
            masterEO = new UserMasterDTO();
            BeanUtils.copyProperties(masterEO1, masterEO);
            if (null != masterEO.getEmail())
                masterEO.setEmail(EncryptionDecryptionUtil.getEncryptedString(masterEO.getEmail()));

            if (null != masterEO.getMobile())
                masterEO.setMobile(EncryptionDecryptionUtil.getEncryptedString(masterEO.getMobile()));

            if (null != masterEO.getPhoneLandline())
                masterEO.setPhoneLandline(EncryptionDecryptionUtil.getEncryptedString(masterEO.getPhoneLandline()));
        }
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), ""));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/api/user-private")
    public ResponseEntity<ReturnResponse> getRegisteredUserByUserIdPrivate(@RequestParam(required = false) String userId, @RequestParam(required = false) Integer currentSurveyYear, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        UserMasterEO masterEO = institutionService.getUserRegistration(userId, currentSurveyYear);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), ""));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/api/user-status")
    public ResponseEntity<ReturnResponse> getUserByUserId(@RequestParam(required = false) String aisheCode) {
        UserMasterEO masterEO = institutionService.getUserRegistrationAisheCode(aisheCode);
        if (masterEO == null) {
            masterEO = new UserMasterEO();
        }
        if (masterEO.getUserId() == null) {
            UserMasterLogDetailEO logdetail = institutionService.getUserLogDetail(aisheCode);
            if (logdetail != null) {
                BeanUtils.copyProperties(logdetail, masterEO);
            }
            if (logdetail == null) {
                UserMasterRequestDetailEO requestdetail = institutionService.getUserRequestDetail(aisheCode);
                if (requestdetail != null) {
                    BeanUtils.copyProperties(requestdetail, masterEO);
                }
            }
        }
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), ""));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/api/user-status-private")
    public ResponseEntity<ReturnResponse> getUserByUserId(@RequestParam(required = false) String aisheCode, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        UserMasterEO masterEO = institutionService.getUserRegistrationAisheCode(aisheCode);
        if (masterEO == null) {
            masterEO = new UserMasterEO();
        }
        if (masterEO.getUserId() == null) {
            UserMasterLogDetailEO logdetail = institutionService.getUserLogDetail(aisheCode);
            if (logdetail != null) {
                BeanUtils.copyProperties(logdetail, masterEO);
            }
            if (logdetail == null) {
                UserMasterRequestDetailEO requestdetail = institutionService.getUserRequestDetail(aisheCode);
                if (requestdetail != null) {
                    BeanUtils.copyProperties(requestdetail, masterEO);
                }
            }
        }
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), ""));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/useridalreadyexist")
    public Map<String, String> userIdAlreadyExist(@RequestParam(required = false) String userId) {
        logger1.info("university controller : fetchCollegeCourse method invoked : {}");
        Boolean isUserExist = aisheUserRequestService.userIdAlreadyExist(userId);
        if (isUserExist == null || isUserExist.equals(false)) {
            isUserExist = aisheUserRequestService.userIdAlreadyExistInLogs(userId);
        }
        Map<String, String> data = new HashMap<String, String>();
        if (isUserExist) {
            data.put("UserExist", "YES");
        } else {
            data.put("UserExist", "NO");
        }
        return data;
    }

    @GetMapping(value = "/api/user/list")
    public ResponseEntity<ReturnResponse> getRegisteredUserListByPagination(@RequestParam Integer roleId, @RequestParam(required = false) Integer surveyYear, @RequestParam(required = false) UserStatus userStatus, @RequestParam(required = false) Integer deoRoleId,
                                                                            @RequestParam(required = false) InstitutionType instituteType, @RequestParam(required = false) Boolean dcfStatus, @RequestParam(required = false) FormUploadStatus formUploadStatus,
                                                                            @RequestParam(required = false) Boolean isApproved, @RequestParam(required = false) String stateCode, @RequestParam(required = false) String aishecode,
                                                                            @RequestParam(required = false) String universityId, @RequestParam SortBy sortBy, /*@RequestParam int page, @RequestParam int pageSize ,*/    @RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate, @RequestParam(required = false) String searchText, @RequestParam(required = false) String categoryType
                                                                            /*@RequestParam Integer roleId, @RequestParam(required = false) String surveyYear, @RequestParam(required = false) String userStatus, @RequestParam(required = false) String deoRoleId,
                                                                            @RequestParam(required = false) InstitutionType instituteType, @RequestParam(required = false) String dcfStatus, @RequestParam(required = false) String formUploadStatus,
                                                                            @RequestParam(required = false) String isApproved, @RequestParam(required = false) String stateCode, @RequestParam(required = false) String aishecode,
                                                                            @RequestParam(required = false) String universityId, @RequestParam SortBy sortBy, @RequestParam int page, @RequestParam int pageSize*/, @WithUser UserInfo userInfo) {
        /*if (pageSize <= 0 || page <= 0) {
            ReturnResponse returnResponse = new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "invalid page or page size ");
            return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
        }*/
        CommanObjectOperation.usernameValidate(userInfo);

        List<RegisteredUserDTO> registeredUserDTO = new ArrayList<>();
        if (userStatus != null && userStatus.getActionType() != null && userStatus.getActionType().equals(UserStatus.New_REGISTRATION.getActionType()) && (roleId != null && roleId != 7)) {
            registeredUserDTO = institutionService.getRegisteredUserRequest(roleId, surveyYear, userStatus == null ? null : userStatus.getActionType(), deoRoleId, dcfStatus, formUploadStatus == null ? null : formUploadStatus.getType(), handler.nullValueHandler(stateCode), universityId, aishecode, isApproved, sortBy, instituteType != null ? instituteType.getType() : null,/* page, pageSize,*/fromDate, toDate, handler.nullValueHandler(searchText), handler.nullValueHandler(categoryType));
        } else if (userStatus != null && userStatus.getActionType() != null && (userStatus.getActionType().equals(UserStatus.INACTIVE.getActionType()) || userStatus.getActionType().equals(UserStatus.USER_DISAPPROVED.getActionType()))) {
            registeredUserDTO = institutionService.getRegisteredUserDisapprovedInactive(roleId, surveyYear, userStatus == null ? null : userStatus.getActionType(), deoRoleId, dcfStatus, formUploadStatus == null ? null : formUploadStatus.getType(), handler.nullValueHandler(stateCode), universityId, aishecode, isApproved, sortBy, instituteType != null ? instituteType.getType() : null,/* page, pageSize,*/fromDate, toDate, handler.nullValueHandler(searchText), handler.nullValueHandler(categoryType));
        } else {
            registeredUserDTO = institutionService.getRegisteredUser(roleId, surveyYear, userStatus == null ? null : userStatus.getActionType(), deoRoleId, dcfStatus, formUploadStatus == null ? null : formUploadStatus.getType(), handler.nullValueHandler(stateCode), universityId, aishecode, isApproved, sortBy, instituteType != null ? instituteType.getType() : null,/* page, pageSize,*/fromDate, toDate, handler.nullValueHandler(searchText), handler.nullValueHandler(categoryType));
        }
        // BigInteger total = institutionService.getRegisteredUserCont(roleId, surveyYear, userStatus == null ? null : userStatus.getActionType(), deoRoleId, dcfStatus, formUploadStatus == null ? null : formUploadStatus.getType(), handler.nullValueHandler(stateCode), universityId, aishecode, isApproved, instituteType != null ? instituteType.getType() : null, fromDate, toDate, handler.nullValueHandler(searchText),handler.nullValueHandler(categoryType));
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", registeredUserDTO != null ? registeredUserDTO : Collections.EMPTY_LIST, BigInteger.valueOf(0));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/api/user/approval")
    public ResponseEntity<ReturnResponse> approveUser(@RequestBody @Valid ApproveUserDTO approveUserDTO, BindingResult bindingResult, HttpServletRequest request, @WithUser UserInfo userInfo) throws Exception {
        CommanObjectOperation.usernameValidate(userInfo);
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        UserMasterDetailEO masterEO = institutionService.saveUserApproval(approveUserDTO, request);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), "User Approval Details Successfully Updated!!", masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/api/user/approve-disapprove")
    public ResponseEntity<ReturnResponse> approveDisapprvoeUser(@RequestBody @Valid ApproveDisapproveUserDTO approveUserDTO, BindingResult bindingResult, HttpServletRequest request/*, @WithUser UserInfo userInfo*/) {
       // CommanObjectOperation.usernameValidate(userInfo);
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        UserMasterDetailNewEO masterEO = institutionService.approveDisapprvoeUserApproval(approveUserDTO, request);
        ReturnResponse returnResponse = null;

        returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), "User Approval/Disapproval Details Successfully Updated!!", masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/api/userregistration")
    public ResponseEntity<ReturnResponse> saveUserRegistration(@RequestBody UserRegistrationDetailEO userMasterEO, HttpServletRequest request//, @WithUser UserInfo userInfo
    ) {
        userMasterEO.setIpAddress(request.getHeader("X-Forwarded-For"));
        if (userMasterEO.getIpAddress() == null) {
            userMasterEO.setIpAddress(request.getRemoteAddr());
        }
        if (userMasterEO.getAisheCode() != null) {
            String[] splitted = userMasterEO.getAisheCode().trim().split("\\s*-\\s*");
            String instituteType = splitted[0];
            if (instituteType.equals("C")) {
                userMasterEO.setBodyType("1");
            }
            if (instituteType.equals("S")) {
                userMasterEO.setBodyType(userMasterEO.getBodyType());
            }
        }
        userMasterEO.setPrivilegeId(3);
        UserRegistrationDetailEO masterEO = institutionService.saveUserRegistrationData(userMasterEO);
        if (masterEO == null) {
            ReturnResponse returnResponse = ((masterEO == null) ? new ReturnResponse(HttpStatus.UNAUTHORIZED.value(), "Please Verify Your Email or Mobile Or Both!!", null) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
            return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
        }
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.CREATED.value(), "User Registration has been Successfully Updated!!", masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/userstatuscount")
    public Map<String, Integer> userCountStatus(@RequestParam(required = false) Integer statusId, @RequestParam(required = false) String stateCode) {
        Integer userCountWithStatus = aisheUserRequestService.userStatusByCount(statusId, stateCode);
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("UserCount", userCountWithStatus);
        return data;
    }

    @PostMapping("/documentuploaduser")
    public ResponseEntity<ReturnResponse> saveRequestForAddInstitute(@RequestParam("file") MultipartFile file,
                                                                     @RequestParam(required = false) String userId) throws ParseException, IOException {
        logger1.info("university TeachingStaff controller : documentuploaduser api staff method invoked : {}");
        UserRegistrationDetailEO requestForAdd = new UserRegistrationDetailEO();

        if (!file.getContentType().equals("application/pdf")) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
        }
        byte[] byteArr = file.getBytes();
        boolean isPd = isPdf(byteArr);
        if (!isPd) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
        }
        InputStream inputStream = new ByteArrayInputStream(byteArr);
        inputStream.read();
        inputStream.close();
        requestForAdd.setDocument(byteArr);
        requestForAdd.setUserId(userId);
        requestForAdd.setDocumentName(file.getOriginalFilename());
        String isSaved = aisheUserRequestService.saveDocumentForUser(requestForAdd);

        if (isSaved.equals("success")) {
            return new ResponseEntity<>(
                    new ReturnResponse(HttpStatus.OK.value(), "Document For Add Institute Details has been  Successfully Saved!!", isSaved),
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
                System.out.printf("Version : %s | Space : %b | CR : %b | LF : %b%n", version, hasSpace, hasCr, hasLf);
                return true;
            }
        }

        return false;
    }

    @GetMapping(value = "/approvingauthority")
    public List<ApprovingAuthorityDTO> approvingAuthority(@RequestParam Integer roleId, @RequestParam(required = false) String stateCode
            , @RequestParam(required = false) List<Integer> authorityId, @RequestParam(required = false) String universityId) {
        List<Integer> moe = new ArrayList<Integer>();
        if (authorityId != null && authorityId.contains(1)) {
            moe.add(1);
        }
        List<ApprovingAuthorityDTO> data = new ArrayList<>();
        if (roleId != 20) {
            data = aisheUserRequestService.userApprovingAuthority(roleId, stateCode, authorityId, universityId);
        }
        if (moe != null && moe.contains(1)) {
            UserMasterNewEO userd = aisheUserRequestService.fetchUserMasterByUserId();
            ApprovingAuthorityDTO roleId1 = new ApprovingAuthorityDTO();
            roleId1.setEmailId(userd.getEmail());
            roleId1.setFirstName(userd.getName());
            roleId1.setLandline(userd.getPhoneLandline());
            // roleId1.setLastName(userd.getLastName());
            // roleId1.setMiddleName(userd.getMiddleName());
            roleId1.setMobile(userd.getMobile());
            roleId1.setRoleName("Ministry of Education");
            roleId1.setStdCode(userd.getStdCode());
            data.add(roleId1);
        }

        return data;
    }

    @GetMapping(value = "/snoalreadyexist")
    public Map<String, String> snoAlreadyExist(@RequestParam(required = false) String stateCode, @RequestParam(required = false) Integer roleId, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        Boolean isUserExist = aisheUserRequestService.snoAlreadyExist(stateCode, roleId);
        Map<String, String> data = new HashMap<String, String>();
        if (isUserExist) {
            data.put("SnoExist", "YES");
        } else {
            data.put("SnoExist", "NO");
        }
        return data;
    }

    @PostMapping(value = "/api/userupdate")
    public ResponseEntity<ReturnResponse> updateUserRegistration(@RequestBody UserRegistrationUpdatedDetailEO userMasterEO, HttpServletRequest request, @WithUser UserInfo userInfo
    		, @RequestParam(defaultValue ="false",required = false) boolean isUpdated) {
        //  UserInfo userInfo = new UserInfo("anwar.khan", 0);
        if (userMasterEO.getAisheCode() != null) {
            String[] splitted = userMasterEO.getAisheCode().trim().split("\\s*-\\s*");
            String instituteType = splitted[0];
            if (instituteType.equals("C")) {
                userMasterEO.setBodyType("1");
            }
            if (instituteType.equals("S")) {
                userMasterEO.setBodyType("0");
            }
        }
        userMasterEO.setIpAddress(request.getHeader("X-Forwarded-For"));
        if (userMasterEO.getIpAddress() == null) {
            userMasterEO.setIpAddress(request.getRemoteAddr());
        }
        userMasterEO.setPrivilegeId(3);
        UserRegistrationUpdatedDetailEO masterEO = institutionService.updateUserRegistration(userMasterEO,isUpdated, userInfo);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.CREATED.value(), "User Registration has been Successfully Updated!!", masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/approving-authority")
    public List<ApprovingAuthorityDTO> approvingAuthorities(@RequestParam Integer roleId, @RequestParam String stateCode, @RequestParam(required = false) String universityId) {
        List<Integer> authorityId = null;
        List<ApprovingAuthorityDTO> data = new ArrayList<>();
        ApprovingAuthorityDTO roleId1 = null;
        RefUserRoleMaster roleMaster = aisheUserRequestService.fetchRoleDetails(roleId);
        if (null != roleMaster) {
            authorityId = roleMaster.getApprovingAuthority();
            if (null != authorityId && authorityId.contains(1)) {
                UserMasterNewEO userd = aisheUserRequestService.fetchUserMasterByUserId();
                roleId1 = new ApprovingAuthorityDTO();
                roleId1.setEmailId(userd.getEmail());
                roleId1.setFirstName(userd.getName());
                roleId1.setLandline(userd.getPhoneLandline());
                // roleId1.setLastName(userd.getLastName());
                // roleId1.setMiddleName(userd.getMiddleName());
                roleId1.setMobile(userd.getMobile());
                roleId1.setRoleName("Ministry of Education");
                roleId1.setStdCode(userd.getStdCode());
            }
        }
        data = aisheUserRequestService.userApprovingAuthorities(roleId, handler.nullValueHandler(stateCode), authorityId, handler.nullValueHandler(universityId));
        if (null != roleId1)
            data.add(roleId1);
        return data;
    }

    @PostMapping(value = "/api/userregistration-new")
    public ResponseEntity<ReturnResponse> saveUserRegistrationNew(@RequestBody UserMasterRequestDetailEO userMasterEO, HttpServletRequest request
    ) {
        userMasterEO.setIpAddress(request.getHeader("X-Forwarded-For"));
        if (userMasterEO.getIpAddress() == null) {
            userMasterEO.setIpAddress(request.getRemoteAddr());
        }
        if (userMasterEO.getAisheCode() != null) {
            String[] splitted = userMasterEO.getAisheCode().trim().split("\\s*-\\s*");
            String instituteType = splitted[0];
            if (instituteType.equals("C")) {
                userMasterEO.setBodyType("1");
            }
            if (instituteType.equals("S")) {
                userMasterEO.setBodyType(userMasterEO.getBodyType());
            }
        }
        userMasterEO.setPrivilegeId(3);
        UserMasterRequestDetailEO masterEO = institutionService.saveUserRegistrationNew(userMasterEO, request,null);
        if (masterEO == null) {
            ReturnResponse returnResponse = new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again");
            return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
        }
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.CREATED.value(), "User Registration has been Successfully Updated!!", masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/api/userregistration-private")
    public ResponseEntity<ReturnResponse> saveUserRegistrationNew(@RequestBody UserMasterRequestDetailEO userMasterEO, HttpServletRequest request, @WithUser UserInfo userInfo
    ) {
        userMasterEO.setIpAddress(request.getHeader("X-Forwarded-For"));
        if (userMasterEO.getIpAddress() == null) {
            userMasterEO.setIpAddress(request.getRemoteAddr());
        }
        if (userMasterEO.getAisheCode() != null) {
            String[] splitted = userMasterEO.getAisheCode().trim().split("\\s*-\\s*");
            String instituteType = splitted[0];
            if (instituteType.equals("C")) {
                userMasterEO.setBodyType("1");
            }
            if (instituteType.equals("S")) {
                userMasterEO.setBodyType(userMasterEO.getBodyType());
            }
        }
        userMasterEO.setPrivilegeId(3);
        UserMasterRequestDetailEO masterEO = institutionService.saveUserRegistrationNew(userMasterEO, request,userInfo);
        if (masterEO == null) {
            ReturnResponse returnResponse = new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again");
            return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
        }
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.CREATED.value(), "User Registration has been Successfully Updated!!", masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping("/documentuploaduser-new")
    public ResponseEntity<ReturnResponse> saveRequestForAddInstituteNew(@RequestParam("file") MultipartFile file,
                                                                        @RequestParam(required = false) String userId) throws IOException {
        logger1.info("university TeachingStaff controller : documentuploaduser api staff method invoked : {}");
        UserMasterRequestDetailEO requestForAdd = new UserMasterRequestDetailEO();

        if (!file.getContentType().equals("application/pdf")) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
        }
        byte[] byteArr = file.getBytes();
        boolean isPd = isPdf(byteArr);
        if (!isPd) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
        }
        InputStream inputStream = new ByteArrayInputStream(byteArr);
        inputStream.read();
        inputStream.close();
        requestForAdd.setDocument(byteArr);
        requestForAdd.setUserId(userId);
        requestForAdd.setDocumentName(file.getOriginalFilename());
        String isSaved = aisheUserRequestService.saveRequestForAddInstituteNew(requestForAdd);

        if (isSaved.equals("success")) {
            return new ResponseEntity<>(
                    new ReturnResponse(HttpStatus.OK.value(), "Document For Add Institute Details has been  Successfully Saved!!", isSaved),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Request Cannot Be Processed. Please Try Again.", null), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/api/update-moe-view-user")
    public ResponseEntity<ReturnResponse> updateMoeViewUser(@RequestParam String userId,
                                                            @RequestParam(required = false) boolean activeMoeUser/* ,@WithUser UserInfo userInfo */) {
        Boolean masterEO = institutionService.updateMoeViewUser(userId, activeMoeUser);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.CREATED.value(), "Moe User Details has been Successfully Updated!!", masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @PostMapping(value = "/api/user-master-request-by-aisheCode")
	public ResponseEntity<ReturnResponse> updateMoeViewUser(
			@RequestParam String aisheCode/* , @WithUser UserInfo userInfo */) {
       // CommanObjectOperation.usernameValidate(userInfo);
        List<UserMasterRequestDetailEO> masterEO = institutionService.getExpiredUserMasterRequest(aisheCode);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), "Success", masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Request Cannot Be Processed. Please Try Again"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/api/active-institute-inactive-user-or-pending")
    public ResponseEntity<ReturnResponse> institutionInactiveUser(@RequestParam InstitutionType institutionType, @RequestParam(required = false) Integer surveyYear, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<ActiveInstituteInactiveUser> list = institutionService.activeInstituteInactiveUser(institutionType, surveyYear);
        ReturnResponse returnResponse = ((list != null) ? new ReturnResponse(HttpStatus.OK.value(), "success", list) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found"));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
}