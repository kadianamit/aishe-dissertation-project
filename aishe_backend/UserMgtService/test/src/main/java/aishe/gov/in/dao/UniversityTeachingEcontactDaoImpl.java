package aishe.gov.in.dao;
/*
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aishe.gov.in.masterseo.TeachingEcontactEO;
import aishe.gov.in.masterseo.TeachingStaffEO;
import aishe.gov.in.mastersvo.Econtact;
@Repository
public class UniversityTeachingEcontactDaoImpl implements UniversityTeachingEcontactDao {
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(UniversityTeachingEcontactDaoImpl.class);
	
	@Override
	public boolean saveOrUpdate(TeachingEcontactEO econtact) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		TeachingStaffEO teachingStaff = new TeachingStaffEO();
		
		
		try {
			tx = session.beginTransaction();
			session.save(teachingStaff);
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
*/
