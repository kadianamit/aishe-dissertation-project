package aishe.gov.in.utility;

import aishe.gov.in.client.EmailFeignClient;
import aishe.gov.in.dao.RefMasterDao;
import aishe.gov.in.dao.SurveyYearDao;
import aishe.gov.in.masterseo.FormDetailEO;
import aishe.gov.in.masterseo.RemunerationStatementApprovalEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailEO;
import aishe.gov.in.mastersvo.EmailVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Component
@Log4j2
public class RemunerationMailUtil {
    @Autowired
    private RefMasterDao refMasterDao;

    @Autowired
    private EmailFeignClient emailFeignClient;

    @Autowired
    private SurveyYearDao surveyYearDao;


    public void remunerationSendMailApprovalStatus(RemunerationStatementApprovalEO approvalEO) {
        String body = null;
        String subject = null;
        Integer status = approvalEO.getStatusId();
        String remarks = approvalEO.getRemarks();
        String roleName;
        String userToContact = null;
        if (RemunerationConstant.UNIVERSITY_OFFICER == approvalEO.getApprovalKey().getApproverRoleId() ||
                RemunerationConstant.STB_DTE_OFFICER == approvalEO.getApprovalKey().getApproverRoleId() ||
                RemunerationConstant.STATE_NURSING_BODY_OFFICER == approvalEO.getApprovalKey().getApproverRoleId() ||
                RemunerationConstant.SCERT_OFFICER == approvalEO.getApprovalKey().getApproverRoleId()) {
            roleName = RemunerationConstant.UNO_SECTORAL;
            userToContact = RemunerationConstant.USER_CONTACT_PERSON1;
        } else if (RemunerationConstant.SNO_OFFICER == approvalEO.getApprovalKey().getApproverRoleId()) {
            roleName = RemunerationConstant.SNO;
            userToContact = RemunerationConstant.USER_CONTACT_PERSON2;
        } else {
            roleName = RemunerationConstant.MOE;
        }
        FormDetailEO detailEO = refMasterDao.getUserDetails(approvalEO.getApprovalKey().getFormUploadId());
        if (RemunerationConstant.APPROVED_STATUS_ID == status) {
            body = "Dear " + detailEO.getUserId() + ",<br /><br /> Remuneration for <b>" + detailEO.getInstitutionName() + "</b> " +
                    "for <b>" + detailEO.getSurveyYear() + "</b> has been approved by MoE. The amount will be transferred  in due course to the following bank " +
                    "account:<br/><br/>Account Holder Name: <b>" + detailEO.getAccountHolderName() + "</b><br/>Account Number: <b>" + detailEO.getAccountNumber() + "</b><br/>IFSC Code: <b>" + detailEO.getIfsc_code() +
                    "</b><br/><br/>An e-mail/SMS will be sent, once amount is transferred in your account.<br /><br />Thank you,<br />AISHE Team<br /><br />(This is an auto generated email, kindly do not reply back.)";
            subject = RemunerationConstant.REMUNERATION_APPROVED_EMAIL_SUBJECT;
            if (null != detailEO.getPhoneMobile() && detailEO.getPhoneMobile().length() == 10) {
                String approvedSMSMessage =
                        "Dear User, Remuneration for " + detailEO.getInstitutionName() + " for " + detailEO.getSurveyYear() + " has been approved by MoE. It may take minimum one month for processing and crediting in your account.";
                //  SMSUtil.sendMessageWithTemplatedId(phoneMobile, approvedSMSMessage,"1007159852024128516");
                emailFeignClient.sendRemunerationMessage("1007159852024128516", approvedSMSMessage,
                        detailEO.getPhoneMobile());
            }
        }
        if (RemunerationConstant.REJECTED_STATUS_ID == status) {
            subject = RemunerationConstant.REMUNERATION_REJECTED_EMAIL_SUBJECT;
            if (userToContact != null) {
                body = "Dear " + detailEO.getUserId() + ",<br /><br /> Remuneration for <b>" + detailEO.getInstitutionName() + "</b> for <b>" + detailEO.getSurveyYear() + "</b> has been rejected by " + roleName + " due to the following reason:<br/>&nbsp;&nbsp;\"<b>" + remarks + "\"</b><br/><br/>Please contact " + userToContact + " for further details.<br/><br/>Thank you,<br />AISHE Team<br /><br />(This is an auto generated email, kindly do not reply back.)";
            } else {
                body = "Dear " + detailEO.getUserId() + ",<br /><br /> Remuneration for <b>" + detailEO.getInstitutionName() + "</b> for <b>" + detailEO.getSurveyYear() + "</b> has been rejected by " + roleName + " due to the following reason:<br/>&nbsp;&nbsp;\"<b>" + remarks + "\"</b><br/><br/>Thank you,<br />AISHE Team<br /><br />(This is an auto generated email, kindly do not reply back.)";
            }
        }
        emailFeignClient.sendMail(new EmailVo(body, detailEO.getEmailId(), subject));
    }


