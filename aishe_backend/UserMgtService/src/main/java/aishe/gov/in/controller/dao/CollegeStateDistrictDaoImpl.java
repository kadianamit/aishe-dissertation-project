package aishe.gov.in.dao;

import aishe.gov.in.masterseo.DistrictRef;
import aishe.gov.in.masterseo.StateRef;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollegeStateDistrictDaoImpl implements CollegeStateDistrictDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(CollegeAffiliationDeaffiliationDaoImpl.class);

    @Override
    public DistrictRef getDistrictByCode(String distCode) {
        logger.info("CollegeStateDistrictDaoImpl : getDistrictByCode method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DistrictRef> criteriaQuery = builder.createQuery(DistrictRef.class);
            Root<DistrictRef> root = criteriaQuery.from(DistrictRef.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (distCode != null) {
                predicates.add(builder.equal(root.get("distCode"), distCode));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<DistrictRef> q = session.createQuery(criteriaQuery);
            return q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public StateRef getStateByCode(String stCode) {
        logger.info("CollegeStateDistrictDaoImpl : getDistrictByCode method invoked :");
        Session session = sessionFactory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<StateRef> criteriaQuery = builder.createQuery(StateRef.class);
            Root<StateRef> root = criteriaQuery.from(StateRef.class);
            List<Predicate> predicates = new ArrayList<Predicate>();

            if (stCode != null) {
                predicates.add(builder.equal(root.get("stCode"), stCode));
            }
            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
            Query<StateRef> q = session.createQuery(criteriaQuery);
            return q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
