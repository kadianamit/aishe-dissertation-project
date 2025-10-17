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
public class UniversityDaoImpl implements UniversityDao {

    @Autowired
    private SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(UniversityDaoImpl.class);

    @Override
    public boolean saveOrUpdateUniversityBasicInformation(UniversityEO university) {

        logger.info("MasterDaoImpl : saveOrUpdateUniversityBasicInformation method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(university);
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
    public Integer saveOrUpdateNodalOfficer(NodalOfficerEO nodalOfficer, String status) {

        logger.info("MasterDaoImpl : saveOrUpdateUniversityNodalOfficer method invoked :");
        Session session = sessionFactory.openSession();
        Session session2 = null;
        Transaction tx = null;
        Integer id = null;
        try {
            session2 = sessionFactory.openSession();
            tx = session2.beginTransaction();
            if (status.equalsIgnoreCase("save")) {
                id = ((Integer) session2.createQuery("select max(Id) from NodalOfficerEO").uniqueResult());
                nodalOfficer.setId(id + 1);
                session2.save(nodalOfficer);
            } else {
                session2.update(nodalOfficer);
            }
            tx.commit();
            return nodalOfficer.getId();
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
            session2.close();
        }
        return id;
    }

    @Override
    public Integer saveOrUpdateStudentHostel(StudentHostelEO studentHostel, String status) {

        logger.info("MasterDaoImpl : saveOrUpdateUniversityStudentHostel method invoked :");
        Session session = sessionFactory.openSession();
        Session session2 = null;
        Transaction tx = null;
        Integer id = null;
        try {
            session2 = sessionFactory.openSession();
            tx = session2.beginTransaction();
            if (status.equalsIgnoreCase("save")) {
                id = ((Integer) session2.createQuery("select max(Id) from StudentHostelEO").uniqueResult());
                studentHostel.setId(id + 1);
                session2.save(studentHostel);
            } else {
                session2.update(studentHostel);
            }
            tx.commit();
            return studentHostel.getId();
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
            session2.close();
        }
        return id;

    }

    @Override
    public RefStudentHostelType getHostelType(String type) {

        Session session = sessionFactory.openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RefStudentHostelType> criteriaQuery = builder.createQuery(RefStudentHostelType.class);
            Root<RefStudentHostelType> root = criteriaQuery.from(RefStudentHostelType.class);
            List<Predicate> predicates = new ArrayList<Predicate>();


            if (type != null) {
                predicates.add(builder.equal(root.get("type"), type));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
                    .orderBy(builder.asc(root.get("type")));
            Query<RefStudentHostelType> q = session.createQuery(criteriaQuery);
            List<RefStudentHostelType> list = q.getResultList();
            if (list.size() > 0) {
                return list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean saveOrUpdateUniversityHostel(UniversityHostelEO universityHostelEO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer saveOrUpdateStaffQuarter(StaffQuarterEO staffQuarter, String status) {

        logger.info("MasterDaoImpl : saveOrUpdateUniversityStaffQuarter method invoked :");
        Session session = sessionFactory.openSession();
        Session session2 = null;
        Transaction tx = null;
        Integer id = null;
        try {
            session2 = sessionFactory.openSession();
            tx = session2.beginTransaction();
            if (status.equalsIgnoreCase("save")) {
                id = ((Integer) session2.createQuery("select max(Id) from StaffQuarterEO").uniqueResult());
                staffQuarter.setId(id + 1);
                session2.save(staffQuarter);
            } else {
                session2.update(staffQuarter);
            }
            tx.commit();
            return staffQuarter.getId();
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
            session2.close();
        }
        return id;
    }

    @Override
    public UniversityEO getUniversityDetail(String universityId, Integer surveyYear) {

        Session session = sessionFactory.openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UniversityEO> criteriaQuery = builder.createQuery(UniversityEO.class);
            Root<UniversityEO> root = criteriaQuery.from(UniversityEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(builder.equal(root.get("surveyYear"), surveyYear));

            if (universityId != null) {
                predicates.add(builder.equal(root.get("id"), universityId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
                    .orderBy(builder.asc(root.get("id")));
            Query<UniversityEO> q = session.createQuery(criteriaQuery);
            List<UniversityEO> list = q.getResultList();

            if (list.size() > 0) {
                return list.get(0);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean getUniversityStudentHostel(String universityId, Integer surveyYear) {

        Session session = sessionFactory.openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UniversityStudentHostelEO> criteriaQuery = builder.createQuery(UniversityStudentHostelEO.class);
            Root<UniversityStudentHostelEO> root = criteriaQuery.from(UniversityStudentHostelEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(builder.equal(root.get("surveyYear"), surveyYear));

            if (universityId != null) {
                predicates.add(builder.equal(root.get("id"), universityId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
                    .orderBy(builder.asc(root.get("id")));
            Query<UniversityStudentHostelEO> q = session.createQuery(criteriaQuery);
            List<UniversityStudentHostelEO> list = q.getResultList();

            if (list.size() > 0) {
                return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public StudentHostelEO getStudentHostel(Integer id) {

        Session session = sessionFactory.openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<StudentHostelEO> criteriaQuery = builder.createQuery(StudentHostelEO.class);
            Root<StudentHostelEO> root = criteriaQuery.from(StudentHostelEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (id != null) {
                predicates.add(builder.equal(root.get("id"), id));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
                    .orderBy(builder.asc(root.get("id")));
            Query<StudentHostelEO> q = session.createQuery(criteriaQuery);
            List<StudentHostelEO> list = q.getResultList();

            if (list.size() > 0) {
                return list.get(0);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean saveOrUpdateUniversityStudentHostel(UniversityStudentHostelEO universityStudentHostelEO) {

        logger.info("MasterDaoImpl : saveOrUpdateUniversityStudentHostel method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(universityStudentHostelEO);
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
    public boolean saveOrUpdateUniversityAddress(UniversityEO university) {

        logger.info("MasterDaoImpl : saveOrUpdateUniversityAddress method invoked :");
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(university);
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
            logger.info("saveOrUpdateUniversityAddress Error" + e.getMessage());
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public StaffQuarterEO getStaffQuarter(Integer staffQuarterId) {

        Session session = sessionFactory.openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<StaffQuarterEO> criteriaQuery = builder.createQuery(StaffQuarterEO.class);
            Root<StaffQuarterEO> root = criteriaQuery.from(StaffQuarterEO.class);
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (staffQuarterId != null) {
                predicates.add(builder.equal(root.get("id"), staffQuarterId));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])))
                    .orderBy(builder.asc(root.get("id")));
            Query<StaffQuarterEO> q = session.createQuery(criteriaQuery);
            List<StaffQuarterEO> list = q.getResultList();
            if (list.size() > 0) {
                return list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer saveStaffQuarter(StaffQuarterEO staffQuarter, String status) {

        logger.info("MasterDaoImpl : saveOrUpdateUniversityNodalOfficer method invoked :");
        Session session = sessionFactory.openSession();
        Session session2 = null;
        Transaction tx = null;
        Integer id = null;
        try {
            session2 = sessionFactory.openSession();
            tx = session2.beginTransaction();
            if (status.equalsIgnoreCase("save")) {
                id = ((Integer) session2.createQuery("select max(Id) from StaffQuarterEO").uniqueResult());
                staffQuarter.setId(id + 1);
                session2.save(staffQuarter);
            } else {
                session2.update(staffQuarter);
            }
            tx.commit();
            return staffQuarter.getId();
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
            session2.close();
        }
        return id;
    }

}
