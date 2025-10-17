package aishe.gov.in.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aishe.gov.in.masterseo.UserActionLogEONew;

@Repository
public class UserActionLogDaoImpl implements UserActionLogDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(CollegeAffiliationDeaffiliationDaoImpl.class);

    @Override
    public boolean saveUserActionLog(UserActionLogEONew userActionLog) {
        {
            logger.info("UserActionLogDaoImpl : saveUserActionLog method invoked :");
            Session session = sessionFactory.openSession();
            Transaction tx = null;
           // Integer id = null;
            try {
                tx = session.beginTransaction();
                session.save(userActionLog);
                tx.commit();
                session.close();
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
    }

    @Override
    public Integer getMaxId() {
        logger.info("UserActionLogDaoImpl : getMaxId method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer id = null;
        try {
            tx = session.beginTransaction();
            id = ((Integer) session.createQuery("select max(id) from UserActionLogEO ").uniqueResult());
            tx.commit();
            session.close();
            return id;
        } catch (Exception e) {
            try {
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            } catch (Exception trEx) {
                logger.error("Couldn’t roll back transaction" + trEx.getMessage());
            }
            logger.info("UserActionLogDaoImpl's saveUserActionLog method Error" + e.getMessage());
        } finally {
            session.close();
        }
        return null;
    }
}
