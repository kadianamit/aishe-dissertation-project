package aishe.gov.in.service;

import aishe.gov.in.dao.RemunerationDao;
import aishe.gov.in.dao.RemunerationSurveyYearDao;
import aishe.gov.in.enums.InstituteCategory;
import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.enums.LogType;
import aishe.gov.in.enums.RefRemunerationStatus;
import aishe.gov.in.exception.EntityException;
import aishe.gov.in.exception.UnAuthorizationException;
import aishe.gov.in.masterseo.ApprovalStatus;
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
import aishe.gov.in.masterseo.TransactionStatus;
import aishe.gov.in.masterseo.UserAccountLogEO;
import aishe.gov.in.masterseo.UserActionLogEO;
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
import aishe.gov.in.utility.FormType;
import aishe.gov.in.utility.IpAddressProvider;
import aishe.gov.in.utility.MultipartFileOperation;
import aishe.gov.in.utility.NullValueHandler;
import aishe.gov.in.utility.RemunerationAmountCalculator;
import aishe.gov.in.utility.RemunerationConstant;
import aishe.gov.in.utility.RemunerationKey;
import aishe.gov.in.utility.RemunerationNativeQueries;
import aishe.gov.in.utility.UserAccountKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemunerationServiceImpl implements RemunerationService {
    @Autowired
    private RemunerationDao remunerationDao;

    @Autowired
    private NullValueHandler handler;

    @Autowired
    private MultipartFileOperation fileOperation;
    @Autowired
    private UserLogService logService;

    @Autowired
    private RemunerationSurveyYearDao surveyYearDao;


    @Autowired
    private RemunerationAmountCalculator amountCalculator;

    @Override
    public UserBankAccountEO saveOrUpdateUserBankDetail(UserBankAccountEO bankAccountEO, HttpServletRequest request) {
        /*String form_upload_id = handler.nullValueHandler(remunerationDao.getSingleStringValue(bankAccountEO.getAccountKey().getUserId(),
                bankAccountEO.getAccountKey().getSurveyYear(),
                RemunerationNativeQueries.CHECK_FORM_UPLOAD_BY_USER_ID_SURVEY_YEAR));*/

        // List<FormUpload> list =surveyYearDao.findByApproverIdAndSurveyYearForBasicForms(bankAccountEO.getAisheCode(), bankAccountEO.getAccountKey().getSurveyYear());
        //  if (null != list && !list.isEmpty()) {

        List<UserBankAccountEO> userBankAccountEOList = remunerationDao.getUserBankDetail(bankAccountEO.getAccountKey().getSurveyYear(), bankAccountEO.getAccountKey().getUserId(), bankAccountEO.getAisheCode());
        UserBankAccountEO userBankAccountEO = null;
        if (null != userBankAccountEOList && userBankAccountEOList.size() > 0)
            userBankAccountEO = userBankAccountEOList.get(0);
        if (null == userBankAccountEO) {
            bankAccountEO.setIpAddress(IpAddressProvider.getClientIpAddr(request));
            bankAccountEO.setUpdatedOn(new Timestamp(new Date().getTime()));
            bankAccountEO.setAmount(amountCalculator.remunerationAmount(bankAccountEO.getAisheCode(),
                    bankAccountEO.getAccountKey().getSurveyYear(),bankAccountEO));
            bankAccountEO.setStatusId(RefRemunerationStatus.PENDING.getId());
            UserBankAccountEO accountEO = remunerationDao.saveUserBankDetail(bankAccountEO);
            // if (null != list && !list.isEmpty()) {
            //  FormUpload formUpload = list.get(0);

            // RemunerationStatementApproval remunerationnApproval=RemunerationStatementApproval.builder().formUploadId(Integer.valueOf(formUpload.getId())).statusId(RemunerationConstant.PENDING_APPROVAL_STATUS_ID).approverRoleId(RemunerationConstant.SNO_ROLE_ID).timestamp(bankAccountEO.getUpdatedOn()).approver_id(bankAccountEO.getAccountKey().getUserId()).ipAddress(bankAccountEO.getIpAddress()).build();
            // remunerationDao.saveRemunerationApproval(remunerationnApproval);
            // }
            // 		RemunerationStatementApproval.builder().formUploadId(Integer.valueOf(formUpload.getId())).statusId(RemunerationConstant.PENDING_APPROVAL_STATUS_ID).approverRoleId(RemunerationConstant.SNO_ROLE_ID).timestamp(bankAccountEO.getUpdatedOn()).approver_id(bankAccountEO.getAccountKey().getUserId()).ipAddress(bankAccountEO.getIpAddress()).build());
            logService.save(new UserActionLogEO(logService.getMaxId() + 1,
                    bankAccountEO.getAccountKey().getUserId(), bankAccountEO.getInstitutionId(),
                    bankAccountEO.getInstituteType(),
                    RemunerationConstant.BANK_AC_ACTION_ID
                    , bankAccountEO.getAccountKey().getSurveyYear(), new Timestamp(new Date().getTime()), IpAddressProvider.getClientIpAddr(request), null));
            return accountEO;
        } else {
            UserAccountKey accountKey = new UserAccountKey();
            accountKey.setUserId(userBankAccountEO.getAccountKey().getUserId());
            accountKey.setSurveyYear((userBankAccountEO.getAccountKey().getSurveyYear()));
            bankAccountEO.setAccountKey(accountKey);
            bankAccountEO.setIpAddress(IpAddressProvider.getClientIpAddr(request));
            bankAccountEO.setUpdatedOn(new Timestamp(new Date().getTime()));
            bankAccountEO.setAmount(amountCalculator.remunerationAmount(bankAccountEO.getAisheCode(),
                    bankAccountEO.getAccountKey().getSurveyYear(),bankAccountEO));
            bankAccountEO.setStatusId(RefRemunerationStatus.PENDING.getId());
            UserBankAccountEO accountEO = remunerationDao.updateUserBankDetail(bankAccountEO);
            logService.save(new UserActionLogEO(logService.getMaxId() + 1,
                    userBankAccountEO.getAccountKey().getUserId(), userBankAccountEO.getInstitutionId(),
                    userBankAccountEO.getInstituteType(),
                    RemunerationConstant.BANK_AC_ACTION_ID
                    , userBankAccountEO.getAccountKey().getSurveyYear(), new Timestamp(new Date().getTime()), IpAddressProvider.getClientIpAddr(request), null));
            return accountEO;
        }
//        } else {
//            throw new EntityException(bankAccountEO.getAccountKey().getUserId() + " Has not uploaded the form for survey year " + bankAccountEO.getAccountKey().getSurveyYear());
//        }
    }

    @Override
    public List<UserBankAccountEO> getUserBankDetail(Integer surveyYear, String userId, String aisheCode) {
        return remunerationDao.getUserBankDetail(surveyYear, userId, aisheCode);
    }

    @Override
    public RemunerationNormEO saveOrUpdateRemunerationNorm(RemunerationNormEO remunerationNorm, HttpServletRequest request) {
        List<RemunerationNormEO> remunerationNormEOS = remunerationDao.getRemunerationNorm(remunerationNorm.getRemunerationKey().getSurveyYear(), remunerationNorm.getRemunerationKey().getFormId());
        RemunerationNormEO remunerationNormEO = null;
        if (null != remunerationNormEOS && remunerationNormEOS.size() > 0)
            remunerationNormEO = remunerationNormEOS.get(0);
        if (null == remunerationNormEO) {
            return remunerationDao.saveRemunerationNorm(remunerationNorm);
        } else {
            RemunerationKey accountKey = new RemunerationKey();
            accountKey.setFormId(remunerationNorm.getRemunerationKey().getFormId());
            accountKey.setSurveyYear(remunerationNorm.getRemunerationKey().getSurveyYear());
            return remunerationDao.updateRemunerationNorm(remunerationNorm);
        }
    }

    @Override
    public List<RemunerationNormEO> getRemunerationNorm(Integer surveyYear, String formId) {
        return remunerationDao.getRemunerationNorm(surveyYear, formId);
    }

    @Override
    public RemunerationNormType1RuleEO saveOrUpdateRemunerationNormType1Rule(RemunerationNormType1RuleEO normType1RuleEO, HttpServletRequest request) {
        if (null == normType1RuleEO.getId() || 0 == normType1RuleEO.getId()) {
            return remunerationDao.saveRemunerationNormType1Rule(normType1RuleEO);
        } else {
            return remunerationDao.updateRemunerationNormType1Rule(normType1RuleEO);
        }
    }

    @Override
    public List<RemunerationNormType1RuleEO> getRemunerationNormType1RuleEO(Integer surveyYear, String aisheCode, InstitutionType institutionType, String formId) {
        return remunerationDao.getRemunerationNormType1RuleEO(surveyYear, aisheCode, institutionType != null ? institutionType.getType() : null, formId);
    }

    @Override
    public RemunerationNormType2RuleEO saveOrUpdateRemunerationNormType2Rule(RemunerationNormType2RuleEO normType2RuleEO, HttpServletRequest request) {
        if (null == normType2RuleEO.getId() || 0 == normType2RuleEO.getId()) {
            return remunerationDao.saveRemunerationNormType2Rule(normType2RuleEO);
        } else {
            return remunerationDao.updateRemunerationNormType2Rule(normType2RuleEO);
        }
    }

    @Override
    public List<RemunerationNormType2RuleEO> getRemunerationNormType2RuleEO(Integer surveyYear, String aisheCode, InstitutionType institutionType, String formId) {
        return remunerationDao.getRemunerationNormType2RuleEO(surveyYear, aisheCode, institutionType != null ? institutionType.getType() : null, formId);
    }

    @Override
    public List<RemunerationTransactionAndApprovalLogVo> getRemunerationTransactionAndApprovalLog(Integer surveyYear, String stateCode, String fromDate, String toDate, String instituteCategory, LogType logType) {
        switch (logType.getType()) {
            case "Approval Statement":
                return remunerationDao.getRemunerationLog(surveyYear, stateCode, fromDate, toDate, instituteCategory,
                        RemunerationNativeQueries.APPROVAL_LOG);
            default:
                return remunerationDao.getRemunerationLog(surveyYear, stateCode, fromDate, toDate, instituteCategory,
                        RemunerationNativeQueries.TRANSACTION_LOG);
        }
    }

    @Override
    public Boolean freezeRemunerationNorm(RemunerationFreezeVO remunerationFreezeVO) {
        List<RemunerationNormEO> remunerationNormEO = remunerationDao.getRemunerationNorm(remunerationFreezeVO.getSurveyYear(), remunerationFreezeVO.getType().getType());
        return remunerationDao.updateRemunerationNorm(remunerationNormEO, remunerationFreezeVO.getIsFreezed());
    }

    @Override
    public Boolean copyRemunerationNorm(RemunerationCopyVO remunerationFreezeVO) {
        List<RemunerationNormEO> remunerationNormEO = remunerationDao.getRemunerationNorm(remunerationFreezeVO.getToSurveyYear(), remunerationFreezeVO.getType().getType());
        return remunerationDao.updateRemunerationNorm(remunerationNormEO, remunerationFreezeVO.getSurveyYear());

    }

    @Override
    public UserAndInstitutionDetails getUserDetail(String userId) {
        return remunerationDao.getUserDetail(userId);
    }

    @Override
    public RemunerationProcessStatusVO getRemunerationStatus(Integer surveyYear, String userId, String aisheCode) {
        RemunerationProcessStatusVO statusVO = new RemunerationProcessStatusVO();
        String formId = handler.nullValueHandler(remunerationDao.getSingleStringValue(userId, surveyYear,
                RemunerationNativeQueries.CHECK_FORM_UPLOAD_BY_USER_ID_SURVEY_YEAR));
        setValues(statusVO, false, true, formId != null);
        if (null != formId) {
            if (!remunerationDao.getUserBankDetail(surveyYear, userId, aisheCode).isEmpty()) {
                List<ApprovalStatus> approvalStatuses = remunerationDao.getApprovalStatus(userId, surveyYear, RemunerationNativeQueries.CHECK_APPROVAL_STATUS_BY_USER_ID_SURVEY_YEAR);
                if (!approvalStatuses.isEmpty()) {
                    setValues(statusVO, true, true, true);
                    /*List<String> approvalStatusList = new ArrayList<>();
                    for (ApprovalStatus status : approvalStatuses) {
                     //   ApprovalStatus status= (ApprovalStatus) obj;
                        approvalStatusList.add("Remuneration Is " + status.getStatusName() + " by " + status.getApprovalRoleName() + " on dated " + status.getApprovalDateTime());
                    }

                     */
                    statusVO.setApprovalStatusList(approvalStatuses);
                    TransactionStatus transactionStatus = remunerationDao.getTransactionStatus(userId, surveyYear, RemunerationNativeQueries.CHECK_TRANSACTION_STATUS_BY_USER_ID_SURVEY_YEAR);
                    if (null != transactionStatus) {
                        statusVO.setTransactionStatus("Transaction Is " + transactionStatus.getTransactionStatus() +
                                " Against Remuneration on dated " + transactionStatus.getTransactionDateTime());
                    } else {
                        statusVO.setTransactionStatus("Pending");
                    }
                } else {
                    setValues(statusVO, true, false, true);
                }
            } else {
                setValues(statusVO, false, true, true);
            }
        } else {
            setValues(statusVO, false, true, false);
        }
        return statusVO;
    }

    @Override
    public List<RemunerationStatementApprovalByCategoryEO> getStatementApprovalByCategory(String stateCode, Integer surveyYear, String instituteCategory, String fromDate, String toDate, String statusId, String universityType, String institutionId, String approverRoleId, String collegeType, String parentInstitutionId, String stateBodyId) {
        List<RemunerationStatementApprovalByCategoryEO> approvalByCategoryWise = new LinkedList<>();
        List<RemunerationStatementApprovalByCategoryEO> approvalByCategory = remunerationDao.getStatementApprovalByCategory(stateCode, surveyYear, instituteCategory, fromDate, toDate, statusId, universityType, institutionId, approverRoleId, collegeType, parentInstitutionId, stateBodyId, RemunerationNativeQueries.APPROVAL_LIST_CATEGORY_WISE);
        if (!approvalByCategory.isEmpty()) {
            switch (instituteCategory) {
                case "University": {
                    List<RemunerationNormEO> remunerationNorm = remunerationDao.getRemunerationNorm(surveyYear, FormType.UNIVERSITY.getType());
                    RemunerationNormEO remunerationNormEO = !remunerationNorm.isEmpty() ? remunerationNorm.get(0) : null;
                    switch (remunerationNormEO.getTypeId()) {
                        case 1: {
                            List<RemunerationNormType1RuleEO> normType1RuleEO = getRemunerationNormType1RuleEO(surveyYear, null, null, FormType.UNIVERSITY.getType());
                            amountCalculatorRule1(approvalByCategory, normType1RuleEO, approvalByCategoryWise);
                            break;
                        }
                        case 2: {
                            List<RemunerationNormType2RuleEO> normType2RuleEO = getRemunerationNormType2RuleEO(surveyYear, null, null, FormType.UNIVERSITY.getType());
                            amountCalculatorRule2(approvalByCategory, normType2RuleEO, approvalByCategoryWise);
                            break;
                        }
                    }
                    break;
                }
                case "College Institution": {
                    List<RemunerationNormEO> remunerationNorm = remunerationDao.getRemunerationNorm(surveyYear, FormType.COLLEGE.getType());
                    RemunerationNormEO remunerationNormEO = !remunerationNorm.isEmpty() ? remunerationNorm.get(0) : null;
                    switch (remunerationNormEO.getTypeId()) {
                        case 1: {
                            List<RemunerationNormType1RuleEO> normType1RuleEO = getRemunerationNormType1RuleEO(surveyYear, null, null, FormType.COLLEGE.getType());
                            amountCalculatorRule1(approvalByCategory, normType1RuleEO, approvalByCategoryWise);
                            break;
                        }
                        case 2: {
                            List<RemunerationNormType2RuleEO> normType2RuleEO = getRemunerationNormType2RuleEO(surveyYear, null, null, FormType.COLLEGE.getType());
                            amountCalculatorRule2(approvalByCategory, normType2RuleEO, approvalByCategoryWise);
                            break;
                        }
                    }
                    break;
                }
                case "Standalone Institution": {
                    List<RemunerationNormEO> remunerationNorm = remunerationDao.getRemunerationNorm(surveyYear, FormType.STANDALONE.getType());
                    RemunerationNormEO remunerationNormEO = !remunerationNorm.isEmpty() ? remunerationNorm.get(0) : null;
                    switch (remunerationNormEO.getTypeId()) {
                        case 1: {
                            List<RemunerationNormType1RuleEO> normType1RuleEO = getRemunerationNormType1RuleEO(surveyYear, null, null, FormType.STANDALONE.getType());
                            amountCalculatorRule1(approvalByCategory, normType1RuleEO, approvalByCategoryWise);
                            break;
                        }
                        case 2: {
                            List<RemunerationNormType2RuleEO> normType2RuleEO = getRemunerationNormType2RuleEO(surveyYear, null, null, FormType.STANDALONE.getType());
                            amountCalculatorRule2(approvalByCategory, normType2RuleEO, approvalByCategoryWise);
                            break;
                        }
                    }
                    break;
                }
                default: {
                    List<RemunerationNormEO> remunerationNorm = remunerationDao.getRemunerationNorm(surveyYear, null);
                    for (RemunerationNormEO normEO : remunerationNorm) {
                        if (normEO.getRemunerationKey().getFormId().equals(FormType.UNIVERSITY.getType()) && normEO.getTypeId() == RemunerationConstant.REMUNERATION_NORM_TYPE_1) {
                            List<RemunerationNormType1RuleEO> normType1RuleEO = getRemunerationNormType1RuleEO(surveyYear, null, null, FormType.UNIVERSITY.getType());
                            List<RemunerationStatementApprovalByCategoryEO> list = approvalByCategory.stream().filter(statementApprovalByCategoryEO -> statementApprovalByCategoryEO.getFromId().equals(FormType.UNIVERSITY.getType())).collect(Collectors.toList());
                            amountCalculatorRule1(list, normType1RuleEO, approvalByCategoryWise);
                        } else if (normEO.getRemunerationKey().getFormId().equals(FormType.UNIVERSITY.getType()) && normEO.getTypeId() == RemunerationConstant.REMUNERATION_NORM_TYPE_2) {
                            List<RemunerationNormType2RuleEO> normType1RuleEO = getRemunerationNormType2RuleEO(surveyYear, null, null, FormType.UNIVERSITY.getType());
                            List<RemunerationStatementApprovalByCategoryEO> list = approvalByCategory.stream().filter(statementApprovalByCategoryEO -> statementApprovalByCategoryEO.getFromId().equals(FormType.UNIVERSITY.getType())).collect(Collectors.toList());
                            amountCalculatorRule2(list, normType1RuleEO, approvalByCategoryWise);
                        } else if (normEO.getRemunerationKey().getFormId().equals(FormType.COLLEGE.getType()) && normEO.getTypeId() == RemunerationConstant.REMUNERATION_NORM_TYPE_1) {
                            List<RemunerationNormType1RuleEO> normType1RuleEO = getRemunerationNormType1RuleEO(surveyYear, null, null, FormType.COLLEGE.getType());
                            List<RemunerationStatementApprovalByCategoryEO> list = approvalByCategory.stream().filter(statementApprovalByCategoryEO -> statementApprovalByCategoryEO.getFromId().equals(FormType.COLLEGE.getType())).collect(Collectors.toList());
                            amountCalculatorRule1(list, normType1RuleEO, approvalByCategoryWise);
                        } else if (normEO.getRemunerationKey().getFormId().equals(FormType.COLLEGE.getType()) && normEO.getTypeId() == RemunerationConstant.REMUNERATION_NORM_TYPE_2) {
                            List<RemunerationNormType2RuleEO> normType1RuleEO = getRemunerationNormType2RuleEO(surveyYear, null, null, FormType.COLLEGE.getType());
                            List<RemunerationStatementApprovalByCategoryEO> list = approvalByCategory.stream().filter(statementApprovalByCategoryEO -> statementApprovalByCategoryEO.getFromId().equals(FormType.COLLEGE.getType())).collect(Collectors.toList());
                            amountCalculatorRule2(list, normType1RuleEO, approvalByCategoryWise);
                        } else if (normEO.getRemunerationKey().getFormId().equals(FormType.STANDALONE.getType()) && normEO.getTypeId() == RemunerationConstant.REMUNERATION_NORM_TYPE_1) {
                            List<RemunerationNormType1RuleEO> normType1RuleEO =
                                    getRemunerationNormType1RuleEO(surveyYear, null, null, FormType.STANDALONE.getType());
                            List<RemunerationStatementApprovalByCategoryEO> list =
                                    approvalByCategory.stream().filter(statementApprovalByCategoryEO -> statementApprovalByCategoryEO.getFromId().equals(FormType.STANDALONE.getType())).collect(Collectors.toList());
                            amountCalculatorRule1(list, normType1RuleEO, approvalByCategoryWise);
                        } else if (normEO.getRemunerationKey().getFormId().equals(FormType.STANDALONE.getType()) && normEO.getTypeId() == RemunerationConstant.REMUNERATION_NORM_TYPE_2) {
                            List<RemunerationNormType2RuleEO> normType1RuleEO = getRemunerationNormType2RuleEO(surveyYear, null, null, FormType.STANDALONE.getType());
                            List<RemunerationStatementApprovalByCategoryEO> list = approvalByCategory.stream().filter(statementApprovalByCategoryEO -> statementApprovalByCategoryEO.getFromId().equals(FormType.STANDALONE.getType())).collect(Collectors.toList());
                            amountCalculatorRule2(list, normType1RuleEO, approvalByCategoryWise);
                        }
                    }
                    break;
                }
            }
        }
        return approvalByCategoryWise;
    }

    @Override
    public List<RemunerationStatementApprovalEO> saveOrUpdateStatementApproval(List<RemunerationStatementApprovalEO> approvalEOS, HttpServletRequest request) {
        return remunerationDao.saveOrUpdateStatementApproval(approvalEOS, request);
    }

    @Override
    public RemunerationStatementEO statementGeneration(RemunerationStatementVo statementGenerationVos, HttpServletRequest request) {
        if (RemunerationConstant.MHRD_ROLE_ID != statementGenerationVos.getApproverRoleId())
            throw new UnAuthorizationException("You're not authorized user for Remuneration Statement Generation !!");
        return remunerationDao.statementGeneration(statementGenerationVos, request);
    }

    @Override
    public List<RemunerationStatementDashBoardEO> getRemunerationStatementDashBoard(String generatorUserId, Integer surveyYear, String fromDate, String toDate) {
        return remunerationDao.getRemunerationStatementDashBoard(generatorUserId, surveyYear, fromDate, toDate, RemunerationNativeQueries.STATEMENT_DETAIL_WITH_TOTAL_AMOUNT);
    }

    @Override

    public RemunerationStatementDetailVO getStatementDetail(String statementId, String stateCode, String instituteCategory, String fromDate, String toDate, String statusId) {
        RemunerationStatementEO statementEO = remunerationDao.getRemunerationStatement(statementId);
        if (null != statementEO)
            return new RemunerationStatementDetailVO(statementEO,
                    remunerationDao.getStatementDetailsByStatementId(statementId, stateCode, statementEO.getSurveyYear(), instituteCategory, fromDate, toDate, statusId, RemunerationNativeQueries.TRANSACTION_DETAIL_STATEMENT_ID));
        return null;
    }

    @Override
    public List<RemunerationStatementDetailEO> saveRemunerationStatementTransactionApproval(List<RemunerationStatementDetailEO> detailEOS, HttpServletRequest request) {
        return remunerationDao.saveRemunerationStatementTransactionApproval(detailEOS, request);
    }

    @Override
    public RemunerationStatementEO getRemunerationStatement(String statementId) {
        return remunerationDao.getRemunerationStatement(statementId);
    }

    @Override
    public List<RemunerationDashboard> findStatementListByIdAndStatus(String statementId, String transactionStatusId, int limit, int offset) {
        return remunerationDao.findStatementListByIdAndStatus(statementId, transactionStatusId, limit, offset, RemunerationNativeQueries.TRANSACTION_STATUS_STATEMENT_ID_FOR_PDF);
    }

    @Override
    public List<RemunerationStatementDetailEO> uploadRemunerationMultipart(MultipartFile file, String generatorUserId, HttpServletRequest request) throws IOException {
        List<RemunerationStatementDetailEO> detailEOS = fileOperation.multipartFileOperation(file, generatorUserId, request);
        if (!detailEOS.isEmpty())
            return remunerationDao.saveRemunerationStatementTransactionApproval(detailEOS, request);
        throw new EntityException("Can't Upload blank file.");
    }

    @Override
    public AfiiliatedWithUniversityCount getAffiliatedCollegeCount(String universityId, Integer surveyYear) {
        return remunerationDao.getAffiliatedCollegeCount(universityId, surveyYear);
    }

    @Override
    public List<SelectOptionVO> checkEligibility(String aisheCode, Integer surveyYear) {
        return remunerationDao.checkEligibility(aisheCode, surveyYear);
    }

    private void setValues(RemunerationProcessStatusVO statusVO, Boolean accountStatus, Boolean npStatus, Boolean dcfStauts) {
        statusVO.setDcfUploadStatus(dcfStauts == true ? "DCF Uploaded" : "DCF Not Uploaded");
        statusVO.setBankAccountStatus(accountStatus == true ? "Account Details Is Updated" : "Account Details Is Not Updated");
        statusVO.setTransactionStatus("Not Applicable");
        List list = new ArrayList();
        list.add(npStatus == true ? "Not Applicable" : "Pending");
        statusVO.setApprovalStatusList(list);
    }


    private void amountCalculatorRule1(List<RemunerationStatementApprovalByCategoryEO> statementApprovalByCategoryEO, List<RemunerationNormType1RuleEO> normType1RuleEO, List<RemunerationStatementApprovalByCategoryEO> approvalByCategoryWise) {
        for (RemunerationStatementApprovalByCategoryEO approval : statementApprovalByCategoryEO) {
            for (RemunerationNormType1RuleEO type1RuleEO : normType1RuleEO) {
                if (approval.getNumberOfProgrammes() >= type1RuleEO.getMinimumProgrammes() && approval.getNumberOfProgrammes() <= type1RuleEO.getMaximumProgrammes() && null != type1RuleEO.getUniversityConstitutedFromColleges() && null != approval.getConstitutedFromColleges() && type1RuleEO.getUniversityConstitutedFromColleges() == approval.getConstitutedFromColleges()) {
                    approval.setAmount(type1RuleEO.getAmount());
                    approval.setNormTypeId(RemunerationConstant.REMUNERATION_NORM_TYPE_1);
                }
            }
            approvalByCategoryWise.add(approval);
        }
    }

    private void amountCalculatorRule2(List<RemunerationStatementApprovalByCategoryEO> statementApprovalByCategoryEO, List<RemunerationNormType2RuleEO> normType2RuleEO, List<RemunerationStatementApprovalByCategoryEO> approvalByCategoryWise) {
        for (RemunerationStatementApprovalByCategoryEO approval : statementApprovalByCategoryEO) {
            for (RemunerationNormType2RuleEO type1RuleEO : normType2RuleEO) {
                if (approval.getNumberOfProgrammes() >= type1RuleEO.getMinimumProgrammes() && approval.getNumberOfProgrammes() <= type1RuleEO.getMaximumProgrammes()) {
                    approval.setAmount(type1RuleEO.getAmount());
                    approval.setNormTypeId(RemunerationConstant.REMUNERATION_NORM_TYPE_2);
                }
            }
            approvalByCategoryWise.add(approval);
        }
    }

    @Override
    public List<UserBankAccountEO> getUserBankDetails(Integer surveyYear, String userId, String aisheCode) {
        return remunerationDao.getUserBankDetails(surveyYear,
                userId, aisheCode);
    }

    @Override
    public List<RemunerationReport> remunerationReport(Integer surveyYear, InstituteCategory institutionType, String aisheCode, String stateCode,
                                                       String districtCode, String categoryType, String universityId,
                                                       Date fDate, Date tDate, Integer statusId) {
        return remunerationDao.remunerationReport(surveyYear, institutionType, aisheCode, stateCode, districtCode,
                categoryType, universityId, fDate, tDate, statusId);
    }

    @Override
    public List<RemunerationChangeStatus> remunerationChangeStatus(List<RemunerationChangeStatus> detailEOS, HttpServletRequest request, UserInfo userInfo) {
        return remunerationDao.remunerationChangeStatus(detailEOS, request, userInfo);
    }

    @Override
    public List<RefRemunerationStatus> refRemunerationStatus() {
        return remunerationDao.refRemunerationStatus();
    }

    @Override
    public List<UserAccountLogEO> track(Integer surveyYear, String aisheCode) {
        return remunerationDao.track(surveyYear,aisheCode);
    }

   /* @Override
    public RemunerationChangeStatus lockBankDetails(RemunerationChangeStatus detailEOS, HttpServletRequest request, UserInfo userInfo) {
        return remunerationDao.lockBankDetails(detailEOS,request,userInfo);
    }*/


}
