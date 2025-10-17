package aishe.gov.in.dao;

import java.util.ArrayList;
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

import aishe.gov.in.masterseo.UniversityEO;

@Repository
public class UniversityRegionalCentreDaoImpl implements UniversityRegionalCentreDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(UniversityDaoImpl.class);

	@Override
	public Boolean saveOrUpdateRegionalCenter(RegionalCenter regionalCenter, String status) {

		logger.info("MasterDaoImpl : saveOrUpdateRegionalCenter method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer id = null;
		try {
			tx = session.beginTransaction();
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from RegionalCenter").uniqueResult());
				regionalCenter.setId(id + 1);
				session.save(regionalCenter);
			} else {
				session.update(regionalCenter);
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

	@Override
	public Boolean updateUniversityRegionalCenter(Boolean programmeThroughDistanceMode, String universityId) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			 String qryString = "update UniversityEO s set s.offersDistanceProgramme=true where s.id=:universityId ";
		        Query query = session.createQuery(qryString);
		        query.setParameter("universityId", universityId);
		      //  query.setParameter("programmeThroughDistanceMode", programmeThroughDistanceMode);
		        query.executeUpdate();
		        tx.commit();
		        return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public RegionalCenter getRegionalCenter(String universityId, Integer surveyYear, Integer id) {
		Session session = sessionFactory.openSession();
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RegionalCenter> criteriaQuery = builder.createQuery(RegionalCenter.class);
			Root<RegionalCenter> root = criteriaQuery.from(RegionalCenter.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (universityId != null) {
				predicates.add(builder.equal(root.get("universityId"), universityId));
			}
			predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
			predicates.add(builder.equal(root.get("id"), id));			
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("id")));

			Query<RegionalCenter> q = session.createQuery(criteriaQuery);
			List<RegionalCenter> list = q.getResultList();
			if(list.size()>0) {
				return list.get(0);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}

	@Override
	public UniversityEO getUniversity(String universityId, Integer surveyYear) {
		Session session = sessionFactory.openSession();
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityEO> criteriaQuery = builder.createQuery(UniversityEO.class);
			Root<UniversityEO> root = criteriaQuery.from(UniversityEO.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (universityId != null) {
				predicates.add(builder.equal(root.get("id"), universityId));
			}
			if (surveyYear != null) {
			predicates.add(builder.equal(root.get("surveyYear"), surveyYear));		
			}
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("id")));

			Query<UniversityEO> q = session.createQuery(criteriaQuery);
			List<UniversityEO> list = q.getResultList();
			if(list.size()>0) {
				return list.get(0);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}

}
