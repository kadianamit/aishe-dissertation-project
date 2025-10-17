package aishe.gov.in.dao;

import aishe.gov.in.enums.InstitutionType;
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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface AisheUserRequestDao {

    Integer saveRequestForAddInstitute(RequestForAddInstitute requestInstitute);

    List<NewInstituteRequestCheckNameVO> InstituteNameCheck(String typeId, String districtName, String stateName,
                                                            String instituteName);

    Integer saveEmailOtp(OtpDetailsEO forgot);

    Integer saveMobileOtp(OtpDetailsEO forgot);

    Boolean verifyEmailOtp(String email, String otp);

    Boolean verifyMobileOtp(String mobile, String otp);

    Boolean userIdAlreadyExist(String userId);

    List<AisheInstituteRequestCheckNameVO> InstituteNameExistInAishe(String districtCode, String instituteName);

    Integer findByEmailIdForCount(String email);

    Integer findByMobileNumberForCount(String mobileNumber);

    List<Integer> surveyYearForAddInstituteRequest();

    Integer minSurveyYearForAddInstituteRequestPrior();

    List<RefActivityActionEO> activityAction();

    List<RefActivityEO> activity();

    Boolean saveOrUpdateActivity(ActivityMasterEO activityMaster);

    List<Integer> surveyYearForAisheOpen();

    Integer userStatusByCount(Integer statusId, String stateCode);

    String saveDocumentForUser(UserRegistrationDetailEO requestForAdd);

    List<ApprovingAuthorityDTO> userApprovingAuthority(Integer roleId, String stateCode, List<Integer> authorityId, String universityId);

    Boolean snoAlreadyExist(String stateCode, Integer roleId);

    UserMasterNewEO fetchUserMasterByUserId();

    RefUserRoleMaster fetchRoleDetails(Integer roleId);
    List<ApprovingAuthorityDTO> userApprovingAuthorities(Integer roleId, String stateCode, List<Integer> authorityId, String universityId);

	NtaQuestionnaire getNtaQuestionNaire(String aisheCode);

	NtaQuestionnaire saveOrUpdateNtaQuestionNaire(NtaQuestionnaire userMaster, UserInfo userInfo, HttpServletRequest request);
    List<NtaQuestionEO> getNtaQuestion();
    List<NtaQuestionnaireEO> getNtaQuestionDetails(InstitutionType institutionType,String stateId, String queryString);

	String saveRequestForAddInstituteNew(UserMasterRequestDetailEO requestForAdd);

    Boolean saveOrUpdateActivityMaster(ActivityMasterEO activityMaster, ActivityMasterLogsEO logsEO);
    List<ActivityMasterEO> getActivityMaster(Integer surveyYear);

    public Integer getMaxActivityMaster();

    ActivityMasterEO getActivityMasterById(Integer id);

    Boolean deleteActivityMaster(ActivityMasterEO masterEO);

	Boolean userIdAlreadyExistInLogs(String userId);

}
