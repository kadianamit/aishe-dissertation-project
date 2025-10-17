package aishe.gov.in.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UniversityTeachingVernacularNameDaoImpl implements UniversityTeachingVernacularNameDao {
  @Autowired
 private  SessionFactory sessionFactory;
  private static final Logger logger = LoggerFactory.getLogger( UniversityTeachingVernacularNameDaoImpl.class);
	@Override
	public boolean saveOrUpdate(TeachingVernacularNamesEO vernacularName) {
		logger.info("MasterDaoImpl : saveOrUpdateUniversityVernacularName method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(vernacularName);
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
			logger.info("saveOrUpdateApplicationTypeDetails Error" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

}
