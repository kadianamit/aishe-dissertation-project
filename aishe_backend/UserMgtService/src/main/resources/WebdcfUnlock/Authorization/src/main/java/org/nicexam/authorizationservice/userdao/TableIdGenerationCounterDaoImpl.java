package org.nicexam.authorizationservice.userdao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.nicexam.authorizationservice.usereo.OfficeAcronymEO;
import org.nicexam.authorizationservice.usereo.TableIdGenerationCounterEO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TableIdGenerationCounterDaoImpl implements TableIdGenerationCounterDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory.getLogger(TableIdGenerationCounterDaoImpl.class);
	
	//this method might need to be implemented in every daoimpl
	@Override
	public synchronized Long fetchOrUpdateTableIdSequence(String tablename) {
		logger.info("TableIdGenerationCounterDaoImpl : fetchOrUpdateTableIdSequence Method Invoked");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Long currentIdCounter=null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TableIdGenerationCounterEO> query = builder.createQuery(TableIdGenerationCounterEO.class);
			Root<TableIdGenerationCounterEO> root = query.from(TableIdGenerationCounterEO.class);
			query.select(root).where(builder.equal(root.get("tableName"), tablename));
			TableIdGenerationCounterEO idGenerationCounterEO = session.createQuery(query).getSingleResult();
			if(idGenerationCounterEO!=null) {
				currentIdCounter = idGenerationCounterEO.getIdCounter();
				idGenerationCounterEO.setIdCounter(currentIdCounter+1);
				session.update(idGenerationCounterEO);
				tx.commit();
			}
			
			return currentIdCounter;
		} catch (Exception e) {
			currentIdCounter=null;
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("saveSlider Error" + e.getMessage());
		} finally {
			session.close();
		}
		return currentIdCounter;
	}
	
}
