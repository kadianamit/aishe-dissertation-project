package aishe.gov.in.dao;

import aishe.gov.in.client.EmailFeignClient;
import aishe.gov.in.enums.Constant;
import aishe.gov.in.masterseo.DataSharingApiUserInformationEO;
import aishe.gov.in.masterseo.DataSharingUserMasterEO;
import aishe.gov.in.masterseo.DataSharingUsersEO;
import aishe.gov.in.masterseo.OtpDetailsEO;
import aishe.gov.in.masterseo.OtpDetailsMouEO;
import aishe.gov.in.masterseo.RefDataTypeRequiredEO;
import aishe.gov.in.masterseo.RefOrganizationCategoryEO;
import aishe.gov.in.masterseo.RefStatusEO;
import aishe.gov.in.masterseo.RefUserCategoryEO;
import aishe.gov.in.masterseo.UserActionLog;
import aishe.gov.in.masterseo.UserRequestApprovalDetailsEO;
import aishe.gov.in.masterseo.UsersEO;
import aishe.gov.in.mastersvo.ChangePasswordDTO;
import aishe.gov.in.mastersvo.DataSharingApiUserInformationWithStatus;
import aishe.gov.in.mastersvo.DataSharingApiUserStatusUpdate;
import aishe.gov.in.mastersvo.EmailVo;
import aishe.gov.in.mastersvo.ForgotPasswordDto;
import aishe.gov.in.mastersvo.ResetPasswordDTO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.EncryptionDecryptionUtil;
import aishe.gov.in.utility.IpAddressProvider;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataSharingDaoImpl implements DataSharingDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    BCryptPasswordEncoder bcryptPass;

    @Autowired
    private EmailFeignClient emailFeignClient;


    private static final Logger log = LoggerFactory.getLogger(DataSharingDaoImpl.class);

    @Override
    public String apiUserInformation(DataSharingApiUserInformationEO eo) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            if (eo.getId() == 0) {
                eo.setId(null);
            }
            session.saveOrUpdate(eo);
            tx.commit();
            String mail = "Dear User,\n" +
                    "\n" +
                    "I hope this message finds you well.\n" +
                    "\n" +
                    "We are pleased to inform you that your request has been successfully submitted. Your Request ID is: " + eo.getRequestId() + ". Please use this ID for any future reference or inquiries regarding this request." +
                    "\r\n" +
                    "\r\n" +
                    "Regards,<br/>\r\n" +
                    "Team AISHE";
           // EmailVo vo = new EmailVo(mail, eo.getEmailId(), "Confirmation of Your Request Submission");
           // emailFeignClient.sendMail(vo);
            return eo.getRequestId();
        } catch (Exception e) {
            log.error("Generic Query can not be completed for save apiUserInformation output of {} column due to this exception", e.getMessage());
            if (null != tx && tx.isActive()) {
                tx.rollback();
            }
        }
        return null;
    }

    @Override
    public String lastRequestId() {
        try (Session session = sessionFactory.openSession()) {
            return (String) session.createNativeQuery("select request_id from data_sharing.api_user_information order by id desc limit 1").getSingleResult();
        } catch (Exception e) {
            log.error("Generic Query can not be completed for get lastRequestId output of {} column due to this exception", e.getMessage());
        }
        return null;
    }

    @Override
    public List<DataSharingApiUserInformationWithStatus> apiUserInformationList(Integer status, Integer orgCategory, Integer ministryId, Integer reqiredDataTypeId, Integer id) {
        try (Session session = sessionFactory.openSession()) {
            List<RefDataTypeRequiredEO> refDataTypeRequired = refDataTypeRequired(null);
            List<DataSharingApiUserInformationWithStatus> list = new ArrayList<>();
            String condition = id == -1 ? " null as request_letter " : " aui.request_letter ";
            List<Object[]> resultList = session.createNativeQuery("SELECT aui.id, aui.user_name, aui.org_category_id,roc.category as org_category_name, aui.ministry_id,rm.name as ministry_name, aui.department_name, aui.state_code,rs.name as state_name, aui.district_code,rd.name as district_name,\n" +
                            "aui.name_of_agency, aui.name_of_nodal_person, aui.gender_id,rg.name as gender_name, aui.mobile_no, aui.email_id, aui.complete_address, aui.purpose, CAST(aui.required_data_type_id AS TEXT) as required_data_type_id,null as required_data_type_name," + condition +
                            ", aui.request_id, aui.submission_date,rrs.status,urad.status_id ,urad.approved_date,urad.approver_id,aui.request_letter_name\n" +
                            "FROM data_sharing.api_user_information  aui\n" +
                            "left join data_sharing.user_request_approval_details urad on urad.request_id=aui.request_id\n" +
                            "left join ref_ministry rm on rm.id=aui.ministry_id\n" +
                            "left join data_sharing.ref_organization_category roc on roc.id=aui.org_category_id\n" +
                            "left join ref_gender rg on rg.id=aui.gender_id\n" +
                            "left join ref_remuneration_status rrs on rrs.id=urad.status_id\n" +
                            "left join ref_state rs on rs.st_code =aui.state_code\n" +
                            "left join ref_district rd on rd.dist_code=aui.district_code\n" +
                            "where 1=1 and  ( urad.status_id = :status OR -1 = :status OR (:status = 1 AND urad.status_id IS NULL))  and (aui.org_category_id=:orgCategory or -1=:orgCategory) and (aui.ministry_id=:ministryId or -1=:ministryId)  and  (aui.id=:id or -1=:id) ")
                    .setParameter("status", status).setParameter("orgCategory", orgCategory).setParameter("id", id)
                    .setParameter("ministryId", ministryId).getResultList();
            if (CommanObjectOperation.listValidate(resultList)) {
                for (Object[] obj : resultList) {
                    DataSharingApiUserInformationWithStatus info = new DataSharingApiUserInformationWithStatus();
                    info.setId(CommanObjectOperation.getIntegerValue(obj[0]));
                    info.setUserName(CommanObjectOperation.getStringValue(obj[1]));
                    info.setOrgCategoryId(CommanObjectOperation.getIntegerValue(obj[2]));
                    info.setOrgCategoryName(CommanObjectOperation.getStringValue(obj[3]));
                    info.setMinistryId(CommanObjectOperation.getIntegerValue(obj[4]));
                    info.setMinistryName(CommanObjectOperation.getStringValue(obj[5]));
                    info.setDepartmentName(CommanObjectOperation.getStringValue(obj[6]));
                    info.setStateCode(CommanObjectOperation.getStringValue(obj[7]));
                    info.setStateName(CommanObjectOperation.getStringValue(obj[8]));
                    info.setDistrictCode(CommanObjectOperation.getStringValue(obj[9]));
                    info.setDistrictName(CommanObjectOperation.getStringValue(obj[10]));
                    info.setNameOfAgency(CommanObjectOperation.getStringValue(obj[11]));
                    info.setNameOfNodalPerson(CommanObjectOperation.getStringValue(obj[12]));
                    info.setGenderId(CommanObjectOperation.getIntegerValue(obj[13]));
                    info.setGenderName(CommanObjectOperation.getStringValue(obj[14]));
                    info.setMobileNo(CommanObjectOperation.getStringValue(obj[15]));
                    info.setEmailId(CommanObjectOperation.getStringValue(obj[16]));
                    info.setCompleteAddress(CommanObjectOperation.getStringValue(obj[17]));
                    info.setPurpose(CommanObjectOperation.getStringValue(obj[18]));
                    if (null != obj[19]) {
                        String json = (String) obj[19];
                        if (json != null) {
                            List<Integer> ids = new ObjectMapper().readValue(json, new TypeReference<Object>() {
                            });
                            info.setRequiredDataType(refDataTypeRequired.stream()
                                    .filter(obj1 -> ids.contains(obj1.getId()))
                                    .collect(Collectors.toList()));
                        }
                    }


                    if (null != obj[21]) {
                        info.setRequestLetter((byte[]) obj[21]);
                    }
                    info.setRequestId(CommanObjectOperation.getStringValue(obj[22]));
                    if (null != obj[23]) {
                        info.setSubmittedOn(DateUtils.convertDBDateTimeToStringNew(LocalDateTime.parse(obj[23].toString(),
                                DateUtils.DB_DATE_LOCAL_TIME_PARSE)));
                    }
                    info.setStatusName(CommanObjectOperation.getStringValue(obj[24]));
                    info.setStatusId(CommanObjectOperation.getIntegerValue(obj[25]));
                    if (null != obj[26]) {
                        info.setActionDateTime(DateUtils.convertDBDateTimeToStringNew(LocalDateTime.parse(obj[26].toString(),
                                DateUtils.DB_DATE_LOCAL_TIME_PARSE)));
                    }
                    info.setApproverId(CommanObjectOperation.getStringValue(obj[27]));
                    info.setRequestLetterName(CommanObjectOperation.getStringValue(obj[28]));
                    list.add(info);
                }
            }
            return list;
        } catch (Exception e) {
            log.error("Generic Query can not be completed for get lastRequestId output of {} column due to this exception", e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean apiUserInformationUpdateStatus(DataSharingApiUserStatusUpdate eo, UserInfo userInfo, HttpServletRequest request) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            UserRequestApprovalDetailsEO detailsEO = new UserRequestApprovalDetailsEO(eo.getRequestId(), userInfo.getUsername(), eo.getStatusId(), IpAddressProvider.getClientIpAddr(request), DateUtils.obtainCurrentTimeStamp(), eo.getRemarks());
            session.saveOrUpdate(detailsEO);
            tx.commit();
            DataSharingApiUserInformationEO eo1 = getDataSharingApiUserInformationEO(eo.getRequestId());
            if (null != eo1) {
                List<RefStatusEO> status = refStatus(eo.getStatusId());
                String mail = "Dear User,\n" +
                        "\n" +
                        "I hope this message finds you well.\n" +
                        "\n" +
                        "We are writing to provide an update on the status of your request. Below are the details:\n" +
                        "\n" +
                        "Request ID: " + eo.getRequestId() + "\n" +
                        "Request Status: " + status.get(0).getStatus() + "\n" +
                        "On: " + DateUtils.convertDBDateTimeToStringNew(detailsEO.getApprovedDate()) + "\n" +
                        "Remarks: " + detailsEO.getRemarks() + "\n" +
                        "We have taken action on your request and are actively processing it. If you have any questions or need further information, please do not hesitate to contact us.\n" +
                        "\n" +
                        "Thank you for your patience, and we will continue to keep you updated as we proceed." +
                        "\r\n" +
                        "\r\n" +
                        "Regards,<br/>\r\n" +
                        "Team AISHE";

                EmailVo vo = new EmailVo(mail, eo1.getEmailId(), "Update on Your Request Submission");
                emailFeignClient.sendMail(vo);
            }
            return true;
        } catch (Exception e) {
            log.error("Generic Query can not be completed for save apiUserInformation output of {} column due to this exception", e.getMessage());
            if (null != tx && tx.isActive()) {
                tx.rollback();
            }
        }
        return null;
    }


    private DataSharingApiUserInformationEO getDataSharingApiUserInformationEO(String requestId) {
        try (Session session = sessionFactory.openSession()) {
            return (DataSharingApiUserInformationEO) session.createQuery(" from DataSharingApiUserInformationEO where requestId=:requestId")
                    .setParameter("requestId", requestId).getSingleResult();
        } catch (Exception e) {
            log.error("Generic Query can not be completed for getDataSharingApiUserInformationEO output of {} column due to this exception", e.getMessage());
        }
        return null;
    }

    @Override
    public UserRequestApprovalDetailsEO getLastUserActionStatus(String requestId) {
        try (Session session = sessionFactory.openSession()) {
            return (UserRequestApprovalDetailsEO) session.createQuery("from UserRequestApprovalDetailsEO where requestId=:requestId", UserRequestApprovalDetailsEO.class)
                    .setParameter("requestId", requestId).getSingleResult();
        } catch (Exception e) {
            log.error("Generic Query can not be completed for get getLastUserActionStatus output of {} column due to this exception", e.getMessage());
        }
        return null;
    }


    @Override
    public List<RefUserCategoryEO> refUserCategory(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder builder = new StringBuilder("from RefUserCategoryEO where 1=1 ");
            if (CommanObjectOperation.integerValidate(id)) {
                builder.append(" and id= " + id);
            }
            builder.append(" order by id ");
            return session.createQuery(builder.toString()).getResultList();
        } catch (Exception e) {
            log.error("Generic Query can not be completed for get refUserCategory output of {} column due to this exception", e.getMessage());
        }
        return null;
    }

    @Override
    public List<RefDataTypeRequiredEO> refDataTypeRequired(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder builder = new StringBuilder("from RefDataTypeRequiredEO where 1=1 ");
            if (CommanObjectOperation.integerValidate(id)) {
                builder.append(" and id= " + id);
            }
            builder.append(" order by id ");
            return session.createQuery(builder.toString()).getResultList();
        } catch (Exception e) {
            log.error("Generic Query can not be completed for get refDataTypeRequired output of {} column due to this exception", e.getMessage());
        }
        return null;
    }


    @Override
    public List<RefOrganizationCategoryEO> refOrgCategory(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder builder = new StringBuilder("from RefOrganizationCategoryEO where 1=1 ");
            if (CommanObjectOperation.integerValidate(id)) {
                builder.append(" and id= " + id);
            }
            builder.append(" order by id ");
            return session.createQuery(builder.toString()).getResultList();
        } catch (Exception e) {
            log.error("Generic Query can not be completed for get refOrgCategory output of {} column due to this exception", e.getMessage());
        }
        return null;
    }

    @Override
    public List<RefStatusEO> refStatus(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder builder = new StringBuilder("select * from ref_remuneration_status where 1=1 ");
            if (CommanObjectOperation.integerValidate(id)) {
                builder.append(" and id= " + id);
            }
            builder.append(" order by id limit 3");
            return session.createNativeQuery(builder.toString(), RefStatusEO.class).getResultList();
        } catch (Exception e) {
            log.error("Generic Query can not be completed for get refStatus output of {} column due to this exception", e.getMessage());
        }
        return null;
    }


    @Override
    public String dataSharingUserMaster(DataSharingUserMasterEO eo, HttpServletRequest request) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            boolean isUserExist = fetchUserNameInDataSharingAlreadyExist(eo.getUserName().toLowerCase());
            if (isUserExist) {
                return "Exist";
            }
            tx = session.beginTransaction();
            eo.setIpAddress(request.getHeader("X-Forwarded-For"));
            eo.setDateTime(DateUtils.obtainCurrentTimeStamp());
            if (eo.getEmailId() != null) {
                eo.setHashEmail(EncryptionDecryptionUtil.toHexString(EncryptionDecryptionUtil.getSHA(eo.getEmailId())));
            }
            if (eo.getMobileNo() != null) {
                eo.setHashMobile(EncryptionDecryptionUtil.toHexString(EncryptionDecryptionUtil.getSHA(eo.getMobileNo())));
            }
            if (eo.getPassword() != null) {
                eo.setPassword(EncryptionDecryptionUtil.getEncryptedString(eo.getPassword()));
                eo.setPassword(bcryptPass.encode(eo.getPassword()));
            }
            session.saveOrUpdate(eo);
            tx.commit();
            return "success";
        } catch (Exception e) {
            log.error("Generic Query can not be completed for save apiUserInformation output of {} column due to this exception", e.getMessage());
            if (null != tx && tx.isActive()) {
                tx.rollback();
            }
        }
        return null;
    }

    private boolean fetchUserNameInDataSharingAlreadyExist(String username) {
        Session session = sessionFactory.openSession();
        try {
            String userExist = (String) session.createNativeQuery("select user_name from data_sharing.user_master where lower(user_name)='" + username + "'")
                    .getSingleResult();
            if (userExist != null) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<DataSharingUserMasterEO> userMasterRequest(String username) {
        try (Session session = sessionFactory.openSession()) {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DataSharingUserMasterEO> query = builder.createQuery(DataSharingUserMasterEO.class);
            Root<DataSharingUserMasterEO> allData = query.from(DataSharingUserMasterEO.class);
            List<Predicate> predicates = new ArrayList<>();
            if (username != null) {
                predicates.add(builder.equal(allData.get("userName"), username));
            }
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            return (List<DataSharingUserMasterEO>) session.createQuery(query).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public String findByEmailIdIgnoreCase(String email) {
        log.info("UserDaoImpl : findByEmailIdIgnoreCase Method Invoked");
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createNativeQuery("select user_name from data_sharing.user_master where email_id =:email ").setParameter("email", email);
            return (String) query.getSingleResult();
        } catch (Exception e) {
            log.info("Users Info Display" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean saveForgotPassword(OtpDetailsMouEO forgot) {
        log.info("UserDaoImpl : saveForgotPassword method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(forgot);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                log.error("Couldn’t roll back transaction" + trEx.getMessage());
            }
            log.error("Error In saveForgotPassword {}" + e.getMessage());
        } finally {
            try {
                session.close();
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public String verifyOtpForogtPassword(ForgotPasswordDto forgotPassword) {
        String password = EncryptionDecryptionUtil.getDecryptedString(forgotPassword.getPassword());
        String cpassword = EncryptionDecryptionUtil.getDecryptedString(forgotPassword.getConfirmPassword());
        forgotPassword.setPassword(password);
//        forgotPassword.setConfirmPassword(cpassword);
        log.info("UserDaoImpl : verifyOtpForogtPassword Method Invoked");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<OtpDetailsEO> cQuery = cb.createQuery(OtpDetailsEO.class);
            Root<OtpDetailsEO> root = cQuery.from(OtpDetailsEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            Predicate cslsy = cb.or(cb.equal(root.get("mobileOtp"), forgotPassword.getMobileOtp()),
                    cb.equal(root.get("emailOtp"), forgotPassword.getEmailOtp()));

            Predicate emmob = cb.or(cb.equal(root.get("mobileNumber"), forgotPassword.getMobile()),
                    cb.equal(root.get("emailId"), forgotPassword.getEmail()));
            predicates.add(emmob);
            predicates.add(cslsy);
            cQuery.where(predicates.toArray(new Predicate[predicates.size()]));
            List<OtpDetailsEO> usersOtpDetailsList = session.createQuery(cQuery).getResultList();
            if (usersOtpDetailsList != null && usersOtpDetailsList.size() > 0) {
                for (OtpDetailsEO detailsEO : usersOtpDetailsList) {
                    if (detailsEO.getEmailOtp() != null && forgotPassword.getEmailOtp() != null && !forgotPassword.getEmailOtp().equals("")) {
                        if (forgotPassword.getEmailOtp().equals(detailsEO.getEmailOtp())) {
                            if (DateUtils.obtainCurrentTimeStamp()
                                    .isAfter(detailsEO.getEmailOtpDatetime().plusMinutes(15))) {
                                return "expemailotp";
                            }
                        }
                    } else if (detailsEO.getMobileOtp() != null && !forgotPassword.getMobileOtp().equals("") && forgotPassword.getMobileOtp() != null) {
                        if (forgotPassword.getMobileOtp().equals(detailsEO.getMobileOtp())) {
                            if (DateUtils.obtainCurrentTimeStamp()
                                    .isAfter(detailsEO.getMobileOtpDatetime().plusMinutes(15))) {
                                return "expmobileotp";
                            }
                        }
                    } else
                        return "invalidemailotp";

                }

                CriteriaBuilder cbContact = session.getCriteriaBuilder();
                CriteriaQuery<DataSharingUsersEO> cQueryContact = cbContact.createQuery(DataSharingUsersEO.class);
                Root<DataSharingUsersEO> rootContacts = cQueryContact.from(DataSharingUsersEO.class);
                cQueryContact.where(cbContact.and(cbContact.equal(rootContacts.get("userName"), forgotPassword.getLoginId())));
                TypedQuery<DataSharingUsersEO> contactQuery = session.createQuery(cQueryContact);
                DataSharingUsersEO usersFpEO = contactQuery.getSingleResult();
                if (password.equals(cpassword)) {
                    usersFpEO.setPassword(bcryptPass.encode(password));
                }
                session.update(usersFpEO);

                for (OtpDetailsEO detailsEO : usersOtpDetailsList) {
                    session.delete(detailsEO);
                }
            }
            tx.commit();
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                log.error("Couldn’t roll back transaction" + trEx.getMessage());
            }
            log.error("Error in verifyOtp " + e.getMessage());
            return "error";
        } finally {
            session.close();
        }
        return "success";
    }

    public String findByLoginId(String loginId) {
        log.info("UserDaoImpl : findByLoginId Method Invoked");
        Session session = sessionFactory.openSession();
        try {
            String userId = (String) session.createNativeQuery("select user_id from user_master where user_id='" + loginId + "'")
                    .uniqueResult();
            return userId;
        } catch (Exception e) {
            log.info("isUserNameExist Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }


    @Override
    public boolean resetPassword(ResetPasswordDTO changePassword, HttpServletRequest request, UserInfo userinfo) {
        log.info("UserDaoImpl : resetPassword Method Invoked");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        DataSharingUsersEO eo = new DataSharingUsersEO();
        try {
            eo = (DataSharingUsersEO) session.get(DataSharingUsersEO.class, userinfo.getUsername());
            if (eo != null) {
                String newp = EncryptionDecryptionUtil.getDecryptedString(changePassword.getNewPassword());
                String oldp = EncryptionDecryptionUtil.getDecryptedString(changePassword.getConfirmPassword());
                boolean check = newp.equals(oldp);
                if (check) {
                    eo.setPassword(bcryptPass.encode(newp));

                    /*if (eo.getPasswordChangeDate() == null && (eo.getIsPasswordChange() == null || eo.getIsPasswordChange().equals(false))) {
                        eo.setPasswordChangeDate(DateUtils.obtainCurrentTimeStamp());
                    }
                    eo.setIsPasswordChange(true);*/
                    //	eo.setUpdatedBy(userinfo.getUserId());
                    //	eo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
                    session.update(eo);
                    tx.commit();

                    UserActionLog userActionLog = new UserActionLog();
                    userActionLog.setId(null);
                    userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                    if (request != null) {
                        userActionLog.setIpAddress(request.getHeader("X-Forwarded-For"));
                        if (userActionLog.getIpAddress() == null || "".equals(userActionLog.getIpAddress())) {
                            userActionLog.setIpAddress(request.getRemoteAddr());
                        }
                    }
                    userActionLog.setIpAddress(request.getRemoteAddr());
                    userActionLog.setUserId(userinfo.getUsername());
                    userActionLog.setActionId(Constant.CHANGE_PASSWORD);
                    saveUserActionLog(userActionLog);
                    return true;
                } else {
                    return false;
                }

            }
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.info("Couldn’t roll back transaction" + rbe.getMessage());
            }
            log.info("Update All Offices Details Info Display" + e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }

    public void saveUserActionLog(UserActionLog useraction) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(useraction);
            tx.commit();
        } catch (Exception e) {
            log.error("Generic Query can not be completed for get output of {} due to this exception", e.getMessage());
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePassword, HttpServletRequest request, UserInfo userInfo) {
        log.info("UserDaoImpl : changePassword Method Invoked");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        DataSharingUsersEO eo = new DataSharingUsersEO();
        try {
            eo = (DataSharingUsersEO) session.get(DataSharingUsersEO.class, userInfo.getUsername());
            if (eo != null) {

                String oldPassword = EncryptionDecryptionUtil.getDecryptedString(changePassword.getOldPassword());
                boolean oldp = bcryptPass.matches(oldPassword, eo.getPassword());
                if (oldp) {

                    String newPassword = EncryptionDecryptionUtil.getDecryptedString(changePassword.getNewPassword());
                    eo.setPassword(bcryptPass.encode(newPassword));
                    /*if (eo.getPasswordChangeDate() == null && (eo.getIsPasswordChange() == null || eo.getIsPasswordChange().equals(false))) {
                        eo.setPasswordChangeDate(DateUtils.obtainCurrentTimeStamp());
                    }
                    eo.setIsPasswordChange(true);*/
                    //eo.setUpdatedBy(eo.getUserId());
                    //eo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
                    session.update(eo);
                    tx.commit();
                    UserActionLog userActionLog = new UserActionLog();
                    userActionLog.setId(null);
                    userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                    if (request != null) {
                        userActionLog.setIpAddress(request.getHeader("X-Forwarded-For"));
                        if (userActionLog.getIpAddress() == null || "".equals(userActionLog.getIpAddress())) {
                            userActionLog.setIpAddress(request.getRemoteAddr());
                        }
                    }
                    userActionLog.setIpAddress(request.getRemoteAddr());
                    userActionLog.setUserId(userInfo.getUsername());
                    userActionLog.setActionId(Constant.CHANGE_PASSWORD);
                    saveUserActionLog(userActionLog);
                    return true;
                } else {
                    return false;
                }

            }
        } catch (Exception e) {
            try {
                tx.rollback();
            } catch (RuntimeException rbe) {
                log.info("Couldn’t roll back transaction" + rbe.getMessage());
            }
            log.info("Update All Offices Details Info Display" + e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }


    @Override
    public boolean fetchTokenNotExpired(String token) {
        Session session = sessionFactory.openSession();
        try {
            LocalDateTime dateTime = DateUtils.obtainCurrentTimeStamp().plusHours(2);
            Long count = (Long) session.createQuery("select count(*) from UserAesUsedPassword where token=:token "
                            + " and dateTime<='" + dateTime + "' AND isTokenExpired is true").setParameter("token", token)
                    .uniqueResult();
            return count > 0 ? true : false;
        } catch (Exception e) {
            log.error("Generic Query can not be completed for get output of {} due to this exception", e.getMessage());

        } finally {
            session.close();
        }
        return false;
    }

}
