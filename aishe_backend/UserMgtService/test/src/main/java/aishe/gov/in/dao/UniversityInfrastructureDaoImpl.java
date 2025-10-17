package aishe.gov.in.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aishe.gov.in.masterseo.UniversityEO;

@Repository
public class UniversityInfrastructureDaoImpl implements UniversityInfrastructureDao {
 @Autowired
 private SessionFactory sessionFactory;
 
 @Autowired
UniversityAisheDao universityDao;
 private static final Logger logger = LoggerFactory.getLogger(UniversityInfrastructureDaoImpl.class);
	@Override
	public boolean saveOrUpdateUniversityInfrastructure(Infrastructure Infrastructure) {
		logger.info("MasterDaoImpl : saveOrUpdateUniversityInfrastructureDaoImpl method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			UniversityEO university = universityDao.fetchUniversityInformation(Infrastructure.getAisheCode().toString(),
					Infrastructure.getYear());
			tx = session.beginTransaction();
			if(university!=null) {
			session.saveOrUpdate(Infrastructure);
			Integer infraId = Infrastructure.getId();
			university.setInfrastructureId(infraId);
			session.saveOrUpdate(university);
			}
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
