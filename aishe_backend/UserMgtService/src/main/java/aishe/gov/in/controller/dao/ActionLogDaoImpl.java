package aishe.gov.in.dao;

import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.masterseo.SurveyMasterLogEO;
import aishe.gov.in.masterseo.UserActionLogEO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ActionLogDaoImpl implements ActionLogDao {
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(ActionLogDaoImpl.class);

    @Override
    public UserActionLogEO saveUserActionLog(UserActionLogEO logEO) {
        logger.info("ActionLogDaoImpl : saveUserActionLog method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(logEO);
            tx.commit();
            return logEO;
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
    public SurveyMasterLogEO saveSurveyMasterLog(SurveyMasterLogEO surveyMasterLogEO) {
        logger.info("ActionLogDaoImpl : saveUserActionLog method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(surveyMasterLogEO);
            tx.commit();
            return surveyMasterLogEO;
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
    public Integer maxId() {
        logger.info("ActionLogDaoImpl : saveUserActionLog method invoked :");
        Session session = sessionFactory.openSession();
        try {
            return (Integer) session.createNativeQuery("select max(id) from user_action_log").uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }


	@Override
	public void updateInfoInstituteDetail(InstituteDetailEO instituteDetail) {
	        Session session = sessionFactory.openSession();
	        Transaction tx = null;
	        try {
	            tx = session.beginTransaction();
	            session.update(instituteDetail);
	            
	           
	            
	            tx.commit();
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
	    }
}
