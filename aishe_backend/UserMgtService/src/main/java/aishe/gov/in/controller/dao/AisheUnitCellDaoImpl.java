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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import aishe.gov.in.masterseo.AisheUnitCellEo;

@Repository
public class AisheUnitCellDaoImpl implements AisheUnitCellDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(AisheUnitCellDaoImpl.class);

	@Override
	public List<AisheUnitCellEo> getAisheUnitCell(String stateCode) {
		 logger.info("CisoDaoImpl : CisoInfoEO method invoked :");
	        Session session = sessionFactory.openSession();
	        try {
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<AisheUnitCellEo> query = builder.createQuery(AisheUnitCellEo.class);
	            Root<AisheUnitCellEo> allData = query.from(AisheUnitCellEo.class);
	            List<Predicate> predicates = new ArrayList<Predicate>();
	            if (stateCode != null) {
	                predicates.add(builder.equal(allData.get("refState").get("id"), stateCode));
	            }
	            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()]))).orderBy(
	                    builder.asc(allData.get("refState").get("name"))
	            );
	            List<AisheUnitCellEo> university = session.createQuery(query).getResultList();
	            return university;
	        } catch (Exception e) {
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	@Override
	public boolean saveOrUpdateAisheUnitCell(List<AisheUnitCellEo> aisheUnitCellList) {
		  logger.info("CisoDaoImpl : saveCiso method invoked :");
	        Session session = sessionFactory.openSession();
	        Transaction tx = null;
	        try {
	            tx = session.beginTransaction();
	            for(Object aisheUnitCell : aisheUnitCellList) {
	            	ObjectMapper mapper = new ObjectMapper();
	            	AisheUnitCellEo entity = mapper.convertValue(aisheUnitCell, AisheUnitCellEo.class);
                    if(entity.getId()==0) {
                    	entity.setId(null);
                    	session.save(entity);
                    }else {
                    	session.update(entity);
                    }
	            }
	            tx.commit();
	            return true;
	        } catch (Exception e) {
	            try {
	                if (tx != null && tx.isActive()) {
	                    tx.rollback();
	                }
	            } catch (Exception trEx) {
	                e.printStackTrace();
	            }
	        } finally {
	            session.close();
	        }
	        return false;
	    }

	@Override
	public boolean deleteAisheUnitCell(Integer id, boolean whetherStateHavingAisheUnitCell, String stateCode) {
		 logger.info("CisoDaoImpl : saveCiso method invoked :");
	        Session session = sessionFactory.openSession();
	        Transaction tx = null;
	        try {
	        	
	        	tx = session.beginTransaction();
	        	if(id!=null) {
	        		AisheUnitCellEo data = (AisheUnitCellEo)session.get(AisheUnitCellEo.class,id);
		            session.delete(data);
		            tx.commit();
		            return true;
	        	}else if(stateCode!=null && !whetherStateHavingAisheUnitCell) {
	        		session.createQuery("delete from AisheUnitCellEo where refState.id =:stateCode").setParameter("stateCode", stateCode).executeUpdate();
	        		 tx.commit();
	 	            return true;
	        	}
	        	
	           
	        } catch (Exception e) {
	            try {
	                if (tx != null && tx.isActive()) {
	                    tx.rollback();
	                }
	            } catch (Exception trEx) {
	                e.printStackTrace();
	            }
	        } finally {
	            session.close();
	        }
	        return false;
	    }
}