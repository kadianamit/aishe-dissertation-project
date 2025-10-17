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

@Repository
public class UniversityEnrolledStudentDaoImpl implements UniversityEnrolledStudentDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(UniversityEnrolledStudentDaoImpl.class);


	@Override
	public UniversityEnrolledRegularStudentEO getUniversityEnrolledRegularStudent(String universityId, Integer enrolledRegularStudentId,
			Integer surveyYear) {
		logger.info("MasterDaoImpl : getUniversityEnrolledRegularStudent method invoked :");
		Session session = sessionFactory.openSession();
		try {			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityEnrolledRegularStudentEO> criteriaQuery = builder.createQuery(UniversityEnrolledRegularStudentEO.class);
			Root<UniversityEnrolledRegularStudentEO> root = criteriaQuery.from(UniversityEnrolledRegularStudentEO.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
		
			if (universityId != null) {
				predicates.add(builder.equal(root.get("universityId"), universityId));
			}
			if (surveyYear != null) {
				predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
			}
			predicates.add(builder.equal(root.get("enrolledRegularStudent").get("id"), enrolledRegularStudentId));
			
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("universityId")));

			Query<UniversityEnrolledRegularStudentEO> q = session.createQuery(criteriaQuery);
			List<UniversityEnrolledRegularStudentEO> list = q.setMaxResults(10000).getResultList();
			if(list.size()>0) {
				return list.get(0);
			}
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}


	@Override
	public Integer saveOrUpdateEnrolledRegularStudent(String status,String universityId, Integer enrolledRegularStudentId, Integer surveyYear,
			String faculty, String department) {
		
		logger.info("MasterDaoImpl : saveOrUpdateEnrolledRegularStudent method invoked :");
		Session session = sessionFactory.openSession();
		Session session2=null;
		Transaction tx = null;	
		Transaction tx1 = null;		
		Integer id = null;
		try {

			tx = session.beginTransaction();
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from EnrolledRegularStudentEO").uniqueResult());
				EnrolledRegularStudentEO enrolledRegularStudentEO=new EnrolledRegularStudentEO();
				enrolledRegularStudentEO.setId(id+1);
				enrolledRegularStudentEO.setFacultyName(faculty);
				enrolledRegularStudentEO.setDepartmentName(department);
				Integer enrolledId=(Integer)session.save(enrolledRegularStudentEO);
				tx.commit();
				session2=sessionFactory.openSession();
				tx1 = session2.beginTransaction();
				
				enrolledRegularStudentEO.setId(enrolledId);
				UniversityEnrolledRegularStudentEO universityEnrolledRegularStudentEO=new UniversityEnrolledRegularStudentEO();
				universityEnrolledRegularStudentEO.setEnrolledRegularStudent(enrolledRegularStudentEO);
				universityEnrolledRegularStudentEO.setSurveyYear(surveyYear);
				session2.save(universityEnrolledRegularStudentEO);
				tx1.commit();
				return enrolledId;
			}
			else {
				EnrolledRegularStudentEO enrolledRegularStudentEO=new EnrolledRegularStudentEO();
				enrolledRegularStudentEO.setId(enrolledRegularStudentId);
				enrolledRegularStudentEO.setFacultyName(faculty);
				enrolledRegularStudentEO.setDepartmentName(department);
				session.update(enrolledRegularStudentEO);
				tx.commit();
				
			}
			
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("saveOrUpdateEnrolledRegularStudent Error" + e.getMessage());
		} finally {
			session.close();
		}
		return enrolledRegularStudentId;
	}


	@Override
	public EnrolledRegularStudentCountEO getEnrolledRegularStudentCount(Integer enrolledRegularStudentId,
			Integer enrolledStudentCountId) {
		logger.info("MasterDaoImpl : getEnrolledRegularStudentCount method invoked :");
		Session session = sessionFactory.openSession();
		try {			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<EnrolledRegularStudentCountEO> criteriaQuery = builder.createQuery(EnrolledRegularStudentCountEO.class);
			Root<EnrolledRegularStudentCountEO> root = criteriaQuery.from(EnrolledRegularStudentCountEO.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
		
			if (enrolledRegularStudentId != null) {
				predicates.add(builder.equal(root.get("enrolledRegularStudentId"), enrolledRegularStudentId));
			}
			if (enrolledStudentCountId != null) {
				predicates.add(builder.equal(root.get("enrolledStudentCountId"), enrolledStudentCountId));
			}
			predicates.add(builder.equal(root.get("enrolledRegularStudent").get("id"), enrolledRegularStudentId));
			
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("enrolledRegularStudentId")));

			Query<EnrolledRegularStudentCountEO> q = session.createQuery(criteriaQuery);
			List<EnrolledRegularStudentCountEO> list = q.setMaxResults(10000).getResultList();
			if(list.size()>0) {
				return list.get(0);
			}
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}

	@Override
	public Integer savePersonCountByCategory(PersonsCountByCategory personCategoryCount) {
		
		logger.info("MasterDaoImpl : savePersonCountByCategory method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = null;	
		Integer id = null;
		try {

			tx = session.beginTransaction();
				id = ((Integer) session.createQuery("select max(id) from PersonsCountByCategory").uniqueResult());
				personCategoryCount.setId(id+1);
				Integer personCountByCategoryId=(Integer)session.save(personCategoryCount);
				tx.commit();				
				return personCountByCategoryId;
			
			
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("savePersonCountByCategory Error" + e.getMessage());
		} finally {
			session.close();
		}
		return id;
	}
	@Override
	public Integer saveOrUpdateStudentCount(String status, Integer enrolledRegularStudentId,EnrolledStudentCountEO studentEnrollment) {
		
		logger.info("MasterDaoImpl : saveOrUpdateStudentCount method invoked :");
		Session session = sessionFactory.openSession();
		Session session2=null;
		Session session3=null;
		Transaction tx = null;	
		Transaction tx1 = null;
		Transaction tx2 = null;
		Integer id = null;
		try {

			tx = session.beginTransaction();
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from PersonsCountByCategory").uniqueResult());
				PersonsCountByCategory personCategoryCount=new PersonsCountByCategory();
				personCategoryCount.setId(id+1);
				Integer personCountByCategoryId=(Integer)session.save(personCategoryCount);
				tx.commit();
				
				session2=sessionFactory.openSession();
				tx1 = session2.beginTransaction();
				Integer enrolledStudentCountId = ((Integer) session2.createQuery("select max(id) from EnrolledStudentCountEO").uniqueResult());
				studentEnrollment.setId(enrolledStudentCountId+1);
				personCategoryCount.setId(personCountByCategoryId);
				studentEnrollment.setPersonCategoryCount(personCategoryCount);
				Integer enrolledId=(Integer)session2.save(studentEnrollment);
				tx1.commit();
				session3=sessionFactory.openSession();
				tx2 = session3.beginTransaction();				
				Integer enrolledRegularStudentCountId = ((Integer) session3.createQuery("select max(id) from EnrolledRegularStudentCountEO").uniqueResult());
				EnrolledRegularStudentCountEO enrolledRegularStudentCountEO=new EnrolledRegularStudentCountEO();
				enrolledRegularStudentCountEO.setEnrolledStudentCountId(enrolledStudentCountId);
				enrolledRegularStudentCountEO.setEnrolledRegularStudentId(enrolledRegularStudentCountId+1);				
				session3.save(enrolledRegularStudentCountEO);
				tx2.commit();
				return enrolledId;
			}
			else {
				session.update(studentEnrollment);
				tx.commit();				
			}
			
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("saveOrUpdateStudentCount Error" + e.getMessage());
		} finally {
			session.close();
		}
		return enrolledRegularStudentId;
	}


	@Override
	public UniversityEnrolledDistanceStudentEO getUniversityEnrolledDistanceStudent(String universityId, Integer enrolledDistanceStudentId,
			Integer surveyYear) {
		logger.info("MasterDaoImpl : getUniversityEnrolledDistanceStudent method invoked :");
		Session session = sessionFactory.openSession();
		try {			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityEnrolledDistanceStudentEO> criteriaQuery = builder.createQuery(UniversityEnrolledDistanceStudentEO.class);
			Root<UniversityEnrolledDistanceStudentEO> root = criteriaQuery.from(UniversityEnrolledDistanceStudentEO.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
		
			if (universityId != null) {
				predicates.add(builder.equal(root.get("universityId"), universityId));
			}
			if (surveyYear != null) {
				predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
			}
			predicates.add(builder.equal(root.get("enrolledDistanceStudentUniversity").get("id"), enrolledDistanceStudentId));
			
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("universityId")));

			Query<UniversityEnrolledDistanceStudentEO> q = session.createQuery(criteriaQuery);
			List<UniversityEnrolledDistanceStudentEO> list = q.setMaxResults(10000).getResultList();
			if(list.size()>0) {
				return list.get(0);
			}
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}


	@Override
	public Integer saveOrUpdateEnrolledDistanceStudent(String status, String universityId,
			Integer enrolledDistanceStudentId, Integer surveyYear, String faculty, String department) {
		/*
		 * 
		 * logger.
		 * info("MasterDaoImpl : saveOrUpdateEnrolledRegularStudent method invoked :");
		 * Session session = sessionFactory.openSession(); Session session2=null;
		 * Transaction tx = null; Transaction tx1 = null; Integer id = null; try {
		 * 
		 * tx = session.beginTransaction(); if (status.equalsIgnoreCase("save")) { id =
		 * ((Integer)
		 * session.createQuery("select max(id) from EnrolledDistanceStudentUniversityEO"
		 * ).uniqueResult()); EnrolledDistanceStudentUniversityEO
		 * enrolledRegularStudentEO=new EnrolledDistanceStudentUniversityEO();
		 * enrolledRegularStudentEO.setId(id+1); Integer
		 * enrolledId=(Integer)session.save(enrolledRegularStudentEO); tx.commit();
		 * session2=sessionFactory.openSession(); tx1 = session2.beginTransaction();
		 * 
		 * enrolledRegularStudentEO.setId(enrolledId);
		 * UniversityEnrolledRegularStudentEO universityEnrolledRegularStudentEO=new
		 * UniversityEnrolledRegularStudentEO();
		 * universityEnrolledRegularStudentEO.setEnrolledRegularStudent(
		 * enrolledRegularStudentEO);
		 * universityEnrolledRegularStudentEO.setSurveyYear(surveyYear);
		 * session2.save(universityEnrolledRegularStudentEO); tx1.commit(); return
		 * enrolledId; } else { EnrolledRegularStudentEO enrolledRegularStudentEO=new
		 * EnrolledRegularStudentEO();
		 * enrolledRegularStudentEO.setId(enrolledRegularStudentId);
		 * enrolledRegularStudentEO.setFacultyName(faculty);
		 * enrolledRegularStudentEO.setDepartmentName(department);
		 * session.update(enrolledRegularStudentEO); tx.commit();
		 * 
		 * }
		 * 
		 * } catch (Exception e) { try { if (tx != null && tx.isActive()) {
		 * tx.rollback(); } } catch (Exception trEx) {
		 * logger.error("Couldn’t roll back transaction" + trEx.getMessage()); }
		 * logger.info("saveOrUpdateEnrolledRegularStudent Error" + e.getMessage()); }
		 * finally { session.close(); } return enrolledRegularStudentId;
		 */
		return null;	
	}


	@Override
	public CourseEnrolledStudentCountEO getCourseEnrolledStudent(Integer courseId, Integer enrolledStudentCountId) {
		logger.info("MasterDaoImpl : getUniversityEnrolledDistanceStudent method invoked :");
		Session session = sessionFactory.openSession();
		try {			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CourseEnrolledStudentCountEO> criteriaQuery = builder.createQuery(CourseEnrolledStudentCountEO.class);
			Root<CourseEnrolledStudentCountEO> root = criteriaQuery.from(CourseEnrolledStudentCountEO.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
		
			if (courseId != null) {
				predicates.add(builder.equal(root.get("course").get("id"), courseId));
			}
			if (enrolledStudentCountId != null) {
				predicates.add(builder.equal(root.get("enrolledStudentCount").get("id"), enrolledStudentCountId));
			}
			predicates.add(builder.equal(root.get("enrolledDistanceStudentUniversity").get("id"), enrolledStudentCountId));
			
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("course").get("id")));

			Query<CourseEnrolledStudentCountEO> q = session.createQuery(criteriaQuery);
			List<CourseEnrolledStudentCountEO> list = q.setMaxResults(10000).getResultList();
			if(list.size()>0) {
				return list.get(0);
			}
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}


	@Override
	public Integer saveOrUpdateStudentThroughFaculty(String status,	CourseEnrolledStudentCountEO countCourseEnrolledStudentCountEO) {
		
		logger.info("MasterDaoImpl : saveOrUpdateStudentCount method invoked :");
		Session session = sessionFactory.openSession();
		Session session2=null;
		Session session3=null;
		Transaction tx = null;	
		Transaction tx1 = null;
		Transaction tx2 = null;
		Integer id = null;
		try {

			tx = session.beginTransaction();
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from PersonsCountByCategory").uniqueResult());
				PersonsCountByCategory personCategoryCount=new PersonsCountByCategory();
				personCategoryCount.setId(id+1);
				Integer personCountByCategoryId=(Integer)session.save(personCategoryCount);
				tx.commit();
				
				session2=sessionFactory.openSession();
				tx1 = session2.beginTransaction();
				Integer enrolledStudentCountId = ((Integer) session2.createQuery("select max(id) from EnrolledStudentCountEO").uniqueResult());
				EnrolledStudentCountEO enrolledStudentCountEO=countCourseEnrolledStudentCountEO.getEnrolledStudentCount();
				enrolledStudentCountEO.setId(enrolledStudentCountId+1);
				personCategoryCount.setId(personCountByCategoryId);
				enrolledStudentCountEO.setPersonCategoryCount(personCategoryCount);
				Integer enrolledStudentId=(Integer)session2.save(enrolledStudentCountEO);
				tx1.commit();
				session3=sessionFactory.openSession();
				tx2 = session3.beginTransaction();				
				Integer courseEnrolledId = ((Integer) session3.createQuery("select max(id) from CourseEnrolledStudentCountEO").uniqueResult());
				countCourseEnrolledStudentCountEO.setId(courseEnrolledId+1);
				countCourseEnrolledStudentCountEO.setCourse(countCourseEnrolledStudentCountEO.getCourse());
				enrolledStudentCountEO.setId(enrolledStudentId);
				countCourseEnrolledStudentCountEO.setEnrolledStudentCount(enrolledStudentCountEO);
				session3.save(countCourseEnrolledStudentCountEO);
				tx2.commit();
				return enrolledStudentId;
			}
			else {
				session.update(countCourseEnrolledStudentCountEO);
				tx.commit();				
			}
			
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.info("saveOrUpdateStudentCount Error" + e.getMessage());
		} finally {
			session.close();
		}
		return countCourseEnrolledStudentCountEO.getEnrolledStudentCount().getId();
	}


	@Override
	public EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO getOtherMinority(Integer enrolledRegularStudentId,Integer otherMinorityCategoryCountId) {
		logger.info("MasterDaoImpl : getUniversityEnrolledDistanceStudent method invoked :");
		Session session = sessionFactory.openSession();
		try {			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO> criteriaQuery = builder.createQuery(EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO.class);
			Root<EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO> root = criteriaQuery.from(EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
		
			if (enrolledRegularStudentId != null) {
				predicates.add(builder.equal(root.get("enrolledStudentCountId"), enrolledRegularStudentId));
			}
			if (otherMinorityCategoryCountId != null) {
				predicates.add(builder.equal(root.get("otherMinorityCategoryCountEO").get("id"), otherMinorityCategoryCountId));
			}
			
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("enrolledStudentCountId")));

			Query<EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO> q = session.createQuery(criteriaQuery);
			List<EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO> list = q.setMaxResults(10000).getResultList();
			if(list.size()>0) {
				return list.get(0);
			}
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return null;
	}


	@Override
	public Boolean saveOtherMinorityPersonsCount(String status, Integer enrolledRegularStudentId,
			OtherMinorityCategoryCountEO otherMinorityCategoryCount) {
		
		logger.info("MasterDaoImpl : saveOrUpdateEnrolledRegularStudent method invoked :");
		Session session = sessionFactory.openSession();
		Session session2=null;
		Transaction tx = null;	
		Transaction tx1 = null;		
		Integer id = null;
		try {

			tx = session.beginTransaction();
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from OtherMinorityCategoryCountEO").uniqueResult());
				otherMinorityCategoryCount.setId(id+1);
				Integer otherMinorityId=(Integer)session.save(otherMinorityCategoryCount);
				tx.commit();
				session2=sessionFactory.openSession();
				tx1 = session2.beginTransaction();
				
				EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO enrolledStudentCountOtherMinorityPersonsCountByCategoryEO=new EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO();
				enrolledStudentCountOtherMinorityPersonsCountByCategoryEO.setEnrolledStudentCountId(enrolledRegularStudentId);
				otherMinorityCategoryCount.setId(otherMinorityId);
				enrolledStudentCountOtherMinorityPersonsCountByCategoryEO.setOtherMinorityCategoryCountEO(otherMinorityCategoryCount);				
				session2.save(enrolledStudentCountOtherMinorityPersonsCountByCategoryEO);
				tx1.commit();
				return true;
			}
			else {
				
				session.update(otherMinorityCategoryCount);
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
			logger.info("saveOrUpdateEnrolledRegularStudent Error" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

}
