package aishe.gov.in.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.enums.SortBy;
import aishe.gov.in.masterseo.ActiveInstituteInactiveUser;
import aishe.gov.in.masterseo.ApproveDisapproveUserDTO;
import aishe.gov.in.masterseo.ApproveUserDTO;
import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.masterseo.UserActionLog;
import aishe.gov.in.masterseo.UserMasterDetailEO;
import aishe.gov.in.masterseo.UserMasterDetailNewEO;
import aishe.gov.in.masterseo.UserMasterDetailStateEO;
import aishe.gov.in.masterseo.UserMasterEO;
import aishe.gov.in.masterseo.UserMasterHistoryEO;
import aishe.gov.in.masterseo.UserMasterLogDetailEO;
import aishe.gov.in.masterseo.UserMasterNewEO;
import aishe.gov.in.masterseo.UserMasterRequestDetailEO;
import aishe.gov.in.masterseo.UserRegistrationDetailEO;
import aishe.gov.in.masterseo.UserRegistrationUpdatedDetailEO;
import aishe.gov.in.masterseo.WebDcfPersonDetailsEO;
import aishe.gov.in.mastersvo.PersonDetailsEmadedPK;
import aishe.gov.in.mastersvo.RegisteredUserDTO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.EncryptionDecryptionUtil;
import aishe.gov.in.utility.IpAddressProvider;

@Repository
public class RegisterationDaoImpl implements RegisterationDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    BCryptPasswordEncoder bcrypt;

    /*
     * @Autowired UserRegistrationServiceImpl userRegistrationServiceImpl;
     */
    private static final Logger logger = LoggerFactory.getLogger(RegisterationDaoImpl.class);

    @Override
    public UserMasterNewEO saveUpdateRegistration(UserMasterNewEO userMasterEO, UserInfo userInfo) {
        logger.info("RegisterationDaoImpl : saveUpdateRegistration method invoked :");
        Session session = sessionFactory.openSession();
        Session session1 = sessionFactory.openSession();
        Transaction tx = null;
        UserMasterNewEO checkForEmail = fetchUserMasterNewDetailEO(userMasterEO.getUserId());
        if (userMasterEO.getNodalOfficerName() != null) {
//            if (userMasterEO.getEmailVerified().equals(false)) {
//                if (!userMasterEO.getEmail().equals(checkForEmail.getEmail())) {
//                    return null;
//                }
//            }
            if (userMasterEO.getMobileVerified().equals(false)) {
                if (!userMasterEO.getMobile().toString().equals(checkForEmail.getMobile().toString())) {
                    return null;
                }
            }
        }
        try {

            tx = session.beginTransaction();
            if (userMasterEO.getNodalOfficerName() != null) {
                checkForEmail.setTitleId(userMasterEO.getNodalOfficerTitleId());
                checkForEmail.setName(userMasterEO.getNodalOfficerName());
                checkForEmail.setNodalOfficerDesignation(userMasterEO.getNodalOfficerDesignation());
                checkForEmail.setPhoneLandline(userMasterEO.getPhoneLandline());
                checkForEmail.setEmail(userMasterEO.getEmail());
                checkForEmail.setMobile(userMasterEO.getMobile());
                session.saveOrUpdate(checkForEmail);
                UserMasterHistoryEO historyEO = UserMasterHistoryEO.bind(userMasterEO, checkForEmail, userInfo);
                session.saveOrUpdate(historyEO);


                if (userMasterEO.getAisheCode() != null) {
                    String[] split = userMasterEO.getAisheCode().trim().split("\\s*-\\s*");
                    String instituteType = split[0].toUpperCase();
                    String instituteId = split[1].toUpperCase();
                    Integer openSurveyYear = null;
                    Integer formUpload = null;
                    if (instituteType.equals("C")) {
                        openSurveyYear = (Integer) session.createNativeQuery("select survey_year from survey_master where end_date>now() and survey_year "
                                + "in (select survey_year from college_institution where id ='" + instituteId + "')").uniqueResult();
                        if (openSurveyYear != null) {
                            formUpload = (Integer) session.createNativeQuery("select id from form_upload where college_institution_id ='" + instituteId + "' and survey_year =" + openSurveyYear + "").uniqueResult();
                        }
                        if (formUpload == null) {
                            List<WebDcfPersonDetailsEO> infraList = getPersonDetailsWebdcf(openSurveyYear, instituteId, instituteType);
                            for (WebDcfPersonDetailsEO person : infraList) {
                                WebDcfPersonDetailsEO nodal = new WebDcfPersonDetailsEO();
                                BeanUtils.copyProperties(person, nodal);
                                if (nodal.getUniversityPk().getOfficerType() != null && nodal.getUniversityPk().getOfficerType().equals("IH")) {
                                    nodal.setOfficerEmail(userMasterEO.getInstituteHeadEmail());
                                    nodal.setOfficerMobile(userMasterEO.getInstituteHeadMobile());
                                    nodal.setOfficerName(userMasterEO.getInstituteHeadName());
                                    nodal.setOfficerTelephone(userMasterEO.getInstituteHeadTelephone());
                                    session.saveOrUpdate(nodal);
                                }
                                if (nodal.getUniversityPk().getOfficerType() != null && nodal.getUniversityPk().getOfficerType().equals("NO")) {
                                    nodal.setOfficerEmail(userMasterEO.getEmail());
                                    nodal.setOfficerMobile(userMasterEO.getMobile());
                                    nodal.setOfficerName(userMasterEO.getNodalOfficerName());
                                    nodal.setOfficerTelephone(userMasterEO.getPhoneLandline());
                                    session.saveOrUpdate(nodal);
                                }
                            }
                        }
                    }
                    if (instituteType.equals("S")) {
                        openSurveyYear = (Integer) session.createNativeQuery("select survey_year from survey_master where end_date>now() and survey_year "
                                + "in (select survey_year from standalone_institution where id ='" + instituteId + "')").uniqueResult();
                        if (openSurveyYear != null) {
                            formUpload = (Integer) session.createNativeQuery("select id from form_upload where standalone_institution_id ='" + instituteId + "' and survey_year =" + openSurveyYear + "").uniqueResult();
                        }
                        if (formUpload == null) {
                            List<WebDcfPersonDetailsEO> infraList = getPersonDetailsWebdcf(openSurveyYear, instituteId, instituteType);
                            for (WebDcfPersonDetailsEO person : infraList) {
                                WebDcfPersonDetailsEO nodal = new WebDcfPersonDetailsEO();
                                BeanUtils.copyProperties(person, nodal);
                                if (nodal.getUniversityPk().getOfficerType() != null && nodal.getUniversityPk().getOfficerType().equals("IH")) {
                                    nodal.setOfficerEmail(userMasterEO.getInstituteHeadEmail());
                                    nodal.setOfficerMobile(userMasterEO.getInstituteHeadMobile());
                                    nodal.setOfficerName(userMasterEO.getInstituteHeadName());
                                    nodal.setOfficerTelephone(userMasterEO.getInstituteHeadTelephone());
                                    session.saveOrUpdate(nodal);
                                }
                                if (nodal.getUniversityPk().getOfficerType() != null && nodal.getUniversityPk().getOfficerType().equals("NO")) {
                                    nodal.setOfficerEmail(userMasterEO.getEmail());
                                    nodal.setOfficerMobile(userMasterEO.getMobile());
                                    nodal.setOfficerName(userMasterEO.getNodalOfficerName());
                                    nodal.setOfficerTelephone(userMasterEO.getPhoneLandline());
                                    session.saveOrUpdate(nodal);
                                }
                            }
                        }

                    }
                    if (instituteType.equals("U")) {
                        openSurveyYear = (Integer) session.createNativeQuery("select survey_year from survey_master where end_date>now() and survey_year "
                                + "in (select survey_year from university where id ='" + instituteId + "')").uniqueResult();
                        if (openSurveyYear != null) {
                            formUpload = (Integer) session.createNativeQuery("select id from form_upload where university_id ='" + instituteId + "' and survey_year =" + openSurveyYear + "").uniqueResult();
                        }
                        if (formUpload == null) {
                            List<WebDcfPersonDetailsEO> infraList = getPersonDetailsWebdcf(openSurveyYear, instituteId, instituteType);
                            for (WebDcfPersonDetailsEO person : infraList) {
                                WebDcfPersonDetailsEO nodal = new WebDcfPersonDetailsEO();
                                BeanUtils.copyProperties(person, nodal);
                                if (nodal.getUniversityPk().getOfficerType() != null && nodal.getUniversityPk().getOfficerType().equals("IH")) {
                                    nodal.setOfficerEmail(userMasterEO.getInstituteHeadEmail());
                                    nodal.setOfficerMobile(userMasterEO.getInstituteHeadMobile());
                                    nodal.setOfficerName(userMasterEO.getInstituteHeadName());
                                    nodal.setOfficerTelephone(userMasterEO.getInstituteHeadTelephone());
                                    session.saveOrUpdate(nodal);
                                }
                                if (nodal.getUniversityPk().getOfficerType() != null && nodal.getUniversityPk().getOfficerType().equals("NO")) {
                                    nodal.setOfficerEmail(userMasterEO.getEmail());
                                    nodal.setOfficerMobile(userMasterEO.getMobile());
                                    nodal.setOfficerName(userMasterEO.getNodalOfficerName());
                                    nodal.setOfficerTelephone(userMasterEO.getPhoneLandline());
                                    session.saveOrUpdate(nodal);
                                }
                            }
                        }
                    }
                }
                tx.commit();
            }
//            if(userMasterEO.getAisheCode()!=null) {
//            String[] splitted = userMasterEO.getAisheCode().trim().split("\\s*-\\s*");
//	        String instituteId = splitted[1];
//	        String instituteType = splitted[0];
//	        
//	        LocalDateTime currTime = DateUtils.obtainCurrentTimeStamp();
//	        List<Integer> surveyYear = ((List<Integer>) session1.createNativeQuery("select survey_year from public.survey_master where"
//					+ " start_date<=cast('"+currTime+"' as timestamp with time zone) and end_date>cast('"+currTime+"' as timestamp with time zone) "
//							+ " or end_date is null order by survey_year asc").getResultList());
//           if(!surveyYear.isEmpty()) {
//	        WebDcfPersonDetailsEO nodal = findNodalOfficerDetail(instituteId,instituteType,"NO",surveyYear.get(0));
//	        WebDcfPersonDetailsEO nodals = new WebDcfPersonDetailsEO();
//            if(nodal==null) {
//            PersonDetailsEmadedPK pk = new PersonDetailsEmadedPK();
//			if(surveyYear.get(0)>2022) {
//				pk.setSurveyYear(2022);
//			}else {
//				pk.setSurveyYear(surveyYear.get(0));
//			}
//			pk.setAisheCode(instituteId);
//			pk.setInstituteType(instituteType);
//			pk.setOfficerType("NO");
//			nodals.setUniversityPk(pk);
//			nodals.setOfficerEmail(userMasterEO.getEmail());
//			nodals.setOfficerMobile(String.valueOf(userMasterEO.getMobile()));
//			if(userMasterEO.getMiddleName()==null) {
//				userMasterEO.setMiddleName("");
//			}
//			if(userMasterEO.getLastName()==null) {
//				userMasterEO.setLastName("");
//			}
//			nodals.setOfficerName(userMasterEO.getNodalOfficerName());//userMasterEO.getFirstName() +" "+ userMasterEO.getMiddleName() + " " + userMasterEO.getLastName());
//			nodals.setOfficerTelephone(userMasterEO.getPhoneLandline());
//			
//			String designation = (String) session1.createNativeQuery("select officer_designation from webdcf.person_details_survey where aishe_code ='"+instituteId+"' and "
//			 		+ "survey_year in (select max(survey_year) from \r\n"
//			 		+ "form_upload where university_id ='"+instituteId+"') and inst_type ='"+instituteType+"' AND officer_type ='NO'").uniqueResult();
//		    if(nodal==null) {
//		    	nodals.setOfficerDesignation(designation);
//		    }
//			session.saveOrUpdate(nodals);
//            }else {
//            	PersonDetailsEmadedPK pk = new PersonDetailsEmadedPK();
//      			
//            	if(surveyYear.get(0)>2022) {
//      				pk.setSurveyYear(2022);
//      			}else {
//      				pk.setSurveyYear(surveyYear.get(0));
//      			}
//      			pk.setAisheCode(instituteId);
//      			pk.setInstituteType(instituteType);
//      			pk.setOfficerType("NO");
//      			nodals.setUniversityPk(pk);
//      			nodals.setOfficerEmail(userMasterEO.getEmail());
//      			nodals.setOfficerMobile(String.valueOf(userMasterEO.getMobile()));
//      			
//      			if(userMasterEO.getMiddleName()==null) {
//      				userMasterEO.setMiddleName("");
//      			}
//      			if(userMasterEO.getLastName()==null) {
//      				userMasterEO.setLastName("");
//      			}
//      			nodals.setOfficerName(userMasterEO.getNodalOfficerName());
//      			nodals.setOfficerTelephone(userMasterEO.getPhoneLandline());
//      	
//      			nodals.setOfficerDesignation(nodal.getOfficerDesignation());
//            	session.update(nodals);
//            }
//           }}

            return userMasterEO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                logger.error("Couldn’t roll back transaction " + trEx.getMessage());
                e.printStackTrace();
            }
        } finally {
            session.close();
            session1.close();
        }
        return userMasterEO;
    }

    public List<WebDcfPersonDetailsEO> getPersonDetailsWebdcf(Integer currentSurveyYear, String instituteId, String instituteType) {
        logger.info("MasterDaoImpl : fetchUniversityStudentHostelData method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<WebDcfPersonDetailsEO> query = builder
                    .createQuery(WebDcfPersonDetailsEO.class);
            Root<WebDcfPersonDetailsEO> allData = query.from(WebDcfPersonDetailsEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            Predicate cslsy = builder.equal(allData.get("universityPk").get("surveyYear"), currentSurveyYear);
            Predicate aishecode = builder.equal(allData.get("universityPk").get("aisheCode"), instituteId);
            Predicate isUpdated = builder.equal(allData.get("universityPk").get("instituteType"), instituteType);

            predicates.add(cslsy);
            predicates.add(aishecode);
            predicates.add(isUpdated);
            query.where(predicates.toArray(new Predicate[predicates.size()]));
            List<WebDcfPersonDetailsEO> university = session.createQuery(query).getResultList();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    private WebDcfPersonDetailsEO findNodalOfficerDetail(String instituteId, String instituteType, String officerType,
                                                         int surveyYear) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<WebDcfPersonDetailsEO> query = builder
                    .createQuery(WebDcfPersonDetailsEO.class);
            Root<WebDcfPersonDetailsEO> allData = query.from(WebDcfPersonDetailsEO.class);
            query.where(builder.and(builder.equal(allData.get("universityPk").get("aisheCode"), instituteId),
                    builder.equal(allData.get("universityPk").get("surveyYear"), 2022)
                    , builder.equal(allData.get("universityPk").get("officerType"), "NO"),
                    builder.equal(allData.get("universityPk").get("instituteType"), instituteType
                    )));
            WebDcfPersonDetailsEO university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public UserMasterEO getUserRegistration(String userId) {
        logger.info("RegisterationDaoImpl : getUserRegistration method invoked :");
        Session session = sessionFactory.openSession();
        try {
            //String userid = userId.toLowerCase();
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" from UserMasterEO where userId= :userId and isApproved=1");
            Query query = session.createQuery(queryBuilder.toString());
            query.setParameter("userId", userId);
            List<UserMasterEO> list = query.getResultList();

//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<UserMasterEO> criteriaQuery = builder.createQuery(UserMasterEO.class);
//        Root<UserMasterEO> root = criteriaQuery.from(UserMasterEO.class);
//        List<Predicate> predicates = new ArrayList<Predicate>();
//            if (userId != null) {
//                predicates.add(builder.equal(root.l("userId"), userId));
//                predicates.add(builder.equal(root.get("isApproved"), 1));
//            }
//            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
//            Query<UserMasterEO> q = session.createQuery(criteriaQuery);
//            List<UserMasterEO> list = q.getResultList();
            Integer lsy = null;
            Integer minlsy = null;
            if (list.get(0).getAisheCode() != null) {
                String aisheCode = list.get(0).getAisheCode();
                String[] splitted = aisheCode.trim().split("\\s*-\\s*");
                String instituteType = splitted[0];
                String instituteId = splitted[1];
                Integer openSurvey = null;
                openSurvey = (Integer) session.createNativeQuery("select max(survey_year) from public.survey_master where end_date>now()").uniqueResult();
                if (openSurvey == null) {
                    openSurvey = 0;
                }
                if (instituteType.equals("C")) {
                    lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                            + " and institute_type ='C' and survey_year<" + openSurvey + "").uniqueResult();
                    if (lsy != null) {
                        minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                + " and institute_type ='C' and survey_year<" + lsy + " ").uniqueResult();
                    }
                }
                if (instituteType.equals("S")) {
                    lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                            + " and institute_type ='S' and survey_year<" + openSurvey + "").uniqueResult();
                    if (lsy != null) {
                        minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                + " and institute_type ='S' and survey_year<" + lsy + "").uniqueResult();
                    }
                }
                if (instituteType.equals("U")) {
                    lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                            + " and institute_type ='U' and survey_year<" + openSurvey + "").uniqueResult();
                    if (lsy != null) {
                        minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                + " and institute_type ='U' and survey_year<" + lsy + "").uniqueResult();
                    }
                }
