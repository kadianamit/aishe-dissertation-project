package aishe.gov.in.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aishe.gov.in.masterseo.FormDetailEO;
import aishe.gov.in.masterseo.FormUpload;
import aishe.gov.in.masterseo.RefCollegeInstitution;
import aishe.gov.in.masterseo.RefDistrict;
import aishe.gov.in.masterseo.RefStandaloneInstitution;
import aishe.gov.in.masterseo.RefState;
import aishe.gov.in.masterseo.RefStateBody;
import aishe.gov.in.masterseo.RefUniversity;
import aishe.gov.in.masterseo.RefUniversityCollegeType;
import aishe.gov.in.masterseo.RefUniversityType;
import aishe.gov.in.masterseo.SurveyStatusEO;
import aishe.gov.in.masterseo.SurveyStatusLogEO;
import aishe.gov.in.mastersvo.SurveyStatusWithUserStatusDTO;
import aishe.gov.in.utility.RemunerationNativeQueries;
import aishe.gov.in.utility.SurveyStatusKey;

@Repository
public class RefMasterDaoImpl implements RefMasterDao {
    @Autowired
    private SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(RegisterationDaoImpl.class);

    @Override
    public RefDistrict getDistrict(String code) {
        logger.info("RefMasterDaoImpl : getDistrict method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefDistrict> criteriaQuery = builder.createQuery(RefDistrict.class);
            Root<RefDistrict> root = criteriaQuery.from(RefDistrict.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (code != null) {
                predicates.add(builder.equal(root.get("id"), code));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<RefDistrict> q = session.createQuery(criteriaQuery);
            RefDistrict list = q.uniqueResult();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public RefState getState(String code) {
        logger.info("RefMasterDaoImpl : getState method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefState> criteriaQuery = builder.createQuery(RefState.class);
            Root<RefState> root = criteriaQuery.from(RefState.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (code != null) {
                predicates.add(builder.equal(root.get("id"), code));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<RefState> q = session.createQuery(criteriaQuery);
            RefState list = q.uniqueResult();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public String getCollegeName(Integer id) {
        logger.info("RefMasterDaoImpl : getCollegeName method invoked :");
        Session session1 = sessionFactory.openSession();
        try {
            Integer maxSurvey = getCollegeMaxYear(id);
            return (String) session1.createNativeQuery("select name from public.college where id=" + id + " and survey_year=" + maxSurvey).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return null;
    }


    public Integer getCollegeMaxYear(Integer id) {
        logger.info("RefMasterDaoImpl : getCollegeMaxYear method invoked :");
        Session session1 = sessionFactory.openSession();
        try {
            return (Integer) session1.createNativeQuery("select max(survey_year) from public.college where id=" + id).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return null;
    }

    @Override
    public String getStandaloneName(Integer id) {
        logger.info("RefMasterDaoImpl : getStandaloneName method invoked :");
        Session session1 = sessionFactory.openSession();
        try {
            Integer maxSurvey = getStandaloneMax(id);
            return (String) session1.createNativeQuery("select name from public.ref_standalone_institution where id=" + id + "and survey_year=" + maxSurvey).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return null;
    }

    public Integer getStandaloneMax(Integer id) {
        logger.info("RefMasterDaoImpl : getStandaloneMax method invoked :");
        Session session1 = sessionFactory.openSession();
        try {
            return (Integer) session1.createNativeQuery("select max(survey_year) from public.ref_standalone_institution where id=" + id).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return null;
    }

    @Override
    public String getUniversityName(String id) {
        logger.info("RefMasterDaoImpl : getUniversityName method invoked :");
        Session session1 = sessionFactory.openSession();
        try {
            Integer maxSurvey = getUniversityMax(id);
            return (String) session1.createNativeQuery("select name from public.ref_university where id='" + id + "' and survey_year=" + maxSurvey).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return null;
    }

    public Integer getUniversityMax(String id) {
        logger.info("RefMasterDaoImpl : getUniversityMax method invoked :");
        Session session1 = sessionFactory.openSession();
        try {
            return (Integer) session1.createNativeQuery("select max(survey_year) from public.ref_university where id='" + id + "'").uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return null;
    }

    @Override
    public FormUpload getFormUpload(String aisheCode, Integer surveyYear) {
        logger.info("RegisterationDaoImpl : getUserFromMaster method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<FormUpload> criteriaQuery = builder.createQuery(FormUpload.class);
            Root<FormUpload> root = criteriaQuery.from(FormUpload.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            String aisheArray[] = aisheCode.split("-");

            if (aisheArray[0] != null) {
                predicates.add(builder.equal(root.get("instituteType"), aisheArray[0]));

                switch (aisheArray[0].toUpperCase()) {
                    case "S": {
                        predicates.add(builder.equal(root.get("standaloneInstitutionId"), Integer.valueOf(aisheArray[1])));
                        break;
                    }
                    case "C": {
                        predicates.add(builder.equal(root.get("collegeInstitutionId"), Integer.valueOf(aisheArray[1])));
                        break;
                    }
                    case "U": {
                        predicates.add(builder.equal(root.get("universityId"), aisheArray[1]));
                        break;
                    }
                }
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<FormUpload> q = session.createQuery(criteriaQuery);
            FormUpload list = q.uniqueResult();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RefUniversity> getUniversityMaster(String id, Integer surveyYear, Boolean dcfStatus, String typeId) {
        logger.info("RegisterationDaoImpl : getUniversityMaster method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefUniversity> criteriaQuery = builder.createQuery(RefUniversity.class);
            Root<RefUniversity> root = criteriaQuery.from(RefUniversity.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            if (id != null) {
                predicates.add(builder.equal(root.get("id"), id));
            }
//            if (dcfStatus != null) {
//                predicates.add(builder.equal(root.get("isDcfApplicable"), dcfStatus));
//            }
            if (typeId != null) {
                predicates.add(builder.equal(root.get("typeId"), typeId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(builder.asc(root.get("surveyYear")));
            ;
            Query<RefUniversity> q = session.createQuery(criteriaQuery);
            List<RefUniversity> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RefCollegeInstitution> getCollegeMaster(Integer id, Integer surveyYear, Boolean dcfStatus, String type) {
        logger.info("RegisterationDaoImpl : getCollegeMaster method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefCollegeInstitution> criteriaQuery = builder.createQuery(RefCollegeInstitution.class);
            Root<RefCollegeInstitution> root = criteriaQuery.from(RefCollegeInstitution.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            if (id != null) {
                predicates.add(builder.equal(root.get("id"), id));
            }
//            if (dcfStatus != null) {
//                predicates.add(builder.equal(root.get("isDcfApplicable"), dcfStatus));
//            }
            if (type != null) {
                predicates.add(builder.equal(root.get("typeId"), type));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(builder.asc(root.get("surveyYear")));
            Query<RefCollegeInstitution> q = session.createQuery(criteriaQuery);
            List<RefCollegeInstitution> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RefStandaloneInstitution> getStandaloneMaster(Integer id, Integer surveyYear, Integer stateBodyId) {
        logger.info("RegistrationDaoImpl : getStandaloneMaster method invoked :");
        Session session = sessionFactory.openSession();
        try {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefStandaloneInstitution> criteriaQuery = builder.createQuery(RefStandaloneInstitution.class);
            Root<RefStandaloneInstitution> root = criteriaQuery.from(RefStandaloneInstitution.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            if (id != null) {
                predicates.add(builder.equal(root.get("id"), id));
            }
            if (stateBodyId != null) {
                predicates.add(builder.equal(root.get("stateBodyId"), stateBodyId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(builder.asc(root.get("surveyYear")));
            Query<RefStandaloneInstitution> q = session.createQuery(criteriaQuery);
            List<RefStandaloneInstitution> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RefUniversity> saveRefUniversityList(List<RefUniversity> refUniversities, Integer newSurveyYear) {
        logger.info("RefMasterDaoImpl : saveRefUniversityList method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (RefUniversity refUniversity : refUniversities) {
                if (newSurveyYear != null)
                    refUniversity.setSurveyYear(newSurveyYear);
                session.save(refUniversity);
                if (refUniversities.size() % 50 == 0) { // Same as JDBC batch size
                    //flush a batch of inserts and release memory:
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
            return refUniversities;
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
    public List<RefCollegeInstitution> saveRefCollegeInstitutionList(List<RefCollegeInstitution> refCollegeInstitutions, Integer newSurveyYear) {
        logger.info("RefMasterDaoImpl : saveRefCollegeInstitutionList method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (RefCollegeInstitution refUniversity : refCollegeInstitutions) {
                if (newSurveyYear != null)
                    refUniversity.setSurveyYear(newSurveyYear);
                session.save(refUniversity);
                if (refCollegeInstitutions.size() % 50 == 0) { // Same as JDBC batch size
                    //flush a batch of inserts and release memory:
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
            return refCollegeInstitutions;
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
    public List<RefStandaloneInstitution> saveRefStandaloneInstitutionList(List<RefStandaloneInstitution> refStandaloneInstitutions, Integer newSurveyYear) {
        logger.info("RefMasterDaoImpl : saveRefStandaloneInstitutionList method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (RefStandaloneInstitution refUniversity : refStandaloneInstitutions) {
                if (newSurveyYear != null)
                    refUniversity.setSurveyYear(newSurveyYear);
                session.save(refUniversity);
                if (refStandaloneInstitutions.size() % 50 == 0) { // Same as JDBC batch size
                    //flush a batch of inserts and release memory:
                    session.flush();
                    session.clear();
                }
            }
            tx.commit();
            return refStandaloneInstitutions;
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
    public List<SurveyStatusEO> getSurveyStatus(Integer surveyYear, String stateCode) {
        logger.info("RegistrationDaoImpl : getStandaloneMaster method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SurveyStatusEO> criteriaQuery = builder.createQuery(SurveyStatusEO.class);
            Root<SurveyStatusEO> root = criteriaQuery.from(SurveyStatusEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("statusKey").get("surveyYear"), surveyYear));
            }
            if (stateCode != null) {
                predicates.add(builder.equal(root.get("statusKey").get("stateCode"), stateCode));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
            ;
            Query<SurveyStatusEO> q = session.createQuery(criteriaQuery);

            List<SurveyStatusEO> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<SurveyStatusEO> saveSurveyStatus(List<SurveyStatusEO> statusEOS, Integer newSurveyYear, String userId) {
        logger.info("RefMasterDaoImpl : saveRefStandaloneInstitutionList method invoked :");
        Session session = sessionFactory.openSession();


        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (SurveyStatusEO refUniversity : statusEOS) {
                if (newSurveyYear != null) refUniversity.getStatusKey().setSurveyYear(newSurveyYear);
                refUniversity.setForm1UploadDisabled(false);
                refUniversity.setForm2UploadDisabled(false);
                refUniversity.setForm3UploadDisabled(false);
                session.save(refUniversity);
                SurveyStatusLogEO logEO = SurveyStatusLogEO
                        .builder()
                        // .id(id + 1)
                        .userId(userId)
                        .surveyYear(refUniversity.getStatusKey().getSurveyYear())
                        .actionDate(new Timestamp(new Date().getTime()))
                        .stateCode(refUniversity.getStatusKey().getStateCode())
                        .form1UploadDisabled(refUniversity.getForm1UploadDisabled())
                        .form2UploadDisabled(refUniversity.getForm2UploadDisabled())
                        .form3UploadDisabled(refUniversity.getForm3UploadDisabled())
                        .build();
                session.save(logEO);

            }
            tx.commit();
            return statusEOS;
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
    public List<SurveyStatusWithUserStatusDTO> freezeSurveyYear(List<SurveyStatusWithUserStatusDTO> statusDTO) {
        logger.info("RefMasterDaoImpl : saveRefStandaloneInstitutionList method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (SurveyStatusWithUserStatusDTO refUniversity : statusDTO) {
                SurveyStatusEO statusEO = SurveyStatusEO
                        .builder()
                        .statusKey(SurveyStatusKey
                                .builder()
                                .surveyYear(refUniversity.getSurveyYear())
                                .stateCode(refUniversity.getStateCode())
                                .build())
                        .form1UploadDisabled(refUniversity.getForm1UploadDisabled())
                        .form2UploadDisabled(refUniversity.getForm2UploadDisabled())
                        .form3UploadDisabled(refUniversity.getForm3UploadDisabled())
                        .build();
                session.update(statusEO);
                SurveyStatusLogEO logEO = SurveyStatusLogEO
                        .builder()
                        // .id(id + 1)
                        .userId(refUniversity.getUserId())
                        .surveyYear(refUniversity.getSurveyYear())
                        .actionDate(new Timestamp(new Date().getTime()))
                        .stateCode(refUniversity.getStateCode())
                        .form1UploadDisabled(refUniversity.getForm1UploadDisabled())
                        .form2UploadDisabled(refUniversity.getForm2UploadDisabled())
                        .form3UploadDisabled(refUniversity.getForm3UploadDisabled())
                        .build();
                session.save(logEO);
            }
            tx.commit();
            return statusDTO;
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
    public List<RefStateBody> getRefStateBody() {
        logger.info("RegistrationDaoImpl : getRefStateBody method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefStateBody> criteriaQuery = builder.createQuery(RefStateBody.class);
            Root<RefStateBody> root = criteriaQuery.from(RefStateBody.class);
            criteriaQuery.select(root).orderBy(builder.asc(root.get("id")));
            ;
            Query<RefStateBody> q = session.createQuery(criteriaQuery);
            List<RefStateBody> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RefUniversityType> getRefUniversityType() {
        logger.info("RegistrationDaoImpl : getRefUniversityType method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefUniversityType> criteriaQuery = builder.createQuery(RefUniversityType.class);
            Root<RefUniversityType> root = criteriaQuery.from(RefUniversityType.class);
            criteriaQuery.select(root).orderBy(builder.asc(root.get("id")));
            ;
            Query<RefUniversityType> q = session.createQuery(criteriaQuery);
            List<RefUniversityType> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RefUniversityCollegeType> getRefUniversityCollegeType() {
        logger.info("RegistrationDaoImpl : getRefUniversityCollegeType method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefUniversityCollegeType> criteriaQuery = builder.createQuery(RefUniversityCollegeType.class);
            Root<RefUniversityCollegeType> root = criteriaQuery.from(RefUniversityCollegeType.class);
            criteriaQuery.select(root).orderBy(builder.asc(root.get("id")));
            Query<RefUniversityCollegeType> q = session.createQuery(criteriaQuery);
            List<RefUniversityCollegeType> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<RefStateBody> getRefStateBody(Integer id, Integer id2) {
        logger.info("RegistrationDaoImpl : getRefStateBody method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefStateBody> criteriaQuery = builder.createQuery(RefStateBody.class);
            Root<RefStateBody> root = criteriaQuery.from(RefStateBody.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            predicates.add(builder.or(builder.equal(root.get("id"), id), builder.equal(root.get("id"), id2)));
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(builder.asc(root.get("id")));
            ;
            Query<RefStateBody> q = session.createQuery(criteriaQuery);
            List<RefStateBody> list = q.getResultList();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public FormDetailEO getUserDetails(Integer formUploadId) {
        Session session1 = sessionFactory.openSession();
        List<FormDetailEO> details;
        try {
            Query query = session1.createNativeQuery(RemunerationNativeQueries.USER_INSTITUTION_DETAIL_BY_FORM_UPLOAD_ID, FormDetailEO.class);
            query.setParameter("formUploadId", formUploadId);
            details = query.getResultList();
            if (!details.isEmpty()) {
                return details.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session1.close();
        }
        return null;
    }

    @Override
    public Double getLattitude(String aisheCode, String instituteType) {
        logger.info("RefMasterDaoImpl : getCollegeName method invoked :");
        Session session1 = sessionFactory.openSession();
        try {
            if (instituteType.equals("C")) {
                return (Double) session1.createNativeQuery("select latitude from public.college_institution where id='" + aisheCode + "' and survey_year=" + 2021).uniqueResult();
            }
            if (instituteType.equals("U")) {
                return (Double) session1.createNativeQuery("select latitude from public.university where id='" + aisheCode + "' and survey_year=" + 2021).uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return null;
    }

    @Override
    public Double getLongitude(String aisheCode, String instituteType) {
        logger.info("RefMasterDaoImpl : getCollegeName method invoked :");
        Session session1 = sessionFactory.openSession();
        try {
            if (instituteType.equals("C")) {
                return (Double) session1.createNativeQuery("select longitude from public.college_institution where id='" + aisheCode + "' and survey_year=" + 2021).uniqueResult();
            }
            if (instituteType.equals("U")) {
                return (Double) session1.createNativeQuery("select longitude from public.university where id='" + aisheCode + "' and survey_year=" + 2021).uniqueResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return null;
    }

    @Override
    public boolean getFinalSubmitDone(String id, String type, Integer currentSurveyYear) {
        logger.info("RefMasterDaoImpl : getCollegeName method invoked :");
        Session session = sessionFactory.openSession();
        Integer submitDone = null;
        try {
            if (type.equals("C")) {
                submitDone = (Integer) session.createNativeQuery("select id from public.form_upload where college_institution_id ='" + id + "' and survey_year =2023")
                        .getSingleResult();
                if (submitDone != null) {
                    return true;
                }
            }
            if (type.equals("U")) {
                submitDone = (Integer) session.createNativeQuery("select id from public.form_upload where university_id ='" + id + "' and survey_year =2023")
                        .getSingleResult();
                if (submitDone != null) {
                    return true;
                }
            }
            if (type.equals("S")) {
                submitDone = (Integer) session.createNativeQuery("select id from public.form_upload where standalone_institution_id ='" + id + "' and survey_year=2023").getSingleResult();
                if (submitDone != null) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean saveCommonMaster(String query, Integer maxSurveyYear, Integer surveyYear) {
        logger.info("RefMasterDaoImpl : saveCommonMaster method invoked :");
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            boolean b = session.createNativeQuery(query)
                    .setParameter("maxSurveyYear", maxSurveyYear)
                    .setParameter("surveyYear", surveyYear).executeUpdate() == 1;

            tx.commit();
            return b;

        } catch (Exception e) {
            e.printStackTrace();
            if (null != tx && tx.isActive())
                tx.rollback();
        }
        return false;
    }

}
