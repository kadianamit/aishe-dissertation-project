package aishe.gov.in.dao;

import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.masterseo.RefDistrict;
import aishe.gov.in.masterseo.RefState;
import aishe.gov.in.masterseo.UserToken;
import aishe.gov.in.masterseo.UserTokenNew;
import aishe.gov.in.mastersvo.AisheCodeDetailsVo;
import aishe.gov.in.mastersvo.BisagResponseVO;
import aishe.gov.in.mastersvo.CollegeCountUniversityWise;
import aishe.gov.in.mastersvo.CreatedInstituteDetailsVo;
import aishe.gov.in.mastersvo.UserDetailsVo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserTokenDaoImpl implements UserTokenDao {


    @Autowired
    private SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(UserTokenDaoImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Boolean logOutWebDcf(String userId) {
        logger.info("MasterDaoImpl : saveOrUpdateUniversityRegulatoryInformation method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            UserTokenNew userToken = fetchUsetDetails(userId);
            tx = session.beginTransaction();
            userToken.setIsWebdcfActive(false);
            session.update(userToken);
            tx.commit();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                logger.error("Couldn’t roll back transaction" + trEx.getMessage());
            }
            logger.info("saveOrUpdateUniversityRegulatoryInformation Error" + e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<UserDetailsVo> userDetails(String userId) {
        logger.info("MasterDaoImpl : userDetails Method Invoked");
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(

                    "select UM.user_id,UM.role_id,UM.first_name,UM.state_code,UM.phone_mobile,UM.email_id,UM.aishe_code,\r\n" +
                            "UM.bcrypt_password,um.address_district_code,um.address_line1,um.address_line2,um.city,rs.name as sname,rd.name as dname,rurm.role_name,um.is_approved,phone_landline,std_code,UM.middle_name,UM.last_name,UM.status_id"
                            + " from user_master UM"
                            + " LEFT JOIN ref_state rs ON  rs.st_code =UM.state_code LEFT JOIN ref_district rd ON  rd.dist_code =UM.address_district_code"
                            + " LEFT JOIN ref_user_role_master rurm ON  rurm.role_id = UM.role_id"
                            + " where UM.aishe_code ='" + userId + "' and UM.status_id=2");


            Query query = session.createNativeQuery(queryBuilder.toString());

            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
            List<UserDetailsVo> postMenuList = new ArrayList<>();
            if (objectlist.isEmpty() || objectlist == null) {
                UserDetailsVo message = new UserDetailsVo();
                message.setMessage("User Id Not Exist for Aishe Code.");
                postMenuList.add(message);
                return postMenuList;
            }

            for (Object[] object : objectlist) {
                UserDetailsVo postMenuData = new UserDetailsVo();
                if (object[0] != null) {
                    postMenuData.setUserId(object[0] + "");
                }
                if (object[1] != null) {
                    postMenuData.setRoleId(Integer.parseInt(object[1] + ""));
                }
                if (object[2] != null) {
                    postMenuData.setFirstName(object[2] + "");
                }
                if (object[3] != null) {
                    postMenuData.setStateCode(object[3] + "");
                }
                if (object[4] != null) {
                    postMenuData.setMobile(object[4] + "");
                }
                if (object[5] != null) {
                    postMenuData.setEmail(object[5] + "");
                }
                if (object[6] != null) {
                    postMenuData.setAisheCode(object[6] + "");
                    String[] splitted = postMenuData.getAisheCode().trim().split("\\s*-\\s*");
                    String universityId = splitted[1];
                    String type = splitted[0];
                    String instituteName = null;
                    if (type.equals("C")) {
                        instituteName = fetcCollegeInstituteNameFromAisheCode(universityId);
                    }
                    if (type.equals("S")) {
                        instituteName = fetcStandaloneInstituteNameFromAisheCode(universityId);
                    }
                    if (type.equals("U")) {
                        instituteName = fetcUniversityInstituteNameFromAisheCode(universityId);
                    }
                    postMenuData.setInstituteName(instituteName);
                }
                if (postMenuData.getAisheCode() != null) {
                    String aisheCode = postMenuData.getAisheCode();
                    String[] splitted = aisheCode.trim().split("\\s*-\\s*");
                    String instituteType = splitted[0];
                    String instituteId = splitted[1];
                    Integer lsy = null;
                    Integer minlsy = null;
                    Integer openSurvey = null;
                    String universityId;
                    openSurvey = (Integer) session.createNativeQuery("select max(survey_year) from public.survey_master where end_date>now()").uniqueResult();

                    if (openSurvey == null) {
                        openSurvey = 0;
                    }
                    if (instituteType.equals("C")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                + " and institute_type ='C' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                    + " and institute_type ='C' and survey_year<" + lsy + " ").uniqueResult();
                        }
                        universityId = (String) session.createNativeQuery("select university_id from public.college where id='" + instituteId + "' and survey_year in (select max(survey_year) from public.college)")
                                .uniqueResult();
                        postMenuData.setUniversityId(universityId);

                        String universityName = (String) session.createNativeQuery("select name from public.ref_university where id='" + universityId + "' and survey_year in (select max(survey_year) from public.ref_university)")
                                .uniqueResult();
                        postMenuData.setUniversityName(universityName);
                    }
                    if (instituteType.equals("S")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                + " and institute_type ='S' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                    + " and institute_type ='S' and survey_year<" + lsy + "").uniqueResult();
                        }
                    }
                    if (instituteType.equals("U")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                + " and institute_type ='U' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                    + " and institute_type ='U' and survey_year<" + lsy + "").uniqueResult();
                        }
                    }
//                    if(lsy==null) {
//                    	lsy=openSurvey;
//                    }
                    postMenuData.setLSY(lsy);
                    postMenuData.setMinlsy(minlsy);
                    postMenuData.setMessage("Elligible");
                    if (postMenuData.getAisheCode() != null) {
                        switch (instituteType) {
                            case "U":
                                if (postMenuData.getAisheCode() != null) {
                                    String isDcfApplicable = (String) session.createNativeQuery("SELECT id FROM PUBLIC.ref_university WHERE ID='" + instituteId + "' and survey_year=2021"
                                            + " AND is_dcf_applicable IS true").uniqueResult();
                                    if (isDcfApplicable == null) {
                                        postMenuData.setMessage("You Are Not Elligible For This Survey");
                                    }
                                }
                                break;
                            case "C":
                                if (postMenuData.getAisheCode() != null) {
                                    Integer isDcfApplicable = (Integer) session.createNativeQuery("SELECT id FROM PUBLIC.college WHERE ID='" + instituteId + "' and survey_year=2021"
                                            + " AND is_dcf_applicable IS true").uniqueResult();
                                    if (isDcfApplicable == null) {
                                        postMenuData.setMessage("You Are Not Elligible For This Survey");
                                    }
                                }
                                break;
                            case "S":
                                if (postMenuData.getAisheCode() != null) {
                                    Integer isDcfApplicable = (Integer) session.createNativeQuery("SELECT id FROM PUBLIC.ref_standalone_institution WHERE ID='" + instituteId + "' and survey_year=2021"
                                            + "").uniqueResult();
                                    if (isDcfApplicable == null) {
                                        postMenuData.setMessage("You Are Not Elligible For This Survey");
                                    }
                                }
                                break;
                        }
                    }
                }
                // if (object[7] != null) {
                //     postMenuData.setBcryptPassword(object[7] + "");
                // }
                if (object[8] != null) {
                    postMenuData.setDistrictCode(object[8] + "");
                }
                if (object[9] != null) {
                    postMenuData.setAddressLine1(object[9] + "");
                }
                if (object[10] != null) {
                    postMenuData.setAddressLine2(object[10] + "");
                }
                if (object[11] != null) {
                    postMenuData.setCityName(object[11] + "");
                }
                if (object[12] != null) {
                    postMenuData.setStateName(object[12] + "");
                }
                if (object[13] != null) {
                    postMenuData.setDistrictName(object[13] + "");
                }
                if (object[14] != null) {
                    postMenuData.setRoleName(object[14] + "");
                }
                if (object[15] != null) {
                    postMenuData.setIsApproved(Integer.parseInt(object[15] + ""));
                }
                if (object[16] != null) {
                    postMenuData.setLandline(object[16] + "");
                }
                if (object[17] != null) {
                    postMenuData.setStdCode(object[17] + "");
                }
                if (object[18] != null) {
                    postMenuData.setMiddleName(object[18] + "");
                }
                if (object[19] != null) {
                    postMenuData.setLastName(object[19] + "");
                }
                if (object[20] != null) {
                    postMenuData.setStatusId(Integer.parseInt(object[20] + ""));
                }


                if (postMenuData.getMessage().equals("You Are Not Elligible For This Survey")) {
                    postMenuList.add(postMenuData);
                    return postMenuList;
                }
                postMenuList.add(postMenuData);
            }
            return postMenuList;
        } catch (Exception e) {
            logger.info("findAllNodalOfficerRole Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    private List<UserDetailsVo> findByAisheCode(String aisheCodes, int i) {
        logger.info("MasterDaoImpl : userDetails Method Invoked");
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(

                    "select UM.user_id,UM.role_id,UM.first_name,UM.state_code,UM.phone_mobile,UM.email_id,UM.aishe_code,\r\n" +
                            "UM.bcrypt_password,um.address_district_code,um.address_line1,um.address_line2,um.city,rs.name as sname,rd.name as dname,rurm.role_name,um.is_approved,phone_landline,std_code,UM.middle_name,UM.last_name,UM.status_id from user_master UM"
                            + " LEFT JOIN ref_state rs ON  rs.st_code =UM.state_code LEFT JOIN ref_district rd ON  rd.dist_code =UM.address_district_code"
                            + " LEFT JOIN ref_user_role_master rurm ON  rurm.role_id = UM.role_id"
                            + " where UM.aishe_code ='" + aisheCodes + "' and UM.status_id=3");


            Query query = session.createNativeQuery(queryBuilder.toString());

            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
            List<UserDetailsVo> postMenuList = new ArrayList<>();
            for (Object[] object : objectlist) {
                UserDetailsVo postMenuData = new UserDetailsVo();
                if (object[0] != null) {
                    postMenuData.setUserId(object[0] + "");
                }
                if (object[1] != null) {
                    postMenuData.setRoleId(Integer.parseInt(object[1] + ""));
                }
                if (object[2] != null) {
                    postMenuData.setFirstName(object[2] + "");
                }
                if (object[3] != null) {
                    postMenuData.setStateCode(object[3] + "");
                }
                if (object[4] != null) {
                    postMenuData.setMobile(object[4] + "");
                }
                if (object[5] != null) {
                    postMenuData.setEmail(object[5] + "");
                }
                if (object[6] != null) {
                    postMenuData.setAisheCode(object[6] + "");
                    String[] splitted = postMenuData.getAisheCode().trim().split("\\s*-\\s*");
                    String universityId = splitted[1];
                    String type = splitted[0];
                    String instituteName = null;
                    String statebodyid = null;
                    if (type.equals("C")) {
                        instituteName = fetcCollegeInstituteNameFromAisheCode(universityId);
                    }
                    if (type.equals("S")) {
                        instituteName = fetcStandaloneInstituteNameFromAisheCode(universityId);
                        statebodyid = fetcStandaloneInstituteStateBodyFromAisheCode(universityId);
                    }
                    if (type.equals("U")) {
                        instituteName = fetcUniversityInstituteNameFromAisheCode(universityId);
                    }
                    postMenuData.setInstituteName(instituteName);
                    postMenuData.setBodyTypeId(statebodyid);
                }
                if (postMenuData.getAisheCode() != null) {
                    String aisheCode = postMenuData.getAisheCode();
                    String[] splitted = aisheCode.trim().split("\\s*-\\s*");
                    String instituteType = splitted[0];
                    String instituteId = splitted[1];
                    Integer lsy = null;
                    Integer minlsy = null;
                    Integer openSurvey = null;
                    String universityId;
                    openSurvey = (Integer) session.createNativeQuery("select max(survey_year) from public.survey_master where end_date>now()").uniqueResult();

                    if (openSurvey == null) {
                        openSurvey = 0;
                    }
                    if (instituteType.equals("C")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                + " and institute_type ='C' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                    + " and institute_type ='C' and survey_year<" + lsy + " ").uniqueResult();
                        }
                        universityId = (String) session.createNativeQuery("select university_id from public.college where id='" + instituteId + "' and survey_year in (select max(survey_year) from public.college)")
                                .uniqueResult();
                        postMenuData.setUniversityId(universityId);

                        String universityName = (String) session.createNativeQuery("select name from public.ref_university where id='" + universityId + "' and survey_year in (select max(survey_year) from public.ref_university)")
                                .uniqueResult();
                        postMenuData.setUniversityName(universityName);
                    }
                    if (instituteType.equals("S")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                + " and institute_type ='S' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                    + " and institute_type ='S' and survey_year<" + lsy + "").uniqueResult();
                        }
                    }
                    if (instituteType.equals("U")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                + " and institute_type ='U' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                    + " and institute_type ='U' and survey_year<" + lsy + "").uniqueResult();
                        }
                    }
//                     if(lsy==null) {
//                     	lsy=openSurvey;
//                     }
                    postMenuData.setLSY(lsy);
                    postMenuData.setMinlsy(minlsy);
                    postMenuData.setMessage("Elligible");
                    if (postMenuData.getAisheCode() != null) {
                        switch (instituteType) {
                            case "U":
                                if (postMenuData.getAisheCode() != null) {
                                    String isDcfApplicable = (String) session.createNativeQuery("SELECT id FROM PUBLIC.ref_university WHERE ID='" + instituteId + "' and survey_year=2021"
                                            + " AND is_dcf_applicable IS true").uniqueResult();
                                    if (isDcfApplicable == null) {
                                        postMenuData.setMessage("You Are Not Elligible For This Survey");
                                    }
                                }
                                break;
                            case "C":
                                if (postMenuData.getAisheCode() != null) {
                                    Integer isDcfApplicable = (Integer) session.createNativeQuery("SELECT id FROM PUBLIC.college WHERE ID='" + instituteId + "' and survey_year=2021"
                                            + " AND is_dcf_applicable IS true").uniqueResult();
                                    if (isDcfApplicable == null) {
                                        postMenuData.setMessage("You Are Not Elligible For This Survey");
                                    }
                                }
                                break;
                            case "S":
                                if (postMenuData.getAisheCode() != null) {
                                    Integer isDcfApplicable = (Integer) session.createNativeQuery("SELECT id FROM PUBLIC.ref_standalone_institution WHERE ID='" + instituteId + "' and survey_year=2021"
                                            + "").uniqueResult();
                                    if (isDcfApplicable == null) {
                                        postMenuData.setMessage("You Are Not Elligible For This Survey");
                                    }
                                }
                                break;
                        }
                    }
                }
                // if (object[7] != null) {
                //     postMenuData.setBcryptPassword(object[7] + "");
                // }
                if (object[8] != null) {
                    postMenuData.setDistrictCode(object[8] + "");
                }
                if (object[9] != null) {
                    postMenuData.setAddressLine1(object[9] + "");
                }
                if (object[10] != null) {
                    postMenuData.setAddressLine2(object[10] + "");
                }
                if (object[11] != null) {
                    postMenuData.setCityName(object[11] + "");
                }
                if (object[12] != null) {
                    postMenuData.setStateName(object[12] + "");
                }
                if (object[13] != null) {
                    postMenuData.setDistrictName(object[13] + "");
                }
                if (object[14] != null) {
                    postMenuData.setRoleName(object[14] + "");
                }
                if (object[15] != null) {
                    postMenuData.setIsApproved(Integer.parseInt(object[15] + ""));
                }
                if (object[16] != null) {
                    postMenuData.setLandline(object[16] + "");
                }
                if (object[17] != null) {
                    postMenuData.setStdCode(object[17] + "");
                }
                if (object[18] != null) {
                    postMenuData.setMiddleName(object[18] + "");
                }
                if (object[19] != null) {
                    postMenuData.setLastName(object[19] + "");
                }
                if (object[20] != null) {
                    postMenuData.setStatusId(Integer.parseInt(object[20] + ""));
                }
                if (postMenuData.getMessage().equals("You Are Not Elligible For This Survey")) {
                    postMenuList.add(postMenuData);
                    return postMenuList;
                }
                postMenuList.add(postMenuData);
            }
            return postMenuList;
        } catch (Exception e) {
            logger.info("findAllNodalOfficerRole Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    private UserTokenNew fetchUsetDetails(String userId) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" from UserTokenNew where userId=:userId ");
            Query query = session.createQuery(queryBuilder.toString());
            query.setParameter("userId", userId);
            List<UserTokenNew> list = query.getResultList();
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean validateToken(String userId, String token) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" from UserToken where userId=:userId and token=:token ");
            Query query = session.createQuery(queryBuilder.toString());
            query.setParameter("userId", userId);
            query.setParameter("token", token);
            List<UserToken> list = query.getResultList();
            if (list.size() > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean saveOrUpdate(UserToken userToken) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(userToken);
            tx.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public UserToken getUserToken(String id) {
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" from UserToken where userId=:userId ");
            Query query = session.createQuery(queryBuilder.toString());
            query.setParameter("userId", id);
            List<UserToken> list = query.getResultList();
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
    public List<UserDetailsVo> userDetailsmOU(String userId) {
        logger.info("MasterDaoImpl : userDetails Method Invoked");
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(
                    "select UM.user_id,UM.role_id,UM.first_name,UM.state_code,UM.phone_mobile,UM.email_id,UM.aishe_code,\r\n" +
                            "UM.bcrypt_password,um.address_district_code,um.address_line1,um.address_line2,um.city,rs.name as sname,rd.name as dname"
                            + ",UM.middle_name,UM.last_name from user_master UM"
                            + " LEFT JOIN ref_state rs ON  rs.st_code =UM.state_code LEFT JOIN ref_district rd ON  rd.dist_code =UM.address_district_code"
                            + " where UM.user_id ='" + userId + "' and UM.is_approved=1");


            Query query = session.createNativeQuery(queryBuilder.toString());

            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
            List<UserDetailsVo> postMenuList = new ArrayList<>();
            for (Object[] object : objectlist) {
                UserDetailsVo postMenuData = new UserDetailsVo();
                if (object[0] != null) {
                    postMenuData.setUserId(object[0] + "");
                }
                if (object[1] != null) {
                    postMenuData.setRoleId(Integer.parseInt(object[1] + ""));
                }
                if (object[2] != null) {
                    postMenuData.setFirstName(object[2] + "");
                }
                if (object[3] != null) {
                    postMenuData.setStateCode(object[3] + "");
                }
                if (object[4] != null) {
                    postMenuData.setMobile(object[4] + "");
                }
                if (object[5] != null) {
                    postMenuData.setEmail(object[5] + "");
                }
                if (object[6] != null) {
                    postMenuData.setAisheCode(object[6] + "");
                    String[] splitted = postMenuData.getAisheCode().trim().split("\\s*-\\s*");
                    String universityId = splitted[1];
                    String type = splitted[0];
                    String instituteName = null;
                    if (type.equals("C")) {
                        instituteName = fetcCollegeInstituteNameFromAisheCode(universityId);
                    }
                    if (type.equals("S")) {
                        instituteName = fetcStandaloneInstituteNameFromAisheCode(universityId);
                    }
                    if (type.equals("U")) {
                        instituteName = fetcUniversityInstituteNameFromAisheCode(universityId);
                    }
                    postMenuData.setInstituteName(instituteName);
                }
                // if (object[7] != null) {
                //     postMenuData.setBcryptPassword(object[7] + "");
                // }
                if (object[8] != null) {
                    postMenuData.setDistrictCode(object[8] + "");
                }
                if (object[9] != null) {
                    postMenuData.setAddressLine1(object[9] + "");
                }
                if (object[10] != null) {
                    postMenuData.setAddressLine2(object[10] + "");
                }
                if (object[11] != null) {
                    postMenuData.setCityName(object[11] + "");
                }
                if (object[12] != null) {
                    postMenuData.setStateName(object[12] + "");
                }
                if (object[13] != null) {
                    postMenuData.setDistrictName(object[13] + "");
                }
                if (object[14] != null) {
                    postMenuData.setMiddleName(object[14] + "");
                }
                if (object[15] != null) {
                    postMenuData.setLastName(object[15] + "");
                }
                if (postMenuData.getAisheCode() != null) {
                    String aisheCode = postMenuData.getAisheCode();
                    String[] splitted = aisheCode.trim().split("\\s*-\\s*");
                    String instituteType = splitted[0];
                    String instituteId = splitted[1];
                    Integer lsy = null;
                    Integer minlsy = null;
                    Integer openSurvey = null;
                    openSurvey = (Integer) session.createNativeQuery("select max(survey_year) from public.survey_master where end_date>now()").uniqueResult();
                    if (openSurvey == null) {
                        openSurvey = 0;
                    }
                    if (instituteType.equals("C")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                + " and institute_type ='C' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                    + " and institute_type ='C' and survey_year<" + lsy + " ").uniqueResult();
                        }
                    }
                    if (instituteType.equals("S")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                + " and institute_type ='S' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                    + " and institute_type ='S' and survey_year<" + lsy + "").uniqueResult();
                        }
                    }
                    if (instituteType.equals("U")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                + " and institute_type ='U' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                    + " and institute_type ='U' and survey_year<" + lsy + "").uniqueResult();
                        }
                    }
                    if (lsy == null) {
                        lsy = openSurvey;
                    }
                    postMenuData.setLSY(lsy);
                    postMenuData.setMinlsy(minlsy);
                }

                postMenuList.add(postMenuData);
            }
            return postMenuList;
        } catch (Exception e) {
            logger.info("findAllNodalOfficerRole Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    private String fetcUniversityInstituteNameFromAisheCode(String universityId) {
        logger.info("PersonDaoImpl : fetcUniversityInstituteNameFromAisheCode method invoked :");
        Session session1 = sessionFactory.openSession();
        String name = null;
        try {
            name = ((String) session1.createNativeQuery(" select name from public.ref_university where id ='" + universityId + "' and survey_year="
                    + "(Select max(survey_year) from ref_university WHERE id ='" + universityId + "')").getSingleResult());
            return name;
        } catch (Exception e) {
            try {
            } catch (Exception trEx) {
            }
        } finally {
            session1.close();
        }
        return name;
    }

    private String fetcStandaloneInstituteNameFromAisheCode(String universityId) {
        logger.info("PersonDaoImpl : fetcUniversityInstituteNameFromAisheCode method invoked :");
        Session session1 = sessionFactory.openSession();
        String name = null;
        try {
            name = ((String) session1.createNativeQuery(" select name from public.ref_standalone_institution where id ='" + universityId + "' and survey_year="
                    + "(Select max(survey_year) from ref_standalone_institution where id ='" + universityId + "')").getSingleResult());
            return name;
        } catch (Exception e) {
            try {
            } catch (Exception trEx) {
            }
        } finally {
            session1.close();
        }
        return name;
    }

    private String fetcCollegeInstituteNameFromAisheCode(String universityId) {
        logger.info("PersonDaoImpl : fetcUniversityInstituteNameFromAisheCode method invoked :");
        Session session1 = sessionFactory.openSession();
        String name = null;
        try {
            name = ((String) session1.createNativeQuery(" select name from public.college where id ='" + universityId + "'"
                    + " and survey_year=(Select max(survey_year) from college where id ='" + universityId + "')").getSingleResult());
            return name;
        } catch (Exception e) {
            try {
            } catch (Exception trEx) {
            }
        } finally {
            session1.close();
        }
        return name;
    }

    @Override
    public AisheCodeDetailsVo userDetailsByAisheCodeOnCurrentSurveyYear(String instituteType, String instituteId,
                                                                        Integer currentSurveyYear) {
        Session session = sessionFactory.openSession();
        try {
            AisheCodeDetailsVo details = new AisheCodeDetailsVo();
            Integer lsy = null;
            if (instituteType.equals("C")) {
                lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                        + " and institute_type ='C' and survey_year< " + currentSurveyYear + "").uniqueResult();
            }
            if (instituteType.equals("S")) {
                lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                        + " and institute_type ='S' and survey_year< " + currentSurveyYear + "").uniqueResult();
            }
            if (instituteType.equals("U")) {
                lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                        + " and institute_type ='U' and survey_year< " + currentSurveyYear + "").uniqueResult();
            }
            details.setLsy(lsy);
            if (lsy == null) {
                lsy = currentSurveyYear;
            }
            if (instituteId != null) {
                switch (instituteType) {
                    case "U":
                        if (instituteId != null) {
                            String isDcfApplicable = (String) session.createNativeQuery("SELECT id FROM PUBLIC.ref_university WHERE ID='" + instituteId + "' and survey_year=" + currentSurveyYear + ""
                                    + " AND is_dcf_applicable IS true").uniqueResult();
                            if (isDcfApplicable == null) {
                                details.setElligible("No");
                            } else {
                                details.setElligible("Yes");
                            }

                            Boolean specialPermis = (Boolean) session.createNativeQuery("SELECT special_permission FROM PUBLIC.ref_university WHERE ID='"
                                    + instituteId + "' and survey_year=" + currentSurveyYear + "").uniqueResult();
                            if (specialPermis != null) {
                                if (specialPermis) {
                                    details.setSpecialPermission(true);
                                }
                            } else {
                                details.setSpecialPermission(false);
                            }
                            
                            String stateCode = (String) session.createNativeQuery("SELECT statecode FROM PUBLIC.ref_university WHERE ID='" + instituteId + "' and survey_year=" + currentSurveyYear + ""
                                    + " AND is_dcf_applicable IS true").uniqueResult();
                            if (stateCode != null) {
                                details.setStateCode(stateCode);
                            }
                        }
                        break;
                    case "C":
                        if (instituteId != null) {
                            Integer isDcfApplicable = (Integer) session.createNativeQuery("SELECT id FROM PUBLIC.college WHERE ID='" + instituteId + "' and survey_year=" + currentSurveyYear + ""
                                    + " AND is_dcf_applicable IS true").uniqueResult();
                            if (isDcfApplicable == null) {
                                details.setElligible("No");
                            } else {
                                details.setElligible("Yes");
                            }

                            Boolean specialPermis = (Boolean) session.createNativeQuery("SELECT special_permission FROM PUBLIC.college WHERE ID='"
                                    + instituteId + "' and survey_year=" + currentSurveyYear + "").uniqueResult();
                            if (specialPermis != null) {
                                if (specialPermis) {
                                    details.setSpecialPermission(true);
                                }
                            } else {
                                details.setSpecialPermission(false);
                            }
                            
                            String stateCode = (String) session.createNativeQuery("SELECT state_code FROM PUBLIC.college WHERE ID='" + instituteId + "' and survey_year=" + currentSurveyYear + ""
                                    + " AND is_dcf_applicable IS true").uniqueResult();
                            if (stateCode != null) {
                                details.setStateCode(stateCode);
                            }
                            
                            String universityId = (String) session.createNativeQuery("SELECT university_id FROM PUBLIC.college WHERE ID='" + instituteId + "' and survey_year=" + currentSurveyYear + ""
                                    + " AND is_dcf_applicable IS true").uniqueResult();
                            if (universityId != null) {
                                details.setUniversityId(universityId);
                            }
                        }
                        break;
                    case "S":
                        if (instituteId != null) {
                            Integer isDcfApplicable = (Integer) session.createNativeQuery("SELECT id FROM PUBLIC.ref_standalone_institution WHERE ID='" + instituteId + "' and survey_year=" + currentSurveyYear + ""
                                    + "").uniqueResult();
                            if (isDcfApplicable == null) {
                                details.setElligible("No");
                            } else {
                                details.setElligible("Yes");
                            }

                            Boolean specialPermis = (Boolean) session.createNativeQuery("SELECT special_permission FROM PUBLIC.ref_standalone_institution WHERE ID='"
                                    + instituteId + "' and survey_year=" + currentSurveyYear + "").uniqueResult();
                            if (specialPermis != null) {
                                if (specialPermis) {
                                    details.setSpecialPermission(true);
                                }
                            } else {
                                details.setSpecialPermission(false);
                            }
                            
                            String stateCode = (String) session.createNativeQuery("SELECT statecode FROM PUBLIC.ref_standalone_institution WHERE ID='" + instituteId + "' and survey_year=" + currentSurveyYear + ""
                                    + " ").uniqueResult();
                            if (stateCode != null) {
                                details.setStateCode(stateCode);
                            }
                        }
                        break;
                }
            }
            details.setLsy(lsy);
            return details;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<CollegeCountUniversityWise> universityWiseCollege(Integer surveyYear) {
        logger.info("MasterDaoImpl : userDetails Method Invoked");
        Session session = sessionFactory.openSession();
        List<CollegeCountUniversityWise> finalList = new ArrayList<>();
        try {
            if (null == surveyYear) {
                surveyYear = getMaxSurveyYear();
            }
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(

                    "select count(*) as college_count,'U-'||university_id as university_code,ru.name as university_name,c.survey_year as survey_year,rs.name\r\n"
                            + "from public.college c left join public.ref_university ru ON ru.id = c.university_id left join ref_state rs on rs.st_code = ru.statecode\r\n"
                            + "WHERE c.survey_year=" + surveyYear + " and c.is_dcf_applicable is true and ru.survey_year=" + surveyYear + " group by university_id,c.survey_year,ru.name,rs.name order by college_count desc;");


            Query query = session.createNativeQuery(queryBuilder.toString());

            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
            List<CollegeCountUniversityWise> postMenuList = new ArrayList<>();
            for (Object[] object : objectlist) {
                CollegeCountUniversityWise postMenuData = new CollegeCountUniversityWise();
                if (object[0] != null) {
                    postMenuData.setCollegeCount(Long.valueOf(object[0] + ""));
                }
                if (object[1] != null) {
                    postMenuData.setUniversityId(object[1] + "");
                }
                if (object[2] != null) {
                    postMenuData.setUniversityName(object[2] + "");
                }
                if (object[3] != null) {
                    postMenuData.setSurveyYear(Integer.parseInt(object[3] + ""));
                }
                if (object[4] != null) {
                    postMenuData.setStateName(object[4] + "");
                }
                postMenuList.add(postMenuData);
            }
            /////////////////0 count university 
            StringBuilder queryBuilders = new StringBuilder();
            queryBuilders.append(

                    "select cast(0 as bigint) ,'U-'||id as university_code,ru.name as university_name,ru.survey_year as sy,rs.name\r\n"
                            + "from public.ref_university ru left join ref_state rs ON rs.st_code = ru.statecode\r\n"
                            + "WHERE ru.is_dcf_applicable is true and ru.survey_year=" + surveyYear + "\r\n"
                            + "and id not in (select university_id from public.college where survey_year =" + surveyYear + ")\r\n"
                            + "group by id,ru.survey_year,ru.name,rs.name order by id asc");


            Query querys = session.createNativeQuery(queryBuilders.toString());

            List<Object[]> objectlists = (List<Object[]>) querys.getResultList();
            List<CollegeCountUniversityWise> postMenuLists = new ArrayList<>();
            for (Object[] objects : objectlists) {
                CollegeCountUniversityWise postMenuDatas = new CollegeCountUniversityWise();
                if (objects[0] != null) {
                    postMenuDatas.setCollegeCount(Long.valueOf(objects[0] + ""));
                }
                if (objects[1] != null) {
                    postMenuDatas.setUniversityId(objects[1] + "");
                }
                if (objects[2] != null) {
                    postMenuDatas.setUniversityName(objects[2] + "");
                }
                if (objects[3] != null) {
                    postMenuDatas.setSurveyYear(Integer.parseInt(objects[3] + ""));
                }
                if (objects[4] != null) {
                    postMenuDatas.setStateName(objects[4] + "");
                }
                postMenuLists.add(postMenuDatas);
            }


            finalList.addAll(postMenuList);
            finalList.addAll(postMenuLists);

            return finalList;
        } catch (Exception e) {
            logger.info("findAllNodalOfficerRole Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    private Integer getMaxSurveyYear() {
        try (Session session = sessionFactory.openSession()) {
            return (Integer) session.createNativeQuery("select max(survey_year) from college ").getSingleResult();
        } catch (Exception e) {
            logger.info("getMaxSurveyYear Error" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<CreatedInstituteDetailsVo> createdInstituteDetailsOnBasisAisheCode(String instituteType, String instituteId) {
        logger.info("MasterDaoImpl : userDetails Method Invoked");
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            if (instituteType.equals("C")) {
                queryBuilder.append(

                        "select created_survey_year,cird.name as collname,cird.state_code as statecode,cird.district_code as distcode,college_type_id,university_id as uniid,location_id,cird.management_id,line1,line2,city,\r\n"
                                + "pin_code,pd.name,pd.designation,pd.email_id,pd.mobile,pd.landline ,rs.name as statename,rde.name as dist,ruct.type as colltype,rim.management,ru.name as university\r\n"
                                + "from college_institution_request_details cird left join request_details rd on rd.id = cird.request_id join address addr on addr.id = rd.address_id\r\n"
                                + "join person_details pd on pd.id = rd.created_by join ref_state rs on rs.st_code = cird.state_code join ref_district rde on rde.dist_code = cird.district_code\r\n"
                                + "join ref_university_college_type ruct on ruct.id = cird.college_type_id join ref_institution_management rim on rim.id = cird.management_id\r\n"
                                + "join ref_university ru on ru.id = cird.university_id\r\n"
                                + "where cird.created_college_institution_id ='" + instituteId + "' and ru.survey_year in(select max(survey_year) from ref_university)");

            }
            if (instituteType.equals("S")) {
                queryBuilder.append(

                        "select created_survey_year,cird.name as collname,cird.state_code as statecode,cird.district_code as distcode,state_body_id,university_id as uniid,location_id,cird.management_id,\r\n"
                                + "line1,line2,city,\r\n"
                                + "pin_code,pd.name,pd.designation,pd.email_id,pd.mobile,pd.landline ,rs.name as statename,rde.name as dist,ruct.type as colltype,rim.management,'NA' as university\r\n"
                                + "from standalone_institution_request_details cird left join request_details rd on rd.id = cird.request_id LEFT join address addr on addr.id = rd.address_id\r\n"
                                + "LEFT join person_details pd on pd.id = rd.created_by LEFT join ref_state rs on rs.st_code = cird.state_code LEFT join ref_district rde on rde.dist_code = cird.district_code\r\n"
                                + "LEFT join ref_state_body ruct on ruct.id = cird.state_body_id LEFT join ref_institution_management rim on rim.id = cird.management_id\r\n"
                                + "where cird.created_standalone_institution_id ='" + instituteId + "'");

            }
            Query query = session.createNativeQuery(queryBuilder.toString());

            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
            List<CreatedInstituteDetailsVo> postMenuList = new ArrayList<>();
            for (Object[] object : objectlist) {
                CreatedInstituteDetailsVo postMenuData = new CreatedInstituteDetailsVo();
                if (object[0] != null) {
                    postMenuData.setCreatedSurvey(Integer.parseInt(object[0] + ""));
                }
                if (object[1] != null) {
                    postMenuData.setInstituteName(object[1] + "");
                }
                if (object[2] != null) {
                    postMenuData.setStateCode(object[2] + "");
                }
                if (object[3] != null) {
                    postMenuData.setDistrictCode(object[3] + "");
                }
                if (object[4] != null) {
                    postMenuData.setCollegeTypeId(object[4] + "");
                }
                if (object[5] != null) {
                    postMenuData.setUniversityId(object[5] + "");
                }
                if (object[6] != null) {
                    postMenuData.setLocationId(object[6] + "");
                }
                if (object[7] != null) {
                    postMenuData.setManagementId(object[7] + "");
                }
                if (object[8] != null) {
                    postMenuData.setAddressLine1(object[8] + "");
                }
                if (object[9] != null) {
                    postMenuData.setAddressLine2(object[9] + "");
                }
                if (object[10] != null) {
                    postMenuData.setCityName(object[10] + "");
                }
                if (object[11] != null) {
                    postMenuData.setPincode(object[11] + "");
                }
                if (object[12] != null) {
                    postMenuData.setName(object[12] + "");
                }
                if (object[13] != null) {
                    postMenuData.setDesignation(object[13] + "");
                }
                if (object[14] != null) {
                    postMenuData.setEmail(object[14] + "");
                }
                if (object[15] != null) {
                    postMenuData.setMobile(object[15] + "");
                }
                if (object[16] != null) {
                    postMenuData.setLandline(object[16] + "");
                }
                if (object[17] != null) {
                    postMenuData.setStateName(object[17] + "");
                }
                if (object[18] != null) {
                    postMenuData.setDistrictName(object[18] + "");
                }
                if (object[19] != null) {
                    postMenuData.setCollegeOrStandaloneTypeName(object[19] + "");
                }
                if (object[20] != null) {
                    postMenuData.setManagementName(object[20] + "");
                }
                if (object[21] != null) {
                    postMenuData.setUniversityName(object[21] + "");
                }
                postMenuList.add(postMenuData);
            }
            return postMenuList;
        } catch (Exception e) {
            logger.info("findAllNodalOfficerRole Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<UserDetailsVo> userDetailsOnBasisAisheCodeAll(String userId) {
        logger.info("MasterDaoImpl : userDetails Method Invoked");
        Session session = sessionFactory.openSession();
        String universityId;
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(

                    "select UM.user_id,UM.role_id,UM.first_name,UM.state_code,UM.phone_mobile,UM.email_id,UM.aishe_code,\r\n" +
                            "UM.bcrypt_password,um.address_district_code,um.address_line1,um.address_line2,um.city,rs.name as sname,rd.name as dname,rurm.role_name,um.is_approved,phone_landline,std_code,UM.middle_name,UM.last_name,UM.status_id from user_master UM"
                            + " LEFT JOIN ref_state rs ON  rs.st_code =UM.state_code LEFT JOIN ref_district rd ON  rd.dist_code =UM.address_district_code"
                            + " LEFT JOIN ref_user_role_master rurm ON  rurm.role_id = UM.role_id"
                            + " where UM.aishe_code ='" + userId + "' and UM.is_approved=1");


            Query query = session.createNativeQuery(queryBuilder.toString());

            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
            List<UserDetailsVo> postMenuList = new ArrayList<>();
            for (Object[] object : objectlist) {
                UserDetailsVo postMenuData = new UserDetailsVo();
                if (object[0] != null) {
                    postMenuData.setUserId(object[0] + "");
                }
                if (object[1] != null) {
                    postMenuData.setRoleId(Integer.parseInt(object[1] + ""));
                }
                if (object[2] != null) {
                    postMenuData.setFirstName(object[2] + "");
                }
                if (object[3] != null) {
                    postMenuData.setStateCode(object[3] + "");
                }
                if (object[4] != null) {
                    postMenuData.setMobile(object[4] + "");
                }
                if (object[5] != null) {
                    postMenuData.setEmail(object[5] + "");
                }
                if (object[6] != null) {
                    postMenuData.setAisheCode(object[6] + "");
                    String[] splitted = postMenuData.getAisheCode().trim().split("\\s*-\\s*");
                    universityId = splitted[1];
                    String type = splitted[0];
                    String instituteName = null;
                    String statebodyid = null;
                    if (type.equals("C")) {
                        instituteName = fetcCollegeInstituteNameFromAisheCode(universityId);
                    }
                    if (type.equals("S")) {
                        instituteName = fetcStandaloneInstituteNameFromAisheCode(universityId);
                        statebodyid = fetcStandaloneInstituteStateBodyFromAisheCode(universityId);
                    }
                    if (type.equals("U")) {
                        instituteName = fetcUniversityInstituteNameFromAisheCode(universityId);
                    }
                    postMenuData.setBodyTypeId(statebodyid);
                    postMenuData.setInstituteName(instituteName);
                }
                if (postMenuData.getAisheCode() != null) {
                    String aisheCode = postMenuData.getAisheCode();
                    String[] splitted = aisheCode.trim().split("\\s*-\\s*");
                    String instituteType = splitted[0];
                    String instituteId = splitted[1];
                    Integer lsy = null;
                    Integer minlsy = null;
                    Integer openSurvey = null;
                    openSurvey = (Integer) session.createNativeQuery("select max(survey_year) from public.survey_master where end_date>now()").uniqueResult();

                    if (openSurvey == null) {
                        openSurvey = 0;
                    }
                    if (instituteType.equals("C")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                + " and institute_type ='C' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where college_institution_id = '" + instituteId + "'"
                                    + " and institute_type ='C' and survey_year<" + lsy + " ").uniqueResult();
                        }
                        universityId = (String) session.createNativeQuery("select university_id from public.college where id='" + instituteId + "' and survey_year in (select max(survey_year) from public.college)")
                                .uniqueResult();
                        postMenuData.setUniversityId(universityId);

                        String universityName = (String) session.createNativeQuery("select name from public.ref_university where id='" + universityId + "' and survey_year in (select max(survey_year) from public.ref_university)")
                                .uniqueResult();
                        postMenuData.setUniversityName(universityName);
                    }
                    if (instituteType.equals("S")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                + " and institute_type ='S' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where standalone_institution_id = '" + instituteId + "'"
                                    + " and institute_type ='S' and survey_year<" + lsy + "").uniqueResult();
                        }
                    }
                    if (instituteType.equals("U")) {
                        lsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                + " and institute_type ='U' and survey_year< " + openSurvey + "").uniqueResult();
                        if (lsy != null) {
                            minlsy = (Integer) session.createNativeQuery("select max(survey_year) from form_upload where university_id = '" + instituteId + "'"
                                    + " and institute_type ='U' and survey_year<" + lsy + "").uniqueResult();
                        }
                    }
//	                    if(lsy==null) {
//	                    	lsy=openSurvey;
//	                    }
                    postMenuData.setLSY(lsy);
                    postMenuData.setMinlsy(minlsy);
                    postMenuData.setMessage("Elligible");
                    if (postMenuData.getAisheCode() != null) {
                        switch (instituteType) {
                            case "U":
                                if (postMenuData.getAisheCode() != null) {
                                    String isDcfApplicable = (String) session.createNativeQuery("SELECT id FROM PUBLIC.ref_university WHERE ID='" + instituteId + "' and survey_year=2021"
                                            + " AND is_dcf_applicable IS true").uniqueResult();
                                    if (isDcfApplicable == null) {
                                        postMenuData.setMessage("You Are Not Elligible For This Survey");
                                    }
                                }
                                break;
                            case "C":
                                if (postMenuData.getAisheCode() != null) {
                                    Integer isDcfApplicable = (Integer) session.createNativeQuery("SELECT id FROM PUBLIC.college WHERE ID='" + instituteId + "' and survey_year=2021"
                                            + " AND is_dcf_applicable IS true").uniqueResult();
                                    if (isDcfApplicable == null) {
                                        postMenuData.setMessage("You Are Not Elligible For This Survey");
                                    }
                                }
                                break;
                            case "S":
                                if (postMenuData.getAisheCode() != null) {
                                    Integer isDcfApplicable = (Integer) session.createNativeQuery("SELECT id FROM PUBLIC.ref_standalone_institution WHERE ID='" + instituteId + "' and survey_year=2021"
                                            + "").uniqueResult();
                                    if (isDcfApplicable == null) {
                                        postMenuData.setMessage("You Are Not Elligible For This Survey");
                                    }
                                }
                                break;
                        }
                    }
                }
                // if (object[7] != null) {
                //     postMenuData.setBcryptPassword(object[7] + "");
                // }
                if (object[8] != null) {
                    postMenuData.setDistrictCode(object[8] + "");
                }
                if (object[9] != null) {
                    postMenuData.setAddressLine1(object[9] + "");
                }
                if (object[10] != null) {
                    postMenuData.setAddressLine2(object[10] + "");
                }
                if (object[11] != null) {
                    postMenuData.setCityName(object[11] + "");
                }
                if (object[12] != null) {
                    postMenuData.setStateName(object[12] + "");
                }
                if (object[13] != null) {
                    postMenuData.setDistrictName(object[13] + "");
                }
                if (object[14] != null) {
                    postMenuData.setRoleName(object[14] + "");
                }
                if (object[15] != null) {
                    postMenuData.setIsApproved(Integer.parseInt(object[15] + ""));
                }
                if (object[16] != null) {
                    postMenuData.setLandline(object[16] + "");
                }
                if (object[17] != null) {
                    postMenuData.setStdCode(object[17] + "");
                }
                if (object[18] != null) {
                    postMenuData.setMiddleName(object[18] + "");
                }
                if (object[19] != null) {
                    postMenuData.setLastName(object[19] + "");
                }
                if (object[20] != null) {
                    postMenuData.setStatusId(Integer.parseInt(object[20] + ""));
                }
                if (postMenuData.getMessage().equals("You Are Not Elligible For This Survey")) {
                    postMenuList.add(postMenuData);
                    return postMenuList;
                }
                postMenuList.add(postMenuData);
            }
            if (postMenuList.isEmpty()) {
                List<UserDetailsVo> userDetails = findByAisheCode(userId, 3);
                postMenuList.addAll(userDetails);
            }
            String instituteType = null;
            String institutionId = null;
            if (userId != null) {
                String aisheCode = userId;
                String[] splitted = aisheCode.trim().split("\\s*-\\s*");
                instituteType = splitted[0];
                institutionId = splitted[1];
            }
            if (postMenuList.isEmpty() && (instituteType.equals("C") || instituteType.equals("S"))) {
                if (userId != null) {
                    List<UserDetailsVo> responseVO = createdRequestForAisheCode(instituteType, userId);

                    if (!responseVO.isEmpty() || responseVO != null && (instituteType.equals("C"))) {
                        UserDetailsVo userDetails = new UserDetailsVo();
                        BeanUtils.copyProperties(responseVO.get(0), userDetails);
                        universityId = (String) session.createNativeQuery("select university_id from public.college where id='" + institutionId + "' and survey_year in (select max(survey_year) from public.college)")
                                .uniqueResult();
                        String universityNames = (String) session.createNativeQuery("select name from public.ref_university where id='" + universityId + "' and survey_year in (select max(survey_year) from public.ref_university)")
                                .uniqueResult();

                        userDetails.setUniversityId(universityId);
                        userDetails.setUniversityName(universityNames);
                        responseVO = new ArrayList<>();
                        responseVO.add(userDetails);
                    }

                    postMenuList.addAll(responseVO);
                }
            }
            if (postMenuList.isEmpty()) {
                List<UserDetailsVo> userDetails = findByAisheCodeInMaster(instituteType, institutionId);
                postMenuList.addAll(userDetails);
            }
            return postMenuList;
        } catch (Exception e) {
            logger.info("findAllNodalOfficerRole Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    private List<UserDetailsVo> findByAisheCodeInMaster(String instituteType, String institutionId) {
        String aisheCoden = null;

        logger.info("MasterDaoImpl : userDetails Method Invoked");
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            if (instituteType.equals("C")) {
                queryBuilder.append(

                        "select c.name,university_id,c.district_code as dist,state_code as stcode,c.type_id as typeid,rd.name as distnam,rs.name as stname,ru.name as uniname,ruct.type as ctype from college c\r\n"
                                + "left join ref_district rd on rd.dist_code = c.district_code\r\n"
                                + "left join ref_state rs on rs.st_code = c.state_code\r\n"
                                + "left join ref_university ru on ru.id = c.university_id\r\n"
                                + "left join ref_university_college_type ruct on ruct.id = c.type_id\r\n"
                                + " where c.id  ='" + institutionId + "' and c.survey_year in (select max(survey_year) from college where id ='" + institutionId + "')\r\n"
                                + " and ru.survey_year in (select max(survey_year) from ref_university)");

            }
            if (instituteType.equals("S")) {
                queryBuilder.append(

                        "select c.name,'NA',c.district_code as dist,statecode as stcode,c.statebodyid as typeid,rd.name as distnam,rs.name as stname,'NA' as uniname,ruct.type as ctype from ref_standalone_institution c\r\n"
                                + "left join ref_district rd on rd.dist_code = c.district_code\r\n"
                                + "left join ref_state rs on rs.st_code = c.statecode\r\n"
                                + "left join ref_state_body ruct on ruct.id = c.statebodyid\r\n"
                                + " where c.id  ='" + institutionId + "' and c.survey_year in (select max(survey_year) from ref_standalone_institution where id ='" + institutionId + "')");

            }
            if (instituteType.equals("U")) {
                queryBuilder.append(

                        "select c.name,'NA',c.district_code as dist,statecode as stcode,c.type_id as typeid,rd.name as distnam,rs.name as stname,'NA' as uniname,ruct.type as ctype from ref_university c\r\n"
                                + "left join ref_district rd on rd.dist_code = c.district_code\r\n"
                                + "left join ref_state rs on rs.st_code = c.statecode\r\n"
                                + "left join ref_university_type ruct on ruct.id = c.type_id\r\n"
                                + " where c.id  ='" + institutionId + "' and c.survey_year in (select max(survey_year) from ref_university where id ='" + institutionId + "')");

            }
            Query query = session.createNativeQuery(queryBuilder.toString());

            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
            List<UserDetailsVo> postMenuList = new ArrayList<>();
            for (Object[] object : objectlist) {
                UserDetailsVo postMenuData = new UserDetailsVo();

                if (object[0] != null) {
                    postMenuData.setInstituteName(object[0] + "");
                }
                if (object[1] != null) {
                    postMenuData.setUniversityId(object[1] + "");
                }
                if (object[2] != null) {
                    postMenuData.setDistrictCode(object[2] + "");
                }
                if (object[3] != null) {
                    postMenuData.setStateCode(object[3] + "");
                }
                if (object[4] != null) {
                    postMenuData.setBodyTypeId(object[4] + "");
                }
                if (object[6] != null) {
                    postMenuData.setStateName(object[6] + "");
                }
                if (object[5] != null) {
                    postMenuData.setDistrictName(object[5] + "");
                }
                if (object[7] != null) {
                    postMenuData.setUniversityName(object[7] + "");
                }
                if (object[8] != null) {
                    postMenuData.setCollegeOrStandaloneTypeName(object[8] + "");
                }
                postMenuList.add(postMenuData);
            }
            return postMenuList;
        } catch (Exception e) {
            logger.info("findAllNodalOfficerRole Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    private String fetcStandaloneInstituteStateBodyFromAisheCode(String universityId) {
        logger.info("PersonDaoImpl : fetcUniversityInstituteNameFromAisheCode method invoked :");
        Session session1 = sessionFactory.openSession();
        String name = null;
        try {
            Integer bodyid = ((Integer) session1.createNativeQuery(" select statebodyid from public.ref_standalone_institution where id ='" + universityId + "' and survey_year="
                    + "(Select max(survey_year) from ref_standalone_institution)").getSingleResult());
            if (bodyid != null) {
                name = bodyid.toString();
            }
            return name;
        } catch (Exception e) {
            try {
            } catch (Exception trEx) {
            }
        } finally {
            session1.close();
        }
        return name;
    }

    private List<UserDetailsVo> createdRequestForAisheCode(String instituteTypes, String userId) {
        String aisheCoden = null;
        if (userId != null) {
            String aisheCode = userId;
            String[] splitted = aisheCode.trim().split("\\s*-\\s*");
            aisheCoden = splitted[1];
        }
        logger.info("MasterDaoImpl : userDetails Method Invoked");
        Session session = sessionFactory.openSession();
        try {
            StringBuilder queryBuilder = new StringBuilder();
            if (instituteTypes.equals("C")) {
                queryBuilder.append(

                        "select created_survey_year,cird.name as collname,cird.state_code as statecode,cird.district_code as distcode,college_type_id,university_id as uniid,location_id,cird.management_id,line1,line2,city,\r\n"
                                + "pin_code,pd.name,pd.designation,pd.email_id,pd.mobile,pd.landline ,rs.name as statename,rde.name as dist,ruct.type as colltype,rim.management,ru.name as university\r\n"
                                + "from college_institution_request_details cird left join request_details rd on rd.id = cird.request_id join address addr on addr.id = rd.address_id\r\n"
                                + "join person_details pd on pd.id = rd.created_by join ref_state rs on rs.st_code = cird.state_code join ref_district rde on rde.dist_code = cird.district_code\r\n"
                                + "join ref_university_college_type ruct on ruct.id = cird.college_type_id join ref_institution_management rim on rim.id = cird.management_id\r\n"
                                + "join ref_university ru on ru.id = cird.university_id\r\n"
                                + "where cird.created_college_institution_id ='" + aisheCoden + "' and ru.survey_year in(select max(survey_year) from ref_university)");

            }
            if (instituteTypes.equals("S")) {
                queryBuilder.append(

                        "select created_survey_year,cird.name as collname,cird.state_code as statecode,cird.district_code as distcode,state_body_id,university_id as uniid,location_id,cird.management_id,\r\n"
                                + "line1,line2,city,\r\n"
                                + "pin_code,pd.name,pd.designation,pd.email_id,pd.mobile,pd.landline ,rs.name as statename,rde.name as dist,ruct.type as colltype,rim.management,'NA' as university\r\n"
                                + "from standalone_institution_request_details cird left join request_details rd on rd.id = cird.request_id LEFT join address addr on addr.id = rd.address_id\r\n"
                                + "LEFT join person_details pd on pd.id = rd.created_by LEFT join ref_state rs on rs.st_code = cird.state_code LEFT join ref_district rde on rde.dist_code = cird.district_code\r\n"
                                + "LEFT join ref_state_body ruct on ruct.id = cird.state_body_id LEFT join ref_institution_management rim on rim.id = cird.management_id\r\n"
                                + "where cird.created_standalone_institution_id ='" + aisheCoden + "'");

            }

            Query query = session.createNativeQuery(queryBuilder.toString());

            List<Object[]> objectlist = (List<Object[]>) query.getResultList();
            List<UserDetailsVo> postMenuList = new ArrayList<>();
            for (Object[] object : objectlist) {
                UserDetailsVo postMenuData = new UserDetailsVo();
                if (object[0] != null) {
                    postMenuData.setCreatedSurvey(Integer.parseInt(object[0] + ""));
                }
                if (object[1] != null) {
                    postMenuData.setInstituteName(object[1] + "");
                }
                if (object[2] != null) {
                    postMenuData.setStateCode(object[2] + "");
                }
                if (object[3] != null) {
                    postMenuData.setDistrictCode(object[3] + "");
                }
                if (object[4] != null) {
                    postMenuData.setCollegeTypeId(object[4] + "");
                }
                if (object[5] != null) {
                    postMenuData.setUniversityId(object[5] + "");
                }
                if (object[6] != null) {
                    postMenuData.setLocationId(object[6] + "");
                }
                if (object[7] != null) {
                    postMenuData.setManagementId(object[7] + "");
                }
                if (object[8] != null) {
                    postMenuData.setAddressLine1(object[8] + "");
                }
                if (object[9] != null) {
                    postMenuData.setAddressLine2(object[9] + "");
                }
                if (object[10] != null) {
                    postMenuData.setCityName(object[10] + "");
                }
                if (object[11] != null) {
                    postMenuData.setPincode(object[11] + "");
                }
                if (object[12] != null) {
                    postMenuData.setName(object[12] + "");
                }
                if (object[13] != null) {
                    postMenuData.setDesignation(object[13] + "");
                }
                if (object[14] != null) {
                    postMenuData.setEmail(object[14] + "");
                }
                if (object[15] != null) {
                    postMenuData.setMobile(object[15] + "");
                }
                if (object[16] != null) {
                    postMenuData.setLandline(object[16] + "");
                }
                if (object[17] != null) {
                    postMenuData.setStateName(object[17] + "");
                }
                if (object[18] != null) {
                    postMenuData.setDistrictName(object[18] + "");
                }
                if (object[19] != null) {
                    postMenuData.setCollegeOrStandaloneTypeName(object[19] + "");
                }
                if (object[20] != null) {
                    postMenuData.setManagementName(object[20] + "");
                }
                if (object[21] != null) {
                    postMenuData.setUniversityName(object[21] + "");
                }
                if (instituteTypes.equals("S")) {
                    String statebody = fetchStateBodyFromAisheCode(aisheCoden);
                    postMenuData.setBodyTypeId(statebody);
                }
                postMenuList.add(postMenuData);
            }
            return postMenuList;
        } catch (Exception e) {
            logger.info("findAllNodalOfficerRole Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }

    private String fetchStateBodyFromAisheCode(String aisheCoden) {
        logger.info("PersonDaoImpl : fetcUniversityInstituteNameFromAisheCode method invoked :");
        Session session1 = sessionFactory.openSession();
        String name = null;
        try {
            Integer bodyid = ((Integer) session1.createNativeQuery(" select state_body_id from public.standalone_institution_request_details where created_standalone_institution_id ='" + aisheCoden + "'").getSingleResult());
            if (bodyid != null) {
                name = bodyid.toString();
            }
            return name;
        } catch (Exception e) {
            try {
            } catch (Exception trEx) {
            }
        } finally {
            session1.close();
        }
        return name;
    }

    @Override
    public List<InstituteDetailEO> getInstituteDetail(String token, String instituteType) {
        logger.info("InstitutionMasterDaoImpl getDetail method invoked");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<InstituteDetailEO> criteriaQuery = builder.createQuery(InstituteDetailEO.class);
            Root<InstituteDetailEO> root = criteriaQuery.from(InstituteDetailEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(builder.equal(root.get("categoryType"), instituteType.toUpperCase()));
            predicates.add(builder.equal(root.get("islgdStateVerified"), false));
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
                    .orderBy(builder.desc(root.get("aisheCode")));
            Query<InstituteDetailEO> q = session.createQuery(criteriaQuery);
            List<InstituteDetailEO> list = q.getResultList();
            for (InstituteDetailEO data : list) {
                HttpHeaders headers = new HttpHeaders();
                headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                headers.set("Authorization", "Bearer " + token);
                if (data != null && data.getLongitude() != null && data.getLatitude() != null) {
                    Map map1 = new HashMap<String, String>();
                    map1.put("lon", data.getLongitude().toString());
                    map1.put("lat", data.getLatitude().toString());
                    //System.out.println("Bearer "+token);
                    HttpEntity<Map<String, String>> request = new HttpEntity<>(map1, headers);
                    String responseurl = "https://ministry.pmgatishakti.gov.in/lat-lon-api/";
                    ResponseEntity<BisagResponseVO> response1 = restTemplate.postForEntity(responseurl, request, BisagResponseVO.class);
                    BisagResponseVO responsedata = response1.getBody();
                    RefState state = (RefState) session.get(RefState.class, data.getStateId());
                    //RefDistrict district = (RefDistrict)session.get(RefDistrict.class,data.getDistrictId());
                    if (responsedata != null && responsedata.getStateId() != null && responsedata.getStateId().equals(state.getLgd_code().toString())) {
                        tx = session.beginTransaction();
                        data.setIslgdStateVerified(true);
                        session.update(data);
                        tx.commit();
                    }
//	            if(responsedata!=null && responsedata.getStateId()!=null && responsedata.getDistrictId()!=null &&
//	            		responsedata.getStateId().equals(state.getLgd_code().toString()) && responsedata.getDistrictId().equals(district.getLgd_district_code().toString())) {
//	            	tx = session.beginTransaction();
//	            	data.setIslgdDistrictVerified(true);
//	            	session.update(data);
//	            	tx.commit();
//	            }
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        } catch (Exception trEx) {
            logger.error("Couldn’t roll back transaction" + trEx.getMessage());
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }
}