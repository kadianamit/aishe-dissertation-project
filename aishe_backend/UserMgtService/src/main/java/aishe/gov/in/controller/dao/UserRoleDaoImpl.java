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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import aishe.gov.in.masterseo.UserRoleEO;
import aishe.gov.in.masterseo.UserRoleMappingEO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.utility.DateUtils;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {
    @Autowired
    private SessionFactory sessionFactory;

	@Override
	public List<UserRoleEO> userRoles() {
	        Session session = sessionFactory.openSession();
	        try {
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<UserRoleEO> query = builder.createQuery(UserRoleEO.class);
	            Root<UserRoleEO> allData = query.from(UserRoleEO.class);
	            query.where(builder.equal(allData.get("status"), "A"));
	            List<UserRoleEO> university = session.createQuery(query).getResultList();
	            return university;
	        } catch (Exception e) {
	        } finally {
	            session.close();
	        }
	        return null;
	    }

	@Override
	public List<UserRoleMappingEO> userRoleMapping(String userId) {
	        Session session = sessionFactory.openSession();
	        try {
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<UserRoleMappingEO> query = builder.createQuery(UserRoleMappingEO.class);
	            Root<UserRoleMappingEO> allData = query.from(UserRoleMappingEO.class);
	            List<Predicate> predicates = new ArrayList<Predicate>();
	            if (userId != null) {
	                predicates.add(builder.equal(allData.get("userId"), userId));
	            }
	            query.select(allData).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
	            List<UserRoleMappingEO> university = session.createQuery(query).getResultList();
	            return university;
	        } catch (Exception e) {
	        } finally {
	            session.close();
	        }
	        return null;
	    }

		@Override
		public Boolean saveuserRoleMapping(List<UserRoleMappingEO> userroleMapping, UserInfo userInfo) {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			try {
				for (UserRoleMappingEO userrole : userroleMapping) {
					if (userrole.getRecordId() == 0 && userrole.getRoleId() != null && userrole.getUserId() != null) {
						userrole.setCreatedTime(DateUtils.obtainCurrentDate());
						userrole.setCreatedBy(userInfo.getUsername());
						userrole.setLastModifiedBy(userInfo.getUsername());
						userrole.setLastModifiedTime(DateUtils.obtainCurrentDate());
						session.save(userrole);
					} else {
						userrole.setLastModifiedBy(userInfo.getUsername());
						userrole.setLastModifiedTime(DateUtils.obtainCurrentDate());
						session.update(userrole);
					}
				}
				tx.commit();
				return true;
			} catch (Exception e) {
				try {
					if (tx != null && tx.isActive()) {
						tx.rollback();
					}
				} catch (Exception trEx) {}
			} finally {
				session.close();
			}
			return false;
		}
}