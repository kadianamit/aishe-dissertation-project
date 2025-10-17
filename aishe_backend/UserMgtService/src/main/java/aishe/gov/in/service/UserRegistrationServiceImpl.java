package aishe.gov.in.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.exception.InvalidInputException;
import aishe.gov.in.masterseo.ActiveInstituteInactiveUser;
import aishe.gov.in.security.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.ActionLogDao;
import aishe.gov.in.dao.RefMasterDao;
import aishe.gov.in.dao.RegisterationDao;
import aishe.gov.in.enums.ActionType;
import aishe.gov.in.enums.SortBy;
import aishe.gov.in.enums.UserStatus;
import aishe.gov.in.masterseo.ApproveDisapproveUserDTO;
import aishe.gov.in.masterseo.ApproveUserDTO;
import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.masterseo.RefDistrict;
import aishe.gov.in.masterseo.RefState;
import aishe.gov.in.masterseo.UserActionLogEO;
import aishe.gov.in.masterseo.UserMasterDetailEO;
import aishe.gov.in.masterseo.UserMasterDetailNewEO;
import aishe.gov.in.masterseo.UserMasterEO;
import aishe.gov.in.masterseo.UserMasterLogDetailEO;
import aishe.gov.in.masterseo.UserMasterNewEO;
import aishe.gov.in.masterseo.UserMasterRequestDetailEO;
import aishe.gov.in.masterseo.UserRegistrationDetailEO;
import aishe.gov.in.masterseo.UserRegistrationUpdatedDetailEO;
import aishe.gov.in.mastersvo.RegisteredUserDTO;
import aishe.gov.in.mastersvo.UserApprovalDTO;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.EmailUtil;
import aishe.gov.in.utility.IpAddressProvider;
import aishe.gov.in.utility.NullValueHandler;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    private RegisterationDao institutionDao;

    @Autowired
    private ActionLogDao userActionLogDao;

    @Autowired
    private RefMasterDao refMasterDao;

    @Autowired
    private NullValueHandler handler;

    @Override
    public UserMasterNewEO saveUpdateRegistration(UserMasterNewEO userMasterEO, HttpServletRequest request, UserInfo userInfo) {
        UserMasterNewEO masterEO = institutionDao.saveUpdateRegistration(userMasterEO, userInfo);
        if (null != masterEO) {
            userActionLogDao.saveUserActionLog(UserActionLogEO.bind(masterEO, IpAddressProvider.getClientIpAddr(request)));
        }
        return masterEO;
    }

    @Override
    public UserMasterEO getUserRegistration(String userId, Integer currentSurveyYear) {


        UserMasterEO masterEO = institutionDao.getUserRegistration1(userId);
        if (masterEO == null) {
            masterEO = new UserMasterEO();
            UserMasterLogDetailEO userMasterLog = institutionDao.getUserMasterLog(userId);
            if (userMasterLog != null) {
                BeanUtils.copyProperties(userMasterLog, masterEO);
                masterEO.setEmail(userMasterLog.getEmailId());
                masterEO.setMobile(userMasterLog.getPhoneMobile());
            }
        }
        if (null != masterEO) {
            if (null != masterEO.getStateId()) {
                RefState state = refMasterDao.getState(masterEO.getStateId());
                masterEO.setStateName(null != state ? state.getName() : null);
                masterEO.setStateLgdCode(null != state ? state.getLgd_code() : null);
            }
            if (null != masterEO.getDistrictId()) {
                RefDistrict district = refMasterDao.getDistrict(masterEO.getDistrictId());
                masterEO.setDistrictName(null != district ? district.getName() : null);
                masterEO.setDisLgdCode(null != district ? district.getLgd_district_code() : null);
            }
            if (null != masterEO.getAisheCode()) {
                String id = masterEO.getAisheCode().trim().split("\\s*-\\s*")[1];
                String type = masterEO.getAisheCode().trim().split("\\s*-\\s*")[0];
                switch (type.toUpperCase()) {
                    case "C": {
                        masterEO.setInstituteName(refMasterDao.getCollegeName(Integer.valueOf(id)));
                        masterEO.setLattitude(refMasterDao.getLattitude(id, type.toUpperCase()));
                        masterEO.setLongitude(refMasterDao.getLongitude(id, type.toUpperCase()));
                        break;
                    }
                    case "S": {
                        masterEO.setInstituteName(refMasterDao.getStandaloneName(Integer.valueOf(id)));
                        break;
                    }
                    case "U": {
                        masterEO.setInstituteName(refMasterDao.getUniversityName(id));
                        masterEO.setLattitude(refMasterDao.getLattitude(id, type.toUpperCase()));
                        masterEO.setLongitude(refMasterDao.getLongitude(id, type.toUpperCase()));
                        break;
                    }
                }
                masterEO.setFinalSubmit(refMasterDao.getFinalSubmitDone(id, type, currentSurveyYear));
            } else {
                masterEO.setMessage("UserId Not Exist");
            }
        }
        return masterEO;
    }

    @Override
    public UserMasterDetailEO saveAndUpdateUserApproval(UserApprovalDTO approvalDTO, HttpServletRequest request) {
        UserMasterDetailEO masterEO = institutionDao.getUserByUserId(approvalDTO.getUserId());
        if (null != masterEO) {
            masterEO.setIsApproved(approvalDTO.getApproval() != false ? 1 : 0);
            masterEO.setApprovedBy(approvalDTO.getApprovedBy());
            UserMasterDetailEO masterEO2 = institutionDao.save(masterEO);
            if (null != masterEO2)
                userActionLogDao.saveUserActionLog(UserActionLogEO.bind(masterEO, masterEO2.getIsApproved() == 1 ? ActionType.USER_APPROVED.getActionType() : ActionType.USER_DISAPPROVED.getActionType(), 0
                        , masterEO2.getIsApproved() == 1 ? masterEO2.getUserId() + " is approved " : masterEO2.getUserId() + " is disapproved "
                        , IpAddressProvider.getClientIpAddr(request)));
        }
        return masterEO;
    }

    @Override
    public Boolean deleteUser(String userId, HttpServletRequest request) {
        UserMasterDetailEO masterEO = institutionDao.getUserByUserId(userId);
        if (masterEO != null) {
            Boolean status = institutionDao.deleteUser(masterEO);
            if (status) {
                userActionLogDao.saveUserActionLog(UserActionLogEO.bind(masterEO, ActionType.USER_DELETE.getActionType(), 0
                        , masterEO.getUserId() + " is deleted "
                        , IpAddressProvider.getClientIpAddr(request)));
                return status;
            }
        }
        return false;
    }


    @Override
    public List<RegisteredUserDTO> getRegisteredUser(Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved, SortBy sortBy, String instituteType, /*int page, int pageSize,*/ String fromDate, String toDate, String searchText, String categoryType) {
        List<RegisteredUserDTO> getRegisteredUser = institutionDao.getRegisteredUser(roleId, surveyYear, userStatus, deoRoleId, dcfStatus, formUploadStatus, handler.nullValueHandler(stateCode), handler.nullValueHandler(universityId), handler.nullValueHandler(aisheCode), isApproved, sortBy, instituteType,/* page, pageSize,*/ handler.nullValueHandler(fromDate), handler.nullValueHandler(toDate), searchText, categoryType);
        List<RegisteredUserDTO> sortedList = null;
        if (getRegisteredUser.size() > 0) {
            sortedList = sortListBySortingType(getRegisteredUser, sortBy);
        }
        return sortedList;
    }

    @Override
    public BigInteger getRegisteredUserCont(Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved, String instituteType, String fromDate, String toDate, String searchText, String categoryType) {
        return institutionDao.getRegisteredUserCont(roleId, surveyYear, userStatus, deoRoleId, dcfStatus, formUploadStatus, handler.nullValueHandler(stateCode), handler.nullValueHandler(universityId), handler.nullValueHandler(aisheCode), isApproved, instituteType, handler.nullValueHandler(fromDate), handler.nullValueHandler(toDate), searchText, categoryType);

    }


    private List<RegisteredUserDTO> sortListBySortingType(List<RegisteredUserDTO> userDTOList, SortBy sortBy) {
        List<RegisteredUserDTO> sortedList = null;
        switch (sortBy.getSortType()) {
            case "USERID":
                sortedList = userDTOList;
                break;
            case "FIRSTNAME":
                List<RegisteredUserDTO> sortedByUserId = userDTOList;
                sortedList = userDTOList
                        .stream()
                        .filter(obj -> obj.getFirstName() != null)
                        .sorted(Comparator.comparing(RegisteredUserDTO::getFirstName))
                        .collect(Collectors.toList());
                if (sortedList.size() == 0)
                    sortedList = sortedByUserId;
                break;
            case "INSTITUTION":
                List<RegisteredUserDTO> sortedByUser = userDTOList;
                sortedList = userDTOList
                        .stream().filter(obj -> obj.getInstituteName() != null)
                        .sorted(Comparator.comparing(RegisteredUserDTO::getInstituteName))
                        .collect(Collectors.toList());
                if (sortedList.size() == 0)
                    sortedList = sortedByUser;
                break;
            case "UNIVERSITY":
                List<RegisteredUserDTO> sorted = userDTOList;
                sortedList = userDTOList
                        .stream().filter(obj -> obj.getUniversityName() != null)
                        .sorted(Comparator.comparing(RegisteredUserDTO::getUniversityName))
                        .collect(Collectors.toList());
                if (sortedList.size() == 0)
                    sortedList = sorted;
                break;
        }
        return sortedList;
    }

    @Override
    public UserMasterDetailEO saveUserApproval(ApproveUserDTO approveUserDTO, HttpServletRequest request) throws Exception {
        UserMasterDetailEO masterEO = institutionDao.getUserByUserId(approveUserDTO.getUserId());
        if (approveUserDTO.getStatus()) {
            if (null != masterEO) {
                approveUserDTO.setRoleId(masterEO.getRoleId());
                UserMasterDetailEO activeMasterUser = institutionDao.getUser(approveUserDTO);
                if (null != activeMasterUser) {
                    activeMasterUser.setIsApproved(approveUserDTO.getStatus() == true ? 0 : 1);
                    UserMasterDetailEO detailEO = approvalObject(activeMasterUser, approveUserDTO, request, UserStatus.INACTIVE.getActionType());
                    emailSender(detailEO.getEmailId(), "Inactive", detailEO.getUserId());
                    masterEO.setIsApproved(approveUserDTO.getStatus() == true ? 1 : 0);
                    masterEO.setApprovedDatetime(DateUtils.obtainCurrentTimeStamp());
                    UserMasterDetailEO detailEO1 = approvalObject(masterEO, approveUserDTO, request, UserStatus.USER_APPROVED.getActionType());
                    emailSender(detailEO1.getEmailId(), "Approved", detailEO1.getUserId());
                    return detailEO1;
                } else {
                    masterEO.setIsApproved(approveUserDTO.getStatus() == true ? 1 : 0);
                    masterEO.setApprovedDatetime(DateUtils.obtainCurrentTimeStamp());
                    UserMasterDetailEO detailEO1 = approvalObject(masterEO, approveUserDTO, request, UserStatus.USER_APPROVED.getActionType());
                    emailSender(detailEO1.getEmailId(), "Approved", detailEO1.getUserId());
                    return detailEO1;
                }
            }
        } else {
            masterEO.setIsApproved(0);
            UserMasterDetailEO detailEO1 = approvalObject(masterEO, approveUserDTO, request, UserStatus.USER_DISAPPROVED.getActionType());
            emailSender(detailEO1.getEmailId(), "Dis-Approved", detailEO1.getUserId());
            return detailEO1;
        }
        return masterEO;
    }

    UserMasterDetailEO approvalObject(UserMasterDetailEO masterEO, ApproveUserDTO approveUserDTO, HttpServletRequest request, Integer userStatus) {
        masterEO.setStatusId(userStatus);
        masterEO.setApprovedBy(approveUserDTO.getApprovedBy());
        masterEO.setModifiedBy(approveUserDTO.getApprovedBy());
        masterEO.setApprovalMessage(userStatus == 3 ? masterEO.getApprovalMessage() : userStatus == 4 ? masterEO.getApprovalMessage() : approveUserDTO.getMessage());
        masterEO.setModifiedDatetime(LocalDateTime.now());
        UserMasterDetailEO masterDetailEO = institutionDao.update(masterEO);
        if (null != masterDetailEO)
            userActionLogDao.saveUserActionLog(UserActionLogEO.bind(masterEO, masterDetailEO.getIsApproved() == 1 ? ActionType.USER_APPROVED.getActionType() : ActionType.USER_DISAPPROVED.getActionType(), 0, masterDetailEO.getIsApproved() == 1 ? masterDetailEO.getUserId() + " is approved " : masterDetailEO.getUserId() + " is disapproved ", IpAddressProvider.getClientIpAddr(request), approveUserDTO.getApprovedBy()));

        return masterDetailEO;
    }

    public void emailSender(String email, String messageStatus, String userId) throws Exception {
        String subject = "User approval info from Aishe-Portal";
        String message = "Dear User,<br>" + "your user id " + userId + " has successfully <b>" + messageStatus + "</b> on aishe Portal.<br> Regards,<br>Team AISHE";
        EmailUtil.sendEmails(email, subject, message);
    }

    @Override
    public UserRegistrationDetailEO saveUserRegistrationData(UserRegistrationDetailEO userMasterEO) {
        UserRegistrationDetailEO masterEO = institutionDao.saveUserRegistrationData(userMasterEO);
        return masterEO;
    }

    @Override
    public UserRegistrationUpdatedDetailEO updateUserRegistration(UserRegistrationUpdatedDetailEO userMasterEO, boolean isUpdated, UserInfo userInfo) {
        UserRegistrationUpdatedDetailEO masterEO = institutionDao.updateUserRegistration(userMasterEO, isUpdated, userInfo);
        return masterEO;
    }

    @Override
    public InstituteDetailEO getInstituteDetail(String aisheCode) {
        return institutionDao.getInstituteDetail(aisheCode);
    }

    @Override
    public void updateInfoInstituteDetail(InstituteDetailEO instituteDetail) {
        userActionLogDao.updateInfoInstituteDetail(instituteDetail);

    }

    @Override
    public UserMasterDetailNewEO approveDisapprvoeUserApproval(ApproveDisapproveUserDTO approveUserDTO,
                                                               HttpServletRequest request) {
        switch (approveUserDTO.getStatus()) {
            case 2: {
                UserMasterDetailNewEO newEO = institutionDao.fetchUserMasterDetailEO(approveUserDTO.getUserId());
                if (newEO != null) {
                    throw new InvalidInputException("User Id already exist with same user Id");
                }
            }
        }
        return institutionDao.approveDisapprvoeUserApproval(approveUserDTO, request);
    }

    @Override
    public UserMasterRequestDetailEO saveUserRegistrationNew(UserMasterRequestDetailEO userMasterEO, HttpServletRequest request, UserInfo userInfo) {
        UserMasterRequestDetailEO masterEO = institutionDao.saveUserRegistrationNew(userMasterEO, request, userInfo);
        return masterEO;
    }

    @Override
    public List<RegisteredUserDTO> getRegisteredUserRequest(Integer roleId, Integer surveyYear, Integer userStatus,
                                                            Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode, String universityId,
                                                            String aisheCode, Boolean isApproved, SortBy sortBy, String instituteType, String fromDate, String toDate,
                                                            String searchText, String categoryType) {
        List<RegisteredUserDTO> getRegisteredUser = institutionDao.getRegisteredUserRequest(roleId, surveyYear, userStatus, deoRoleId, dcfStatus, formUploadStatus, handler.nullValueHandler(stateCode), handler.nullValueHandler(universityId), handler.nullValueHandler(aisheCode), isApproved, sortBy, instituteType,/* page, pageSize,*/ handler.nullValueHandler(fromDate), handler.nullValueHandler(toDate), searchText, categoryType);
        List<RegisteredUserDTO> sortedList = null;
        if (getRegisteredUser.size() > 0) {
            sortedList = sortListBySortingType(getRegisteredUser, sortBy);
        }
        return sortedList;
    }

    @Override
    public List<RegisteredUserDTO> getRegisteredUserDisapprovedInactive(Integer roleId, Integer surveyYear,
                                                                        Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode,
                                                                        String universityId, String aisheCode, Boolean isApproved, SortBy sortBy, String instituteType,
                                                                        String fromDate, String toDate, String searchText, String categoryType) {
        List<RegisteredUserDTO> getRegisteredUser = institutionDao.getRegisteredUserDisapprovedInactive(roleId, surveyYear, userStatus, deoRoleId, dcfStatus, formUploadStatus, handler.nullValueHandler(stateCode), handler.nullValueHandler(universityId), handler.nullValueHandler(aisheCode), isApproved, sortBy, instituteType,/* page, pageSize,*/ handler.nullValueHandler(fromDate), handler.nullValueHandler(toDate), searchText, categoryType);
        List<RegisteredUserDTO> sortedList = null;
        if (getRegisteredUser.size() > 0) {
            sortedList = sortListBySortingType(getRegisteredUser, sortBy);
        }
        return sortedList;
    }

    @Override
    public Boolean updateMoeViewUser(String userId, Boolean activeMoeUser) {
        return institutionDao.updateMoeViewUser(userId, activeMoeUser);
    }

    @Override
    public List<UserMasterRequestDetailEO> getExpiredUserMasterRequest(String aisheCode) {
        return institutionDao.getExpiredUserMasterRequest(aisheCode);
    }

    @Override
    public UserMasterLogDetailEO getUserLogDetail(String userId) {
        return institutionDao.getUserLogDetail(userId);
    }

    @Override
    public UserMasterRequestDetailEO getUserRequestDetail(String userId) {
        return institutionDao.getUserRequestDetail(userId);
    }

    @Override
    public UserMasterEO getUserRegistrationAisheCode(String aisheCode) {
        return institutionDao.getUserRegistrationAisheCode(aisheCode);
    }

    @Override
    public List<ActiveInstituteInactiveUser> activeInstituteInactiveUser(InstitutionType institutionType, Integer surveyYear) {
        if (null == surveyYear)
            surveyYear = institutionDao.getMaxSurveyYearFromMaster();
        return institutionDao.institutionInactiveUser(institutionType, surveyYear);
    }

}