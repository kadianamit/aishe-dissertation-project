package aishe.gov.in.dao;

import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.masterseo.ActivityMasterEO;
import aishe.gov.in.masterseo.ActivityMasterLogsEO;
import aishe.gov.in.masterseo.AddressDetailsEO;
import aishe.gov.in.masterseo.CollegeFormEO;
import aishe.gov.in.masterseo.CollegeInstitutionRequestDetailsEO;
import aishe.gov.in.masterseo.NtaQuestionEO;
import aishe.gov.in.masterseo.NtaQuestionnaire;
import aishe.gov.in.masterseo.NtaQuestionnaireEO;
import aishe.gov.in.masterseo.OtpDetailsEO;
import aishe.gov.in.masterseo.PersonDetailsEO;
import aishe.gov.in.masterseo.RefActivityActionEO;
import aishe.gov.in.masterseo.RefActivityEO;
import aishe.gov.in.masterseo.RefUserRoleMaster;
import aishe.gov.in.masterseo.RequestDetailsEO;
import aishe.gov.in.masterseo.StandaloneInstitutionRequestDetailsEO;
import aishe.gov.in.masterseo.UniversityFormEO;
import aishe.gov.in.masterseo.UserActionLog;
import aishe.gov.in.masterseo.UserMasterDetailEO;
import aishe.gov.in.masterseo.UserMasterEO;
import aishe.gov.in.masterseo.UserMasterLogDetailEO;
import aishe.gov.in.masterseo.UserMasterNewEO;
import aishe.gov.in.masterseo.UserMasterRequestDetailEO;
import aishe.gov.in.masterseo.UserRegistrationDetailEO;
import aishe.gov.in.mastersvo.AisheInstituteRequestCheckNameVO;
import aishe.gov.in.mastersvo.ApprovingAuthorityDTO;
import aishe.gov.in.mastersvo.NewInstituteRequestCheckNameVO;
import aishe.gov.in.mastersvo.RequestForAddInstitute;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.IpAddressProvider;
import aishe.gov.in.utility.NullValueHandler;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Repository
@Log4j2
public class AisheUserRequestDaoImpl implements AisheUserRequestDao {
    @Autowired(required = true)
    private SessionFactory sessionFactory;
    @Autowired(required = true)
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private NullValueHandler handler;

