package aishe.gov.in.dao;

import java.sql.Timestamp;
import java.time.Instant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aishe.gov.in.masterseo.Form_UploadBean;
import aishe.gov.in.masterseo.UserActionLogNewEO;
import aishe.gov.in.mastersvo.UnlockVO;

@Repository
public class LockDaoImpl implements LockDao {
    @Autowired
    SessionFactory sessionfactory;

	@Override
	public boolean unlockWebdcf(UnlockVO unlockVO,HttpServletRequest request) {
		Session session = sessionfactory.openSession();
		Transaction tx = null;	
		Form_UploadBean formUpload = new Form_UploadBean();
		try {
			tx = session.beginTransaction();
			switch (unlockVO.getInstitutionType().toUpperCase()) {
			 case "C":
				 formUpload = fetchFromUploadDataForCollege(unlockVO.getAishecode(),unlockVO.getSurveyYear());
				 if(formUpload!=null) {
					 session.delete(formUpload);
					 session.createNativeQuery("update college_lock_status set final_lock =false where survey_year = "+unlockVO.getSurveyYear()+" and "
					 		+ "aishe_code = '"+unlockVO.getAishecode()+"'").executeUpdate();
				 }
				break;
			case "S":
				 formUpload = fetchFromUploadDataForStandalone(unlockVO.getAishecode(),unlockVO.getSurveyYear());
				 if(formUpload!=null) {
					 session.delete(formUpload);
					 session.createNativeQuery("update standalone_lock_status set final_lock =false where survey_year = "+unlockVO.getSurveyYear()+" "
						 		+ " and aishe_code ='"+unlockVO.getAishecode()+"'").executeUpdate();
				 }
				break;
			case "U":
				 formUpload = fetchFromUploadDataForUniversity(unlockVO.getAishecode(),unlockVO.getSurveyYear());
				 if(formUpload!=null) {
					 session.delete(formUpload);
					 session.createNativeQuery("update university_lock_status set final_lock =false where survey_year = "+unlockVO.getSurveyYear()+" "
						 		+ " and aishe_code ='"+unlockVO.getAishecode()+"'").executeUpdate();
				 }
				break;
			default:
				break;
			}
			 UserActionLogNewEO userActionLog = new UserActionLogNewEO();
		        userActionLog.setId(null);
		        userActionLog.setInstitutionType(unlockVO.getInstitutionType().toUpperCase());
		        userActionLog.setInstitutionId(unlockVO.getAishecode());
		        userActionLog.setActionId(37);
		        userActionLog.setIpAddress(request.getHeader("X-Forwarded-For"));
				if(userActionLog.getIpAddress()==null) {
					userActionLog.setIpAddress(request.getRemoteAddr());
				}
				Timestamp ts = Timestamp.from(Instant.now());
		        userActionLog.setActionTime(ts);
		        userActionLog.setUserId(unlockVO.getUserName());
		        userActionLog.setSurveyYear(unlockVO.getSurveyYear());
		        userActionLog.setRemarks(unlockVO.getRemarks());
		        saveCertificateLog(userActionLog);
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
		}
		return false;
	}
	
	public void saveCertificateLog(UserActionLogNewEO userActionLogNewEO) {
		Session session = sessionfactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
		    session.save(userActionLogNewEO);
		    tx.commit();
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

	private Form_UploadBean fetchFromUploadDataForUniversity(String aishecode, Integer surveyYear) {
	        Session session = sessionfactory.openSession();
	        try {
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<Form_UploadBean> query = builder.createQuery(Form_UploadBean.class);
	            Root<Form_UploadBean> allData = query.from(Form_UploadBean.class);
	            query.where(builder.and(builder.equal(allData.get("university_id"), aishecode),
	                    builder.equal(allData.get("survey_year"), surveyYear)));
	            Form_UploadBean university = session.createQuery(query).uniqueResult();
	            return university;
	        } catch (Exception e) {
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	private Form_UploadBean fetchFromUploadDataForStandalone(String aishecode, Integer surveyYear) {
	        Session session = sessionfactory.openSession();
	        try {
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<Form_UploadBean> query = builder.createQuery(Form_UploadBean.class);
	            Root<Form_UploadBean> allData = query.from(Form_UploadBean.class);
	            query.where(builder.and(builder.equal(allData.get("standalone_institution_id"), aishecode),
	                    builder.equal(allData.get("survey_year"), surveyYear)));
	            Form_UploadBean university = session.createQuery(query).uniqueResult();
	            return university;
	        } catch (Exception e) {
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	private Form_UploadBean fetchFromUploadDataForCollege(String aishecode, Integer surveyYear) {
	        Session session = sessionfactory.openSession();
	        try {
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<Form_UploadBean> query = builder.createQuery(Form_UploadBean.class);
	            Root<Form_UploadBean> allData = query.from(Form_UploadBean.class);
	            query.where(builder.and(builder.equal(allData.get("college_institution_id"), aishecode),
	                    builder.equal(allData.get("survey_year"), surveyYear)));
	            Form_UploadBean university = session.createQuery(query).uniqueResult();
	            return university;
	        } catch (Exception e) {
	        } finally {
	            session.close();
	        }
	        return null;
	    }
}