//                if(lsy==null) {
//                	lsy=openSurvey;
//                }
            }
            UserMasterEO usermaster = new UserMasterEO();
            BeanUtils.copyProperties(list.get(0), usermaster);
            usermaster.setLSY(lsy);
            usermaster.setMinlsy(minlsy);
            if (list.size() > 0) {
                return usermaster;//list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;

    }

    @Override
    public UserMasterEO getUserRegistration1(String userId) {
        logger.info("RegisterationDaoImpl : getUserRegistration method invoked :");
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" from UserMasterEO where userId= :userId");
            Query query = session.createQuery(queryBuilder.toString());
            query.setParameter("userId", userId);
            List<UserMasterEO> list = query.getResultList();
            Integer lsy = null;
            Integer minlsy = null;
            if (list.get(0).getAisheCode() != null) {
                String aisheCode = list.get(0).getAisheCode();
                String[] splitted = aisheCode.trim().split("\\s*-\\s*");
                String instituteType = splitted[0];
                String instituteId = splitted[1];
                Integer openSurvey = null;
                openSurvey = (Integer) session.createNativeQuery("select max(survey_year) from public.survey_master where end_date>now()").uniqueResult();
                if (openSurvey == null) {
                    openSurvey = 0;
                }
                if (instituteType.equals("C")) {
                    lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                            + " and institute_type ='C' and survey_year<" + openSurvey + "").uniqueResult();
                    if (lsy != null) {
                        minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                + " and institute_type ='C' and survey_year<" + lsy + " ").uniqueResult();
                    }
                }
                if (instituteType.equals("S")) {
                    lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                            + " and institute_type ='S' and survey_year<" + openSurvey + "").uniqueResult();
                    if (lsy != null) {
                        minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                + " and institute_type ='S' and survey_year<" + lsy + "").uniqueResult();
                    }
                }
                if (instituteType.equals("U")) {
                    lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                            + " and institute_type ='U' and survey_year<" + openSurvey + "").uniqueResult();
                    if (lsy != null) {
                        minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                + " and institute_type ='U' and survey_year<" + lsy + "").uniqueResult();
                    }
                }
