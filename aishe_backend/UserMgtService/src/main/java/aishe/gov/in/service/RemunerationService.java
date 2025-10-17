package aishe.gov.in.service;

import aishe.gov.in.enums.InstituteCategory;
import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.enums.LogType;
import aishe.gov.in.enums.RefRemunerationStatus;
import aishe.gov.in.masterseo.RemunerationDashboard;
import aishe.gov.in.masterseo.RemunerationNormEO;
import aishe.gov.in.masterseo.RemunerationNormType1RuleEO;
import aishe.gov.in.masterseo.RemunerationNormType2RuleEO;
import aishe.gov.in.masterseo.RemunerationReport;
import aishe.gov.in.masterseo.RemunerationStatementApprovalByCategoryEO;
import aishe.gov.in.masterseo.RemunerationStatementApprovalEO;
import aishe.gov.in.masterseo.RemunerationStatementDashBoardEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailEO;
import aishe.gov.in.masterseo.RemunerationStatementEO;
import aishe.gov.in.masterseo.UserAccountLogEO;
import aishe.gov.in.masterseo.UserAndInstitutionDetails;
import aishe.gov.in.masterseo.UserBankAccountEO;
import aishe.gov.in.mastersvo.AfiiliatedWithUniversityCount;
import aishe.gov.in.mastersvo.RemunerationChangeStatus;
import aishe.gov.in.mastersvo.RemunerationCopyVO;
import aishe.gov.in.mastersvo.RemunerationFreezeVO;
import aishe.gov.in.mastersvo.RemunerationProcessStatusVO;
import aishe.gov.in.mastersvo.RemunerationStatementDetailVO;
import aishe.gov.in.mastersvo.RemunerationStatementVo;
import aishe.gov.in.mastersvo.RemunerationTransactionAndApprovalLogVo;
import aishe.gov.in.mastersvo.SelectOptionVO;
import aishe.gov.in.security.UserInfo;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface RemunerationService {

    UserBankAccountEO saveOrUpdateUserBankDetail(UserBankAccountEO bankAccountEO, HttpServletRequest request);

    List<UserBankAccountEO> getUserBankDetail(Integer surveyYear, String userId, String aisheCode);

    RemunerationNormEO saveOrUpdateRemunerationNorm(RemunerationNormEO remunerationNorm,  HttpServletRequest request);

    List<RemunerationNormEO> getRemunerationNorm(Integer surveyYear,  String formId);

    RemunerationNormType1RuleEO saveOrUpdateRemunerationNormType1Rule(RemunerationNormType1RuleEO normType1RuleEO,  HttpServletRequest request);


    List<RemunerationNormType1RuleEO> getRemunerationNormType1RuleEO(Integer surveyYear, String aisheCode, InstitutionType institutionType, String formId);

    RemunerationNormType2RuleEO saveOrUpdateRemunerationNormType2Rule(RemunerationNormType2RuleEO normType1RuleEO, HttpServletRequest request);

    List<RemunerationNormType2RuleEO> getRemunerationNormType2RuleEO(Integer surveyYear, String aisheCode, InstitutionType institutionType, String formId);

   List<RemunerationTransactionAndApprovalLogVo> getRemunerationTransactionAndApprovalLog(Integer surveyYear, String stateCode, String fromDate, String toDate, String instituteCategory, LogType logType);

   Boolean freezeRemunerationNorm(RemunerationFreezeVO  remunerationFreezeVO);
   Boolean copyRemunerationNorm(RemunerationCopyVO remunerationFreezeVO);
   UserAndInstitutionDetails getUserDetail(String userId);

    RemunerationProcessStatusVO getRemunerationStatus(Integer surveyYear, String userId, String aisheCode);

    List<RemunerationStatementApprovalByCategoryEO> getStatementApprovalByCategory(String stateCode, Integer surveyYear, String instituteCategory, String fromDate, String toDate, String statusId, String universityType, String institutionId, String approverRoleId, String collegeType, String parentInstitutionId, String stateBodyId);

    List<RemunerationStatementApprovalEO>  saveOrUpdateStatementApproval(List<RemunerationStatementApprovalEO> approvalEOS, HttpServletRequest request);
    RemunerationStatementEO statementGeneration(RemunerationStatementVo statementGenerationVos, HttpServletRequest request);

    List<RemunerationStatementDashBoardEO> getRemunerationStatementDashBoard(String generatorUserId, Integer surveyYear, String fromDate, String toDate);

    RemunerationStatementDetailVO getStatementDetail(String statementId,String stateCode, String instituteCategory, String fromDate, String toDate, String statusId);

    List<RemunerationStatementDetailEO> saveRemunerationStatementTransactionApproval(List<RemunerationStatementDetailEO> detailEOS,HttpServletRequest request);

    RemunerationStatementEO getRemunerationStatement( String statementId);

    List<RemunerationDashboard> findStatementListByIdAndStatus(String statementId, String transactionStatusId, int limit, int offset);

    List<RemunerationStatementDetailEO> uploadRemunerationMultipart(MultipartFile file, String generatorUserId, HttpServletRequest request) throws IOException;

    AfiiliatedWithUniversityCount getAffiliatedCollegeCount( String universityId,Integer surveyYear);

    List<SelectOptionVO> checkEligibility(String aisheCode, @RequestParam  Integer surveyYear);

	List<UserBankAccountEO> getUserBankDetails(Integer surveyYear, String userId, String aisheCode);

    List<RemunerationReport> remunerationReport(Integer surveyYear, InstituteCategory institutionType,
                                                String aisheCode, String stateCode, String districtCode, String categoryType, String universityId, Date fDate, Date tDate,   Integer statusId);

    List<RemunerationChangeStatus> remunerationChangeStatus(List<RemunerationChangeStatus> detailEOS, HttpServletRequest request, UserInfo userInfo);

    List<RefRemunerationStatus> refRemunerationStatus();

    List<UserAccountLogEO> track(Integer surveyYear, String aisheCode);

    //  RemunerationChangeStatus lockBankDetails(RemunerationChangeStatus detailEOS, HttpServletRequest request,
    //  UserInfo userInfo);
}
