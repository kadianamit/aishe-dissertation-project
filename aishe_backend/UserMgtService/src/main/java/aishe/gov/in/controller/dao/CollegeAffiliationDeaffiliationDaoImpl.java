package aishe.gov.in.dao;

import aishe.gov.in.masterseo.CollegeAffiliationLogEO;
import aishe.gov.in.masterseo.CollegeEO;
import aishe.gov.in.masterseo.CollegeInstitutionEO;
import aishe.gov.in.masterseo.Form_UploadBean;
import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.masterseo.UniversityRef;
import aishe.gov.in.masterseo.UserMasterEO;
import aishe.gov.in.mastersvo.CollegeDeaffiliationAffiliationVO;
import aishe.gov.in.security.UserInfo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class CollegeAffiliationDeaffiliationDaoImpl implements CollegeAffiliationDeaffliationDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(CollegeAffiliationDeaffiliationDaoImpl.class);

    @Override
    public CollegeEO getCollegeMaster(String collegeId, Integer surveyYear) {
        logger.info("AffiliationDeaffiliationDaoImpl : getCollegeMaster method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CollegeEO> criteriaQuery = builder.createQuery(CollegeEO.class);
            Root<CollegeEO> root = criteriaQuery.from(CollegeEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (collegeId != null) {
                predicates.add(builder.equal(root.get("universityPk").get("id"), collegeId));
            }
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("universityPk").get("surveyYear"), surveyYear));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<CollegeEO> q = session.createQuery(criteriaQuery);

            return q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    @Override
    public List<CollegeEO> getCollegeMasterList(String collegeId, Integer surveyYear) {
        logger.info("AffiliationDeaffiliationDaoImpl : getCollegeMasterList method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CollegeEO> criteriaQuery = builder.createQuery(CollegeEO.class);
            Root<CollegeEO> root = criteriaQuery.from(CollegeEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (collegeId != null) {
                predicates.add(builder.equal(root.get("universityPk").get("id"), collegeId));
            }
            if (surveyYear != null) {
                predicates.add(builder.greaterThan(root.get("universityPk").get("surveyYear"), surveyYear));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<CollegeEO> q = session.createQuery(criteriaQuery);
            return q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public UniversityRef getUniversityMaster(String universityId, Integer surveyYear) {
        logger.info("AffiliationDeaffiliationDaoImpl : getUniversityMaster method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UniversityRef> criteriaQuery = builder.createQuery(UniversityRef.class);
            Root<UniversityRef> root = criteriaQuery.from(UniversityRef.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (universityId != null) {
                predicates.add(builder.equal(root.get("id"), universityId));
            }
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<UniversityRef> q = session.createQuery(criteriaQuery);
            return q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public CollegeAffiliationLogEO getCollegeAffiliationLogBySurveyYear(String collegId, Integer surveyYear) {
        logger.info("AffiliationDeaffiliationDaoImpl : getCollegeAffiliationLogBySurveyYear method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CollegeAffiliationLogEO> criteriaQuery = builder.createQuery(CollegeAffiliationLogEO.class);
            Root<CollegeAffiliationLogEO> root = criteriaQuery.from(CollegeAffiliationLogEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (collegId != null) {
                predicates.add(builder.equal(root.get("id"), collegId));
            }
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<CollegeAffiliationLogEO> q = session.createQuery(criteriaQuery);
            return q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }


    public CollegeInstitutionEO getCollegeTansaction(String collegeId, Integer surveyYear) {
        logger.info("AffiliationDeaffiliationDaoImpl : getCollegeTansaction method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CollegeInstitutionEO> criteriaQuery = builder.createQuery(CollegeInstitutionEO.class);
            Root<CollegeInstitutionEO> root = criteriaQuery.from(CollegeInstitutionEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (collegeId != null) {
                predicates.add(builder.equal(root.get("collegePk").get("id"), Integer.parseInt(collegeId)));
            }
            if (surveyYear != null) {
                predicates.add(builder.equal(root.get("collegePk").get("surveyYear"), surveyYear));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<CollegeInstitutionEO> q = session.createQuery(criteriaQuery);
            return q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean saveCollegeAffiliationDeaffiliation(CollegeDeaffiliationAffiliationVO deaffiliationAffiliationVO, CollegeEO collegeEO, UniversityRef oldUniversityRef, UniversityRef newUnversityRef
    		, UserInfo userInfo) {
        logger.info("AffiliationDeaffiliationDaoImpl : saveCollegeAffiliationDeaffiliation method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer id = null;
        try {

            tx = session.beginTransaction();
            CollegeAffiliationLogEO logEO = getCollegeAffiliationLogBySurveyYear(deaffiliationAffiliationVO.getCollegeAisheCode(), null);
            Timestamp currrentTime = new Timestamp(new Date().getTime());
            if (null == logEO) {
                CollegeAffiliationLogEO affiliationLog = new CollegeAffiliationLogEO();
                affiliationLog.setId(Integer.parseInt(deaffiliationAffiliationVO.getCollegeAisheCode()));
                affiliationLog.setAffiliatedBy(userInfo.getUsername());
                affiliationLog.setName(collegeEO.getName());
                affiliationLog.setAffiliatingUniversityId(newUnversityRef.getId());
                affiliationLog.setAffiliationDatetime(currrentTime);
                if (!(oldUniversityRef.getId().equals(newUnversityRef.getId()))) {
                    affiliationLog.setDeaffiliatedBy(userInfo.getUsername());
                    affiliationLog.setDeaffiliationDatetime(currrentTime);
                    affiliationLog.setDeaffiliatingUniversityId(oldUniversityRef.getId());
                }
                affiliationLog.setSurveyYear(deaffiliationAffiliationVO.getSurveyYear());
                session.saveOrUpdate(affiliationLog);
            } else {
                logEO.setAffiliatedBy(userInfo.getUsername());
                logEO.setAffiliatingUniversityId(newUnversityRef.getId());
                logEO.setAffiliationDatetime(currrentTime);
                if (!(oldUniversityRef.getId().equals(newUnversityRef.getId())) && null != oldUniversityRef.getId()) {
                    logEO.setDeaffiliatedBy(userInfo.getUsername());
                    logEO.setDeaffiliationDatetime(currrentTime);
                    logEO.setDeaffiliatingUniversityId(oldUniversityRef.getId());
                }
                logEO.setSurveyYear(deaffiliationAffiliationVO.getSurveyYear());
                session.update(logEO);
            }
            tx.commit();
            session.close();
            return true;

        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                logger.error("Couldn’t roll back transaction" + trEx.getMessage());
            }
            logger.info("getCollegeMaster Error" + e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public Boolean saveUpdateCollegeAffiliationDeaffiliation(CollegeDeaffiliationAffiliationVO deaffiliationAffiliationVO, CollegeEO collegeEO, UniversityRef newUnversityRef) {
        logger.info("AffiliationDeaffiliationDaoImpl : saveUpdateCollegeAffiliationDeaffiliation method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            collegeEO.setUniversityId(newUnversityRef.getId());
            session.update(collegeEO);
            //Search college according to survey year
            CollegeInstitutionEO collegeInstitutionEO = getCollegeTansaction(deaffiliationAffiliationVO.getCollegeAisheCode(), deaffiliationAffiliationVO.getSurveyYear());
            if (null != collegeInstitutionEO) {
                //tx2 = session3.beginTransaction();
                collegeInstitutionEO.setUniversityId(newUnversityRef.getId());
                session.update(collegeInstitutionEO);
            }
            UserMasterEO userMaster = fetchUserMasterFromAisheCode("C-"+deaffiliationAffiliationVO.getCollegeAisheCode());
            if(userMaster!=null) {
            	userMaster.setStateLevelBody(newUnversityRef.getId());
                 session.update(userMaster);
            }
            
            InstituteDetailEO instituteDetail = fetchInstituteDetailsFromAisheCode("C-"+deaffiliationAffiliationVO.getCollegeAisheCode());
            if(instituteDetail!=null) {
            	instituteDetail.setUniversityId(newUnversityRef.getId());
                session.update(instituteDetail);
            }
            
            tx.commit();
            session.close();
            return true;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                logger.error("Couldn’t roll back transaction" + trEx.getMessage());
            }
            logger.info("saveUpdateCollegeAffiliationDeaffiliation Error" + e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }
	private InstituteDetailEO fetchInstituteDetailsFromAisheCode(String aisheCode) {
		Session session = sessionFactory.openSession();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(
					"from InstituteDetailEO as userMaster where userMaster.aisheCode="
					+ ":aisheCode");
			Query query = session.createQuery(sb.toString());
			query.setParameter("aisheCode", aisheCode);
			InstituteDetailEO collegeId = (InstituteDetailEO) query.getSingleResult();
			return collegeId;
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	private UserMasterEO fetchUserMasterFromAisheCode(String aisheCode) {
		Session session = sessionFactory.openSession();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(
					"from UserMasterEO as userMaster where userMaster.aisheCode="
					+ ":aisheCode and userMaster.statusId=:statusId");
			Query query = session.createQuery(sb.toString());
			query.setParameter("aisheCode", aisheCode);
			query.setParameter("statusId", 2);
			UserMasterEO collegeId = (UserMasterEO) query.getSingleResult();
			return collegeId;
		
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return null;
	}
	@Override
	public Form_UploadBean getFormUploadForCollege(String aisheCode, Integer surveyYear) {
		Session session = sessionFactory.openSession();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(
					"from Form_UploadBean as form_UploadBean  where form_UploadBean.college_institution_id="
					+ ":aishecode and "
					+ "form_UploadBean.survey_year= :surveyYear");
			Query query = session.createQuery(sb.toString());
			query.setParameter("aishecode", Integer.parseInt(aisheCode));
			query.setParameter("surveyYear", surveyYear);
			Form_UploadBean collegeId = (Form_UploadBean) query.getSingleResult();
			return collegeId;
		
		} catch (Exception e) {
			logger.info("savecollegeId Error" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	@Override
	public Form_UploadBean getFormUploadForStandalone(String aisheCode, Integer surveyYear) {
		Session session = sessionFactory.openSession();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(
					"from Form_UploadBean as form_UploadBean  where form_UploadBean.standalone_institution_id="
					+ ":aishecode and "
					+ "form_UploadBean.survey_year= :surveyYear");
			Query query = session.createQuery(sb.toString());
			query.setParameter("aishecode", Integer.parseInt(aisheCode));
			query.setParameter("surveyYear", surveyYear);
			Form_UploadBean collegeId = (Form_UploadBean) query.getSingleResult();
			return collegeId;
		
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return null;
	}
}