//                if(lsy==null) {
//                	lsy=openSurvey;
//                }
            }
            UserMasterEO usermaster = new UserMasterEO();
            BeanUtils.copyProperties(list.get(0), usermaster);
            usermaster.setLSY(lsy);
            usermaster.setMinlsy(minlsy);
            if (usermaster.getAisheCode() != null) {
                InstituteDetailEO instituteDetail = getInstituteDetail(usermaster.getAisheCode());
                if (instituteDetail != null) {
                    if (instituteDetail.getNodalOfficerName() != null) {
                        //  usermaster.setFirstName(instituteDetail.getNodalOfficerName());//////////////first_name change
                    }
                    if (instituteDetail.getNodalOfficerEmail() != null) {
                        usermaster.setEmail(instituteDetail.getNodalOfficerEmail());
                    }
                    if (instituteDetail.getNodalOfficerMobile() != null) {
                        usermaster.setMobile(instituteDetail.getNodalOfficerMobile());
                    }
                }
            }
            if (usermaster != null && usermaster.getRoleId() != null) {
                usermaster.setRoleName((String) session.createNativeQuery("select role_name from ref_user_role_master where role_id =" + usermaster.getRoleId() + "").uniqueResult());
            }
            if (list.size() > 0) {
                return usermaster;//list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;

    }


    @Override
    public UserMasterDetailEO getUserByUserId(String userId) {
        logger.info("RegisterationDaoImpl : getUserRegistration method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterDetailEO> criteriaQuery = builder.createQuery(UserMasterDetailEO.class);
            Root<UserMasterDetailEO> root = criteriaQuery.from(UserMasterDetailEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (userId != null) {
                predicates.add(builder.equal(root.get("userId"), userId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterDetailEO> q = session.createQuery(criteriaQuery);
            List<UserMasterDetailEO> list = q.getResultList();
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
    public UserMasterDetailEO getUser(ApproveUserDTO approveUserDTO) {
        logger.info("RegisterationDaoImpl : getUserRegistration method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterDetailEO> criteriaQuery = builder.createQuery(UserMasterDetailEO.class);
            Root<UserMasterDetailEO> root = criteriaQuery.from(UserMasterDetailEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (approveUserDTO.getAisheCode() != null) {
                predicates.add(builder.equal(root.get("aisheCode"), approveUserDTO.getAisheCode()));
            }
            if (approveUserDTO.getRoleId() != null) {
                predicates.add(builder.equal(root.get("roleId"), approveUserDTO.getRoleId()));
            }
            predicates.add(builder.equal(root.get("isApproved"), 1));
            predicates.add(builder.equal(root.get("statusId"), 2));
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterDetailEO> q = session.createQuery(criteriaQuery);
            List<UserMasterDetailEO> list = q.getResultList();
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
    public UserMasterDetailEO save(UserMasterDetailEO UserMasterDetailEO) {
        logger.info("RegisterationDaoImpl : saveUpdateRegistration method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(UserMasterDetailEO);
            tx.commit();
            return UserMasterDetailEO;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                logger.error("Couldn’t roll back transaction " + trEx.getMessage());
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean deleteUser(UserMasterDetailEO masterEO) {
        logger.info("RegisterationDaoImpl : deleteUser method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(masterEO);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                logger.error("Couldn’t roll back transaction " + trEx.getMessage());
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<UserMasterDetailStateEO> getUserByCondition(Integer roleId, String aisheCode, Integer userStatus, String stateCode, String UniversityId, Integer deoRoleId, Boolean isApproved) {
        logger.info("RegisterationDaoImpl : getUserRegistration method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterDetailStateEO> criteriaQuery = builder.createQuery(UserMasterDetailStateEO.class);
            Root<UserMasterDetailStateEO> root = criteriaQuery.from(UserMasterDetailStateEO.class);
            List<Predicate> predicates = new ArrayList<>();
            if (roleId != null) {
                predicates.add(builder.equal(root.get("roleId"), roleId));
            }
            if (aisheCode != null) {
                predicates.add(builder.equal(root.get("aisheCode"), aisheCode));
            }
            if (userStatus != null) {
                predicates.add(builder.equal(root.get("statusId"), userStatus));
            }
            if (stateCode != null) {
                predicates.add(builder.equal(root.get("state").get("id"), stateCode));
            }

            if (deoRoleId != null) {
                predicates.add(builder.equal(root.get("deoUserType"), deoRoleId));
            }
            if (isApproved != null) {
                predicates.add(builder.equal(root.get("isApproved"), isApproved == true ? 1 : 0));
            }
            if (UniversityId != null) {
                predicates.add(builder.equal(root.get("stateLevelBody"), UniversityId));
            }


            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterDetailStateEO> q = session.createQuery(criteriaQuery);
            List<UserMasterDetailStateEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;

    }

    @Override
    public List<RegisteredUserDTO> getRegisteredUser(Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved, SortBy sortBy, String instituteType, /*int page, int pageSize,*/ String fromDate, String toDate, String searchText, String categoryType) {
        logger.info("RegisterationDaoImpl : getRegisteredUser method invoked :");
        Session session = sessionFactory.openSession();
        List<RegisteredUserDTO> userDTOS = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        try {
            if (null != instituteType) {
                if (instituteType.equalsIgnoreCase("C")) {
                    if (formUploadStatus != null) {
                        if (true == formUploadStatus) {
                            if (surveyYear != null) {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_COLLEGE_WITH_SURVEY);
                            } else {
                                queryBuilder.append(NativeQuerySystem.FROM_UPLOAD_COLLEGE_WITHOUT_SURVEY);
                            }
                        } else {
                            if (surveyYear != null)
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_UN_FILED_COLLEGE);
                        }

                    } else {
                        if (surveyYear != null) {
                            queryBuilder.append(NativeQuerySystem.COLLEGE_WITH_SURVEY);
                        } else {
                            queryBuilder.append(NativeQuerySystem.COLLEGE_WITHOUT_SURVEY);
                        }
                    }
                }
                if (instituteType.equalsIgnoreCase("S")) {
                    if (formUploadStatus != null) {
                        if (true == formUploadStatus) {
                            if (surveyYear != null) {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_STANDALONE_WITH_SURVEY);
                            } else {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_STANDALONE_WITHOUT_SURVEY);
                            }
                        } else {
                            if (surveyYear != null)
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_UN_FILED_STANDALONE);
                        }
                    } else {
                        if (surveyYear != null) {
                            queryBuilder.append(NativeQuerySystem.STANDALONE_WITH_SURVEY);
                        } else {
                            queryBuilder.append(NativeQuerySystem.STANDALONE_WITHOUT_SURVEY);
                        }
                    }
                }
                if (instituteType.equalsIgnoreCase("U")) {
                    if (formUploadStatus != null) {
                        if (true == formUploadStatus) {
                            if (surveyYear != null) {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_UNIVERSITY_WITH_SURVEY);
                            } else {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_UNIVERSITY_WITHOUT_SURVEY);
                            }
                        } else {
                            queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_UN_FILED_UNIVERSITY);
                        }
                    } else {
                        if (surveyYear != null) {
                            queryBuilder.append(NativeQuerySystem.UNIVERSITY_WITH_SURVEY);
                        } else {
                            queryBuilder.append(NativeQuerySystem.UNIVERSITY_WITHOUT_SURVEY);
                        }
                    }
                }
                if (instituteType.equalsIgnoreCase("ALL")) {
                    if (null == formUploadStatus) {
                        queryBuilder.append(NativeQuerySystem.OTHERS_USER);
                    }
                }
            } else {
                if (null == formUploadStatus) {
                    queryBuilder = new StringBuilder();
                    queryBuilder.append(NativeQuerySystem.OTHERS_USER);
                }
            }
            //if (formUploadStatus != null && formUploadStatus == false)
            queryBuilder = new StringBuilder(fillParameter(queryBuilder.toString(), surveyYear, formUploadStatus));
            String finalQuery = setParameterInQuery(queryBuilder.toString(), roleId, surveyYear, userStatus, deoRoleId, dcfStatus, formUploadStatus, stateCode, universityId, aisheCode, isApproved, instituteType, fromDate, toDate, searchText, categoryType, 1);
            Query query = session.createNativeQuery(finalQuery + " order by um.user_id");
            query.setParameter("roleId", roleId);
            /*query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);*/

            @SuppressWarnings("unchecked")
            List<Object[]> userListData = (List<Object[]>) query.getResultList();
            userDTOS = bindObjectInTODto(userListData, userDTOS, formUploadStatus, surveyYear);
            return userDTOS;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return userDTOS;
    }

    private String getNameByNative(String query) {
        logger.info("MasterDataDaoImpl : getNameByNative method invoked :");

        Session session1 = sessionFactory.openSession();
        Transaction tx = null;
        try {
            return String.valueOf(session1.createNativeQuery(query).uniqueResult());
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return null;
    }

    private List<RegisteredUserDTO> bindObjectInTODto(List<Object[]> userListData, List<RegisteredUserDTO> userDTOS, Boolean formUploadStatus, Integer surveyYear) {
        for (Object[] object : userListData) {
            RegisteredUserDTO registeredUserDTO = new RegisteredUserDTO();
            if (object[0] != null) {
                registeredUserDTO.setUserId(object[0].toString());
                //examinationResult.setRequestIdOrAisheCode(object[0].toString());
            }
            if (object[1] != null) {
                registeredUserDTO.setRoleId(Integer.valueOf(object[1].toString()));
                // examinationResult.setSurveyYear(Integer.parseInt(object[1].toString()));
            }
            if (object[2] != null) {
                registeredUserDTO.setFirstName(object[2].toString());
                registeredUserDTO.setFullName(registeredUserDTO.getFirstName());
            }
            if (object[3] != null) {
                registeredUserDTO.setMiddleName(object[3].toString());
                registeredUserDTO.setFullName(registeredUserDTO.getFullName() + " " + registeredUserDTO.getMiddleName());
            }
            if (object[4] != null) {
                registeredUserDTO.setLastName(object[4].toString());
                registeredUserDTO.setFullName(registeredUserDTO.getFullName() + " " + registeredUserDTO.getLastName());
            }
            if (object[5] != null) {
                registeredUserDTO.setStateName(object[5].toString());
            }
            if (object[6] != null) {
                registeredUserDTO.setIsApproved(Integer.valueOf(object[6].toString()));
            }
            if (object[7] != null) {
                registeredUserDTO.setInstituteName(object[7].toString());
            }
            if (object[8] != null) {
                registeredUserDTO.setAisheCode(object[8].toString());
            }
            if (object[9] != null) {
                registeredUserDTO.setUserStatus(Integer.valueOf(object[9].toString()));
            }
            if (object[10] != null) {
                registeredUserDTO.setUniversityName(object[10].toString());
            }


            if (object[10] == null) {
                String aisheCode = registeredUserDTO.getAisheCode();
                if (null == object[7]) {
                    if (null != aisheCode) {
                        String[] splitted = aisheCode.trim().split("\\s*-\\s*");
                        String instituteId = splitted[1];
                        String instituteType = splitted[0];
                        switch (instituteType.toUpperCase()) {
                            case "U": {
                                registeredUserDTO.setUniversityName(getNameByNative(NativeQuerySystem.IS_DCF_APPLICABLE_UNIVERSITY_NAME + instituteId + "') and id='" + instituteId + "'"));
                                break;
                            }
                            case "C": {
                                registeredUserDTO.setInstituteName(getNameByNative(NativeQuerySystem.IS_DCF_APPLICABLE_COLLEGE_NAME + instituteId + "') and id='" + instituteId + "'"));
                                break;
                            }
                            case "S": {
                                registeredUserDTO.setInstituteName(getNameByNative(NativeQuerySystem.IS_DCF_APPLICABLE_STANDALONE_NAME + instituteId + "') and id='" + instituteId + "'"));
                                break;
                            }
                        }
                    }
                }

            }
            if (formUploadStatus != null) {
                if (object[11] != null) {
                    registeredUserDTO.setFormUpload(object[11].toString() != null ? true : false);
                }
            }
            if (object[12] != null) {
                registeredUserDTO.setLevelName(object[12].toString());
            }
            if (object[13] != null) {
                // if (Integer.valueOf(object[9].toString()) == 2) {
                // registeredUserDTO.setUserStatusName("Active");
                // } else {
                registeredUserDTO.setUserStatusName(object[13].toString());
            }
            //}

            if (object[14] != null) {
                registeredUserDTO.setAddressLine1(object[14].toString());
            }
            if (object[15] != null) {
                registeredUserDTO.setAddressLine2(object[15].toString());
            }
            if (object[16] != null) {
                registeredUserDTO.setCity(object[16].toString());
            }
            if (object[17] != null) {
                registeredUserDTO.setPhone(object[17].toString());
            }
            if (object[18] != null) {
                registeredUserDTO.setMobile(object[18].toString());
            }
            if (object[19] != null) {
                registeredUserDTO.setEmailId(object[19].toString());
            }
            if (object[20] != null) {
                registeredUserDTO.setStateLevelBody(object[20].toString());
                if (object[10] == null) {
                    registeredUserDTO.setUniversityName(getNameByNative(NativeQuerySystem.IS_DCF_APPLICABLE_UNIVERSITY_NAME + object[20].toString() + "') and id='" + object[20].toString() + "'"));
                }
            }
            if (object[21] != null) {
                registeredUserDTO.setStateLevelBodyInstitute(object[21].toString());
            }
            if (object[22] != null) {
                registeredUserDTO.setBodyType(object[22].toString());
            }
            if (object[23] != null) {
                registeredUserDTO.setStdCode(object[23].toString());
            }
            if (object[24] != null) {
                registeredUserDTO.setAlternateEmailId(object[24].toString());
            }
            if (object[25] != null) {
                registeredUserDTO.setGender(object[25].toString());
            }
            if (object[26] != null) {
                registeredUserDTO.setRoleName(object[26].toString());
            }
            if (object[27] != null) {
                registeredUserDTO.setDistrictName(object[27].toString());
            }
            if (object[28] != null) {
                registeredUserDTO.setDistrictCode(object[28].toString());
            }
            if (object[29] != null) {
                registeredUserDTO.setDocument((byte[]) object[29]);
            }
            if (object[30] != null) {
                registeredUserDTO.setDocumentName(object[30] + "");
            }
            if (object[31] != null) {
                registeredUserDTO.setStateCode(object[31] + "");
            }
            if (object[32] != null) {
                registeredUserDTO.setFullName(object[32] + "");
            }
            if (object[9] != null) {
                if (Integer.valueOf(object[9].toString()) == 2) {
                    if (object[33] != null) {
                        Timestamp ts = Timestamp.valueOf(object[33].toString());
                        LocalDateTime dob = ts.toLocalDateTime();
                        registeredUserDTO.setApprovedDate(DateUtils.convertDBDateTimeToStringNew(dob) + "");
                    }
                    if (object[34] != null) {
                        registeredUserDTO.setApprovedBy(object[34] + "");
                    }
                }
                if (Integer.valueOf(object[9].toString()) == 1)
                    if (object[33] != null) {
                        registeredUserDTO.setType(object[33] + "");
                    }
            }

            userDTOS.add(registeredUserDTO);
        }
        return userDTOS;
    }

    @Override
    public BigInteger getRegisteredUserCont(Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved, String instituteType, String fromDate, String toDate, String searchText, String categoryType) {
        logger.info("RegisterationDaoImpl : getRegisteredUserCont method invoked :");
        Session session = sessionFactory.openSession();
        BigInteger result = null;
        StringBuilder queryBuilder = new StringBuilder();
        try {
            if (null != instituteType) {
                if (instituteType.equalsIgnoreCase("C")) {
                    if (formUploadStatus != null) {
                        if (formUploadStatus == true) {
                            if (surveyYear != null) {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_COUNT_COLLEGE_WITH_SURVEY);
                            } else {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_COUNT_COLLEGE_WITHOUT_SURVEY);
                            }
                        } else {
                            if (surveyYear != null) {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_UN_FILED_COUNT_COLLEGE);
                            }
                        }
                    } else {
                        if (surveyYear != null) {
                            queryBuilder.append(NativeQuerySystem.COUNT_COLLEGE_WITH_SURVEY);
                        } else {
                            queryBuilder.append(NativeQuerySystem.COUNT_COLLEGE_WITHOUT_SURVEY);
                        }
                    }
                }
                if (instituteType.equalsIgnoreCase("S")) {
                    if (formUploadStatus != null) {
                        if (formUploadStatus == true) {
                            if (surveyYear != null) {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_COUNT_STANDALONE_WITH_SURVEY);
                            } else {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_COUNT_STANDALONE_WITHOUT_SURVEY);
                            }
                        } else {
                            if (surveyYear != null) {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_UN_FILED_COUNT_STANDALONE);
                            }
                        }
                    } else {
                        if (surveyYear != null) {
                            queryBuilder.append(NativeQuerySystem.COUNT_STANDALONE_WITH_SURVEY);
                        } else {
                            queryBuilder.append(NativeQuerySystem.COUNT_STANDALONE_WITHOUT_SURVEY);
                        }
                    }
                }
                if (instituteType.equalsIgnoreCase("U")) {
                    if (formUploadStatus != null) {
                        if (formUploadStatus == true) {
                            if (surveyYear != null) {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_COUNT_UNIVERSITY_WITH_SURVEY);
                            } else {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_COUNT_UNIVERSITY_WITHOUT_SURVEY);
                            }
                        } else {
                            if (surveyYear != null) {
                                queryBuilder.append(NativeQuerySystem.FORM_UPLOAD_UN_FILED_COUNT_UNIVERSITY);
                            }
                        }
                    } else {
                        if (surveyYear != null) {
                            queryBuilder.append(NativeQuerySystem.COUNT_UNIVERSITY_WITH_SURVEY);
                        } else {
                            queryBuilder.append(NativeQuerySystem.COUNT_UNIVERSITY_WITHOUT_SURVEY);
                        }
                    }
                }
                if (instituteType.equalsIgnoreCase("ALL")) {
                    if (null == formUploadStatus) {
                        queryBuilder.append(NativeQuerySystem.COUNT_OTHERS_USER);
                    }
                }

            } else {
                if (formUploadStatus == null)
                    queryBuilder.append(NativeQuerySystem.COUNT_OTHERS_USER);
            }
            if (formUploadStatus != null && formUploadStatus == false)
                queryBuilder = new StringBuilder(fillParameter(queryBuilder.toString(), surveyYear, formUploadStatus));
            String finalQuery = setParameterInQuery(queryBuilder.toString(), roleId, surveyYear, userStatus, deoRoleId, dcfStatus, formUploadStatus, stateCode, universityId, aisheCode, isApproved, instituteType, fromDate, toDate, searchText, categoryType, 1);

            Query query = session.createNativeQuery(finalQuery);
            query.setParameter("roleId", roleId);
            result = (BigInteger) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;

    }

    private String fillParameter(String query, Integer surveyYear, Boolean formUplodStatus) {
        return query.replaceAll("=0", "=" + surveyYear);
    }

    private String setParameterInQuery(String query, Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved, String instituteType, String fromDate, String toDate, String searchText, String categoryType, int type) {
       /* if (null != roleId) {
            query = query + "where um.role_id=" + roleId;
        }*/
        if (null != searchText) {
            searchText = searchText.toLowerCase();
            if (null == instituteType || instituteType.equalsIgnoreCase("ALL")) {
                query = query + " and (lower(um.user_id) like '%" + searchText + "%'" +
                        " or lower(um.first_name) like '%" + searchText + "' " +
                        " or lower(um.middle_name) like '%" + searchText + "%' " +
                        " or lower(um.last_name) like '%" + searchText + "' " +
                        " or lower(um.email_id) like '%" + searchText + "%' " +
                        " or lower(um.phone_mobile) like '%" + searchText + "%' " +
                        " or lower(s.name) like '%" + searchText + "%' " +
                        " or lower(us.status) like '%" + searchText + "%' " +
                        " or lower(um.aishe_code) like '%" + searchText + "%'" +
                        " or lower(si.name) like '%" + searchText + "%'" +
                        " or lower(c.name) like '%" + searchText + "%'" +
                        " or lower(ru.name) like '%" + searchText + "%')";
            } else if (instituteType.equalsIgnoreCase("C")) {
                query = query + " and (lower(um.user_id) like '%" + searchText + "%'" +
                        " or lower(um.first_name) like '%" + searchText + "' " +
                        " or lower(um.middle_name) like '%" + searchText + "%' " +
                        " or lower(um.last_name) like '%" + searchText + "' " +
                        " or lower(um.email_id) like '%" + searchText + "%' " +
                        " or lower(um.phone_mobile) like '%" + searchText + "%' " +
                        " or lower(s.name) like '%" + searchText + "%' " +
                        " or lower(us.status) like '%" + searchText + "%' " +
                        " or lower(um.aishe_code) like '%" + searchText + "%'" +
                        " or lower(c.name) like '%" + searchText + "%'" +
                        " or lower(ru.name) like '%" + searchText + "%')";

            } else if (instituteType.equalsIgnoreCase("S") || instituteType.equalsIgnoreCase("U")) {
                query = query + " and (lower(um.user_id) like '%" + searchText + "%'" +
                        " or lower(um.first_name) like '%" + searchText + "' " +
                        " or lower(um.middle_name) like '%" + searchText + "%' " +
                        " or lower(um.last_name) like '%" + searchText + "' " +
                        " or lower(um.email_id) like '%" + searchText + "%' " +
                        " or lower(um.phone_mobile) like '%" + searchText + "%' " +
                        " or lower(s.name) like '%" + searchText + "%' " +
                        " or lower(us.status) like '%" + searchText + "%' " +
                        " or lower(um.aishe_code) like '%" + searchText + "%'" +
                        " or lower(c.name) like '%" + searchText + "%')";
            }
        }

        if (null != surveyYear && null != instituteType && instituteType.equalsIgnoreCase("C")) {
            query = query + " and c.survey_year=" + surveyYear + "and ru.survey_year=" + surveyYear;
        }

        if (null != surveyYear && null != instituteType && (!instituteType.equalsIgnoreCase("C") && !instituteType.equalsIgnoreCase("ALL"))) {
            query = query + " and c.survey_year=" + surveyYear;
        }
        if (null != userStatus && roleId != null && roleId != 1) {
            query = query + " and um.status_id=" + userStatus;
        }
        if (null != userStatus && roleId != null && roleId == 1) {
            query = query + " and um.status_id =" + userStatus;
        }
        if (null != deoRoleId) {
            query = query + " and um.deo_user_type=" + deoRoleId;
        }
        if (null != dcfStatus && null != instituteType && (!instituteType.equalsIgnoreCase("S"))) {
            query = query + " and c.is_dcf_applicable=" + dcfStatus;
        }
        if (null != formUploadStatus && null != surveyYear) {
            if (true == formUploadStatus) {
                query = query + " and fu.survey_year=" + surveyYear;
            } else {
                if (instituteType.equals("C")) {
                    query = query + " and c.survey_year=" + surveyYear + "and ru.survey_year=" + surveyYear;
                } else if (instituteType.equals("U") || instituteType.equals("S")) {
                    query = query + " and c.survey_year=" + surveyYear;
                }
            }
        }
        if (null != stateCode) {
            query = query + " and um.state_code='" + stateCode + "'";
        }
        if (null != universityId) {
            query = query + " and um.state_level_body='" + universityId + "'";
        }
        if (null != aisheCode) {
            query = query + " and um.aishe_code='" + aisheCode + "'";
        }
        if (null != isApproved) {
            int approve = isApproved == true ? 1 : 0;
            query = query + " and um.is_approved=" + approve;
        }
        if (null != categoryType && null != instituteType && null != categoryType && type == 1) {
            switch (instituteType.toUpperCase()) {
                case "C":
                case "U":
                    query = query + " and c.type_id='" + categoryType + "' ";
                    break;
                case "S":
                    query = query + " and c.statebodyid='" + categoryType + "' ";
                    break;
            }
        } else if (null != categoryType && null != instituteType && null != categoryType && type == 2) {
            switch (instituteType.toUpperCase()) {
                case "C":
                    query = query + " and c.type_id='" + categoryType + "' ";
                    break;
                case "U":
                    query = query + " and ru.type_id='" + categoryType + "' ";
                    break;
                case "S":
                    query = query + " and si.statebodyid='" + categoryType + "' ";
                    break;
            }
        }
        if (null != toDate && null != fromDate) {
            LocalDate fromDateFrom = DateUtils.convertStringSlashDateToDBDate(fromDate);
            LocalDate fromDateTO = DateUtils.convertStringSlashDateToDBDate(toDate);
            query = query + " and  cast(um.registration_datetime as date) >='" + fromDateFrom + "' and cast(um.registration_datetime as date) <='" + fromDateTO + "'";
        }
        return query;
    }


    @Override
    public UserMasterDetailEO update(UserMasterDetailEO activeUser) {
        logger.info("RegisterationDaoImpl : saveUpdateRegistration method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(activeUser);
            tx.commit();
            return activeUser;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                logger.error("Couldn’t roll back transaction " + trEx.getMessage());
                e.printStackTrace();
            }
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public UserRegistrationDetailEO saveUserRegistrationData(UserRegistrationDetailEO userMasterEO) {
        logger.info("RegisterationDaoImpl : saveUpdateRegistration method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String newp = EncryptionDecryptionUtil.getDecryptedString(userMasterEO.getBcryptPassword());
            String oldp = EncryptionDecryptionUtil.getDecryptedString(userMasterEO.getConfirmBcryptPassword());
            boolean check = newp.equals(oldp);
            if (check) {
                userMasterEO.setBcryptPassword(bcrypt.encode(newp));
                userMasterEO.setUserPasswordInHash("999");
                userMasterEO.setStatusId(userMasterEO.getStatusId());
                userMasterEO.setRegistrationDatetime(DateUtils.obtainCurrentTimeStamp());
                session.save(userMasterEO);
                tx.commit();
            }
            return userMasterEO;
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

    @Override
    public UserRegistrationUpdatedDetailEO updateUserRegistration(UserRegistrationUpdatedDetailEO userMaster, boolean isUpdated, UserInfo userInfo) {
        logger.info("RegisterationDaoImpl : saveUpdateRegistration method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            if (isUpdated) {
                UserRegistrationUpdatedDetailEO master = session.get(UserRegistrationUpdatedDetailEO.class, userMaster.getUserId());
                UserMasterHistoryEO userMasterLogEo = new UserMasterHistoryEO();
                tx = session.beginTransaction();
                userMasterLogEo.setUserId(master.getUserId());
                userMasterLogEo.setRoleId(master.getRoleId());
                if (null != userMaster.getEmailId() && !"null".equals(
                        userMaster.getEmailId()) /* && !userMaster.getEmailId().equals(master.getEmailId()) */) {
                    master.setEmailId(userMaster.getEmailId());
                    if (!userMaster.getOldEmailId().equals("")) {
                        userMasterLogEo.setEmailId(userMaster.getOldEmailId());
                    }
                }
                if (null != userMaster.getPhoneLandline() && !"null".equals(userMaster
                        .getPhoneLandline()) /* && !userMaster.getPhoneLandline().equals(master.getPhoneLandline()) */) {
                    master.setPhoneLandline(userMaster.getPhoneLandline());
                    userMasterLogEo.setPhoneLandline(userMaster.getPhoneLandline());
                }
                if (null != userMaster.getName() && !"null".equals(userMaster.getName())  /*! && userMaster.getFirstName().equals(master.getFirstName())*/) {
                    master.setName(userMaster.getName());
                    userMasterLogEo.setFirstName(userMaster.getOldFirstName());
                }
                if (null != userMaster.getGenderId() /* && !userMaster.getGender().equals(master.getGender()) */) {
                    master.setGenderId(userMaster.getGenderId());
                    userMasterLogEo.setGenderId(userMaster.getOldGender());
                }
                if (null != userMaster.getPhoneMobile() && !"null".equals(userMaster.getPhoneMobile())/* && !userMaster.getMobile().equals(master.getMobile()*/) {
                    master.setPhoneMobile(userMaster.getPhoneMobile());
                    if (!userMaster.getOldMobile().equals("")) {
                        userMasterLogEo.setPhoneMobile(userMaster.getOldMobile());
                    }
                }
                userMasterLogEo.setActionOn(DateUtils.obtainCurrentTimeStamp());
                // userMasterLogEo.setIpAddressModifiedBy(IpAddressProvider.getClientIpAddr(request));
                userMasterLogEo.setActionBy(userInfo.getUsername());
                session.update(master);
                session.saveOrUpdate(userMasterLogEo);
                if (CommanObjectOperation.stringValidate(master.getAisheCode())) {
                    InstituteDetailEO detailEO = getInstituteDetail(master.getAisheCode());
                    if (null != detailEO) {
                        detailEO.setNodalOfficerName(master.getName());
                        detailEO.setNodalOfficerEmail(master.getEmailId());
                        detailEO.setNodalOfficerTelephone(master.getPhoneLandline());
                        detailEO.setNodalOfficerMobile(master.getPhoneMobile());
                        detailEO.setNodalOfficerGender(master.getGenderId());
                        session.saveOrUpdate(detailEO);
                    }
                }

                tx.commit();
                return userMaster;
            } else {
                UserRegistrationUpdatedDetailEO checkForEmail = (UserRegistrationUpdatedDetailEO) session.get(UserRegistrationUpdatedDetailEO.class, userMaster.getUserId());
                BeanUtils.copyProperties(userMaster, checkForEmail);
                tx = session.beginTransaction();
                session.update(checkForEmail);
                UserMasterHistoryEO historyEO = UserMasterHistoryEO.bind(userMaster, checkForEmail, userInfo);
                tx.commit();
                return userMaster;
            }
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

    @Override
    public InstituteDetailEO getInstituteDetail(String aisheCode) {
        logger.info("InstitutionMasterDaoImpl getDetail method invoked");
        Session session = sessionFactory.openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<InstituteDetailEO> criteriaQuery = builder.createQuery(InstituteDetailEO.class);
            Root<InstituteDetailEO> root = criteriaQuery.from(InstituteDetailEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (aisheCode != null) {
                predicates.add(builder.equal(root.get("aisheCode"), aisheCode));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
                    .orderBy(builder.desc(root.get("aisheCode")));
            Query<InstituteDetailEO> q = session.createQuery(criteriaQuery);
            List<InstituteDetailEO> list = q.getResultList();
            if (!list.isEmpty()) {
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
    public UserMasterDetailNewEO approveDisapprvoeUserApproval(ApproveDisapproveUserDTO approveUserDTO,
                                                               HttpServletRequest request) {
        logger.info("approveDisapprvoeUserApproval method invoked");
        Session sessionGet = sessionFactory.openSession();
        Session sessionSave = sessionFactory.openSession();
        UserMasterDetailNewEO userMaster = new UserMasterDetailNewEO();
        UserMasterLogDetailEO userMasterLog = new UserMasterLogDetailEO();
        UserMasterRequestDetailEO userMasterRequest;


        Transaction tx = null;
        Transaction tx1 = sessionGet.beginTransaction();
        try {
            switch (approveUserDTO.getStatus()) {
                case 2:
                    userMasterRequest = fetchUserMasterRequestDetail(approveUserDTO.getUserId());
                    if (userMasterRequest != null) {

                        BeanUtils.copyProperties(userMasterRequest, userMaster);
                        tx = sessionSave.beginTransaction();
                        userMaster.setApprovalMessage(approveUserDTO.getMessage());
                        userMaster.setApprovedBy(approveUserDTO.getApprovedBy());
                        userMaster.setApprovedDatetime(DateUtils.obtainCurrentTimeStamp());
                        userMaster.setStatusId(2);

                        sessionSave.save(userMaster);/////insert into user master

                        String instituteId = null;
                        String instituteType = null;
                        if (approveUserDTO.getAisheCode() != null) {
                            String[] splitted = approveUserDTO.getAisheCode().trim().split("\\s*-\\s*");
                            instituteId = splitted[1];
                            instituteType = splitted[0];
                        }

                        //userRegistrationServiceImpl.emailSender(userMaster.getEmailId(), "Approved", userMaster.getUserId());
                        UserMasterDetailNewEO oldUser = getUserApproveUserMasterNewDTO(approveUserDTO);
                        if (oldUser != null) {
                            oldUser.setStatusId(3);
                            //sessionSave.saveOrUpdate(activeMasterUser);
                            BeanUtils.copyProperties(oldUser, userMasterLog);
                            userMasterLog.setId(null);
                            sessionSave.save(userMasterLog);//////////save into log
                            sessionGet.delete(oldUser);///delete from user master
                            UserActionLog userActionLog = new UserActionLog();
                            userActionLog.setInstitutionId(instituteId);
                            userActionLog.setInstitutionType(instituteType);
                            userActionLog.setActionId(11);
                            userActionLog.setSurveyYear(0);
                            userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                            userActionLog.setIpAddress(request.getRemoteAddr());
                            userActionLog.setRemarks(approveUserDTO.getMessage());
                            userActionLog.setId(null);
                            userActionLog.setUserId(approveUserDTO.getApprovedBy());
                            sessionSave.save(userActionLog);
                            //	userRegistrationServiceImpl.emailSender(userMaster.getEmailId(), "Dis-Approved", activeMasterUser.getUserId());
                        }
                        sessionSave.delete(userMasterRequest);
                        UserActionLog userActionLog = new UserActionLog();
                        userActionLog.setUserId(approveUserDTO.getApprovedBy());
                        userActionLog.setInstitutionId(instituteId);
                        userActionLog.setInstitutionType(instituteType);
                        userActionLog.setActionId(8);
                        userActionLog.setSurveyYear(0);
                        userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                        userActionLog.setIpAddress(request.getRemoteAddr());
                        userActionLog.setRemarks(approveUserDTO.getMessage() + " UserId Approved and Delete from user request table.");
                        userActionLog.setId(null);
                        sessionSave.save(userActionLog);
                        tx.commit();
                        tx1.commit();
                        return userMaster;

                    } else {
                        UserMasterLogDetailEO userMasterLog1 = fetchUserMasterLog(approveUserDTO.getUserId());
                        String instituteId = null;
                        String instituteType = null;
                        if (approveUserDTO.getAisheCode() != null) {
                            String[] splitted = approveUserDTO.getAisheCode().trim().split("\\s*-\\s*");
                            instituteId = splitted[1];
                            instituteType = splitted[0];
                        }
                        if (null != userMasterLog1) {
                            UserMasterDetailNewEO activeMasterUser = getUserApproveUserMasterNewDTO(approveUserDTO);
                            if (activeMasterUser != null) {
                                activeMasterUser.setStatusId(3);
                                //sessionSave.saveOrUpdate(activeMasterUser);
                                BeanUtils.copyProperties(activeMasterUser, userMasterLog);
                                userMasterLog.setId(null);
                                sessionSave.save(userMasterLog);
                                sessionGet.delete(activeMasterUser);
                                UserActionLog userActionLog = new UserActionLog();
                                userActionLog.setInstitutionId(instituteId);
                                userActionLog.setInstitutionType(instituteType);
                                userActionLog.setActionId(11);
                                userActionLog.setSurveyYear(0);
                                userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                                userActionLog.setIpAddress(request.getRemoteAddr());
                                userActionLog.setRemarks(approveUserDTO.getMessage());
                                userActionLog.setId(null);
                                userActionLog.setUserId(approveUserDTO.getApprovedBy());
                                sessionSave.save(userActionLog);
                                //	userRegistrationServiceImpl.emailSender(userMaster.getEmailId(), "Dis-Approved", activeMasterUser.getUserId());
                            }

                            BeanUtils.copyProperties(userMasterLog1, userMaster);
                            tx = sessionSave.beginTransaction();
                            userMaster.setApprovalMessage(approveUserDTO.getMessage());
                            userMaster.setApprovedBy(approveUserDTO.getApprovedBy());
                            userMaster.setApprovedDatetime(DateUtils.obtainCurrentTimeStamp());
                            if (null != userMaster.getAisheCode()) {
                                String[] splitted = approveUserDTO.getAisheCode().trim().split("\\s*-\\s*");
                                instituteId = splitted[1];
                                instituteType = splitted[0];
                                if ("C".equalsIgnoreCase(instituteType)) {
                                    userMaster.setStateLevelBody(getUniversityIdByAisheCode(userMaster.getAisheCode()));
                                }
                            }

                            userMaster.setIsApproved(1);
                            userMaster.setStatusId(2);

                            sessionSave.save(userMaster);
                            //userRegistrationServiceImpl.emailSender(userMaster.getEmailId(), "Approved", userMaster.getUserId());

                            sessionSave.delete(userMasterLog1);
                            UserActionLog userActionLog = new UserActionLog();
                            userActionLog.setUserId(approveUserDTO.getApprovedBy());
                            userActionLog.setInstitutionId(instituteId);
                            userActionLog.setInstitutionType(instituteType);
                            userActionLog.setActionId(8);
                            userActionLog.setSurveyYear(0);
                            userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                            userActionLog.setIpAddress(request.getRemoteAddr());
                            userActionLog.setRemarks(approveUserDTO.getMessage() + " UserId Approved and Delete from user master log table.");
                            userActionLog.setId(null);
                            sessionSave.save(userActionLog);
                            tx.commit();
                            tx1.commit();
                            return userMaster;
                        }
                    }

                    break;
                case 3:
                    userMaster = fetchUserMasterDetailEO(approveUserDTO.getUserId());
                    if (userMaster != null) {
                        tx = sessionSave.beginTransaction();
                        // Perform operations within the transaction
                        BeanUtils.copyProperties(userMaster, userMasterLog);
                        userMasterLog.setId(null);
                        userMasterLog.setStatusId(3);
                        sessionSave.saveOrUpdate(userMasterLog);
                        sessionSave.delete(userMaster);
                        // Perform other operations
                        UserActionLog userActionLog = new UserActionLog();
                        userActionLog.setUserId(approveUserDTO.getApprovedBy());
                        String instituteId = null;
                        String instituteType = null;
                        if (approveUserDTO.getAisheCode() != null) {
                            String[] splitted = approveUserDTO.getAisheCode().trim().split("\\s*-\\s*");
                            instituteId = splitted[1];
                            instituteType = splitted[0];
                        }
                        userActionLog.setInstitutionId(instituteId);
                        userActionLog.setInstitutionType(instituteType);
                        userActionLog.setActionId(11);
                        userActionLog.setSurveyYear(0);
                        userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                        userActionLog.setIpAddress(request.getRemoteAddr());
                        userActionLog.setRemarks(approveUserDTO.getMessage() + " UserId Inactive");
                        userActionLog.setId(null);
                        sessionSave.save(userActionLog);
                        // Commit transaction
                        tx.commit();
                        return userMaster;
                    }
                    break;
                case 4:

                    userMaster = fetchUserMasterDetailEO(approveUserDTO.getUserId());
                    userMasterRequest = fetchUserMasterRequestDetail(approveUserDTO.getUserId());

                    // Check if either userMaster or userMasterRequest exists
                    if (userMaster != null || userMasterRequest != null) {
                        tx = sessionSave.beginTransaction();
                        // Create userMasterLog and set properties

                        if (userMaster != null) {
                            BeanUtils.copyProperties(userMaster, userMasterLog);
                            // Mark userMaster for deletion
                            sessionSave.delete(userMaster);
                        } else {
                            BeanUtils.copyProperties(userMasterRequest, userMasterLog);
                            userMaster = new UserMasterDetailNewEO();
                            BeanUtils.copyProperties(userMasterRequest, userMaster);
                            // Mark userMasterRequest for deletion
                            sessionSave.delete(userMasterRequest);
                        }

                        // Prepare userMasterLog for disapproval
                        userMasterLog.setId(null);
                        userMasterLog.setIsApproved(0);
                        userMasterLog.setStatusId(4);
                        userMasterLog.setApprovalMessage(approveUserDTO.getMessage());
                        userMasterLog.setApprovedBy(approveUserDTO.getApprovedBy());
                        userMasterLog.setApprovedDatetime(DateUtils.obtainCurrentTimeStamp());

                        // Save userMasterLog
                        sessionSave.saveOrUpdate(userMasterLog);

                        // Prepare and save user action log
                        UserActionLog userActionLog = new UserActionLog();
                        userActionLog.setUserId(approveUserDTO.getApprovedBy());
                        String instituteId = null;
                        String instituteType = null;
                        if (approveUserDTO.getAisheCode() != null) {
                            String[] splitted = approveUserDTO.getAisheCode().trim().split("\\s*-\\s*");
                            instituteId = splitted[1];
                            instituteType = splitted[0];
                        }
                        userActionLog.setInstitutionId(instituteId);
                        userActionLog.setInstitutionType(instituteType);
                        userActionLog.setActionId(9);
                        userActionLog.setSurveyYear(0);
                        userActionLog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                        userActionLog.setIpAddress(request.getRemoteAddr());
                        userActionLog.setRemarks(approveUserDTO.getMessage() + " UserId DisApproved");
                        userActionLog.setId(null);
                        sessionSave.save(userActionLog);

                        // Commit transaction
                        tx.commit();
                        return userMaster;
                    }

                    break;
            }
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive() || tx1 != null && tx1.isActive()) {
                    tx.rollback();
                    tx1.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            sessionGet.close();
            sessionSave.close();
        }
        return null;
    }

    private String getUniversityIdByAisheCode(String aisheCode) {
        try (Session session = sessionFactory.openSession()) {
            return (String) session.createNativeQuery("SELECT university_id FROM COLLEGE WHERE 'C-'||ID=:aisheCode ORDER BY SURVEY_YEAR DESC LIMIT 1")
                    .setParameter("aisheCode", aisheCode).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private UserMasterDetailNewEO getUserApproveUserMasterNewDTO(ApproveDisapproveUserDTO approveUserDTO) {
        logger.info("RegisterationDaoImpl : getUserRegistration method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterDetailNewEO> criteriaQuery = builder.createQuery(UserMasterDetailNewEO.class);
            Root<UserMasterDetailNewEO> root = criteriaQuery.from(UserMasterDetailNewEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (approveUserDTO.getAisheCode() != null) {
                predicates.add(builder.equal(root.get("aisheCode"), approveUserDTO.getAisheCode()));
            }
            if (approveUserDTO.getRoleId() != null) {
                predicates.add(builder.equal(root.get("roleId"), approveUserDTO.getRoleId()));
            }
            // predicates.add(builder.equal(root.get("isApproved"), 1));
            predicates.add(builder.equal(root.get("statusId"), 2));
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterDetailNewEO> q = session.createQuery(criteriaQuery);
            List<UserMasterDetailNewEO> list = q.getResultList();
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
    public UserMasterDetailNewEO fetchUserMasterDetailEO(String userId) {
        logger.info("RegisterationDaoImpl : UserMasterRequestDetailEO method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterDetailNewEO> criteriaQuery = builder.createQuery(UserMasterDetailNewEO.class);
            Root<UserMasterDetailNewEO> root = criteriaQuery.from(UserMasterDetailNewEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (userId != null) {
                predicates.add(builder.equal(root.get("userId"), userId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterDetailNewEO> q = session.createQuery(criteriaQuery);
            List<UserMasterDetailNewEO> list = q.getResultList();
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

    private UserMasterNewEO fetchUserMasterNewDetailEO(String userId) {
        logger.info("RegisterationDaoImpl : UserMasterRequestDetailEO method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterNewEO> criteriaQuery = builder.createQuery(UserMasterNewEO.class);
            Root<UserMasterNewEO> root = criteriaQuery.from(UserMasterNewEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (userId != null) {
                predicates.add(builder.equal(root.get("userId"), userId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterNewEO> q = session.createQuery(criteriaQuery);
            List<UserMasterNewEO> list = q.getResultList();
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

    public UserMasterRequestDetailEO fetchUserMasterRequestDetail(String userId) {
        logger.info("RegisterationDaoImpl : UserMasterRequestDetailEO method invoked :");
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

    public UserMasterLogDetailEO fetchUserMasterLog(String userId) {
        logger.info("RegisterationDaoImpl : fetchUserMasterLog method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterLogDetailEO> criteriaQuery = builder.createQuery(UserMasterLogDetailEO.class);
            Root<UserMasterLogDetailEO> root = criteriaQuery.from(UserMasterLogDetailEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (userId != null) {
                predicates.add(builder.equal(root.get("userId"), userId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterLogDetailEO> q = session.createQuery(criteriaQuery);
            List<UserMasterLogDetailEO> list = q.getResultList();
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
    public UserMasterRequestDetailEO saveUserRegistrationNew(UserMasterRequestDetailEO userMasterEO, HttpServletRequest request, UserInfo userInfo) {
        logger.info("RegisterationDaoImpl : saveUpdateRegistration method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String newp = EncryptionDecryptionUtil.getDecryptedString(userMasterEO.getBcryptPassword());
            String oldp = EncryptionDecryptionUtil.getDecryptedString(userMasterEO.getConfirmBcryptPassword());
            boolean check = newp.equals(oldp);
            if (check && (userMasterEO.getStatusId() != null && userMasterEO.getStatusId() != 2)) {

                userMasterEO.setBcryptPassword(bcrypt.encode(newp));
                userMasterEO.setUserPasswordInHash("999");
                userMasterEO.setStatusId(userMasterEO.getStatusId());
                userMasterEO.setRegistrationDatetime(DateUtils.obtainCurrentTimeStamp());

                userMasterEO.setAisheCode(userMasterEO.getAisheCode());
                session.save(userMasterEO);
                UserActionLog useractionlog = new UserActionLog();
                useractionlog.setActionId(12);
                useractionlog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                if (null != userMasterEO.getAisheCode()) {

                    useractionlog.setInstitutionId(userMasterEO.getAisheCode());
                    useractionlog.setInstitutionId(userMasterEO.getAisheCode().trim().split("\\s*-\\s*")[1]);
                    useractionlog.setInstitutionType(userMasterEO.getAisheCode().trim().split("\\s*-\\s*")[0]);
                }
                useractionlog.setIpAddress(IpAddressProvider.getClientIpAddr(request));
                useractionlog.setSurveyYear(0);
                if (null != userInfo) {
                    useractionlog.setUserId(userInfo.getUsername());
                } else {
                    useractionlog.setUserId(userMasterEO.getUserId());
                }
                session.save(useractionlog);

                InstituteDetailEO id = getInstituteDetail(userMasterEO.getAisheCode());
                if (id != null) {
                    id.setNodalOfficerName(userMasterEO.getName());
                    id.setNodalOfficerEmail(userMasterEO.getEmailId());
                    id.setNodalOfficerGender(userMasterEO.getGenderId());
                    id.setNodalOfficerMobile(userMasterEO.getPhoneMobile());
                    id.setNodalOfficerTelephone(userMasterEO.getPhoneLandline());
                    id.setAddressLine1(userMasterEO.getAddressLine1());
                    id.setAddressLine2(userMasterEO.getAddressLine2());
                    id.setCity(userMasterEO.getCity());
                    session.saveOrUpdate(id);
                }
                tx.commit();
            } else {
                UserMasterDetailEO alreadyRegister = fetchUserMasterAlready(userMasterEO.getAisheCode(), userMasterEO.getStatusId());
                if (alreadyRegister != null) {
                    UserMasterLogDetailEO userMasterLog = new UserMasterLogDetailEO();
                    BeanUtils.copyProperties(alreadyRegister, userMasterLog);
                    session.save(userMasterLog);
                    session.delete(alreadyRegister);
                }
                UserMasterDetailEO userMaster = new UserMasterDetailEO();
                BeanUtils.copyProperties(userMasterEO, userMaster);
                userMaster.setBcryptPassword(bcrypt.encode(newp));
                userMaster.setUserPasswordInHash("999");
                userMaster.setStatusId(userMasterEO.getStatusId());
                userMaster.setRegistrationDatetime(DateUtils.obtainCurrentTimeStamp());
                userMaster.setAisheCode(userMasterEO.getAisheCode());
                userMaster.setStateCode(userMasterEO.getStateCode());
                userMaster.setAddressStateCode(userMasterEO.getStateCode());
                userMaster.setAddressDistrictCode(userMasterEO.getAddressDistrictCode());
                userMaster.setAddressLine1(userMasterEO.getAddressLine1());
                userMaster.setAddressLine2(userMasterEO.getAddressLine2());
                session.save(userMaster);
                UserActionLog useractionlog = new UserActionLog();
                useractionlog.setActionId(12);
                useractionlog.setActionTime(DateUtils.obtainCurrentTimeStamp());
                if (null != userMasterEO.getAisheCode()) {
                    useractionlog.setInstitutionId(userMasterEO.getAisheCode());
                    useractionlog.setInstitutionId(userMasterEO.getAisheCode().trim().split("\\s*-\\s*")[1]);
                    useractionlog.setInstitutionType(userMasterEO.getAisheCode().trim().split("\\s*-\\s*")[0]);
                }
                useractionlog.setIpAddress(IpAddressProvider.getClientIpAddr(request));
                useractionlog.setSurveyYear(0);
                if (null != userInfo) {
                    useractionlog.setUserId(userInfo.getUsername());
                } else {
                    useractionlog.setUserId(userMasterEO.getUserId());
                }
                session.save(useractionlog);
                InstituteDetailEO id = getInstituteDetail(userMasterEO.getAisheCode());
                if (id != null) {
                    id.setNodalOfficerName(userMasterEO.getName());
                    id.setNodalOfficerEmail(userMasterEO.getEmailId());
                    id.setNodalOfficerGender(userMasterEO.getGenderId());
                    id.setNodalOfficerMobile(userMasterEO.getPhoneMobile());
                    id.setNodalOfficerTelephone(userMasterEO.getPhoneLandline());
                    id.setAddressLine1(userMasterEO.getAddressLine1());
                    id.setAddressLine2(userMasterEO.getAddressLine2());
                    id.setCity(userMasterEO.getCity());
                    session.saveOrUpdate(id);
                }
                tx.commit();
            }
            return userMasterEO;
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


    private UserMasterDetailEO fetchUserMasterAlready(String aisheCode, Integer statusId) {
        logger.info("RegisterationDaoImpl : getUserRegistration method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterDetailEO> criteriaQuery = builder.createQuery(UserMasterDetailEO.class);
            Root<UserMasterDetailEO> root = criteriaQuery.from(UserMasterDetailEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (aisheCode != null) {
                predicates.add(builder.equal(root.get("aisheCode"), aisheCode));
            }
            predicates.add(builder.equal(root.get("statusId"), 2));
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterDetailEO> q = session.createQuery(criteriaQuery);
            UserMasterDetailEO list = q.getSingleResult();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;

    }

    @Override
    public UserMasterDetailEO getUserApproveUserDTO(ApproveDisapproveUserDTO approveUserDTO) {
        logger.info("RegisterationDaoImpl : getUserRegistration method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterDetailEO> criteriaQuery = builder.createQuery(UserMasterDetailEO.class);
            Root<UserMasterDetailEO> root = criteriaQuery.from(UserMasterDetailEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (approveUserDTO.getAisheCode() != null) {
                predicates.add(builder.equal(root.get("aisheCode"), approveUserDTO.getAisheCode()));
            }
            if (approveUserDTO.getRoleId() != null) {
                predicates.add(builder.equal(root.get("roleId"), approveUserDTO.getRoleId()));
            }
            predicates.add(builder.equal(root.get("isApproved"), 1));
            predicates.add(builder.equal(root.get("statusId"), 2));
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterDetailEO> q = session.createQuery(criteriaQuery);
            List<UserMasterDetailEO> list = q.getResultList();
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
    public List<RegisteredUserDTO> getRegisteredUserRequest(Integer roleId, Integer surveyYear, Integer userStatus,
                                                            Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode,
                                                            String universityId, String aisheCode, Boolean isApproved, SortBy sortBy, String instituteType,
                                                            String fromDate, String toDate, String searchText, String categoryType) {
        logger.info("RegisterationDaoImpl : getRegisteredUser method invoked :");
        Session session = sessionFactory.openSession();
        List<RegisteredUserDTO> userDTOS = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        try {
            if (null != instituteType) {
                if (null == formUploadStatus) {
                    queryBuilder = new StringBuilder();
                    queryBuilder.append(NativeQuerySystem.OTHERS_USER_MASTER_REQUEST);
                }
            }
            queryBuilder = new StringBuilder(fillParameter(queryBuilder.toString(), surveyYear, formUploadStatus));
            String finalQuery = setParameterInQuery(queryBuilder.toString(), roleId, surveyYear, userStatus, deoRoleId, dcfStatus, formUploadStatus, stateCode, universityId, aisheCode, isApproved, instituteType, fromDate, toDate, searchText, categoryType, 2);
            Query query = session.createNativeQuery(finalQuery + " order by um.user_id");
            query.setParameter("roleId", roleId);
            @SuppressWarnings("unchecked")
            List<Object[]> userListData = (List<Object[]>) query.getResultList();
            userDTOS = bindObjectInTODto(userListData, userDTOS, formUploadStatus, surveyYear);
            return userDTOS;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return userDTOS;
    }

    @Override
    public List<RegisteredUserDTO> getRegisteredUserDisapprovedInactive(Integer roleId, Integer surveyYear,
                                                                        Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode,
                                                                        String universityId, String aisheCode, Boolean isApproved, SortBy sortBy, String instituteType,
                                                                        String fromDate, String toDate, String searchText, String categoryType) {
        logger.info("RegisterationDaoImpl : getRegisteredUser method invoked :");
        Session session = sessionFactory.openSession();
        List<RegisteredUserDTO> userDTOS = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder();
        try {
            if (null != instituteType) {
                if (null == formUploadStatus) {
                    queryBuilder = new StringBuilder();
                    queryBuilder.append(NativeQuerySystem.OTHERS_USER_MASTER_LOGS);
                }
            } else if (roleId != null && roleId == 1 && instituteType == null) {
                queryBuilder = new StringBuilder();
                queryBuilder.append(NativeQuerySystem.OTHERS_USER_MASTER_LOGS_ROLEID_1);
            }
            queryBuilder = new StringBuilder(fillParameter(queryBuilder.toString(), surveyYear, formUploadStatus));
            String finalQuery = setParameterInQuery(queryBuilder.toString(), roleId, surveyYear, userStatus, deoRoleId, dcfStatus, formUploadStatus, stateCode, universityId, aisheCode, isApproved, instituteType, fromDate, toDate, searchText, categoryType, 2);
            Query query = session.createNativeQuery(finalQuery + " order by um.user_id");
            query.setParameter("roleId", roleId);
            @SuppressWarnings("unchecked")
            List<Object[]> userListData = (List<Object[]>) query.getResultList();
            userDTOS = bindObjectInTODto(userListData, userDTOS, formUploadStatus, surveyYear);
            return userDTOS;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return userDTOS;
    }

    @Override
    public Boolean updateMoeViewUser(String userId, Boolean activeMoeUser) {
        logger.info("RegisterationDaoImpl : updateMoeViewUser method invoked :");
        Session session = sessionFactory.openSession();
        Session session1 = sessionFactory.openSession();
        Transaction tx = null;
        Transaction tx1 = session1.beginTransaction();
        try {
            UserMasterDetailNewEO userMaster = fetchUserMasterDetailEO(userId);
            UserMasterDetailNewEO userMasteractive = fetchUserMasterDetailMoeActiveEO();
            tx = session.beginTransaction();
            if (userMaster != null) {
                if (userMasteractive != null && !userMasteractive.getUserId().equals(userMaster.getUserId())) {
                    userMasteractive.setIsMoeDisplayUser(false);
                    session1.saveOrUpdate(userMasteractive);
                    tx1.commit();
                }
                userMaster.setIsMoeDisplayUser(activeMoeUser);
                session.saveOrUpdate(userMaster);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive() || tx1 != null && tx1.isActive()) {
                    tx.rollback();
                    tx1.rollback();
                }
            } catch (Exception trEx) {
                e.printStackTrace();
            }
        } finally {
            session.close();
            session1.close();
        }
        return null;
    }

    private UserMasterDetailNewEO fetchUserMasterDetailMoeActiveEO() {
        logger.info("RegisterationDaoImpl : UserMasterRequestDetailEO method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterDetailNewEO> criteriaQuery = builder.createQuery(UserMasterDetailNewEO.class);
            Root<UserMasterDetailNewEO> root = criteriaQuery.from(UserMasterDetailNewEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(builder.equal(root.get("isMoeDisplayUser"), true));
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UserMasterDetailNewEO> q = session.createQuery(criteriaQuery);
            List<UserMasterDetailNewEO> list = q.getResultList();
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
    public UserMasterLogDetailEO getUserMasterLog(String userId) {
        logger.info("RegisterationDaoImpl : getUserRegistration method invoked :");
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" from UserMasterLogDetailEO where userId= :userId");
            Query query = session.createQuery(queryBuilder.toString());
            query.setParameter("userId", userId);
            List<UserMasterLogDetailEO> list = query.getResultList();
            Integer lsy = null;
            Integer minlsy = null;
            if (list.get(0).getAisheCode() != null) {
                String aisheCode = list.get(0).getAisheCode();
                String[] splitted = aisheCode.trim().split("\\s*-\\s*");
                String instituteType = splitted[0];
                String instituteId = splitted[1];
                Integer openSurvey = null;
                openSurvey = (Integer) session.createNativeQuery("select max(survey_year) from public.survey_master where end_date>now()").uniqueResult();
                if (openSurvey == null) {
                    openSurvey = 0;
                }
                if (instituteType.equals("C")) {
                    lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                            + " and institute_type ='C' and survey_year<" + openSurvey + "").uniqueResult();
                    if (lsy != null) {
                        minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                + " and institute_type ='C' and survey_year<" + lsy + " ").uniqueResult();
                    }
                }
                if (instituteType.equals("S")) {
                    lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                            + " and institute_type ='S' and survey_year<" + openSurvey + "").uniqueResult();
                    if (lsy != null) {
                        minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                + " and institute_type ='S' and survey_year<" + lsy + "").uniqueResult();
                    }
                }
                if (instituteType.equals("U")) {
                    lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                            + " and institute_type ='U' and survey_year<" + openSurvey + "").uniqueResult();
                    if (lsy != null) {
                        minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                + " and institute_type ='U' and survey_year<" + lsy + "").uniqueResult();
                    }
                }
//	                if(lsy==null) {
//	                	lsy=openSurvey;
//	                }
            }
            UserMasterLogDetailEO usermaster = new UserMasterLogDetailEO();
            BeanUtils.copyProperties(list.get(0), usermaster);
            usermaster.setLSY(lsy);
            usermaster.setMinlsy(minlsy);
            if (usermaster.getAisheCode() != null) {
                InstituteDetailEO instituteDetail = getInstituteDetail(usermaster.getAisheCode());
                if (instituteDetail != null) {
                    if (instituteDetail.getNodalOfficerName() != null) {
                        usermaster.setFirstName(instituteDetail.getNodalOfficerName());
                    }
                    if (instituteDetail.getNodalOfficerEmail() != null) {
                        usermaster.setEmailId(instituteDetail.getNodalOfficerEmail());
                    }
                    if (instituteDetail.getNodalOfficerMobile() != null) {
                        usermaster.setPhoneMobile(instituteDetail.getNodalOfficerMobile());
                    }
                }
            }
            if (usermaster != null && usermaster.getRoleId() != null) {
                usermaster.setRoleName((String) session.createNativeQuery("select role_name from ref_user_role_master where role_id =" + usermaster.getRoleId() + "").uniqueResult());
            }
            if (list.size() > 0) {
                return usermaster;//list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;

    }

    @Override
    public List<UserMasterRequestDetailEO> getExpiredUserMasterRequest(String aisheCode) {
        logger.info("RegisterationDaoImpl : getExpiredUserMasterRequest method invoked :");
        try (Session session = sessionFactory.openSession()) {
//            if (Boolean.FALSE.equals(CommanObjectOperation.stringValidate(aisheCode))) {
//                return session.createNativeQuery("select * FROM public.user_master_request WHERE aishe_Code is not null and  registration_datetime < CURRENT_TIMESTAMP - INTERVAL '90 days' ", UserMasterRequestDetailEO.class)
//                        .getResultList();
//            } else {
            return session.createNativeQuery("SELECT DISTINCT on (aishe_Code) * FROM public.user_master_request WHERE aishe_Code =:aisheCode ORDER BY aishe_Code, registration_datetime DESC ", UserMasterRequestDetailEO.class).setParameter("aisheCode", aisheCode.toUpperCase())
                    .getResultList();
            //   }
        } catch (Exception e) {
            logger.error(" RegisterationDaoImpl : getExpiredUserMasterRequest error occurred due to {}  this exception ", e.getMessage());
        }
        return null;
    }

    @Override
    public UserMasterLogDetailEO getUserLogDetail(String userId) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterLogDetailEO> query = builder
                    .createQuery(UserMasterLogDetailEO.class);
            Root<UserMasterLogDetailEO> allData = query.from(UserMasterLogDetailEO.class);
            query.where(builder.and(builder.equal(allData.get("aisheCode"), userId)
                    , builder.and(builder.notEqual(allData.get("roleId"), 16)
                    )));
            UserMasterLogDetailEO university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public UserMasterRequestDetailEO getUserRequestDetail(String userId) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterRequestDetailEO> query = builder
                    .createQuery(UserMasterRequestDetailEO.class);
            Root<UserMasterRequestDetailEO> allData = query.from(UserMasterRequestDetailEO.class);
            query.where(builder.and(builder.equal(allData.get("aisheCode"), userId)
                    , builder.and(builder.notEqual(allData.get("roleId"), 16))
            ));
            UserMasterRequestDetailEO university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public UserMasterEO getUserRegistrationAisheCode(String aisheCode) {
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserMasterEO> query = builder
                    .createQuery(UserMasterEO.class);
            Root<UserMasterEO> allData = query.from(UserMasterEO.class);
            query.where(builder.and(builder.equal(allData.get("aisheCode"), aisheCode)
                    , builder.and(builder.notEqual(allData.get("roleId"), 16))
            ));
            UserMasterEO university = session.createQuery(query).getSingleResult();
            return university;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<ActiveInstituteInactiveUser> institutionInactiveUser(InstitutionType institutionType, Integer surveyYear) {
        logger.info("RegisterationDaoImpl : institutionInactiveUser method invoked :");
        try (Session session = sessionFactory.openSession()) {
            StringBuilder builder = new StringBuilder("WITH max_approved_nodal AS (SELECT  aishe_code,name, gender,phone_mobile, email_id, phone_landline,status_id, ROW_NUMBER() OVER (PARTITION BY aishe_code ORDER BY approved_datetime DESC) AS rn  FROM  user_master_log WHERE  1=1  \n" +
                    "                     ), \n" +
                    "  max_unapproved_nodal AS(SELECT  aishe_code,name, gender,phone_mobile, email_id, phone_landline,status_id, ROW_NUMBER() OVER (PARTITION BY aishe_code ORDER BY registration_datetime DESC) AS rn  FROM  user_master_request WHERE  aishe_code is not null \n" +
                    "  ) \n" +
                    "     SELECT 'U' as institution_type, 'U-' || u.id AS aishe_code,u.name,u.statecode AS state_code,rs.name AS state_name,u.district_code,rd.name AS district_name, \n" +
                    "  case when  uml.name is null or uml.name='NULL' then uml1.name else uml.name end  AS nodal_officer_name, \n" +
                    "  case when uml.name is null or uml.name='NULL' then uml1.gender else uml.gender end AS nodal_officer_gender, \n" +
                    "  case when  uml.name is null or uml.name='NULL' then uml1.phone_mobile else uml.phone_mobile end AS nodal_officer_mobile, \n" +
                    "  case when  uml.name is null or uml.name='NULL' then uml1.email_id else uml.email_id end AS nodal_officer_email,  \n" +
                    "  case when uml.name is null or uml.name='NULL' then uml1.phone_landline else uml.phone_landline end AS nodal_officer_telephone, \n" +
                    "  case when uml.name is null or uml.name='NULL' then rg1.name else rg.name end AS nodal_officer_gender_name, \n" +
                    "  case when uml.name is null or uml.name='NULL' then rus1.status else rus.status end AS nodal_officer_status,\n" +
                    "  u.type_id,rut.type as type ,null as university_aishe_code,null as university_name\n" +
                    "  FROM ref_university u LEFT JOIN user_master um1 ON (um1.aishe_code = 'U-' || u.id) \n" +
                    "  LEFT JOIN max_approved_nodal uml ON (uml.aishe_code = 'U-' || u.id AND uml.rn = 1) \n" +
                    "  LEFT JOIN max_unapproved_nodal uml1 ON (uml1.aishe_code = 'U-' || u.id AND uml1.rn = 1)  \n" +
                    "  LEFT JOIN  ref_gender rg ON (rg.id=uml.gender) \n" +
                    "  LEFT JOIN  ref_gender rg1 ON (rg1.id=uml1.gender) \n" +
                    "  LEFT JOIN ref_state rs ON rs.st_code = u.statecode \n" +
                    "  left join public.ref_university_type  rut on (rut.id=u.type_id) \n" +
                    "  LEFT JOIN ref_user_status rus on rus.id=uml.status_id\n" +
                    "  LEFT JOIN ref_user_status rus1 on rus1.id=uml1.status_id\n" +
                    "  LEFT JOIN ref_district rd ON rd.dist_code = u.district_code \n" +
                    "  WHERE u.survey_year =:surveyYear\n" +
                    "  AND um1.aishe_code IS NULL and ('U'=:institutionType or 'ALL'=:institutionType) \n" +
                    "   \n" +
                    "  UNION ALL \n" +
                    "   \n" +
                    "  SELECT  'C' as institution_type,'C-' || c.id AS aishe_code,c.name,c.state_code,rs.name AS state_name,c.district_code,rd.name AS district_name, \n" +
                    "  case when  uml.name is null or uml.name='NULL' then uml1.name else uml.name end  AS nodal_officer_name, \n" +
                    "  case when uml.name is null or uml.name='NULL' then uml1.gender else uml.gender end AS nodal_officer_gender, \n" +
                    "  case when  uml.name is null or uml.name='NULL' then uml1.phone_mobile else uml.phone_mobile end AS nodal_officer_mobile, \n" +
                    "  case when  uml.name is null or uml.name='NULL' then uml1.email_id else uml.email_id end AS nodal_officer_email,  \n" +
                    "  case when uml.name is null or uml.name='NULL' then uml1.phone_landline else uml.phone_landline end AS nodal_officer_telephone, \n" +
                    "  case when uml.name is null or uml.name='NULL' then rg1.name else rg.name end AS nodal_officer_gender_name , \n" +
                    "  case when uml.name is null or uml.name='NULL' then rus1.status else rus.status end AS nodal_officer_status,\n" +
                    "  c.type_id,rut.type as type ,'U-'||c.university_id as university_aishe_code,u.name as university_name\n" +
                    "  FROM  college c LEFT JOIN user_master um1 ON (um1.aishe_code = 'C-' || c.id) \n" +
                    "  LEFT JOIN ref_university u on (u.id=c.university_id and u.survey_year=c.survey_year)\n" +
                    "  LEFT JOIN max_approved_nodal uml ON (uml.aishe_code = 'C-' || c.id AND uml.rn = 1) \n" +
                    "  LEFT JOIN max_unapproved_nodal uml1 ON (uml1.aishe_code = 'C-' || c.id AND uml1.rn = 1) \n" +
                    "  LEFT JOIN ref_gender rg ON (rg.id=uml.gender) \n" +
                    "  LEFT JOIN ref_gender rg1 ON (rg1.id=uml1.gender) \n" +
                    "  LEFT JOIN ref_state rs ON rs.st_code = c.state_code \n" +
                    "  LEFT JOIN ref_district rd ON rd.dist_code = c.district_code \n" +
                    "  LEFT JOIN REF_UNIVERSITY_COLLEGE_TYPE  RuT ON (RuT.ID = C.TYPE_ID)\n" +
                    "  LEFT JOIN ref_user_status rus on rus.id=uml.status_id\n" +
                    "  LEFT JOIN ref_user_status rus1 on rus1.id=uml1.status_id\n" +
                    "  WHERE  c.survey_year = :surveyYear AND um1.aishe_code IS NULL and ('C'=:institutionType or 'ALL'=:institutionType)  \n" +
                    "  \n" +
                    "  UNION  ALL   \n" +
                    "  \n" +
                    "  SELECT  'S' as institution_type,'S-' || s.id AS aishe_code, s.name,s.statecode AS state_code,rs.name AS state_name,s.district_code,rd.name AS district_name, \n" +
                    "  case when  uml.name is null or uml.name='NULL' then uml1.name else uml.name end  AS nodal_officer_name, \n" +
                    "  case when uml.name is null or uml.name='NULL' then uml1.gender else uml.gender end AS nodal_officer_gender, \n" +
                    "  case when  uml.name is null or uml.name='NULL' then uml1.phone_mobile else uml.phone_mobile end AS nodal_officer_mobile, \n" +
                    "  case when  uml.name is null or uml.name='NULL' then uml1.email_id else uml.email_id end AS nodal_officer_email,  \n" +
                    "  case when uml.name is null or uml.name='NULL' then uml1.phone_landline else uml.phone_landline end AS nodal_officer_telephone, \n" +
                    "  case when uml.name is null or uml.name='NULL' then rg1.name else rg.name end AS nodal_officer_gender_name , \n" +
                    "  case when uml.name is null or uml.name='NULL' then rus1.status else rus.status end AS nodal_officer_status,\n" +
                    "  cast(s.statebodyid as text) as type_id,rut.type as type ,null as university_aishe_code,null as university_name\n" +
                    "  FROM ref_standalone_institution s LEFT JOIN user_master um1 ON (um1.aishe_code = 'S-' || s.id) \n" +
                    "  LEFT JOIN  max_approved_nodal uml ON (uml.aishe_code = 'S-' || s.id AND uml.rn = 1)   \n" +
                    "  LEFT JOIN max_unapproved_nodal uml1 ON (uml1.aishe_code = 'S-' || s.id AND uml1.rn = 1) \n" +
                    "  LEFT JOIN  ref_gender rg ON (rg.id=uml.gender) \n" +
                    "  LEFT JOIN  ref_gender rg1 ON (rg1.id=uml1.gender) \n" +
                    "  LEFT JOIN  ref_state rs ON rs.st_code = s.statecode \n" +
                    "  left join ref_state_body  rut on rut.id=s.statebodyid  \n" +
                    "  LEFT JOIN ref_district rd ON rd.dist_code = s.district_code \n" +
                    "  LEFT JOIN ref_user_status rus on rus.id=uml.status_id\n" +
                    "  LEFT JOIN ref_user_status rus1 on rus1.id=uml1.status_id\n" +
                    "  WHERE s.survey_year =:surveyYear and um1.aishe_code is null and ('S'=:institutionType or 'ALL'=:institutionType) \n" +
                    "  ORDER BY institution_type desc,name asc");
            return session.createNativeQuery(builder.toString(), ActiveInstituteInactiveUser.class)
                    .setParameter("institutionType", institutionType.getType())
                    .setParameter("surveyYear", surveyYear).getResultList();
        } catch (Exception e) {
            logger.error(" RegisterationDaoImpl : institutionInactiveUser error occurred due to {}  this exception ", e.getMessage());
        }
        return null;
    }

    @Override
    public Integer getMaxSurveyYearFromMaster() {
        try (Session session = sessionFactory.openSession()) {
            return (Integer) session.createNativeQuery("SELECT MAX(survey_year) FROM ref_standalone_institution\n" +
                    "union\n" +
                    "SELECT MAX(survey_year) FROM ref_university\n" +
                    "union\n" +
                    "SELECT MAX(survey_year) FROM college").getSingleResult();
        } catch (Exception e) {
            logger.error(" RegisterationDaoImpl : getMaxSurveyYearFromMaster error occurred due to {}  this exception ", e.getMessage());
        }
        return null;
    }

}