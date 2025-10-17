package aishe.gov.in.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aishe.gov.in.masterseo.CollegeRefEO;
import aishe.gov.in.masterseo.DeleteStandaloneInstitution;
import aishe.gov.in.masterseo.Form_UploadBean;
import aishe.gov.in.masterseo.RefStandaloneInstitution1;
import aishe.gov.in.masterseo.StandAloneEmadedPK;
import aishe.gov.in.masterseo.StandaloneInstitutionMaster;
import aishe.gov.in.masterseo.UniversityRefMaster;
import aishe.gov.in.masterseo.UserActionLogEO;
import aishe.gov.in.masterseo.UserActionLogNewEO;
import aishe.gov.in.utility.DateUtils;

@Repository
public class SpecialPermissionDaoImpl implements SpecialPermissionDao {

	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(SpecialPermissionDaoImpl.class);
	@Override
	public boolean assignSpecialPermissionToInstitute(String instituteType, String aisheCode, String username,
			Integer surveyYear, Boolean specialPermission,String remarks,HttpServletRequest request) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UniversityRefMaster university = new UniversityRefMaster();
		CollegeRefEO college = new CollegeRefEO();
		StandaloneInstitutionMaster standalone = new StandaloneInstitutionMaster();
		
		Integer isSpecialPermission = fetchSpecialPermissionAlready(instituteType,aisheCode,surveyYear);
		if(isSpecialPermission!=null) {
			return true;
		}
		UserActionLogNewEO userActionLog = new UserActionLogNewEO();
		userActionLog.setId(null);
		userActionLog.setInstitutionType(instituteType);
		userActionLog.setInstitutionId(aisheCode);
		userActionLog.setIpAddress(request.getHeader("X-Forwarded-For"));
		if (userActionLog.getIpAddress() == null) {
			userActionLog.setIpAddress(request.getRemoteAddr());
		}
		Date date = new Date();
		Timestamp timestamp2 = new Timestamp(date.getTime());
		userActionLog.setActionTime(timestamp2);
		userActionLog.setUserId(username);
		userActionLog.setSurveyYear(surveyYear);
		if(specialPermission) {
			userActionLog.setActionId(39);
			userActionLog.setRemarks(remarks);
		}else {
			userActionLog.setActionId(40);
			userActionLog.setRemarks(remarks);
		}
		session.save(userActionLog);
		Form_UploadBean formUpload = new Form_UploadBean();
		
