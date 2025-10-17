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

import aishe.gov.in.masterseo.RefCourseLevel;
import aishe.gov.in.masterseo.RefCourseMode;
import aishe.gov.in.masterseo.RefCourseType;
import aishe.gov.in.masterseo.RefExaminationSystem;

@Repository
public class UniversityFacultyDepartmentDaoImpl implements UniversityFacultyDepartmentDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(UniversityDaoImpl.class);


	@Override
	public Boolean saveOrUpdateUniversityFaculty(UniversityFaculty universityFaculty,String status) {
		
		logger.info("MasterDaoImpl : saveOrUpdateRegionalCenter method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = null;		
		try {
			tx = session.beginTransaction();
			if(status.equalsIgnoreCase("save")) {
			   session.save(universityFaculty);
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
	public UniversityFaculty getUniversityFaculty(String universityId, Integer id) {
		Session session = sessionFactory.openSession();
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityFaculty> criteriaQuery = builder.createQuery(UniversityFaculty.class);
			Root<UniversityFaculty> root = criteriaQuery.from(UniversityFaculty.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (universityId != null) {
				predicates.add(builder.equal(root.get("universityId"), universityId));
			}
			if (id != null) {
				predicates.add(builder.equal(root.get("faculty").get("id"), id));
			}
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("universityId")));

			Query<UniversityFaculty> q = session.createQuery(criteriaQuery);
			List<UniversityFaculty> list = q.getResultList();
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
	public FacultyDepartment getUniversityFacultyDepartment(Integer departmentId) {
		Session session = sessionFactory.openSession();
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<FacultyDepartment> criteriaQuery = builder.createQuery(FacultyDepartment.class);
			Root<FacultyDepartment> root = criteriaQuery.from(FacultyDepartment.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (departmentId != null) {
				predicates.add(builder.equal(root.get("department").get("id"), departmentId));
			}
			
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("department").get("id")));

			Query<FacultyDepartment> q = session.createQuery(criteriaQuery);
			List<FacultyDepartment> list = q.getResultList();
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
	public Boolean saveOrUpdateUniversityFacultyDepartment(FacultyDepartment facultyDepartment, String status) {
		
		logger.info("MasterDaoImpl : saveOrUpdateRegionalCenter method invoked :");
		Session session = sessionFactory.openSession();
		Session session2=null;
		Transaction tx = null;	
		Transaction tx1 = null;		
		Integer id = null;
		try {

			tx = session.beginTransaction();
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from Department").uniqueResult());
				FacultyDepartment facultyDepartmentEO=new FacultyDepartment();
				Department department=new Department();
				department.setId(id+1);
				department.setName(facultyDepartment.getDepartment().getName());
				Integer deptId=(Integer)session.save(department);
				tx.commit();
				session2=sessionFactory.openSession();
				tx1 = session2.beginTransaction();
				Department department2=new Department();
				department2.setId(deptId);
				department2.setName(facultyDepartment.getDepartment().getName());
				facultyDepartmentEO.setDepartment(department2);
				facultyDepartmentEO.setFacultyId(facultyDepartment.getFacultyId());
				session2.save(facultyDepartmentEO);
				tx1.commit();
			}
			else {
				session.update(facultyDepartment.getDepartment());
				tx.commit();
			}
			
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
	public Boolean saveOrUpdateUniversityFacultyRegularProgramme(FacultyCourse facultyCourse, String status) {
		
		logger.info("MasterDaoImpl : saveOrUpdateRegionalCenter method invoked :");
		Session session = sessionFactory.openSession();
		Session session2=null;
		Transaction tx = null;
		Transaction tx1 = null;
		Integer id = null;
		try {
			tx = session.beginTransaction();
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from Course").uniqueResult());
				Course course=facultyCourse.getCourseId();
				course.setId(id+1);
				Integer courseId=(Integer)session.save(course);
				tx.commit();
				session2=sessionFactory.openSession();
				tx1 = session2.beginTransaction();
				course.setId(courseId);
				facultyCourse.setCourseId(course);
				session2.save(facultyCourse);
				tx1.commit();
				
			}
			else {
				session.update(facultyCourse.getCourseId());
				tx.commit();
			}
			
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
	public FacultyCourse getUniversityFacultyRegularProgramme(Integer courseId) {
		Session session = sessionFactory.openSession();
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<FacultyCourse> criteriaQuery = builder.createQuery(FacultyCourse.class);
			Root<FacultyCourse> root = criteriaQuery.from(FacultyCourse.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (courseId != null) {
				predicates.add(builder.equal(root.get("courseId").get("id"), courseId));
			}			
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("courseId").get("id")));
			Query<FacultyCourse> q = session.createQuery(criteriaQuery);
			List<FacultyCourse> list = q.getResultList();
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
	public Integer saveOrUpdateFaculty(Faculty faculty, String status) {
		
		logger.info("MasterDaoImpl : saveOrUpdateRegionalCenter method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = null;		
		Integer id = null;
		Integer facultyId=faculty.getId();
		try {

			tx = session.beginTransaction();
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from Faculty").uniqueResult());
				faculty.setId(id+1);
				faculty.setName(faculty.getName());
				facultyId=(Integer)session.save(faculty);
				
			}
			else {
				session.update(faculty);
			}
			tx.commit();
			return facultyId;
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
		return facultyId;
	}


	@Override
	public Integer saveOrUpdateDepartment(Department department, String status) {
		
		logger.info("MasterDaoImpl : saveOrUpdateRegionalCenter method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = null;		
		Integer id = null;
		Integer departmentId=department.getId();
		try {

			tx = session.beginTransaction();
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from Department").uniqueResult());
				department.setId(id+1);
				department.setName(department.getName());
				departmentId=(Integer)session.save(department);
				
			}
			else {
				session.update(department);
			}
			tx.commit();
			return departmentId;
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
		return departmentId;
	}


	@Override
	public DepartmentCourseEO getUniversityDepartmentRegularProgramme(Integer courseId) {
		Session session = sessionFactory.openSession();
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<DepartmentCourseEO> criteriaQuery = builder.createQuery(DepartmentCourseEO.class);
			Root<DepartmentCourseEO> root = criteriaQuery.from(DepartmentCourseEO.class);
			List<Predicate> predicates = new ArrayList<Predicate>();
			if (courseId != null) {
				predicates.add(builder.equal(root.get("courseId").get("id"), courseId));
			}			
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("courseId").get("id")));
			Query<DepartmentCourseEO> q = session.createQuery(criteriaQuery);
			List<DepartmentCourseEO> list = q.getResultList();
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
	public Boolean saveOrUpdateUniversityDepartmentRegularProgramme(DepartmentCourse departmentCourse,
			String status) {
		
		logger.info("MasterDaoImpl : saveOrUpdateDepartmentRegularProgramme method invoked :");
		Session session = sessionFactory.openSession();
		Session session2=null;
		Session session3=null;
		Transaction tx = null;
		Transaction tx1 = null;
		Transaction tx2 = null;
		Integer id = null;
		try {
			tx = session.beginTransaction();
			Course course=new Course();			
			RefAdmissionCriteria admissionCriterionId =new RefAdmissionCriteria();			
			admissionCriterionId.setId(admissionCriterionId.getId());
			admissionCriterionId.setName(admissionCriterionId.getName());
			course.setAdmissionCriterionId(admissionCriterionId);			
			RefBroadDisciplineGroup broadDisciplineGroup =new RefBroadDisciplineGroup();
			broadDisciplineGroup.setId(broadDisciplineGroup.getId());
			broadDisciplineGroup.setDisciplineGroup(broadDisciplineGroup.getDisciplineGroup());
			course.setBroadDisciplineGroup(broadDisciplineGroup);			
			RefBroadDisciplineGroupCategory broadDisciplineGroupCategoryId =new RefBroadDisciplineGroupCategory();
			broadDisciplineGroupCategoryId.setDisciplineGroupCategory(broadDisciplineGroupCategoryId.getDisciplineGroupCategory());
			broadDisciplineGroupCategoryId.setId(broadDisciplineGroupCategoryId.getId());
			course.setBroadDisciplineGroupCategoryId(broadDisciplineGroupCategoryId);
			course.setDiscipline(course.getDiscipline());
			course.setDurationMonth(course.getDurationMonth());
			course.setDurationYear(course.getDurationYear());			
			RefExaminationSystem examinationSystem =new RefExaminationSystem();			
			examinationSystem.setId(examinationSystem.getId());
			examinationSystem.setSystem(examinationSystem.getSystem());
			course.setExaminationSystem(examinationSystem);
			course.setIntake(course.getIntake());			
			RefCourseLevel levelId=new RefCourseLevel();			
			levelId.setId(levelId.getId());
			levelId.setLevel(levelId.getLevel());
			levelId.setName(levelId.getName());
			course.setLevelId(levelId);			
			RefCourseMode modeId =new RefCourseMode();			
			modeId.setId(modeId .getId());
			modeId.setMode(modeId .getMode());
			course.setModeId(modeId);
			RefProgramme programmeId=new RefProgramme();
			programmeId.setId(departmentCourse.getCourseId().getProgrammeId().getId());
			programmeId.setProgramme(departmentCourse.getCourseId().getProgrammeId().getProgramme());
			programmeId.setCourseLevelId(departmentCourse.getCourseId().getProgrammeId().getCourseLevelId());			
			course.setProgrammeId(programmeId);			
			RefCourseType type =new RefCourseType();			
			type.setId(type.getId());
			type.setType(type.getType());
			course.setType(type);
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from Course").uniqueResult());
				course.setId(id+1);
				Integer courseId=(Integer)session.save(course);
				tx.commit();
				session2=sessionFactory.openSession();
				tx1 = session2.beginTransaction();
				session2.save(departmentCourse);
				tx1.commit();
				DepartmentCourse departmentCourseEO=new DepartmentCourse();
				departmentCourse.setCourseId(course);
				session2.save(departmentCourse);
				tx1.commit();
				if(departmentCourse!=null) {
					session3=sessionFactory.openSession();
					tx2 = session2.beginTransaction();
					FacultyDepartment facultyDepartment=new FacultyDepartment();					
					facultyDepartment.setFacultyId(facultyDepartment.getFacultyId());					
					Department department=new Department();					
					department.setId(department.getId());
					department.setName(department.getName());
					facultyDepartment.setDepartment(department);
					session3.save(facultyDepartment);
					tx2.commit();
				}
				
			}
			else {				
				session.update(course);
				tx.commit();
			}
			
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
	public UniversityCourseEO getUniversityOtherProgramme(Integer courseId, Integer surveyYear, String universityId) {
		Session session = sessionFactory.openSession();
		try {

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UniversityCourseEO> criteriaQuery = builder.createQuery(UniversityCourseEO.class);
			Root<UniversityCourseEO> root = criteriaQuery.from(UniversityCourseEO.class);
			List<Predicate> predicates = new ArrayList<Predicate>();

			if (universityId != null) {
				predicates.add(builder.equal(root.get("universityId"), universityId));
			}
			predicates.add(builder.equal(root.get("surveyYear"), surveyYear));
			predicates.add(builder.equal(root.get("course").get("id"), courseId));

			
			criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
					.orderBy(builder.asc(root.get("universityId")));

			Query<UniversityCourseEO> q = session.createQuery(criteriaQuery);
			List<UniversityCourseEO> list = q.getResultList();
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
	public Boolean saveOrUpdateUniversityOtherProgramme(OtherCourseVO otherCourseVO, String status) {
		
		logger.info("MasterDaoImpl : saveOrUpdateRegionalCenter method invoked :");
		Session session = sessionFactory.openSession();
		Session session2=null;
		Transaction tx = null;
		Transaction tx1 = null;
		Integer id = null;
		try {
			tx = session.beginTransaction();
			if (status.equalsIgnoreCase("save")) {
				id = ((Integer) session.createQuery("select max(id) from Course").uniqueResult());
				otherCourseVO.setId(id+1);
				Integer courseId=(Integer)session.save(otherCourseVO);
				tx.commit();
				session2=sessionFactory.openSession();
				tx1 = session2.beginTransaction();
				Course course=new Course();
				course.setId(courseId);
				UniversityCourseEO universityCourseEO=new UniversityCourseEO();
				universityCourseEO.setCourse(course);
				session2.save(universityCourseEO);
				tx1.commit();
				
			}
			else {
				session.update(otherCourseVO);
				tx.commit();
			}
			
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
