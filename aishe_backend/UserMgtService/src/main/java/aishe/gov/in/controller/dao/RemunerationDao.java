package aishe.gov.in.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import aishe.gov.in.enums.InstituteCategory;
import aishe.gov.in.enums.RefRemunerationStatus;
import aishe.gov.in.masterseo.ApprovalStatus;
import aishe.gov.in.masterseo.RemunerationDashboard;
import aishe.gov.in.masterseo.RemunerationNormEO;
import aishe.gov.in.masterseo.RemunerationNormType1RuleEO;
import aishe.gov.in.masterseo.RemunerationNormType2RuleEO;
import aishe.gov.in.masterseo.RemunerationReport;
import aishe.gov.in.masterseo.RemunerationStatementApproval;
import aishe.gov.in.masterseo.RemunerationStatementApprovalByCategoryEO;
import aishe.gov.in.masterseo.RemunerationStatementApprovalEO;
import aishe.gov.in.masterseo.RemunerationStatementDashBoardEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailWithAmountEO;
import aishe.gov.in.masterseo.RemunerationStatementEO;
import aishe.gov.in.masterseo.TransactionStatus;
import aishe.gov.in.masterseo.UserAccountLogEO;
import aishe.gov.in.masterseo.UserAndInstitutionDetails;
import aishe.gov.in.masterseo.UserBankAccountEO;
import aishe.gov.in.mastersvo.AfiiliatedWithUniversityCount;
import aishe.gov.in.mastersvo.RemunerationChangeStatus;
import aishe.gov.in.mastersvo.RemunerationStatementVo;
import aishe.gov.in.mastersvo.RemunerationTransactionAndApprovalLogVo;
import aishe.gov.in.mastersvo.SelectOptionVO;
import aishe.gov.in.security.UserInfo;

public interface RemunerationDao {

	UserBankAccountEO saveUserBankDetail(
			UserBankAccountEO bankAccountEO/*
											 * , RemunerationStatementApproval statementApproval
											 */);

    UserBankAccountEO updateUserBankDetail(UserBankAccountEO bankAccountEO);

    List<UserBankAccountEO> getUserBankDetail(Integer surveyYear, String userId, String aisheCode);

    RemunerationNormEO saveRemunerationNorm(RemunerationNormEO remunerationNorm);

    RemunerationNormEO updateRemunerationNorm(RemunerationNormEO remunerationNorm);

    List<RemunerationNormEO> getRemunerationNorm(Integer surveyYear, String formId);

    RemunerationNormType1RuleEO saveRemunerationNormType1Rule(RemunerationNormType1RuleEO normType1RuleEO);

    RemunerationNormType1RuleEO updateRemunerationNormType1Rule(RemunerationNormType1RuleEO normType1RuleEO);

    List<RemunerationNormType1RuleEO> getRemunerationNormType1RuleEO(Integer surveyYear, String aisheCode, String institutionType, String formId);

    RemunerationNormType2RuleEO saveRemunerationNormType2Rule(RemunerationNormType2RuleEO normType1RuleEO);

    RemunerationNormType2RuleEO updateRemunerationNormType2Rule(RemunerationNormType2RuleEO normType1RuleEO);

    List<RemunerationNormType2RuleEO> getRemunerationNormType2RuleEO(Integer surveyYear, String aisheCode, String institutionType, String formId);

    List<RemunerationTransactionAndApprovalLogVo> getRemunerationLog(Integer surveyYear, String stateCode, String fromDate, String toDate, String instituteCategory, String nativeQuery);

    Boolean updateRemunerationNorm(List<RemunerationNormEO> remunerationNorm, Boolean freeze);

    Boolean updateRemunerationNorm(List<RemunerationNormEO> remunerationNorm, Integer freeze);

    UserAndInstitutionDetails getUserDetail(String userId);

    String getSingleStringValue(String userId, Integer surveyYear, String nativeQuery);

    List<ApprovalStatus> getApprovalStatus(String userId, Integer surveyYear, String nativeQuery);

    TransactionStatus getTransactionStatus(String userId, Integer surveyYear, String nativeQuery);

    List<RemunerationStatementApprovalByCategoryEO> getStatementApprovalByCategory(String stateCode, Integer surveyYear, String instituteCategory, String fromDate, String toDate, String statusId, String universityType, String institutionId, String approverRoleId, String collegeType, String parentInstitutionId, String stateBodyId, String nativeQuery);

    List<RemunerationStatementApprovalEO> saveOrUpdateStatementApproval(List<RemunerationStatementApprovalEO> approvalEOS, HttpServletRequest request);

    RemunerationStatementApprovalEO getRemunerationStatementApproval(Integer formUploadId, Integer approverRoleId);

    BigInteger getNextValueFromSequence();

    RemunerationStatementEO statementGeneration(RemunerationStatementVo statementGenerationVos, HttpServletRequest request);

    List<RemunerationStatementDashBoardEO> getRemunerationStatementDashBoard(String generatorUserId, Integer surveyYear, String fromDate, String toDate, String nativeQuery);

    List<RemunerationStatementDetailWithAmountEO> getStatementDetailsByStatementId(String statementId, String stateCode, Integer surveyYear, String instituteCategory, String fromDate, String toDate, String statusId, String nativeQuery);

    RemunerationStatementEO getRemunerationStatement(String statementId);

    List<RemunerationStatementDetailEO> saveRemunerationStatementTransactionApproval(List<RemunerationStatementDetailEO> detailEOS, HttpServletRequest request);

    List<RemunerationDashboard> findStatementListByIdAndStatus(String statementId, String transactionStatusId, int limit, int offset, String query);

    AfiiliatedWithUniversityCount getAffiliatedCollegeCount(String universityId, Integer surveyYear);

    List<SelectOptionVO> checkEligibility(String aisheCode, Integer surveyYear);

	void saveRemunerationApproval(RemunerationStatementApproval remunerationnApproval);

	List<UserBankAccountEO> getUserBankDetails(Integer surveyYear, String userId, String aisheCode);

    List<RemunerationReport> remunerationReport(Integer surveyYear, InstituteCategory institutionType,
                                                String aisheCode, String stateCode, String districtCode, String categoryType, String universityId, Date fDate, Date tDate,   Integer statusId);

    List<RemunerationChangeStatus> remunerationChangeStatus(List<RemunerationChangeStatus> detailEOS, HttpServletRequest request, UserInfo userInfo);

    List<RefRemunerationStatus> refRemunerationStatus();

   // RemunerationChangeStatus lockBankDetails(RemunerationChangeStatus detailEOS, HttpServletRequest request,UserInfo userInfo);

    List<UserAccountLogEO> track(Integer surveyYear, String aisheCode);
}
