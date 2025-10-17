package org.nicexam.authorizationservice.userdao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.nicexam.authorizationservice.usereo.UserMenuPrivilegesEO;
import org.nicexam.authorizationservice.usereo.UserRolesEO;
import org.nicexam.authorizationservice.usereo.UsersEO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	@Autowired
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(UserRoleDaoImpl.class);

	@Override
	public boolean saveUserRoleData(UserRolesEO userRole) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(userRole);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				logger.info("Couldn’t roll back transaction" + rbe.getMessage());
			}
			logger.info("User Role Save" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateUserRoleData(UserRolesEO userRole) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.update(userRole);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				logger.info("Couldn’t roll back transaction" + rbe.getMessage());
			}
			logger.info("User Role Update" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean changeUserRoleStatus(UserRolesEO roles) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UsersEO newUsers = new UsersEO();
		try {
			//change needed
			//newUsers.setId(roles.getRecordId());
			newUsers = (UsersEO) session.get(UsersEO.class, roles.getRecordId());
			//newUsers.setActive(roles.isActive());
			session.update(newUsers);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				logger.info("Couldn’t roll back transaction" + rbe.getMessage());
			}
			logger.info("Change User Role Status Display" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<UserRolesEO> fetchAllRoleData() {
		Session session = sessionFactory.openSession();
		List<UserRolesEO> userRoles = new ArrayList<>(1);
		try {
			Criteria cr = session.createCriteria(UserRolesEO.class);
			//cr.add(Restrictions.eq("status", ActiveStatusType.ACTIVE.getApplicationVernacularType()));
			@SuppressWarnings("unchecked")
			List<UserRolesEO> userrole = cr.list();
			return userrole;
		} catch (Exception e) {
			logger.info("Users Role Display" + e.getMessage());
		} finally {
			session.close();
		}
		return userRoles;
	}

	@Override
	public UserRolesEO fetchRoleDataById(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UserRolesEO userRoles = new UserRolesEO();
		try {
			userRoles = (UserRolesEO) session.get(UserRolesEO.class, id);
			tx.commit();
		} catch (Exception e) {
			logger.info("User Data Display" + e.getMessage());
		} finally {
			session.close();
		}
		return userRoles;
	}

	@Override
	public List<UserRolesEO> fetchuserroledata(List<Integer> rolesRecordId) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<UserRolesEO> list = new ArrayList<UserRolesEO>();
		UserRolesEO userRoles = new UserRolesEO();
		try {
			for (int i = 0; i <= rolesRecordId.get(i).SIZE; i++) {
				userRoles = (UserRolesEO) session.get(UserRolesEO.class, rolesRecordId.get(i));
				list.add(userRoles);
			}
			tx.commit();
		} catch (Exception e) {
			logger.info("fetchuserroledata in list role id Display" + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<UserMenuPrivilegesEO> fetchAllPrivligesData() {
		Session session = sessionFactory.openSession();
		List<UserMenuPrivilegesEO> userRoles = new ArrayList<>();
		try {
			Criteria cr = session.createCriteria(UserMenuPrivilegesEO.class);
			cr.add(Restrictions.eq("isActive", true));
			@SuppressWarnings("unchecked")
			List<UserMenuPrivilegesEO> userrole = cr.list();
			return userrole;
		} catch (Exception e) {
			logger.info("fetchAllPrivligesData Error Display" + e.getMessage());
		} finally {
			session.close();
		}
		return userRoles;
	}
}