    @Override
    public Integer saveRequestForAddInstitute(RequestForAddInstitute requestInstitute) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {

            Integer headId = ((Integer) session.createQuery("select max(Id) from PersonDetailsEO").uniqueResult());
            PersonDetailsEO headBy = new PersonDetailsEO();
            headBy.setId(headId + 1);
            headBy.setName(requestInstitute.getDirectorName());
            headBy.setDesignation(requestInstitute.getDirectorDesignation());
            headBy.setEmail(requestInstitute.getDirectorEmail());
            headBy.setMobile(requestInstitute.getDirectorMobile());
            headBy.setGender(requestInstitute.getDirectorGender());
            headBy.setLandline(requestInstitute.getDirectorTelephone());
            headBy.setReason(requestInstitute.getDirectorReason());
            session.save(headBy);

            Integer personId = ((Integer) session.createQuery("select max(Id) from PersonDetailsEO").uniqueResult());
            PersonDetailsEO createdBy = new PersonDetailsEO();
            createdBy.setId(personId + 1);
            createdBy.setName(requestInstitute.getPersonName());
            createdBy.setDesignation(requestInstitute.getPersonDesignation());
            createdBy.setEmail(requestInstitute.getPersonEmail());
            createdBy.setMobile(requestInstitute.getPersonMobile());
            createdBy.setGender(requestInstitute.getPersonGender());
            createdBy.setLandline(requestInstitute.getPersonLandline());
            createdBy.setReason(requestInstitute.getPersonReason());
            session.save(createdBy);

            Integer addressId = ((Integer) session.createQuery("select max(Id) from AddressDetailsEO").uniqueResult());
            AddressDetailsEO address = new AddressDetailsEO();
            address.setLine1(requestInstitute.getPersonLine1());
            address.setLine2(requestInstitute.getPersonLine2());
            address.setCity(requestInstitute.getPersonCity());
            address.setStateCode(requestInstitute.getStateCode());
            address.setDistrictCode(requestInstitute.getDistrictCode());
            address.setPincode(requestInstitute.getPersonPincode());
            address.setId(addressId + 1);
            session.save(address);

            RequestDetailsEO request = new RequestDetailsEO();
            Integer requestId = ((Integer) session.createQuery("select max(id) from RequestDetailsEO").uniqueResult());
            if (requestInstitute.getInstituteTypeCorS().equals("C")) {
                request.setType(1);
            }
            if (requestInstitute.getInstituteTypeCorS().equals("S")) {
                request.setType(2);
            }
            //HttpServletRequest requests =null;
            request.setDocumentPdf(requestInstitute.getDocumentPdf());
            request.setCreatedBy(createdBy.getId());
            request.setAddressId(address.getId());
            request.setCreatedDate(DateUtils.obtainCurrentTimeStamp());
            request.setIpAddress(requestInstitute.getIpAddress());//set ip address
            request.setIsDeclarationAccepted(requestInstitute.getIsDeclarationAccepted());
            request.setInstitutionalHeadDetails(headBy.getId());
            request.setId(requestId + 1);
            session.save(request);
            //Integer requestApprovalId = requestId;//RequestApprovalDetailsEO;

            if (requestInstitute.getInstituteTypeCorS().equals("C")) {
                CollegeInstitutionRequestDetailsEO cird = new CollegeInstitutionRequestDetailsEO();
                cird.setRequestId(request.getId());
                cird.setSurveyYear(requestInstitute.getSurveyYear());
                cird.setName(requestInstitute.getInstituteName());
                cird.setStateCode(requestInstitute.getStateCode());
                cird.setDistrictCode(requestInstitute.getDistrictCode());
                cird.setCollegeTypeId(requestInstitute.getCollegeTypeId());
                cird.setUniversityId(requestInstitute.getUniversityId());
                cird.setLocationId(requestInstitute.getLocationId());
                cird.setManagementId(requestInstitute.getManagementId());
                cird.setAdmissionYear(requestInstitute.getAdmissionYear());
                cird.setCreatedCollegeInstitutionId(null);
                cird.setCreatedSurveyYear(null);
                cird.setIsAffiliatedToOtherUniversity(requestInstitute.getIsAffiliatedToOtherUniversity());
                cird.setOtherAffiliatedUniversityName(requestInstitute.getOtherAffiliatedUniversityName());
                cird.setAdmissionProcessCompleted(requestInstitute.getAdmissionProcessCompleted());
                session.save(cird);
            }

            if (requestInstitute.getInstituteTypeCorS().equals("S")) {
                StandaloneInstitutionRequestDetailsEO sird = new StandaloneInstitutionRequestDetailsEO();
                sird.setRequestId(request.getId());
                sird.setSurveyYear(requestInstitute.getSurveyYear());
                sird.setName(requestInstitute.getInstituteName());
                sird.setStateCode(requestInstitute.getStateCode());
                sird.setDistrictCode(requestInstitute.getDistrictCode());
                sird.setStateBodyId(requestInstitute.getStateBodyId());
                sird.setUniversityId(requestInstitute.getUniversityId());
                if (requestInstitute.getUniversityId().equals("")) {
                    sird.setUniversityId(null);
                }
                sird.setLocationId(requestInstitute.getLocationId());
                sird.setManagementId(requestInstitute.getManagementId());
                sird.setAdmissionYear(requestInstitute.getAdmissionYear());
                sird.setIsAffiliatedToUniversity(requestInstitute.getIsAffiliatedToUniversity());
                sird.setCreatedStandaloneInstitutionId(null);
                sird.setCreatedSurveyYear(null);
                sird.setMinistryId(requestInstitute.getMinistryId());
                sird.setAdmissionProcessCompleted(requestInstitute.getAdmissionProcessCompleted());
                session.save(sird);
            }
            // save in user master
            UserMasterDetailEO userMaster = new UserMasterDetailEO();
            userMaster.setStateCode(requestInstitute.getStateCode());
            userMaster.setUserId(requestInstitute.getUserId());
            userMaster.setBcryptPassword(bCryptPasswordEncoder.encode(requestInstitute.getBcryptPassword()));
            userMaster.setRoleId(requestInstitute.getRoleId());
            userMaster.setFirstName(requestInstitute.getFirstName());
            userMaster.setMiddleName(requestInstitute.getMiddleName());
            userMaster.setLastName(requestInstitute.getLastName());//not null constraint
            //father first middle last name not enter here
            userMaster.setAddressLine1(requestInstitute.getPersonLine1());
            userMaster.setAddressLine2(requestInstitute.getPersonLine2());
            userMaster.setPhoneLandline(requestInstitute.getPersonLandline());
            userMaster.setPhoneMobile(requestInstitute.getPersonMobile());
            userMaster.setCity(requestInstitute.getPersonCity());
            userMaster.setEmailId(requestInstitute.getPersonEmail());
            userMaster.setUserPasswordInHash(null);
            userMaster.setIsApproved(0);
            userMaster.setPrivilegeId(3);
            userMaster.setApprovedDatetime(null);
            userMaster.setModifiedDatetime(null);
            userMaster.setApprovedBy(null);
            userMaster.setModifiedBy(null);
            userMaster.setStateLevelBody(requestInstitute.getUniversityId());//university id
            userMaster.setStateLevelBodyInstitute(requestInstitute.getStateLevelBodyInstitute());//c aishe code
            userMaster.setBodyType(requestInstitute.getBodyType());//s aishe code
            if (requestInstitute.getInstituteTypeCorS().equals("C")) {
                userMaster.setBodyType("1");
            }
            userMaster.setAddressStateCode(requestInstitute.getPersonStateCode());
            userMaster.setAddressDistrictCode(requestInstitute.getPersonDistrictCode());
            userMaster.setStdCode(requestInstitute.getStdCode());
            userMaster.setRegistrationDatetime(DateUtils.obtainCurrentTimeStamp());
            userMaster.setAltEmailId(requestInstitute.getAltEmailId());
            userMaster.setStatusId(1);//
            userMaster.setDeoUserType(requestInstitute.getDeoUserType());
            userMaster.setAisheCode(requestInstitute.getAisheCode());
            userMaster.setGenderId(requestInstitute.getPersonGender());
            userMaster.setRequestId(request.getId());
            userMaster.setIpAddress(requestInstitute.getIpAddress());//set ip address
            userMaster.setUserPasswordInHash("999");
            session.save(userMaster);

            UserActionLog userActionLog = new UserActionLog();
            userActionLog.setUserId(requestInstitute.getUserId());
            userActionLog.setInstitutionId(null);
            userActionLog.setInstitutionType(null);
            userActionLog.setActionId(12);
            userActionLog.setSurveyYear(0);
            userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
            userActionLog.setIpAddress(requestInstitute.getIpAddress());
            userActionLog.setRemarks("UserId Created by Self");
            Integer userActionLogId = ((Integer) session.createQuery("select max(id) from UserActionLog").uniqueResult());
            userActionLog.setId(userActionLogId + 1);
            session.save(userActionLog);
            tx.commit();
            return request.getId();
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            try {
                session.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public List<NewInstituteRequestCheckNameVO> InstituteNameCheck(String typeId, String districtName, String stateName,
                                                                   String instituteName) {
        Session session = sessionFactory.openSession();
        List<NewInstituteRequestCheckNameVO> examinationResultList = new ArrayList<>();
        try {
            //Aishe code/Request Id	   Category 	University/State Body	Institution Name	Status
            StringBuilder queryBuilder = new StringBuilder();
            //REQUEST FOR COLLEGE
            if (typeId.equals("C")) {
                queryBuilder.append(
                        "select cast(cird.request_id as text),cird.survey_year,cird.name as insName,ru.name as university,--,cird.created_college_institution_id\r\n" +
                                "'College' as category,rqd.approver_id as userid,rurm.role_name as roles,rrsas.name as status\r\n" +
                                "from college_institution_request_details cird left join public.request_approval_details rqd on rqd.request_id = cird.request_id\r\n" +
                                "left join public.ref_user_role_master rurm on rurm.role_id = rqd.approver_role_id\r\n" +
                                "left join public.ref_remuneration_statement_approval_status rrsas on rrsas.id = rqd.status_id\r\n" +
                                "left join public.ref_university ru on ru.id = cird.university_id \r\n" +
                                "where cird.state_code ='" + stateName + "' and cird.district_code='" + districtName + "' and cird.survey_year in (select max(survey_year) from\r\n" +
                                "college_institution_request_details) and cird.created_college_institution_id is null\r\n" +
                                "and ru.survey_year in (select max(survey_year) from ref_university)\r\n" +
                                "and rqd.status_id !='2' and rqd.approver_role_id in(1,7,8,9,10,11) \r\n" +
                                "and rqd.request_id in (select distinct request_id from request_approval_details) \r\n" +
                                "union all\r\n" +
                                "select 'C-'||c.id as aisheCode,c.survey_year,c.name as insName,ru.name as university,'College' as category\r\n" +
                                ",'' as userid,'' as roles,'Active' as Status\r\n" +
                                "from college c\r\n" +
                                "left join public.ref_university ru on ru.id = c.university_id \r\n" +
                                "where c.state_code ='" + stateName + "' and c.district_code='" + districtName + "' \r\n" +
                                "and c.survey_year in (select max(survey_year) from college) and ru.survey_year in (select max(survey_year) from ref_university)");
            }
            //REQUEST FOR STANDALONE
            if (typeId.equals("S")) {
                queryBuilder.append(
                        "select cast(cird.request_id as text),cird.survey_year,cird.name as insName,ru.type as statebody,--,cird.created_college_institution_id\r\n" +
                                "'Standalone' as category,rqd.approver_id as userid,rurm.role_name as roles,rrsas.name as status\r\n" +
                                "from standalone_institution_request_details cird left join public.request_approval_details rqd on rqd.request_id = cird.request_id\r\n" +
                                "left join public.ref_user_role_master rurm on rurm.role_id = rqd.approver_role_id\r\n" +
                                "left join public.ref_remuneration_statement_approval_status rrsas on rrsas.id = rqd.status_id\r\n" +
                                "left join public.ref_state_body ru on ru.id = cird.state_body_id \r\n" +
                                "where cird.state_code ='" + stateName + "' and cird.district_code='" + districtName + "' and\r\n" +
                                "cird.survey_year in (select max(survey_year) from\r\n" +
                                "standalone_institution_request_details) and cird.created_standalone_institution_id is null\r\n" +
                                "and rqd.status_id !='2' and rqd.approver_role_id in(1,7,8,9,10,11) \r\n" +
                                "and rqd.request_id in (select distinct request_id from request_approval_details)\r\n" +
                                "union all \r\n" +
                                "select 'S-'||c.id as aisheCode,c.survey_year,c.name as insName,ru.type as statebody,'Standalone' as category\r\n" +
                                ",'' as userid,'' as roles,'Active' as Status\r\n" +
                                "from ref_standalone_institution c \r\n" +
                                "left join public.ref_state_body ru on ru.id = c.statebodyid \r\n" +
                                "where c.statecode ='" + stateName + "' and c.district_code='" + districtName + "' \r\n" +
                                "and c.survey_year in (select max(survey_year) from ref_standalone_institution)");
            }
            Query query = session.createNativeQuery(queryBuilder.toString());

            @SuppressWarnings("unchecked")
            List<Object[]> countryListData = (List<Object[]>) query.getResultList();
            for (Object[] object : countryListData) {
                NewInstituteRequestCheckNameVO examinationResult = new NewInstituteRequestCheckNameVO();
                if (object[0] != null) {
                    examinationResult.setRequestIdOrAisheCode(object[0].toString());
                }
                if (object[1] != null) {
                    examinationResult.setSurveyYear(Integer.parseInt(object[1].toString()));
                }
                if (object[2] != null) {
                    examinationResult.setInstituteName(object[2].toString());
                }
                if (object[3] != null) {
                    examinationResult.setUniversityOrStateBodyName(object[3].toString());
                }
                if (object[4] != null) {
                    examinationResult.setCategoryName(object[4].toString());
                }
                if (object[5] != null) {
                    examinationResult.setUserId(object[5].toString());
                }
                if (object[6] != null) {
                    examinationResult.setRoleName(object[6].toString());
                }
                if (object[7] != null) {
                    examinationResult.setStatusName(object[7].toString());
                    if (examinationResult.getStatusName().equals("Approved")) {
                        examinationResult.setStatusName("Active");
                    }
                }
                String teamName = examinationResult.getInstituteName();
                String propositionName = instituteName;
                @SuppressWarnings("deprecation")
                double distance = StringUtils.getJaroWinklerDistance(teamName, propositionName) * 100;
                int dist = (int) distance;
                if (dist == 100) {
                    examinationResultList.add(examinationResult);
                }
            }
            return examinationResultList;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return examinationResultList;
    }

    @Override
    public Integer saveEmailOtp(OtpDetailsEO forgot) {
        Session session = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Integer id = 0;
            if (forgot.getSno() == 0) {
                id = ((Integer) session.createQuery("select max(sno) from OtpDetailsEO").uniqueResult());
                if (id == null) {
                    id = 0;
                }
                forgot.setSno(id + 1);
            } else {
                OtpDetailsEO data = (OtpDetailsEO) session2.get(OtpDetailsEO.class, forgot.getSno());
                forgot.setMobileNumber(data.getMobileNumber());
                forgot.setMobileOtp(data.getMobileOtp());
                forgot.setMobileOtpDatetime(data.getMobileOtpDatetime());
                forgot.setIsMobilenoVerified(data.getIsMobilenoVerified());
                forgot.setMobileNoVerifiedDatetime(data.getMobileNoVerifiedDatetime());
                forgot.setSno(forgot.getSno());
            }
            session.saveOrUpdate(forgot);
            tx.commit();
            return forgot.getSno();
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            try {
                session2.close();
                session.close();
            } catch (Exception e) {
            }
        }
        return forgot.getSno();
    }

    @Override
    public Integer saveMobileOtp(OtpDetailsEO forgot) {
        Session session = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Integer id = 0;
            if (forgot.getSno() == 0) {
                id = ((Integer) session.createQuery("select max(sno) from OtpDetailsEO").uniqueResult());
                if (id == null) {
                    id = 0;
                }
                forgot.setSno(id + 1);
            } else {
                OtpDetailsEO data = (OtpDetailsEO) session2.get(OtpDetailsEO.class, forgot.getSno());
                forgot.setEmailId(data.getEmailId());
                forgot.setEmailOtp(data.getEmailOtp());
                forgot.setEmailOtpDatetime(data.getEmailOtpDatetime());
                forgot.setIsEmailIdVerified(data.getIsEmailIdVerified());
                forgot.setEmailIdVerifiedDatetime(data.getEmailIdVerifiedDatetime());
                forgot.setSno(forgot.getSno());
            }
            session.saveOrUpdate(forgot);
            tx.commit();
            return forgot.getSno();
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            try {
                session.close();
                session2.close();
            } catch (Exception e) {
            }
        }
        return forgot.getSno();
    }

    @Override
    public Boolean verifyEmailOtp(String email, String otp) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            OtpDetailsEO otpVerify = fetchOtpDetailsFromEmailOtp(email, otp);
            if (DateUtils.obtainCurrentTimeStamp().isAfter(otpVerify.getEmailOtpDatetime().plusMinutes(15))) {
                return false;
            }
            otpVerify.setEmailIdVerifiedDatetime(DateUtils.obtainCurrentTimeStamp());
            otpVerify.setIsEmailIdVerified(true);
            session.update(otpVerify);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            try {
                session.close();
            } catch (Exception e) {
            }
        }
        return false;
    }

    private OtpDetailsEO fetchOtpDetailsFromEmailOtp(String email, String otp) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OtpDetailsEO> query = builder.createQuery(OtpDetailsEO.class);
            Root<OtpDetailsEO> allData = query.from(OtpDetailsEO.class);
            query.where(builder.and(builder.equal(allData.get("emailId"), email),
                    builder.equal(allData.get("emailOtp"), otp)));
            OtpDetailsEO university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean verifyMobileOtp(String mobile, String otp) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            OtpDetailsEO otpVerify = fetchOtpDetailsFromMobileandOtp(mobile, otp);
            if (DateUtils.obtainCurrentTimeStamp().isAfter(otpVerify.getMobileOtpDatetime().plusMinutes(15))) {
                return false;
            }
            otpVerify.setIsMobilenoVerified(true);
            otpVerify.setMobileNoVerifiedDatetime(DateUtils.obtainCurrentTimeStamp());
            session.update(otpVerify);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            try {
                session.close();
            } catch (Exception e) {
            }
        }
        return false;
    }

    private OtpDetailsEO fetchOtpDetailsFromMobileandOtp(String mobile, String otp) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OtpDetailsEO> query = builder.createQuery(OtpDetailsEO.class);
            Root<OtpDetailsEO> allData = query.from(OtpDetailsEO.class);
            query.where(builder.and(builder.equal(allData.get("mobileNumber"), mobile),
                    builder.equal(allData.get("mobileOtp"), otp)));
            OtpDetailsEO university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean userIdAlreadyExist(String userId) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterRequestDetailEO> query = builder.createQuery(UserMasterRequestDetailEO.class);
            Root<UserMasterRequestDetailEO> allData = query.from(UserMasterRequestDetailEO.class);
            query.where(builder.and(builder.equal(allData.get("userId"), userId)));
            UserMasterRequestDetailEO user = session.createQuery(query).getSingleResult();
            if (user != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<AisheInstituteRequestCheckNameVO> InstituteNameExistInAishe(String districtCode, String instituteName) {
        Session session = sessionFactory.openSession();
        List<AisheInstituteRequestCheckNameVO> completeResultList = new ArrayList<>();
        List<Integer> unApprovedList = new ArrayList<>();
        List<AisheInstituteRequestCheckNameVO> completeResultList2 = new ArrayList<>();
        try {
            //Aishe code/Request Id	   Category 	University/State Body	Institution Name	Status

            //public.ref_university
            List<AisheInstituteRequestCheckNameVO> uniMasterData =
                    (List<AisheInstituteRequestCheckNameVO>) session.createNativeQuery("select 'U-'||ru.id as aishecode,rut.type,'University' as category,ru.name as name,CASE WHEN ru.is_dcf_applicable is true THEN 'Active'\r\n" +
                            "WHEN ru.is_dcf_applicable is false THEN 'InActive' ELSE 'InActive' END  as status from public.ref_university ru\r\n" +
                            "left join public.ref_university_type rut on rut.id= ru.type_id where ru.survey_year in \r\n" +
                            "(select max(survey_year)from public.survey_master) and district_code ='" + districtCode + "'").getResultList();
            completeResultList.addAll(uniMasterData);

            //public.college
            List<AisheInstituteRequestCheckNameVO> collegeMasterData = (List<AisheInstituteRequestCheckNameVO>)
                    session.createNativeQuery("select 'C-'||c.id as aishecode,'College' as category,ru.name as uniname,c.name as collegename,CASE WHEN c.is_dcf_applicable is true THEN 'Active' WHEN ru.is_dcf_applicable\r\n" +
                            "is false THEN 'InActive' ELSE 'InActive' END as status from public.college c left join ref_university ru on ru.id = \r\n" +
                            "c.university_id where c.survey_year in (select max(survey_year)from public.survey_master)\r\n" +
                            "and ru.survey_year in (select max(survey_year)from public.survey_master) and c.district_code ='" + districtCode + "'").getResultList();
            completeResultList.addAll(collegeMasterData);

            //public.ref_standalone_institution
            List<AisheInstituteRequestCheckNameVO> standMasterMasterData = (List<AisheInstituteRequestCheckNameVO>)
                    session.createNativeQuery("select 'S-'||c.id as aishecode,'Standalone' as category,ru.type as type,c.name as collegename,'Active' as status from public.ref_standalone_institution c left join ref_state_body ru on ru.id = \r\n" +
                            "c.statebodyid where c.survey_year in (select max(survey_year)from public.survey_master) and c.district_code ='" + districtCode + "'").getResultList();
            completeResultList.addAll(standMasterMasterData);

            //public.college_institution_request_details
            List<AisheInstituteRequestCheckNameVO> cirdData = (List<AisheInstituteRequestCheckNameVO>)
                    session.createNativeQuery("select CASE WHEN c.created_college_institution_id is null THEN CAST(c.request_id AS TEXT) WHEN c.created_college_institution_id is not null THEN 'C-'||c.created_college_institution_id\r\n" +
                            "END as aishecode,'College' as category,ru.type as type,c.name as cname,'Active' as status from public.college_institution_request_details\r\n" +
                            "c left join ref_university_college_type ru on ru.id = c.college_type_id"
                            + " join request_approval_details apd on apd.request_id = c.request_id\r\n" +
                            "where c.survey_year in (select max(survey_year)from public.survey_master) and c.district_code ='" + districtCode + "'"
                            + " and c.created_college_institution_id is null and apd.status_id!=2").getResultList();
            completeResultList.addAll(cirdData);
            //public.standalone_institution_request_details
            List<AisheInstituteRequestCheckNameVO> sirdData = (List<AisheInstituteRequestCheckNameVO>)
                    session.createNativeQuery("select CASE WHEN c.created_standalone_institution_id is null THEN CAST(c.request_id AS TEXT) WHEN\r\n" +
                            "c.created_standalone_institution_id is not null THEN 'S-'||c.created_standalone_institution_id\r\n" +
                            "END as aishecode,'Standalone' as category,ru.type as type,c.name as cname,'Active' as status from public.standalone_institution_request_details\r\n" +
                            "c left join ref_state_body ru on ru.id = c.state_body_id "
                            + " join request_approval_details apd on apd.request_id = c.request_id "
                            + "where c.survey_year in (select max(survey_year)\r\n" +
                            "from public.survey_master) and c.district_code ='" + districtCode + "'"
                            + " and c.created_standalone_institution_id is null and apd.status_id!=2").getResultList();
            completeResultList.addAll(sirdData);
          //public.standalone_institution_request_details not in request approval details
			List<AisheInstituteRequestCheckNameVO> sirdDatanotinrad = (List<AisheInstituteRequestCheckNameVO>)
			session.createNativeQuery("select CASE WHEN c.created_standalone_institution_id is null THEN CAST(c.request_id AS TEXT) WHEN\r\n"
					+ "			c.created_standalone_institution_id is not null THEN 'S-'||c.created_standalone_institution_id\r\n"
					+ "			END as aishecode,'Standalone' as category,ru.type as type,c.name as cname,'Active' as status from public.standalone_institution_request_details\r\n"
					+ "			c left join ref_state_body ru on ru.id = c.state_body_id \r\n"
					+ "			where c.survey_year in (select survey_year\r\n"
					+ "			from public.survey_master where end_date>now()) and c.district_code ='"+districtCode+"' and c.created_standalone_institution_id is null"
							+ " and c.request_id not in (select request_id from request_approval_details) ").getResultList();
	completeResultList.addAll(sirdDatanotinrad);
	//public.college_institution_request_details  not in request approval details
	List<AisheInstituteRequestCheckNameVO> cirdDatanotrad = (List<AisheInstituteRequestCheckNameVO>)
			session.createNativeQuery("select CASE WHEN c.created_college_institution_id is null THEN CAST(c.request_id AS TEXT) WHEN c.created_college_institution_id is not null THEN 'C-'||c.created_college_institution_id\r\n" + 
					"END as aishecode,'College' as category,ru.type as type,c.name as cname,'Active' as status from public.college_institution_request_details\r\n" + 
					"c left join ref_university_college_type ru on ru.id = c.college_type_id " +
					"where c.survey_year in (select max(survey_year)from public.survey_master where end_date>now()) and c.district_code ='"+districtCode+"'"
							+ " and c.created_college_institution_id is null and c.request_id not in (select request_id from request_approval_details)").getResultList();
	completeResultList.addAll(cirdDatanotrad);
            //public.deleted_standalone_institution
            List<AisheInstituteRequestCheckNameVO> deleteSIData = (List<AisheInstituteRequestCheckNameVO>)
                    session.createNativeQuery("select distinct 'S-'||c.id as aishecode,'Standalone' as category,ru.type as type,c.name as cname,'DeAffiliated' as status from public.deleted_standalone_institution c left join ref_state_body ru on ru.id = \r\n" +
                            "c.statebodyid where c.id in (select distinct id from public.deleted_standalone_institution group by survey_year,id having survey_year>max(survey_year-1)) and c.district_code ='" + districtCode + "'").getResultList();
            completeResultList.addAll(deleteSIData);
            //public.college_deaffiliation
            List<AisheInstituteRequestCheckNameVO> collDeaffData = (List<AisheInstituteRequestCheckNameVO>)
                    session.createNativeQuery("select distinct 'C-'||c.id as aishecode,'College' as category,ru.name as uniname,c.name as collname,'DeAffiliated' as status from public.college_deaffiliation c left join ref_university ru on ru.id=c.university_id \r\n" +
                            "where c.district_code ='" + districtCode + "' and ru.survey_year in (select max(survey_year) from ref_university)\r\n" +
                            "and c.id in (select distinct id from college_deaffiliation group by survey_year,id having survey_year>max(survey_year-1))").getResultList();
            completeResultList.addAll(collDeaffData);
            //deleted college
            //List<AisheInstituteRequestCheckNameVO> collDeleteData = (List<AisheInstituteRequestCheckNameVO>)
            //		session.createNativeQuery("select 'C-'||cd.id as aishecode,'College' as category,ru.name as universityname,cd.name as insname,'InActive' as status from public.deleted_college cd\r\n" +
            //				"left join ref_university ru on ru.id= cd.university_id where cd.survey_year in (select max(survey_year) from survey_master) and cd.district_code ='"+districtCode+"'").getResultList();
            //completeResultList.addAll(collDeleteData);

            //UNAPPROVED REQUEST COLLEGE
            List<Integer> collUnapprovedRequest = (List<Integer>)
                    session.createNativeQuery("select c.request_id from public.college_institution_request_details "
                            + "c join request_approval_details apd on apd.request_id = c.request_id "
                            + "where c.survey_year in (select max(survey_year)from public.survey_master) and c.district_code ='" + districtCode + "' and c.created_college_institution_id is null "
                            + "and status_id = 2").getResultList();
            unApprovedList.addAll(collUnapprovedRequest);
            //UNAPPROVED REQUEST STANDALONE
            List<Integer> standUnapprovedRequest = (List<Integer>)
                    session.createNativeQuery("select c.request_id from public.standalone_institution_request_details "
                            + "c join request_approval_details apd on apd.request_id = c.request_id "
                            + "where c.survey_year in (select max(survey_year)from public.survey_master) and c.district_code ='" + districtCode + "' and c.created_standalone_institution_id is null "
                            + "and status_id = 2").getResultList();
            unApprovedList.addAll(standUnapprovedRequest);


            Object arrayObj = completeResultList;
            List<Object[]> dataen = (List<Object[]>) arrayObj;
            for (Object[] object : dataen) {
                AisheInstituteRequestCheckNameVO examinationResult = new AisheInstituteRequestCheckNameVO();
                if (object[0] != null) {
                    examinationResult.setRequestIdOrAisheCode(object[0].toString());
                }
                if (object[1] != null) {
                    examinationResult.setCategoryName(object[1].toString());
                }
                if (object[2] != null) {
                    examinationResult.setUniversityOrStateBodyName(object[2].toString());
                }
                if (object[3] != null) {
                    examinationResult.setInstituteName(object[3].toString());
                }
                if (object[4] != null) {
                    examinationResult.setStatusName(object[4].toString());
                    if (examinationResult.getStatusName().equals("Approved")) {
                        examinationResult.setStatusName("Active");
                    }
                }
                String teamName = examinationResult.getInstituteName().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
                String propositionName = instituteName;
                propositionName = propositionName.replaceAll("[^a-zA-Z0-9]", "");
                // System.out.println(teamName  +"-------------------"+ propositionName);
                //@SuppressWarnings("deprecation")
                //double distance = StringUtils.getJaroWinklerDistance(teamName, propositionName)*100;
                // int dist = (int) distance;
                //if(dist==100) {

                if (teamName.equalsIgnoreCase(propositionName)) {
                    completeResultList2.add(examinationResult);
                } else {
                    completeResultList2.remove(examinationResult);
                }
            }
            removeAll(unApprovedList, completeResultList2);
            removeAll(unApprovedList, completeResultList2);
            return completeResultList2;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
        return completeResultList2;
    }

    private void removeAll(List<Integer> unApprovedList, List<AisheInstituteRequestCheckNameVO> completeResultList2) {
        for (int i = 0; i < unApprovedList.size(); i++) {
            //if(completeResultList2.size()>i) {
            for (int j = 0; j < completeResultList2.size(); j++) {
                if (Objects.equals(Integer.valueOf(completeResultList2.get(j).getRequestIdOrAisheCode()), unApprovedList.get(i).intValue())) {
                    completeResultList2.remove(j);
                }
            }
        }
    }

    @Override
    public Integer findByEmailIdForCount(String email) {
        LocalDateTime currTime = DateUtils.obtainCurrentTimeStamp().minusMinutes(1);
        Session session1 = sessionFactory.openSession();
        try {
            BigInteger surveyYearCsy = ((BigInteger) session1.createNativeQuery("select count(*) from otp_details where"
                    + " email_id='" + email + "' and email_otp_datetime >= cast('" + currTime + "' as timestamp with time zone) and is_emailid_verified is false ").uniqueResult());
            Integer data = Integer.valueOf(surveyYearCsy.toString());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return 0;
    }

    @Override
    public Integer findByMobileNumberForCount(String mobileNumber) {
        LocalDateTime currTime = DateUtils.obtainCurrentTimeStamp().minusMinutes(1);
        Session session1 = sessionFactory.openSession();
        try {
            BigInteger surveyYearCsy = ((BigInteger) session1.createNativeQuery("select count(*) from otp_details where"
                    + " mobile_no='" + mobileNumber + "' and mobile_otp_datetime>=cast('" + currTime + "' as timestamp with time zone)and is_mobileno_verified is false ").uniqueResult());
            Integer data = Integer.valueOf(surveyYearCsy.toString());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return 0;
    }

    @Override
    public List<Integer> surveyYearForAddInstituteRequest() {
        LocalDateTime currTime = DateUtils.obtainCurrentTimeStamp();
        Session session1 = sessionFactory.openSession();
        try {
            List<Integer> surveyYear = ((List<Integer>) session1.createNativeQuery("select survey_year from public.activity_master where"
                    + " start_date<=cast('" + currTime + "' as timestamp with time zone)  "
                    + " and( end_date is null or  end_date>cast('" + currTime + "' as timestamp with" +
                    " time zone)) ").getResultList());
            return surveyYear;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return new ArrayList<Integer>();
    }

    @Override
    public Integer minSurveyYearForAddInstituteRequestPrior() {
        LocalDateTime currTime = DateUtils.obtainCurrentTimeStamp();
        Session session1 = sessionFactory.openSession();
        try {
            Integer surveyYear = ((Integer) session1.createNativeQuery("select min(survey_year) from public.activity_master where"
                    + " start_date<=cast('" + currTime + "' as timestamp with time zone) "
                    + " and( end_date is null or end_date>cast('" + currTime + "' as timestamp with " +
                    "time zone) )").getResultList().get(0));
            return surveyYear;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return 0;
    }

    @Override
    public List<RefActivityActionEO> activityAction() {
        Session session = sessionFactory.openSession();
        try {

            String hql = "FROM RefActivityActionEO ";

            List result = session.createQuery(hql).list();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RefActivityEO> activity() {
        Session session = sessionFactory.openSession();
        try {

            String hql = "FROM RefActivityEO ";

            List result = session.createQuery(hql).list();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean saveOrUpdateActivity(ActivityMasterEO activityMaster) {
        Session session = sessionFactory.openSession();
        Session session2 = null;
        Transaction tx = null;
        ActivityMasterLogsEO activityMasterLogs = new ActivityMasterLogsEO();
        if (activityMaster.getStartDateString() != null) {
            activityMaster.setStartDate(DateUtils.convertStringDateTimeToDBDateTimeNew(activityMaster.getStartDateString()));
        }
        if (activityMaster.getEndDateString() != null) {
            activityMaster.setEndDate(DateUtils.convertStringDateTimeToDBDateTimeNew(activityMaster.getEndDateString()));
        }
        ActivityMasterEO foreignInstitute = null;
        try {
            foreignInstitute = fetchActivityDetail(activityMaster.getId());
            session2 = sessionFactory.openSession();
            tx = session2.beginTransaction();
            if (foreignInstitute == null) {
                /*Integer currentStatusId = 1;
                if (session.createQuery("select max(id) from ActivityMasterEO").uniqueResult() != null) {
                    currentStatusId = ((Integer) session.createQuery("select max(id) from ActivityMasterEO").uniqueResult() + 1);
                }
                activityMaster.setId(currentStatusId);*/
                activityMaster.setId(null);
                session2.save(activityMaster);
            } else if (foreignInstitute != null) {
                session2.update(activityMaster);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            session.close();
            session2.close();
        }
        return null;
    }

    public ActivityMasterEO fetchActivityDetail(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {

            String hql = "FROM ActivityMasterEO as userActionLogEO where userActionLogEO.id = :id";

            List list = session.createQuery(hql).setParameter("id", id).list();
            if (list.size() > 0) {
                return (ActivityMasterEO) list.get(0);
            }

        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<Integer> surveyYearForAisheOpen() {
        LocalDateTime currTime = DateUtils.obtainCurrentTimeStamp();
        Session session1 = sessionFactory.openSession();
        try {
            List<Integer> surveyYear = ((List<Integer>) session1.createNativeQuery("select survey_year from public.survey_master where"
                    + " start_date<=cast('" + currTime + "' as timestamp with time zone) and end_date>cast('" + currTime + "' as timestamp with time zone) "
                    + " or end_date is null ").getResultList());
            return surveyYear;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return new ArrayList<Integer>();
    }

    @Override
    public Integer userStatusByCount(Integer statusId, String stateCode) {
        Session session1 = sessionFactory.openSession();
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select count(*) from public.user_master where status_id =" + statusId + " ");
            if (stateCode != null) {
                sb.append(" and state_code ='" + stateCode + "'");
            }
            BigInteger surveyYearCsy = ((BigInteger) session1.createNativeQuery(sb.toString())
                    .uniqueResult());
            Integer data = Integer.valueOf(surveyYearCsy.toString());
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return 0;
    }

    @Override
    public String saveDocumentForUser(UserRegistrationDetailEO requestForAdd) {
        Session session = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            UserRegistrationDetailEO data = (UserRegistrationDetailEO) session2.get(UserRegistrationDetailEO.class, requestForAdd.getUserId());
            data.setDocument(requestForAdd.getDocument());
            data.setDocumentName(requestForAdd.getDocumentName());
            session.update(data);
            tx.commit();
            return "success";
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            try {
                session.close();
                session2.close();
            } catch (Exception e) {
            }
        }
        return "error";
    }

    @Override
    public List<ApprovingAuthorityDTO> userApprovingAuthority(Integer roleId, String stateCode, List<Integer> authorityId, String universityId) {
        Session session = sessionFactory.openSession();
        try {
            if (authorityId.contains(1)) {
                authorityId.removeIf(t -> t.intValue() == 1);
            }
            if (universityId != null) {
                if (universityId.equals("null")) {
                    universityId = null;
                }
            }
            StringBuilder queryBuilder = new StringBuilder();
            if (universityId != null) {
                queryBuilder.append("select first_name,middle_name,last_name,rum.role_name,phone_mobile,email_id,phone_landline,std_code from public.user_master um "
                        + "left join ref_user_role_master rum on rum.role_id = um.role_id "
                        + "where is_approved=1 AND ((um.role_id =('6')  \r\n");
                for (int i = 1; i < authorityId.size(); i++) {
                    queryBuilder.append(" or um.role_id =(" + authorityId.get(i) + ")");
                }
                if (!universityId.equals("null")) {
                    queryBuilder.append("AND aishe_code ='" + universityId + "'");
                }
                queryBuilder.append(")");
                queryBuilder.append(")");
                queryBuilder.append(" AND state_code ='" + stateCode + "'");
            } else {
                queryBuilder.append(
                        "select first_name,middle_name,last_name,rum.role_name,phone_mobile,email_id,phone_landline,std_code from public.user_master um \r\n"
                                + "left join ref_user_role_master rum on rum.role_id = um.role_id \r\n"
                                + "where is_approved=1");
                if (authorityId != null) {
                    queryBuilder.append(" and (um.role_id =(" + authorityId.get(0) + ")");
                    for (int i = 1; i < authorityId.size(); i++) {
                        queryBuilder.append(" or um.role_id =(" + authorityId.get(i) + ")");
                    }
                }
                queryBuilder.append(" ) ");
                queryBuilder.append(" and state_code='" + stateCode + "'");
            }
            if (authorityId.contains(2)) {
                queryBuilder.append(" or ((um.role_id =2 and aishe_code is null))");
            }
            if (authorityId.contains(3)) {
                queryBuilder.append(" or ((um.role_id =3 and aishe_code is null))");
            }
            if (authorityId.contains(4)) {
                queryBuilder.append(" or ((um.role_id =4 and aishe_code is null))");
            }
            if (authorityId.contains(5)) {
                queryBuilder.append(" or ((um.role_id =5 and aishe_code is null))");
            }

            Query query = session.createNativeQuery(queryBuilder.toString());

            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
            List<ApprovingAuthorityDTO> postMenuList = new ArrayList<>();
            for (Object[] object : objectlist) {
                ApprovingAuthorityDTO postMenuData = new ApprovingAuthorityDTO();
                if (object[0] != null) {
                    postMenuData.setFirstName(object[0] + "");
                }
                if (object[1] != null) {
                    postMenuData.setMiddleName(object[1] + "");
                }
                if (object[2] != null) {
                    postMenuData.setLastName(object[2] + "");
                }
                if (object[3] != null) {
                    postMenuData.setRoleName(object[3] + "");
                }
                if (object[4] != null) {
                    postMenuData.setMobile(object[4] + "");
                }
                if (object[5] != null) {
                    postMenuData.setEmailId(object[5] + "");
                }
                if (object[6] != null) {
                    postMenuData.setLandline(object[6] + "");
                }
                if (object[7] != null) {
                    postMenuData.setStdCode(object[7] + "");
                }
                postMenuList.add(postMenuData);
            }
            return postMenuList;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean snoAlreadyExist(String stateCode, Integer roleId) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterEO> query = builder.createQuery(UserMasterEO.class);
            Root<UserMasterEO> allData = query.from(UserMasterEO.class);
            query.where(builder.and(builder.equal(allData.get("roleId"), roleId)),
                    builder.and(builder.equal(allData.get("stateCode"), stateCode)),
                    builder.and(builder.equal(allData.get("isApproved"), 1)));
            UserMasterEO user = session.createQuery(query).getSingleResult();
            if (user != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public UserMasterNewEO fetchUserMasterByUserId() {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterNewEO> query = builder.createQuery(UserMasterNewEO.class);
            Root<UserMasterNewEO> allData = query.from(UserMasterNewEO.class);
            query.where(builder.and(builder.equal(allData.get("isMoeDisplayUser"), true)));
            UserMasterNewEO university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public RefUserRoleMaster fetchRoleDetails(Integer roleId) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefUserRoleMaster> query = builder.createQuery(RefUserRoleMaster.class);
            Root<RefUserRoleMaster> allData = query.from(RefUserRoleMaster.class);
            query.where(builder.and(builder.equal(allData.get("roleId"), roleId)));
            RefUserRoleMaster university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<ApprovingAuthorityDTO> userApprovingAuthorities(Integer roleId, String stateCode, List<Integer> authorityId, String universityId) {
        Session session = sessionFactory.openSession();
        try {
            if (authorityId.contains(1))
                authorityId.removeIf(t -> t.intValue() == 1);
            stateCode = handler.nullValueHandler(stateCode);
            universityId = handler.nullValueHandler(universityId);

            StringBuilder queryBuilder = new StringBuilder();
            if (universityId != null) {
                queryBuilder.append("select name,null as middle_name,null as last_name,rum.role_name,phone_mobile,email_id,phone_landline,std_code from public.user_master um "
                        + "left join ref_user_role_master rum on rum.role_id = um.role_id "
                        + "where is_approved=1 and status_id=2 AND ((um.role_id =('6')  \r\n");
                for (int i = 1; i < authorityId.size(); i++) {
                    queryBuilder.append(" or um.role_id =(" + authorityId.get(i) + ")");
                }
                queryBuilder.append(")");
                if (null != universityId) {
                    queryBuilder.append("  and aishe_code ='" + universityId + "'");
                }
                queryBuilder.append(")");
               /* if (null != stateCode)
                queryBuilder.append(" AND state_code ='" + stateCode + "')");*/
            } else {
                queryBuilder.append(
                        "select name,null as middle_name,null as last_name,rum.role_name,phone_mobile,email_id,phone_landline,std_code from public.user_master um \r\n"
                                + "left join ref_user_role_master rum on rum.role_id = um.role_id \r\n"
                                + "where is_approved=1 and status_id=2 ");
                if (authorityId != null) {
                    queryBuilder.append(" and (um.role_id =(" + authorityId.get(0) + ")");
                    for (int i = 1; i < authorityId.size(); i++) {
                        queryBuilder.append(" or um.role_id =(" + authorityId.get(i) + ")");
                    }
                }
                queryBuilder.append(" ) ");
                if (null != stateCode)
                    queryBuilder.append(" and state_code='" + stateCode + "'");
            }
            if (authorityId.contains(2)) {
                queryBuilder.append(" or ((um.role_id =2 and aishe_code is null and is_approved=1 and status_id=2))");
            }
            if (authorityId.contains(3)) {
                queryBuilder.append(" or ((um.role_id =3 and aishe_code is null and is_approved=1 and status_id=2))");
            }
            if (authorityId.contains(4)) {
                queryBuilder.append(" or ((um.role_id =4 and aishe_code is null and is_approved=1 and status_id=2))");
            }
            if (authorityId.contains(5)) {
                queryBuilder.append(" or ((um.role_id =5 and aishe_code is null and is_approved=1 and status_id=2))");
            }
            if (authorityId.contains(6)) {
                queryBuilder.append(" or ((um.role_id =6 and aishe_code is null and is_approved=1 and status_id=2  ) ");
                if (null != stateCode)
                    queryBuilder.append(" and state_code='" + stateCode + "')");
            }
            if (authorityId.contains(18)) {
                queryBuilder.append(" or ((um.role_id =18 and aishe_code is null and is_approved=1 and status_id=2  ) )");
            }
            if (authorityId.contains(11)) {
                queryBuilder.append(" or ((um.role_id =11 and aishe_code is null and is_approved=1 and status_id=2  ) ");
                if (null != stateCode)
                    queryBuilder.append(" and state_code='" + stateCode + "')");
            }
            if (authorityId.contains(10)) {
                queryBuilder.append(" or ((um.role_id =10 and aishe_code is null and is_approved=1 and status_id=2  ) ");
                if (null != stateCode)
                    queryBuilder.append(" and state_code='" + stateCode + "')");
            }
            if (authorityId.contains(22)) {
                queryBuilder.append(" or ((um.role_id =22 and aishe_code is null and is_approved=1 and status_id=2  )) ");
            }
            Query query = session.createNativeQuery(queryBuilder.toString());

            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
            List<ApprovingAuthorityDTO> postMenuList = new ArrayList<>();
            for (Object[] object : objectlist) {
                ApprovingAuthorityDTO postMenuData = new ApprovingAuthorityDTO();
                if (object[0] != null) {
                    postMenuData.setFirstName(object[0] + "");
                }
                if (object[1] != null) {
                    postMenuData.setMiddleName(object[1] + "");
                }
                if (object[2] != null) {
                    postMenuData.setLastName(object[2] + "");
                }
                if (object[3] != null) {
                    postMenuData.setRoleName(object[3] + "");
                }
                if (object[4] != null) {
                    postMenuData.setMobile(object[4] + "");
                }
                if (object[5] != null) {
                    postMenuData.setEmailId(object[5] + "");
                }
                if (object[6] != null) {
                    postMenuData.setLandline(object[6] + "");
                }
                if (object[7] != null) {
                    postMenuData.setStdCode(object[7] + "");
                }
                postMenuList.add(postMenuData);
            }
            return postMenuList;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public NtaQuestionnaire getNtaQuestionNaire(String aisheCode) {
        Session session = sessionFactory.openSession();
        NtaQuestionnaire datais = new NtaQuestionnaire();
        try {
            CollegeFormEO data = new CollegeFormEO();
            UniversityFormEO dataU = new UniversityFormEO();
            String[] splitted = aisheCode.trim().split("\\s*-\\s*");
            String instituteType = splitted[0];
            if (instituteType.equals("C")) {
                data = getStatusMaster(aisheCode);
                if (data == null) {
                    datais.setMessage("Aishe Code Not Participated in Survey Year 2021");
                    //return datais;
                }
            }
            if (instituteType.equals("U")) {
                dataU = getStatus(aisheCode);
                if (dataU == null) {
                    datais.setMessage("Aishe Code Not Participated in Survey Year 2021");
                    //return datais;
                }
            }
            //not participate in survey will not fill form
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<NtaQuestionnaire> query = builder.createQuery(NtaQuestionnaire.class);
            Root<NtaQuestionnaire> allData = query.from(NtaQuestionnaire.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (aisheCode != null) {
                predicates.add(builder.equal(allData.get("aisheCode"), aisheCode));
            }
            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
                    .orderBy(builder.asc(allData.get("id")));
            List<NtaQuestionnaire> university = session.createQuery(query).getResultList();
            if (!university.isEmpty()) {
                datais = university.get(0);
                if (instituteType.equals("C")) {
                    data = getStatusMaster(aisheCode);
                    if (data == null) {
                        datais.setParticipateInSurvey(false);
                        datais.setMessage("Aishe Code Not Participated in Survey Year 2021");
                        //return datais;
                    }
                }
                if (instituteType.equals("U")) {
                    dataU = getStatus(aisheCode);
                    if (dataU == null) {
                        datais.setParticipateInSurvey(false);
                        datais.setMessage("Aishe Code Not Participated in Survey Year 2021");
                        //return datais;
                    }
                }
                if (instituteType.equals("U") && (datais.getLatitude() == null || datais.getLongitude() == null)) {
                    datais.setLatitude(dataU.getLatitude());
                    datais.setLongitude(dataU.getLongitude());
                    datais.setParticipateInSurvey(true);
                }
                if (instituteType.equals("C") && (datais.getLatitude() == null || datais.getLongitude() == null)) {
                    datais.setLatitude(data.getLatitude());
                    datais.setLongitude(data.getLongitude());
                    datais.setParticipateInSurvey(true);
                }
                datais.setMessage("Success");
                return datais;
            } else if (dataU.getUniversityPk() != null || data != null) {
                datais.setMessage("Elligible");
                datais.setParticipateInSurvey(true);
                return datais;
            } else {
                datais.setMessage("IN Elligible");
                datais.setParticipateInSurvey(false);
                return datais;
            }
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public NtaQuestionnaire saveOrUpdateNtaQuestionNaire(NtaQuestionnaire userMaster, UserInfo userInfo, HttpServletRequest request) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            UniversityFormEO dataU = new UniversityFormEO();
            CollegeFormEO dataC = new CollegeFormEO();

            if (userMaster.isParticipateInSurvey()) {
                if (userMaster.getInstituteType().equals("U")) {
                    dataU = getStatus(userMaster.getAisheCode());
                    dataU.setLatitude(userMaster.getLatitude());
                    dataU.setLongitude(userMaster.getLongitude());
                    userMaster.setLatitude(null);
                    userMaster.setLongitude(null);
                }
                if (userMaster.getInstituteType().equals("C")) {
                    dataC = getStatusMaster(userMaster.getAisheCode());
                    dataC.setLatitude(userMaster.getLatitude());
                    dataC.setLongitude(userMaster.getLongitude());
                    userMaster.setLatitude(null);
                    userMaster.setLongitude(null);
                }
            }

            if (userMaster.getId() == 0) {
                //ADD NTA RECORD
                session.save(userMaster);

                UserActionLog useraction = new UserActionLog();
                useraction.setActionId(43);
                useraction.setActionTime(DateUtils.obtainCurrentTimeStamp());
                useraction.setId(null);
                String[] splitted = userMaster.getAisheCode().trim().split("\\s*-\\s*");
                String institutionId = splitted[1];
                useraction.setInstitutionId(institutionId);
                useraction.setInstitutionType(userMaster.getInstituteType());
                useraction.setIpAddress(IpAddressProvider.getClientIpAddr(request));
                useraction.setSurveyYear(null);
                useraction.setUserId(userInfo.getUsername());
                saveUserActionLog(useraction);

                if (userMaster.isParticipateInSurvey()) {
                    if (userMaster.getInstituteType().equals("U")) {
                        session.update(dataU);
                    }
                    if (userMaster.getInstituteType().equals("C")) {
                        session.update(dataC);
                    }
                }
            } else {
                //UPDATE NTA RECORD
                session.update(userMaster);
                UserActionLog useraction = new UserActionLog();
                useraction.setActionId(44);
                useraction.setActionTime(DateUtils.obtainCurrentTimeStamp());
                useraction.setId(null);
                String[] splitted = userMaster.getAisheCode().trim().split("\\s*-\\s*");
                String institutionId = splitted[1];
                useraction.setInstitutionId(institutionId);
                useraction.setInstitutionType(userMaster.getInstituteType());
                useraction.setIpAddress(IpAddressProvider.getClientIpAddr(request));
                useraction.setSurveyYear(null);
                useraction.setUserId(userInfo.getUsername());
                saveUserActionLog(useraction);
                if (userMaster.isParticipateInSurvey()) {
                    if (userMaster.getInstituteType().equals("U")) {
                        session.update(dataU);
                    }
                    if (userMaster.getInstituteType().equals("C")) {
                        session.update(dataC);
                    }
                }
            }
            tx.commit();
            return userMaster;
        } catch (Exception e) {
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
        return null;
    }

    public void saveUserActionLog(UserActionLog userActionLog) {
        {
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(userActionLog);
                tx.commit();
                session.close();
            } catch (Exception e) {
                try {
                    if (tx != null && tx.isActive()) {
                        tx.rollback();
                    }
                } catch (Exception trEx) {
                }
            } finally {
                session.close();
            }
        }
    }

    private UniversityFormEO getStatus(String aishecode) {
        Session session = sessionFactory.openSession();
        try {
            String[] splitted = aishecode.trim().split("\\s*-\\s*");
            String instituteId = splitted[1];
            StringBuilder sb = new StringBuilder();
            sb.append(
                    "from UniversityFormEO as u2  where u2.universityPk.id=:aishecode"
                            + " and u2.universityPk.surveyYear =2021");
            Query query = session.createQuery(sb.toString());
            query.setParameter("aishecode", instituteId);
            UniversityFormEO universitys = (UniversityFormEO) query.getSingleResult();
            return universitys;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();

        }
        return null;
    }

    private CollegeFormEO getStatusMaster(String aishecode) {
        Session session = sessionFactory.openSession();
        StringBuilder sb = new StringBuilder();
        try {
            String[] splitted = aishecode.trim().split("\\s*-\\s*");
            String instituteId = splitted[1];
            sb.append("from  CollegeFormEO as ci  where  ci.universityPk.id=:aishecode and"
                    + " ci.universityPk.surveyYear =2021");
            Query query = session.createQuery(sb.toString());
            query.setParameter("aishecode", Integer.parseInt(instituteId));
            CollegeFormEO college = (CollegeFormEO) query.getSingleResult();
            return college;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();

        }
        return null;
    }

    @Override
    public List<NtaQuestionEO> getNtaQuestion() {
        Session session = sessionFactory.openSession();
        StringBuilder sb = new StringBuilder("from  NtaQuestionEO  order by id");
        try {
            Query query = session.createQuery(sb.toString(), NtaQuestionEO.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<NtaQuestionnaireEO> getNtaQuestionDetails(InstitutionType institutionType, String stateId, String queryString) {
        Session session = sessionFactory.openSession();
        try {
            Query query = session.createNativeQuery(queryString, NtaQuestionnaireEO.class);
            query.setParameter("institutionType", institutionType.getType());
            query.setParameter("surveyYear", 2021);
            query.setParameter("stateId", stateId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public String saveRequestForAddInstituteNew(UserMasterRequestDetailEO requestForAdd) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            UserMasterRequestDetailEO data = fetchUserMasterRequestDetail(requestForAdd.getUserId());
            data.setDocument(requestForAdd.getDocument());
            data.setDocumentName(requestForAdd.getDocumentName());
            session.update(data);
            tx.commit();
            return "success";
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
            }
        } finally {
            try {
                session.close();
            } catch (Exception e) {
            }
        }
        return "error";
    }

    @Override
    public Boolean saveOrUpdateActivityMaster(ActivityMasterEO activityMaster, ActivityMasterLogsEO logsEO) {
        log.info("AisheUserRequestDaoImpl controller : saveOrUpdateActivityMaster method invoked : {}");
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(activityMaster);
            logsEO.setActivityMasterId(activityMaster.getActivityId());
            session.saveOrUpdate(logsEO);
            tx.commit();
            return true;
        } catch (Exception e) {
            log.error("error occurred in saveOrUpdateActivityMaster due to this {} exception ", e.getMessage());
            if (null != tx && tx.isActive()) {
                tx.rollback();
            }
        }
        return false;
    }

    @Override
    public List<ActivityMasterEO> getActivityMaster(Integer surveyYear) {
        log.info("AisheUserRequestDaoImpl controller : saveOrUpdateActivityMaster method invoked with parameter: {}",
                surveyYear);
        if (null != surveyYear) {
            try (Session session = sessionFactory.openSession()) {
                StringBuilder builder = new StringBuilder("from ActivityMasterEO where 1=1 ");
                builder.append(" and surveyYear=:surveyYear ");
                builder.append(" order by surveyYear desc ");
                Query query = session.createQuery(builder.toString());
                query.setParameter("surveyYear", surveyYear);
                return query.getResultList();
            } catch (Exception e) {
                log.error("error occurred in getActivityMaster due to this {} exception ", e.getMessage());
            }
        } else {
            return getAllActivitySurveyYear();
        }
        return Collections.EMPTY_LIST;
    }

    private List<ActivityMasterEO> getAllActivitySurveyYear() {
        log.info("AisheUserRequestDaoImpl controller : getAllActivitySurveyYear method invoked with parameter: {}");
        try (Session session = sessionFactory.openSession()) {
            LocalDateTime dateTime = DateUtils.obtainCurrentTimeStamp();
            StringBuilder newActivityList = new StringBuilder("select * from (select activity_id, start_date, end_date, survey_year,\n" +
                    " created_by, created_on, modified_by, modified_on,true as is_active, id, remarks \n" +
                    " from activity_master where 1=1 and start_Date > :dateTime   union " +

                    " select am.activity_id, am.start_date, am.end_date, am.survey_year,\n" +
                    " am.created_by, am.created_on, am.modified_by, am.modified_on,null as is_active, id, am.remarks" +
                    " from \n" +
                    " activity_master am left join survey_master sm on sm.survey_year=am.survey_year\n" +
                    "  where 1=1 and sm.end_date < :dateTime union     \n" +

                    " select activity_id, start_date, end_date, survey_year,\n" +
                    " created_by, created_on, modified_by, modified_on,false as is_active, id, remarks from \n" +
                    " activity_master where 1=1 and survey_year not in (select survey_year\n" +
                    " from activity_master where 1=1 and start_Date > :dateTime   union\n" +
                    "  select am.survey_year from activity_master am \n" +
                    " left join survey_master sm on sm.survey_year=am.survey_year\n" +
                    " where 1=1 and sm.end_date < :dateTime )) as tab order by survey_year desc ");

            Query query =
                    session.createNativeQuery(newActivityList.toString(), ActivityMasterEO.class).setParameter(
                            "dateTime", dateTime);
            return query.getResultList();
        } catch (Exception e) {
            log.error("error occurred in getActivityMaster due to this {} exception ", e.getMessage());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Integer getMaxActivityMaster() {
        log.info("AisheUserRequestDaoImpl controller : getMaxActivityMaster method invoked with parameter: ");
        try (Session session = sessionFactory.openSession()) {
            StringBuilder builder = new StringBuilder("select max(survey_year) from public.activity_master ");
            Query query = session.createNativeQuery(builder.toString());
            return (Integer) query.getSingleResult();
        } catch (Exception e) {
            log.error("error occurred in getMaxActivityMaster due to this {} exception ", e.getMessage());
        }
        return null;
    }

    @Override
    public ActivityMasterEO getActivityMasterById(Integer id) {
        log.info("AisheUserRequestDaoImpl controller : getActivityMasterById method invoked with parameter: ");
        try (Session session = sessionFactory.openSession()) {
            StringBuilder builder = new StringBuilder("from ActivityMasterEO where id=:id ");
            Query query = session.createQuery(builder.toString()).setParameter("id",id);
            return (ActivityMasterEO) query.getSingleResult();
        } catch (Exception e) {
            log.error("error occurred in getActivityMasterById due to this {} exception ", e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean deleteActivityMaster(ActivityMasterEO masterEO) {
        log.info("AisheUserRequestDaoImpl controller : getActivityMasterById method invoked {}");
        Transaction tx=null;
        try(Session session= sessionFactory.openSession()){
            tx=session.beginTransaction();
            session.delete(masterEO);
            tx.commit();
            return true;
        }catch (Exception e){
            log.error("error occurred in deleteActivityMaster due to this {} exception ", e.getMessage());
            if(null !=tx && tx.isActive()){
                tx.rollback();
            }
        }
        return false;
    }

    public UserMasterRequestDetailEO fetchUserMasterRequestDetail(String userId) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterRequestDetailEO> criteriaQuery = builder.createQuery(UserMasterRequestDetailEO.class);
            Root<UserMasterRequestDetailEO> root = criteriaQuery.from(UserMasterRequestDetailEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (userId != null) {
                predicates.add(builder.equal(root.get("userId"), userId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterRequestDetailEO> q = session.createQuery(criteriaQuery);
            List<UserMasterRequestDetailEO> list = q.getResultList();
            if (list.size() > 0) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;

    }

	@Override
	public Boolean userIdAlreadyExistInLogs(String userId) {
		  Session session = sessionFactory.openSession();
	        try {
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<UserMasterLogDetailEO> criteriaQuery = builder.createQuery(UserMasterLogDetailEO.class);
	            Root<UserMasterLogDetailEO> allData = criteriaQuery.from(UserMasterLogDetailEO.class);
	            criteriaQuery.where(builder.and(builder.equal(allData.get("userId"), userId)));
	            UserMasterLogDetailEO user = session.createQuery(criteriaQuery).getSingleResult();
	            if (user != null) {
	                return true;
	            } else {
	                return false;
	            }
	        } catch (Exception e) {
	        } finally {
	            session.close();
	        }
	        return false;
	    }

}