		try {
			switch (instituteType) {
			case "U":
				university = fetchUniversityMasterData(aisheCode, surveyYear);
				university.setUserBy(username);
				university.setPermissionOnDate(DateUtils.obtainCurrentTimeStamp());
				university.setSpecialPermission(specialPermission);
				if (university != null) {
					session.update(university);
				}
				if(userActionLog.getActionId()==39) {
				formUpload = fetchFromUploadDataForUniversity(aisheCode, surveyYear);
				 if(formUpload!=null) {
					 session.delete(formUpload);
					 session.createNativeQuery("update university_lock_status set final_lock =false where survey_year = "+surveyYear+" "
						 		+ " and aishe_code ='"+aisheCode+"'").executeUpdate();
					 
					 
						if(userActionLog.getActionId()==39) {
							UserActionLogEO userActionLognew = new UserActionLogEO();
							userActionLognew.setId(null);
							userActionLognew.setInstitutionType(instituteType);
							userActionLognew.setInstitutionId(aisheCode);
							userActionLognew.setActionId(37);
					        userActionLog.setIpAddress(request.getHeader("X-Forwarded-For"));
							if(userActionLognew.getIpAddress()==null) {
								userActionLognew.setIpAddress(request.getRemoteAddr());
							}
							Date dates = new Date();
							Timestamp timestamp3 = new Timestamp(dates.getTime());
							userActionLognew.setActionTime(timestamp3);
							userActionLognew.setUserId(username);
							userActionLognew.setSurveyYear(surveyYear);
							Integer maxLogId = ((Integer) session.createNativeQuery("select max(id) from user_action_log").uniqueResult());
							userActionLognew.setId(maxLogId+1);
							session.save(userActionLognew);
							}
				 }
			}
				tx.commit();
				return true;
			case "S":
				standalone = fetchStandaloneMasterData(aisheCode, surveyYear);
				standalone.setUserBy(username);
				standalone.setPermissionOnDate(DateUtils.obtainCurrentTimeStamp());
				standalone.setSpecialPermission(specialPermission);
				if (standalone != null) {
					session.update(standalone);
				}
				if(userActionLog.getActionId()==39) {
				 formUpload = fetchFromUploadDataForStandalone(aisheCode, surveyYear);
				 if(formUpload!=null) {
					 session.delete(formUpload);
					 session.createNativeQuery("update standalone_lock_status set final_lock =false where survey_year = "+surveyYear+" "
						 		+ " and aishe_code ='"+aisheCode+"'").executeUpdate();
					 
						if(userActionLog.getActionId()==39) {
							UserActionLogEO userActionLognew = new UserActionLogEO();
							userActionLognew.setId(null);
							userActionLognew.setInstitutionType(instituteType);
							userActionLognew.setInstitutionId(aisheCode);
							userActionLognew.setActionId(37);
					        userActionLog.setIpAddress(request.getHeader("X-Forwarded-For"));
							if(userActionLognew.getIpAddress()==null) {
								userActionLognew.setIpAddress(request.getRemoteAddr());
							}
							Date dates = new Date();
							Timestamp timestamp3 = new Timestamp(dates.getTime());
							userActionLognew.setActionTime(timestamp3);
							userActionLognew.setUserId(username);
							userActionLognew.setSurveyYear(surveyYear);
							Integer maxLogId = ((Integer) session.createNativeQuery("select max(id) from user_action_log").uniqueResult());
							userActionLognew.setId(maxLogId+1);
							session.save(userActionLognew);
							}
				 }
				}
				tx.commit();
				return true;
			case "C":
				college = fetchCollegeMasterData(aisheCode, surveyYear);
				college.setUserBy(username);
				college.setPermissionOnDate(DateUtils.obtainCurrentTimeStamp());
				college.setSpecialPermission(specialPermission);
				if (college != null) {
					session.update(college);
				}
				if(userActionLog.getActionId()==39) {
				 formUpload = fetchFromUploadDataForCollege(aisheCode,surveyYear);
				 if(formUpload!=null) {
					 session.delete(formUpload);
					 session.createNativeQuery("update college_lock_status set final_lock =false where survey_year = "+surveyYear+" and "
						 		+ "aishe_code = '"+aisheCode+"'").executeUpdate();
					 
						if(userActionLog.getActionId()==39) {
							UserActionLogEO userActionLognew = new UserActionLogEO();
							userActionLognew.setId(null);
							userActionLognew.setInstitutionType(instituteType);
							userActionLognew.setInstitutionId(aisheCode);
							userActionLognew.setActionId(37);
					        userActionLog.setIpAddress(request.getHeader("X-Forwarded-For"));
							if(userActionLognew.getIpAddress()==null) {
								userActionLognew.setIpAddress(request.getRemoteAddr());
							}
							Date dates = new Date();
							Timestamp timestamp3 = new Timestamp(dates.getTime());
							userActionLognew.setActionTime(timestamp3);
							userActionLognew.setUserId(username);
							userActionLognew.setSurveyYear(surveyYear);
							Integer maxLogId = ((Integer) session.createNativeQuery("select max(id) from user_action_log").uniqueResult());
							userActionLognew.setId(maxLogId+1);
							session.save(userActionLognew);
							}
				 }
				}
				tx.commit();
				return true;
			}
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("deleteHostel Error" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}
	private Integer fetchSpecialPermissionAlready(String instituteType, String aisheCode, Integer surveyYear) {
		Session session = sessionFactory.openSession();
		Integer id = null;
		try {
					id = ((Integer) session.createNativeQuery("select max(id) from user_action_log_new ual where ual.action_id =39 and ual.institution_id='"+aisheCode+"' and ual.institution_type='"+instituteType+"'  and ual.survey_year ="+surveyYear+"\r\n"
							+ "and ual.id > (select max(id) from user_action_log_new where institution_id='"+aisheCode+"' and institution_type='"+instituteType+"' and action_id =40 and survey_year ="+surveyYear+")").uniqueResult());
					return id;
				}
		 catch (Exception e) {
		} finally {
			session.close();
		}
		return null;
	}
	private UniversityRefMaster fetchUniversityMasterData(String aisheCode, Integer surveyYear) {
		logger.info("MasterDaoImpl : fetchUniversityStudentHostelData method invoked :");
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityRefMaster> query = builder.createQuery(UniversityRefMaster.class);
			Root<UniversityRefMaster> allData = query.from(UniversityRefMaster.class);
			query.where(builder.and(builder.equal(allData.get("universityEmaded").get("id"), aisheCode),
					builder.equal(allData.get("universityEmaded").get("surveyYear"), surveyYear)));
			UniversityRefMaster college = session.createQuery(query).getSingleResult();
			return college;
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return null;
	}
	private StandaloneInstitutionMaster fetchStandaloneMasterData(String aisheCode, Integer surveyYear) {
		logger.info("MasterDaoImpl : fetchUniversityStudentHostelData method invoked :");
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<StandaloneInstitutionMaster> query = builder.createQuery(StandaloneInstitutionMaster.class);
			Root<StandaloneInstitutionMaster> allData = query.from(StandaloneInstitutionMaster.class);
			query.where(builder.and(builder.equal(allData.get("collegePk").get("id"), aisheCode),
					builder.equal(allData.get("collegePk").get("surveyYear"), surveyYear)));
			StandaloneInstitutionMaster college = session.createQuery(query).getSingleResult();
			return college;
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return null;
	}
	private CollegeRefEO fetchCollegeMasterData(String aisheCode, Integer surveyYear) {
		logger.info("MasterDaoImpl : fetchUniversityStudentHostelData method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CollegeRefEO> query = builder.createQuery(CollegeRefEO.class);
			Root<CollegeRefEO> allData = query.from(CollegeRefEO.class);
			query.where(builder.and(builder.equal(allData.get("universityPk").get("id"), aisheCode),
					builder.equal(allData.get("universityPk").get("surveyYear"), surveyYear)));
			CollegeRefEO college = session.createQuery(query).getSingleResult();
			return college;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("fetchCollegeMasterData Error" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	private Form_UploadBean fetchFromUploadDataForUniversity(String aishecode, Integer surveyYear) {
        Session session = sessionFactory.openSession();
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
        Session session = sessionFactory.openSession();
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
        Session session = sessionFactory.openSession();
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

@Override
public boolean activateInactivateStandalone(String aisheCode, Integer actionId, String remarks, Integer surveyYear, String username,
		HttpServletRequest request) {
	Session session = sessionFactory.openSession();
	Session session2 = sessionFactory.openSession();
	Transaction tx = session2.beginTransaction();
	Transaction tx1 = session.beginTransaction();
	DeleteStandaloneInstitution deleteStandalone = new DeleteStandaloneInstitution();
    String[] splitted = aisheCode.trim().split("\\s*-\\s*");
    String standalonType = splitted[0];
    String standalonId = splitted[1];
    UserActionLogEO userActionLognew = new UserActionLogEO();
    Date dates = new Date();
	Timestamp timestamp3 = new Timestamp(dates.getTime());
	try {
		switch (actionId) {
		case 1:
			deleteStandalone = fetchDeletedStandalone(Integer.valueOf(standalonId));
            if(surveyYear==2022) {
			RefStandaloneInstitution1 standaloneMaster = new RefStandaloneInstitution1();
			standaloneMaster.setDistrictCode(deleteStandalone.getDistrictCode());
			standaloneMaster.setName(deleteStandalone.getName());
			standaloneMaster.setStateBodyId(deleteStandalone.getStateBodyId());
			standaloneMaster.setStateCode(deleteStandalone.getStateCode());
			StandAloneEmadedPK standpk = new StandAloneEmadedPK();
			standpk.setId(Integer.valueOf(standalonId));
			standpk.setSurveyYear(2022);
			standaloneMaster.setUniversityPk(standpk);
			session2.saveOrUpdate(standaloneMaster);
            }
			RefStandaloneInstitution1 standaloneMaster1 = new RefStandaloneInstitution1();
			standaloneMaster1.setDistrictCode(deleteStandalone.getDistrictCode());
			standaloneMaster1.setName(deleteStandalone.getName());
			standaloneMaster1.setStateBodyId(deleteStandalone.getStateBodyId());
			standaloneMaster1.setStateCode(deleteStandalone.getStateCode());
			StandAloneEmadedPK standpk1 = new StandAloneEmadedPK();
			standpk1.setId(Integer.valueOf(standalonId));
			standpk1.setSurveyYear(2023);
			standaloneMaster1.setUniversityPk(standpk1);
			session.saveOrUpdate(standaloneMaster1);

			if (deleteStandalone != null) {
				session2.delete(deleteStandalone);
			}

			//// user action log entry
			userActionLognew.setId(null);
			userActionLognew.setInstitutionType(standalonType);
			userActionLognew.setInstitutionId(standalonId);
			userActionLognew.setActionId(24);
			if (userActionLognew.getIpAddress() == null) {
				userActionLognew.setIpAddress(request.getRemoteAddr());
			}
			userActionLognew.setActionTime(timestamp3);
			userActionLognew.setUserId(username);
			userActionLognew.setSurveyYear(surveyYear);
			userActionLognew.setRemarks(remarks);
			session.save(userActionLognew);
			tx1.commit();
			tx.commit();
			break;
		case 2:
			RefStandaloneInstitution1 standaloneMaster11 =null;
			if (surveyYear == 2022) {
			standaloneMaster11 = fetchStandaloneMaster(Integer.valueOf(standalonId),
					surveyYear);
			}
			RefStandaloneInstitution1 standaloneMaster111 = null;
			if (surveyYear == 2023) {
				standaloneMaster111 = fetchStandaloneMaster(Integer.valueOf(standalonId), 2023);
			}
			// add in delete master
			if(standaloneMaster11!=null && surveyYear==2022) {
				StandAloneEmadedPK standpk2 = new StandAloneEmadedPK();
				standpk2.setId(Integer.valueOf(standalonId));
				standpk2.setSurveyYear(surveyYear);
				deleteStandalone.setUniversityPk(standpk2);
				deleteStandalone.setDistrictCode(standaloneMaster11.getDistrictCode());
				deleteStandalone.setName(standaloneMaster11.getName());
				deleteStandalone.setStateBodyId(standaloneMaster11.getStateBodyId());
				deleteStandalone.setStateCode(standaloneMaster11.getStateCode());
				session2.saveOrUpdate(deleteStandalone);		
			}
			if(standaloneMaster111!=null && surveyYear>2022) {
				StandAloneEmadedPK standpk2 = new StandAloneEmadedPK();
				standpk2.setId(Integer.valueOf(standalonId));
				standpk2.setSurveyYear(surveyYear);
				deleteStandalone.setUniversityPk(standpk2);
				deleteStandalone.setDistrictCode(standaloneMaster111.getDistrictCode());
				deleteStandalone.setName(standaloneMaster111.getName());
				deleteStandalone.setStateBodyId(standaloneMaster111.getStateBodyId());
				deleteStandalone.setStateCode(standaloneMaster111.getStateCode());
				session2.saveOrUpdate(deleteStandalone);		
			}

			// delete from master 2022,2023
			if (standaloneMaster11 != null) {
				session.delete(standaloneMaster11);
			}
			if (standaloneMaster111 != null) {
				session.delete(standaloneMaster111);
			}
			//// user action log entry
			userActionLognew.setId(null);
			userActionLognew.setInstitutionType(standalonType);
			userActionLognew.setInstitutionId(standalonId);
			userActionLognew.setActionId(25);
			if (userActionLognew.getIpAddress() == null) {
				userActionLognew.setIpAddress(request.getRemoteAddr());
			}
			userActionLognew.setActionTime(timestamp3);
			userActionLognew.setUserId(username);
			userActionLognew.setSurveyYear(surveyYear);
			userActionLognew.setRemarks(remarks);
			session.save(userActionLognew);

			tx.commit();
			tx1.commit();
			break;

		}
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
private RefStandaloneInstitution1 fetchStandaloneMaster(Integer standaloneId, Integer surveyYear) {
	 Session session = sessionFactory.openSession();
     List<RefStandaloneInstitution1> list = new ArrayList<>();
     try {
         Query<RefStandaloneInstitution1> query = session.createQuery("from RefStandaloneInstitution1 where universityPk.id=:id and universityPk.surveyYear=:surveyYear ");
         query.setParameter("id", standaloneId);
         query.setParameter("surveyYear", surveyYear);
         list = query.getResultList();
         if (list.size() > 0) {
             return query.list().get(0);
         }
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         session.close();
     }
     return null;
 }
private DeleteStandaloneInstitution fetchDeletedStandalone(Integer standaloneId) {
	 Session session = sessionFactory.openSession();
     List<DeleteStandaloneInstitution> list = new ArrayList<>();
     try {
         Query<DeleteStandaloneInstitution> query = session.createQuery("from DeleteStandaloneInstitution where universityPk.id=:id ");
         query.setParameter("id", standaloneId);
         list = query.getResultList();
         if (list.size() > 0) {
             return query.list().get(0);
         }
     } catch (Exception e) {
         e.printStackTrace();
     } finally {
         session.close();
     }
     return null;
 }
}