package aishe.gov.in.service;

import aishe.gov.in.dao.AisheUserRequestDao;
import aishe.gov.in.enums.Constant;
import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.enums.NtaSortBy;
import aishe.gov.in.exception.InvalidInputException;
import aishe.gov.in.masterseo.ActivityMasterEO;
import aishe.gov.in.masterseo.ActivityMasterLogsEO;
import aishe.gov.in.masterseo.NtaQuestionEO;
import aishe.gov.in.masterseo.NtaQuestionnaire;
import aishe.gov.in.masterseo.NtaQuestionnaireEO;
import aishe.gov.in.masterseo.OtpDetailsEO;
import aishe.gov.in.masterseo.RefActivityActionEO;
import aishe.gov.in.masterseo.RefActivityEO;
import aishe.gov.in.masterseo.RefUserRoleMaster;
import aishe.gov.in.masterseo.UserMasterNewEO;
import aishe.gov.in.masterseo.UserMasterRequestDetailEO;
import aishe.gov.in.masterseo.UserRegistrationDetailEO;
import aishe.gov.in.mastersvo.ActivityMasterVO;
import aishe.gov.in.mastersvo.AisheInstituteRequestCheckNameVO;
import aishe.gov.in.mastersvo.ApprovingAuthorityDTO;
import aishe.gov.in.mastersvo.NewInstituteRequestCheckNameVO;
import aishe.gov.in.mastersvo.RequestForAddInstitute;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.VerticalWriteNTAExcelReport;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class AisheUserRequestServiceImpl implements AisheUserRequestService {

    @Autowired(required = true)
    AisheUserRequestDao aisheUserRequestDao;


    @Autowired(required = true)
    private VerticalWriteNTAExcelReport ntaExcelReport;

    @Override
    public Integer saveRequestForAddInstitute(RequestForAddInstitute requestInstitute) {
        return aisheUserRequestDao.saveRequestForAddInstitute(requestInstitute);
    }

    @Override
    public List<NewInstituteRequestCheckNameVO> InstituteNameCheck(String typeId, String districtName, String stateName,
                                                                   String instituteName) {
        return aisheUserRequestDao.InstituteNameCheck(typeId, districtName, stateName, instituteName);
    }

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
        return aisheUserRequestDao.verifyEmailOtp(email, otp);
    }

    @Override
    public Boolean verifyMobileOtp(String mobile, String otp) {
        return aisheUserRequestDao.verifyMobileOtp(mobile, otp);
    }

    @Override
    public Boolean userIdAlreadyExist(String userId) {
        return aisheUserRequestDao.userIdAlreadyExist(userId);
    }

    @Override
    public List<AisheInstituteRequestCheckNameVO> InstituteNameExistInAishe(String districtCode, String instituteName) {
        return aisheUserRequestDao.InstituteNameExistInAishe(districtCode, instituteName);
    }

    @Override
    public Integer findByEmailIdForCount(String email) {
        return aisheUserRequestDao.findByEmailIdForCount(email);
    }

    @Override
    public Integer findByMobileNumberForCount(String mobileNumber) {
        return aisheUserRequestDao.findByMobileNumberForCount(mobileNumber);
    }

    @Override
    public List<Integer> surveyYearForAddInstituteRequest() {
        return aisheUserRequestDao.surveyYearForAddInstituteRequest();
    }

    @Override
    public Integer minSurveyYearForAddInstituteRequestPrior() {
        return aisheUserRequestDao.minSurveyYearForAddInstituteRequestPrior();
    }

    @Override
    public List<RefActivityActionEO> activityAction() {
        return aisheUserRequestDao.activityAction();
    }

    @Override
    public List<RefActivityEO> activity() {
        return aisheUserRequestDao.activity();
    }

    @Override
    public Boolean saveOrUpdateActivity(ActivityMasterEO activityMaster) {
        return aisheUserRequestDao.saveOrUpdateActivity(activityMaster);
    }

    @Override
    public List<Integer> surveyYearForAisheOpen() {
        return aisheUserRequestDao.surveyYearForAisheOpen();
    }

    @Override
    public Integer userStatusByCount(Integer statusId, String stateCode) {
        return aisheUserRequestDao.userStatusByCount(statusId, stateCode);
    }

    @Override
    public String saveDocumentForUser(UserRegistrationDetailEO requestForAdd) {
        return aisheUserRequestDao.saveDocumentForUser(requestForAdd);
    }

    @Override
    public List<ApprovingAuthorityDTO> userApprovingAuthority(Integer roleId, String stateCode, List<Integer> authorityId, String universityId) {
        return aisheUserRequestDao.userApprovingAuthority(roleId, stateCode, authorityId, universityId);
    }

    @Override
    public Boolean snoAlreadyExist(String stateCode, Integer roleId) {
        return aisheUserRequestDao.snoAlreadyExist(stateCode, roleId);
    }

    @Override
    public UserMasterNewEO fetchUserMasterByUserId() {
        return aisheUserRequestDao.fetchUserMasterByUserId();
    }

    @Override
    public RefUserRoleMaster fetchRoleDetails(Integer roleId) {
        return aisheUserRequestDao.fetchRoleDetails(roleId);
    }

    @Override
    public List<ApprovingAuthorityDTO> userApprovingAuthorities(Integer roleId, String stateCode, List<Integer> authorityId, String universityId) {
        return aisheUserRequestDao.userApprovingAuthorities(roleId, stateCode, authorityId, universityId);
    }

    @Override
    public NtaQuestionnaire getNtaQuestionNaire(String aisheCode) {
        return aisheUserRequestDao.getNtaQuestionNaire(aisheCode);
    }

    @Override
    public NtaQuestionnaire saveOrUpdateNtaQuestionNaire(NtaQuestionnaire userMaster, UserInfo userInfo, HttpServletRequest request) {
        return aisheUserRequestDao.saveOrUpdateNtaQuestionNaire(userMaster, userInfo, request);
    }

    @Override
    public void getReportExcel(InstitutionType institutionType, String stateId, NtaSortBy ntaSortBy, HttpServletResponse response) {
        List<NtaQuestionEO> questionEOS = aisheUserRequestDao.getNtaQuestion();
        List<NtaQuestionnaireEO> resultCollege = null;
        List<NtaQuestionnaireEO> resultUniversity = null;
        switch (institutionType.getType()) {
            case "ALL": {
                resultCollege = aisheUserRequestDao.getNtaQuestionDetails(InstitutionType.COLLEGE, stateId, Constant.NTA_QUESTION_DETAIL_QUERY);
                resultUniversity = aisheUserRequestDao.getNtaQuestionDetails(InstitutionType.UNIVERSITY, stateId, Constant.NTA_QUESTION_DETAIL_QUERY);
                break;
            }
            case "C": {
                resultCollege = aisheUserRequestDao.getNtaQuestionDetails(InstitutionType.COLLEGE, stateId, Constant.NTA_QUESTION_DETAIL_QUERY);
                break;
            }
            case "U": {
                resultUniversity = aisheUserRequestDao.getNtaQuestionDetails(InstitutionType.UNIVERSITY, stateId, Constant.NTA_QUESTION_DETAIL_QUERY);
                break;
            }
            default:
                break;

        }
        List<NtaQuestionnaireEO> sortedCollege = sortNtaQuestionnaire(resultCollege, ntaSortBy);
        List<NtaQuestionnaireEO> sortedUniversity = sortNtaQuestionnaire(resultUniversity, ntaSortBy);
        ntaExcelReport.writeNTAExcel(questionEOS, sortedCollege, sortedUniversity, response);
        // horizontalWriteNTAExcelReport.writeNTAExcel(questionEOS, sortedCollege, sortedUniversity, response);
    }


    public List<NtaQuestionnaireEO> sortNtaQuestionnaire(List<NtaQuestionnaireEO> list, NtaSortBy ntaSortBy) {
        List<NtaQuestionnaireEO> sortedCollege = null;
        try {
            switch (ntaSortBy) {
                case BY_STATE:
                    sortedCollege = list.stream().sorted(Comparator.comparing(NtaQuestionnaireEO::getState_name)).collect(Collectors.toList());
                    break;
                case BY_DISTRICT:
                    sortedCollege = list.stream().sorted(Comparator.comparing(NtaQuestionnaireEO::getDistrict)).collect(Collectors.toList());
                    break;
                case BY_AISHE_CODE:
                    sortedCollege = list.stream().sorted(Comparator.comparing(NtaQuestionnaireEO::getAisheCode)).collect(Collectors.toList());
                    break;
                case DEFAULT:
                    sortedCollege = list;
                    break;
            }
        } catch (NullPointerException e) {
            log.error("unable to  sort the nta object by {} cause of " + e.getMessage(), ntaSortBy.getSortType());
            sortedCollege = list;

        }
        return sortedCollege;
    }

    @Override
    public String saveRequestForAddInstituteNew(UserMasterRequestDetailEO requestForAdd) {
        return aisheUserRequestDao.saveRequestForAddInstituteNew(requestForAdd);
    }

    @Override
    public Boolean saveOrUpdateActivityMaster(ActivityMasterVO activityMaster, UserInfo userInfo, HttpServletRequest request) {
        List<ActivityMasterEO> list = getActivityMaster(activityMaster.getSurveyYear());
        if (CommanObjectOperation.listValidate(list) && activityMaster.getId() == 0) {
            throw new InvalidInputException("Duplicate survey year not allowed !! ");
        }

        ActivityMasterEO masterEO = new ActivityMasterEO();
        ActivityMasterLogsEO logsEO = new ActivityMasterLogsEO();

        if (list.isEmpty()) {
            if (DateUtils.obtainCurrentTimeStamp().isAfter(DateUtils.convertStringDateTimeToDBDateTimeNew(activityMaster.getStartDate())))
                throw new InvalidInputException("Start Date cannot less then from Current Date.. !! ");
            masterEO.setCreatedBy(userInfo.getUsername());
            masterEO.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
            masterEO.setActivityId(1);
            masterEO.setId(null);
           // masterEO.setIsActive(true);

        } else {
            masterEO = list.get(0);
            if (masterEO.getStartDate().isBefore(DateUtils.convertStringDateTimeToDBDateTimeNew(activityMaster.getStartDate()))) {
                throw new InvalidInputException("Once Survey year started then you are not allowed to change Start Date !! ");
            }

            if (!masterEO.getStartDate().equals(DateUtils.convertStringDateTimeToDBDateTimeNew(activityMaster.getStartDate()))) {
                if (DateUtils.obtainCurrentTimeStamp().isAfter(DateUtils.convertStringDateTimeToDBDateTimeNew(activityMaster.getStartDate()))) {
                    throw new InvalidInputException("Start Date cannot less then from Current Date.. !! ");
                }
            }

            logsEO.setPrevEndDate(masterEO.getEndDate());
            logsEO.setPrevStartDate(masterEO.getStartDate());
            masterEO.setModifiedBy(userInfo.getUsername());
            masterEO.setModifiedOn(DateUtils.obtainCurrentTimeStamp());
            if (activityMaster.getEndDate() != null) {
                if (DateUtils.obtainCurrentTimeStamp().isAfter(DateUtils.convertStringDateTimeToDBDateTimeNew(activityMaster.getEndDate())))
                    throw new InvalidInputException("End Date cannot less then from Current Date.. !! ");

                masterEO.setActivityId(2);
            }
        }

        if (activityMaster.getStartDate() != null) {
            masterEO.setStartDate(DateUtils.convertStringDateTimeToDBDateTimeNew(activityMaster.getStartDate()));
        }
        if (activityMaster.getEndDate() != null) {
            masterEO.setEndDate(DateUtils.convertStringDateTimeToDBDateTimeNew(activityMaster.getEndDate()));
        } else {
            masterEO.setEndDate(null);
        }
        if (null != activityMaster.getStartDate() && null != activityMaster.getEndDate() && null !=
                masterEO.getStartDate() && null != masterEO.getEndDate() && masterEO.getStartDate().isAfter(masterEO.getEndDate()))
            throw new InvalidInputException("Start Date cannot less then from End Date.. !! ");

        masterEO.setSurveyYear(activityMaster.getSurveyYear());
        masterEO.setRemarks(activityMaster.getRemarks());
        logsEO.setRemarks(activityMaster.getRemarks());
        logsEO.setUserId(userInfo.getUsername());
        logsEO.setActionDate(DateUtils.obtainCurrentTimeStamp());
        logsEO.setActionId(masterEO.getActivityId());
        logsEO.setSurveyYear(masterEO.getSurveyYear());
        logsEO.setStartDate(masterEO.getStartDate());
        logsEO.setEndDate(masterEO.getEndDate());

        return aisheUserRequestDao.saveOrUpdateActivityMaster(masterEO, logsEO);

    }

    @Override
    public List<ActivityMasterEO> getActivityMaster(Integer surveyYear) {
        return aisheUserRequestDao.getActivityMaster(surveyYear);
    }



    @Override
    public Boolean deleteActivityMaster(Integer id) {
        ActivityMasterEO masterEO=aisheUserRequestDao.getActivityMasterById(id);
        if(CommanObjectOperation.objectValidate(masterEO)){
             if(DateUtils.obtainCurrentTimeStamp().isBefore(masterEO.getStartDate())){
                return aisheUserRequestDao.deleteActivityMaster(masterEO);
             }else {
                 throw new InvalidInputException("Only you can delete Upcoming Survey year !! ");
             }
        }
        return false;
    }

	@Override
	public Boolean userIdAlreadyExistInLogs(String userId) {
		 return aisheUserRequestDao.userIdAlreadyExistInLogs(userId);
	}


}