    public void sendEmailsAndSMSTransaction(RemunerationStatementDetailEO remuneration) {
        try {
            String transactionSuccessEmailMessage;
            String transactionSuccessSMSMessage;
            String transactionFailureEmailMessage;
            String transactionFailureSMSMessage;
            Integer transactionStatusId = remuneration.getTransactionStatusId();

            FormDetailEO detailEO = refMasterDao.getUserDetails(remuneration.getFormUploadId());

            log.info("Sending email to " + detailEO.getEmailId());
            if (RemunerationConstant.TRANSACTION_SUCCESSFUL_STATUS_ID == transactionStatusId) {

                transactionSuccessEmailMessage =
                        "Dear User,<br /><br /> Remuneration amount <b>" + detailEO.getAmount() + "</b> for <b>" + detailEO.getInstitutionName() + "</b> for <b>" + detailEO.getInstitutionName() + "</b> has been transferred to the following bank account:<br/><br/>Account Holder Name: <b>" + detailEO.getAccountHolderName() + "</b><br/>Account Number: <b>" + detailEO.getAccountNumber() + "</b><br/>IFSC Code: <b>" + detailEO.getIfsc_code() + "</b><br/><br/>Thank you,<br />AISHE Team<br /><br />(This is an auto generated email, kindly do not reply back.)";
                transactionSuccessSMSMessage = "Dear User, Remuneration amount " + detailEO.getAmount() + " for " + detailEO.getInstitutionName() + " for " + detailEO.getSurveyYear() + " has been transferred to your bank account.";

                emailFeignClient.sendMail(new EmailVo(detailEO.getEmailId(), RemunerationConstant.TRANSACTION_SUCCESS_EMAIL_SUBJECT, transactionSuccessEmailMessage));
                if (detailEO.getPhoneMobile() != null && detailEO.getPhoneMobile().length() == 10) {
                    emailFeignClient.sendRemunerationMessage("1007159852036925229", transactionSuccessSMSMessage,
                            detailEO.getPhoneMobile());
                    //SMSUtil.sendMessageWithTemplatedId(phoneMobile, transactionSuccessSMSMessage,"1007159852036925229");
                }
            }
            if (RemunerationConstant.TRANSACTION_FAILED_STATUS_ID == transactionStatusId) {
                //User is allowed to update their account details upto 3 months after closure of survey year
                Date surveyEndDate = Date.from(surveyYearDao.getSurveyMaster(detailEO.getSurveyYear()).getEndDate().atZone(ZoneId.systemDefault()).toInstant());
                Calendar c = Calendar.getInstance();
                c.setTime(surveyEndDate);
                c.add(Calendar.MONTH, 3);
                Date lastSubmissionDate = c.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                transactionFailureEmailMessage =
                        "Dear User,<br /><br /> Remuneration transaction of amount <b>" + detailEO.getAmount() + "</b" +
                                "> for <b>" + detailEO.getInstitutionName() + "</b> for <b>" + detailEO.getSurveyYear() + "</b> has failed. Please correct your bank account details on AISHE portal by " + sdf.format(lastSubmissionDate) + ". Current bank account details are:<br/><br/>Account Holder Name: <b>" + detailEO.getAccountHolderName() + "</b><br/>Account Number: <b>" + detailEO.getAccountNumber() + "</b><br/>IFSC Code: <b>" + detailEO.getIfsc_code() + "</b><br/><br/>Thank you,<br />AISHE Team<br/><br/>(This is an auto generated email, kindly do not reply back.)";
                transactionFailureSMSMessage =
                        "Dear User, Remuneration transaction of amount " + detailEO.getAmount() + " for " + detailEO.getInstitutionName() +
                                " for " + detailEO.getSurveyYear() + " has failed. Please correct your bank account " +
                                "details on AISHE" +
                                " portal by " + sdf.format(lastSubmissionDate) + ".";
                emailFeignClient.sendMail(new EmailVo(detailEO.getEmailId(),
                        RemunerationConstant.TRANSACTION_FAILURE_EMAIL_SUBJECT, transactionFailureEmailMessage));
                if (detailEO.getPhoneMobile() != null && detailEO.getPhoneMobile().length() == 10) {
                    emailFeignClient.sendRemunerationMessage("1007159852031976372", transactionFailureSMSMessage,
                            detailEO.getPhoneMobile());
                }
            }
        } catch (Exception e) {
            log.error("Exception in class: RemunerationStatementDetailDashboardAction and method: sendEmailsAndSMS()-" +
                    " ", e.getMessage());
            e.printStackTrace();
        }
    }

    public void setSMS(String number){
        Date surveyEndDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(surveyEndDate);
        c.add(Calendar.MONTH, 3);
        Date lastSubmissionDate = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String transactionFailureSMSMessage =
                "Dear User, Remuneration transaction of amount " + 10000 + " for " + "AISHE" +
                        " for " + 2020 + " has failed. Please correct your bank account " +
                        "details on AISHE" +
                        " portal by " + sdf.format(lastSubmissionDate) + ".";
        if (number != null && number.length() == 10) {
            emailFeignClient.sendRemunerationMessage("1007159852031976372", transactionFailureSMSMessage, number);
        }
    }
}
