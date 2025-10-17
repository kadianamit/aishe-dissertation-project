package org.nicexam.authorizationservice.userdao;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.nicexam.authorizationservice.enums.ActiveStatusType;
import org.nicexam.authorizationservice.enums.Message;
import org.nicexam.authorizationservice.enums.OtpType;
import org.nicexam.authorizationservice.usereo.ActiveStatusEO;
import org.nicexam.authorizationservice.usereo.ActivityLogsEO;
import org.nicexam.authorizationservice.usereo.ChangeLogsEO;
import org.nicexam.authorizationservice.usereo.EbaNameEO;
import org.nicexam.authorizationservice.usereo.LoginHeaderEO;
import org.nicexam.authorizationservice.usereo.LoginSliderEO;
import org.nicexam.authorizationservice.usereo.MenuSubmenuMappingEO;
import org.nicexam.authorizationservice.usereo.MenuUrlMappingEO;
import org.nicexam.authorizationservice.usereo.OfficeAcronymEO;
import org.nicexam.authorizationservice.usereo.OfficeTypeEO;
import org.nicexam.authorizationservice.usereo.OtpDetailsEO;
import org.nicexam.authorizationservice.usereo.UserMenuMasterEO;
import org.nicexam.authorizationservice.usereo.UserRoleMappingEO;
import org.nicexam.authorizationservice.usereo.UserRoleMenuPrivilegesEO;
import org.nicexam.authorizationservice.usereo.UserRolesEO;
import org.nicexam.authorizationservice.usereo.UserTokenEO;
import org.nicexam.authorizationservice.usereo.UsersContactsEO;
import org.nicexam.authorizationservice.usereo.UsersEO;
import org.nicexam.authorizationservice.uservo.AppUserDetails;
import org.nicexam.authorizationservice.uservo.AssignRolePrivilegesVO;
import org.nicexam.authorizationservice.uservo.MenuPrivilegesVO;
import org.nicexam.authorizationservice.uservo.MenuSubMenuVO;
import org.nicexam.authorizationservice.uservo.MenuSubmenuMappingVO;
import org.nicexam.authorizationservice.uservo.MenuUrlMappingVO;
import org.nicexam.authorizationservice.uservo.OtpDetailsVerifyVO;
import org.nicexam.authorizationservice.uservo.UserDesignationVO;
import org.nicexam.authorizationservice.uservo.UserInfo;
import org.nicexam.authorizationservice.uservo.UserMenuMasterVO;
import org.nicexam.authorizationservice.uservo.UserRoleMappingVO;
import org.nicexam.authorizationservice.uservo.UserRolesVO;
import org.nicexam.authorizationservice.uservo.UsersVO;
import org.nicexam.authorizationservice.utility.CommonUtils;
import org.nicexam.authorizationservice.utility.DateUtils;
import org.nicexam.authorizationservice.utility.Decryption;
import org.nicexam.authorizationservice.utility.Encryption;
import org.nicexam.authorizationservice.utility.EncryptionDecryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	BCryptPasswordEncoder bcrypt;

private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public String saveUserData(UsersEO users) {
		logger.info("UserDaoImpl : saveUserData Method Invoked");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		boolean isUserExist = isUserNameExist(users.getLoginId());
		if (isUserExist) {
			return Message.EXISTS.getMessage();
		} else {
			try {
				String pwd = EncryptionDecryptionUtil.getDecryptedString(users.getPassword());
				users.setPassword(bcrypt.encode(pwd));
//				Long nextUserContactTableSequenceId = tableIdGenerationCounterDao
//						.fetchOrUpdateTableIdSequence(TableNamesForIdGeneration.USERS_CONTACTS.getTableName());
//				if (nextUserContactTableSequenceId != null) {
//					users.setId(TableIdGenerationUtil.generateTableId(nextUserContactTableSequenceId));
//				}
				session.save(users);
				tx.commit();
				return Message.SUCCESS.getMessage();
			} catch (Exception e) {
				try {
					if (tx != null && tx.isActive()) {
						tx.rollback();
					}
				} catch (Exception trEx) {
					logger.error("Couldn’t roll back transaction" + trEx.getMessage());
				}
				logger.error("Error In saveUserData {}" + e.getMessage());
			} finally {
				session.close();
			}
		}
		return Message.ERROR.getMessage();
	}

	@Override
	public boolean updateUserData(UsersEO userEo) {
		logger.info("UserDaoImpl : updateUserData Method Invoked");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			userEo.setPassword(bcrypt.encode(userEo.getPassword()));
			session.update(userEo);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				logger.info("Couldn’t roll back transaction" + rbe.getMessage());
			}
			logger.info("Update User Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean changeUserStatus(UsersEO users) {
		logger.info("UserDaoImpl : changeUserStatus Method Invoked");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		UsersEO newUsers = new UsersEO();
		try {
		//	newUsers.setId(users.getId());
		//	newUsers = (UsersEO) session.get(UsersEO.class, users.getId());
			//newUsers.setActive(users.isActive());
			session.update(newUsers);
			tx.commit();
			return true;
		} catch (Exception e) {
			try {
				tx.rollback();
			} catch (RuntimeException rbe) {
				logger.info("Couldn’t roll back transaction" + rbe.getMessage());
			}
			logger.info("User Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

//change needed
	@Override
	public UsersEO fetchUserDataById(Long id) {
		logger.info("UserDaoImpl : fetchUserDataById Method Invoked " + id);
		Session session = sessionFactory.openSession();
		UsersEO newUsers = new UsersEO();
		try {
			newUsers = (UsersEO) session.get(UsersEO.class, id);
			return newUsers;
		} catch (Exception e) {
			logger.info("User fetchUserDataById Display" + e.getMessage());
		} finally {
			session.close();
		}
		return newUsers;
	}

//change needed
	@Override
	public List<UsersVO> fetchAllEbaUsersData(Integer officeTypeId, Integer ebaId) {
		logger.info("UserDaoImpl : fetchAllUsersData Method Invoked");
		// Session session = sessionFactory.openSession();
		List<UsersVO> userList = new ArrayList<>();
		/*
		 * try { StringBuilder builder = new StringBuilder(); builder.
		 * append("select userd.recordId,userd.userName,userd.emailId,userd.mobileNo,\r\n"
		 * + " userd.password,office.officeType,eba.ebaName from UsersEO as userd\r\n" +
		 * " left join OfficeTypeEO as office on office.recordId=userd.officeTypeId\r\n"
		 * + " left join EbaNameEO as eba on eba.recordId=userd.ebaId\r\n" +
		 * " where userd.isActive=true and userd.officeTypeId=:officeTypeId"); if
		 * (officeTypeId == 1) { builder.append(" and userd.ebaId=:ebaId"); } Query
		 * query = session.createQuery(builder.toString());
		 * query.setParameter("officeTypeId", officeTypeId);
		 * 
		 * if (officeTypeId == 1) { query.setParameter("ebaId", ebaId); } List<Object[]>
		 * userslist = (List<Object[]>) query.getResultList(); for (Object[] object :
		 * userslist) { UsersVO usersVO = new UsersVO();
		 * usersVO.setRecordId(Integer.parseInt(object[0] + ""));
		 * usersVO.setUserName(object[1] + ""); usersVO.setEmailId(object[2] + "");
		 * usersVO.setMobileNo(object[3] + ""); usersVO.setPassword(object[4] + "");
		 * usersVO.setOfficeLevel(object[5] + ""); usersVO.setEbaName(object[6] + "");
		 * userList.add(usersVO); } return userList; } catch (Exception e) {
		 * logger.info("Users Info Display" + e.getMessage()); } finally {
		 * session.close(); }
		 */
		return userList;
	}

	@Override
	public boolean saveuserrolemapping(List<UserRoleMappingEO> userroleMappinglistdata) {
		logger.info("UserDaoImpl : saveuserrolemapping method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			for (UserRoleMappingEO alluserRoleMappingEO : userroleMappinglistdata) {
				session.save(alluserRoleMappingEO);
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
			logger.error("Error In saveuserrolemapping {}" + e.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception e) {
			}
		}
		return false;
	}

	@Override
	public boolean saveUserMenu(UserMenuMasterEO userMenuEo) {
		logger.info("UserDaoImpl : saveUserMenu method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(userMenuEo);
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
			logger.error("Error In saveUserMenu {}" + e.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception e) {
			}
		}
		return false;
	}

	@Override
	public List<UserMenuMasterEO> fetchAllMenu() {
		logger.info("UserDaoImpl : fetchAllMenu Method Invoked");
		Session session = sessionFactory.openSession();
		List<UserMenuMasterEO> userMenuList = new ArrayList<>(1);
		try {
			@SuppressWarnings("deprecation")
			Criteria cr = session.createCriteria(UserMenuMasterEO.class);
			cr.add(Restrictions.eq("isActive", true));
			@SuppressWarnings("unchecked")
			List<UserMenuMasterEO> usersMenuList = cr.list();
			return usersMenuList;
		} catch (Exception e) {
			logger.info("Users Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return userMenuList;
	}

	@Override
	public boolean savemenuUrlMapping(MenuUrlMappingEO urlMappingEo) {
		logger.info("UserDaoImpl : savemenuUrlMapping method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(urlMappingEo);
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
			logger.error("Error In savemenuUrlMapping {}" + e.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception e) {
			}
		}
		return false;
	}

	@Override
	public List<OfficeTypeEO> fetchAllOfficeTypeEo() {
		logger.info("UserDaoImpl : fetchAllOfficeTypeEo Method Invoked");
		Session session = sessionFactory.openSession();
		List<OfficeTypeEO> officeEoList = new ArrayList<>(1);
		try {
			@SuppressWarnings("deprecation")
			Criteria cr = session.createCriteria(OfficeTypeEO.class);
			cr.add(Restrictions.eq("isActive", true));
			@SuppressWarnings("unchecked")
			List<OfficeTypeEO> eoList = cr.list();
			return eoList;
		} catch (Exception e) {
			logger.info("fetchAllOfficeTypeEo Info Display Error" + e.getMessage());
		} finally {
			session.close();
		}
		return officeEoList;
	}

	@Override
	public List<EbaNameEO> fetchAllEbaNameList() {
		logger.info("UserDaoImpl : fetchAllEbaNameList Method Invoked");
		Session session = sessionFactory.openSession();
		List<EbaNameEO> ebaList = new ArrayList<>(1);
		try {
			Criteria cr = session.createCriteria(EbaNameEO.class);
			cr.add(Restrictions.eq("isActive", true));
			@SuppressWarnings("unchecked")
			List<EbaNameEO> listeba = cr.list();
			return listeba;
		} catch (Exception e) {
			logger.info("Users Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return ebaList;
	}

	@Override
	public List<UserRolesVO> fetchAllUserRolesEbaData() {
		logger.info("UserDaoImpl : fetchAllUserRolesEbaData Method Invoked");
		Session session = sessionFactory.openSession();
		List<UserRolesVO> userRoleList = new ArrayList<>();
		try {
			Query query = session.createQuery("select  roles.recordId,roles.roleName,roles.roleDescription\r\n"
					+ ",roles.status from UserRolesEO as roles \r\n" + " where roles.status =:status");
			query.setParameter("status", ActiveStatusType.ACTIVE.getStatusType());
			List<Object[]> userrolelist = (List<Object[]>) query.getResultList();
			for (Object[] object : userrolelist) {
				UserRolesVO usersRolesVO = new UserRolesVO();
				usersRolesVO.setRecordId(Integer.parseInt(object[0] + ""));
				usersRolesVO.setRoleName(object[1] + "");
				usersRolesVO.setRoleDescription(object[2] + "");
				usersRolesVO.setStatus(Integer.parseInt(object[3] + ""));
				userRoleList.add(usersRolesVO);
			}
			return userRoleList;
		} catch (Exception e) {
			logger.info("Users Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return userRoleList;
	}

//change needed
	@Override
	public List<UsersVO> fetchAllUserData(UserInfo userInfo) {

		logger.info("UserDaoImpl : fetchAllUserData Method Invoked");
		Session session = sessionFactory.openSession();
		List<UsersVO> userList = new ArrayList<>();
		try {
			Query query = session.createQuery("select userd.Id,coalesce(userd.loginId,'')as loginId,\r\n"
					+ " userd.password from UsersEO as userd \r\n"
					+ " where userd.isActive=true and loginId is not null and login_id !='" + userInfo.getUsername()
					+ "'");

			@SuppressWarnings("unchecked")
			List<Object[]> userslist = query.getResultList();
			for (Object[] object : userslist) {
				UsersVO usersVO = new UsersVO();
				usersVO.setId(Long.parseLong(object[0] + ""));
				usersVO.setLoginId(object[1] + "");
				// usersVO.setPassword(object[2] + "");
				userList.add(usersVO);
			}
			return userList;
		} catch (Exception e) {
			logger.info("Error In fetchAllUserData Info Display" + e.getMessage());
		} finally {
			session.close();
		}

		return null;
	}

	@Override
	public List<ActiveStatusEO> fetchAllActiveStatus() {
		Session session = sessionFactory.openSession();
		List<ActiveStatusEO> statuseo = new ArrayList<>();
		try {
			Criteria cr = session.createCriteria(ActiveStatusEO.class);
			cr.add(Restrictions.eq("isActive", true));
			@SuppressWarnings("unchecked")
			List<ActiveStatusEO> activedata = cr.list();
			return activedata;
		} catch (Exception e) {
			logger.info("fetchAllActiveStatus Display" + e.getMessage());
		} finally {
			session.close();
		}
		return statuseo;
	}

	@Override
	public List<UserRolesVO> fetchAllUserRolesFromEbaData(Integer ebaId, Integer officeTypeId) {
		logger.info("UserDaoImpl : fetchAllUserRolesFromEbaData Method Invoked");
		Session session = sessionFactory.openSession();
		List<UserRolesVO> userRoleList = new ArrayList<>();
		try {
			StringBuilder builder = new StringBuilder();
			builder.append("select roles.recordId,roles.roleName from UserRolesEO roles \r\n"
					+ "where roles.officeTypeId=:officeTypeId");

			if (officeTypeId == 1) {
				builder.append(" and roles.ebaId=:ebaId");
			}
			Query query = session.createQuery(builder.toString());
			query.setParameter("officeTypeId", officeTypeId);

			if (officeTypeId == 1) {
				query.setParameter("ebaId", ebaId);
			}

			List<Object[]> userrolelist = query.getResultList();
			for (Object[] object : userrolelist) {
				UserRolesVO usersRolesVO = new UserRolesVO();
				usersRolesVO.setRecordId(Integer.parseInt(object[0] + ""));
				usersRolesVO.setRoleName(object[1] + "");
				userRoleList.add(usersRolesVO);
			}
			return userRoleList;
		} catch (Exception e) {
			logger.info("fetchAllUserRolesFromEbaData Error" + e.getMessage());
		} finally {
			session.close();
		}
		return userRoleList;
	}

	@Override
	public List<MenuUrlMappingEO> fetchAllSubMenuById() {
		logger.info("UserDaoImpl : fetchAllSubMenuById Method Invoked");
		Session session = sessionFactory.openSession();
		List<MenuUrlMappingEO> submenuList = new ArrayList<>();
		try {
			@SuppressWarnings("deprecation")
			Criteria cr = session.createCriteria(MenuUrlMappingEO.class);
			cr.add(Restrictions.eq("status", ActiveStatusType.ACTIVE.getStatusType()));
			@SuppressWarnings("unchecked")
			List<MenuUrlMappingEO> eoList = cr.list();
			return eoList;
		} catch (Exception e) {
			logger.info("fetchAllSubMenuById Info Display Error" + e.getMessage());
		} finally {
			session.close();
		}
		return submenuList;
	}

	@Override
	public List<MenuUrlMappingEO> fetchAllSubmenuData() {
		logger.info("UserDaoImpl : fetchAllSubmenuData Method Invoked");
		Session session = sessionFactory.openSession();
		List<MenuUrlMappingEO> submenuList = new ArrayList<>();
		try {
			@SuppressWarnings("deprecation")
			Criteria cr = session.createCriteria(MenuUrlMappingEO.class);
			cr.add(Restrictions.eq("isActive", true));
			@SuppressWarnings("unchecked")
			List<MenuUrlMappingEO> eoList = cr.list();
			return eoList;
		} catch (Exception e) {
			logger.info("fetchAllSubmenuData Info Display Error" + e.getMessage());
		} finally {
			session.close();
		}
		return submenuList;
	}

	@Override
	public boolean saveMenuSubmenuMapping(MenuSubmenuMappingEO menuSubmenuMapEo) {
		logger.info("UserDaoImpl : saveMenuSubmenuMapping Method Invoked");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(menuSubmenuMapEo);
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
			logger.info("saveMenuSubmenuMapping Error Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<MenuUrlMappingVO> fetchAllSubmenuDatalist() {
		logger.info("UserDaoImpl : fetchAllSubmenuDatalist Method Invoked");
		Session session = sessionFactory.openSession();
		List<MenuUrlMappingVO> userRoleList = new ArrayList<>();
		try {
			Query query = session.createQuery(
					"select  msm.recordId,msm.menuName,msm.menuUrl,msm.status\r\n" + " from MenuUrlMappingEO as msm ");
			List<Object[]> userrolelist = (List<Object[]>) query.getResultList();
			for (Object[] object : userrolelist) {
				MenuUrlMappingVO usersRolesVO = new MenuUrlMappingVO();
				usersRolesVO.setRecordId(Integer.parseInt(object[0] + ""));
				usersRolesVO.setMenuName(object[1] + "");
				usersRolesVO.setMenuUrl(object[2] + "");
				usersRolesVO.setStatus(Integer.parseInt(object[3] + ""));
				userRoleList.add(usersRolesVO);
			}
			return userRoleList;
		} catch (Exception e) {
			logger.info("Users Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return userRoleList;
	}

	@Override
	public List<MenuSubMenuVO> fetchAllMenuSubmenu(MenuSubMenuVO menuSubmenuVO) {
		logger.info("UserDaoImpl : fetchAllSubmenuDatalist Method Invoked");
		Session session = sessionFactory.openSession();
		List<MenuSubMenuVO> userRoleList = new ArrayList<>();
		try {
			Query query = session.createQuery("select menu.menuName,menu.displaySequence as menudisplaysequence,"
					+ "submenu.menuName  as submenu_name,\r\n"
					+ "submenu.menuUrl as submenuurl,msm.displaySequence as submenudisplaysequence,menu.recordId,submenu.recordId\r\n"
					+ " from MenuSubmenuMappingEO msm\r\n"
					+ " left join UserMenuMasterEO as menu on msm.menuId=menu.recordId\r\n"
					+ " left join MenuUrlMappingEO as submenu on msm.submenuId=submenu.recordId");

			List<Object[]> examFormDatalist = (List<Object[]>) query.getResultList();
			for (Object[] object : examFormDatalist) {
				MenuSubMenuVO examSectionVO = new MenuSubMenuVO();
				examSectionVO.setMenu(object[0] + "");
				examSectionVO.setMenudisplaySequence(Integer.parseInt(object[1] + ""));
				examSectionVO.setSubmenu(object[2] + "");
				examSectionVO.setSubmenuUrl(object[3] + "");
				examSectionVO.setSubmenudisplaySequence(Integer.parseInt(object[4] + ""));
				examSectionVO.setMenuRecordId(Integer.parseInt(object[5] + ""));
				examSectionVO.setSubmenuRecordId(Integer.parseInt(object[6] + ""));
				userRoleList.add(examSectionVO);
			}
			return userRoleList;
		} catch (Exception e) {
			logger.info("Users Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return userRoleList;
	}

	@Override
	public List<UserMenuMasterVO> fetchAllMenuDatalist() {
		logger.info("UserDaoImpl : fetchAllMenuDatalist Method Invoked");
		Session session = sessionFactory.openSession();
		List<UserMenuMasterVO> menuList = new ArrayList<>();
		try {
			Query query = session.createQuery("select  menu.menuName,menu.displaySequence,menu.status\r\n"
					+ ",menu.recordId from UserMenuMasterEO as menu \r\n"
					+ "join ActiveStatusEO statusEo on menu.status=statusEo.recordId");
			List<Object[]> menulist = (List<Object[]>) query.getResultList();
			for (Object[] object : menulist) {
				UserMenuMasterVO menuVO = new UserMenuMasterVO();
				menuVO.setMenuName(object[0] + "");
				menuVO.setDisplaySequence(Integer.parseInt(object[1] + ""));
				menuVO.setStatus(Integer.parseInt(object[2] + ""));
				menuVO.setRecordId(Integer.parseInt(object[3] + ""));
				menuList.add(menuVO);
			}
			return menuList;
		} catch (Exception e) {
			logger.info("Users Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return menuList;
	}

	@Override
	public List<UserRoleMappingVO> fetchAllUserRoleMappingDatalist() {
		logger.info("UserDaoImpl : fetchAllUserRoleMappingDatalist Method Invoked");
		Session session = sessionFactory.openSession();
		List<UserRoleMappingVO> userRoleMappingList = new ArrayList<>();
		try {
			Query query = session
					.createQuery("select  mappings.recordId,roles.roleName,users.loginId,mappings.status\r\n"
							+ "from UserRoleMappingEO as mappings \r\n"
							+ "left join UserRolesEO as roles on mappings.rolesRecordId=roles.recordId\r\n"
							+ "left join UsersEO as users on mappings.userRecordId=users.Id");
			List<Object[]> userrolelist = (List<Object[]>) query.getResultList();
			for (Object[] object : userrolelist) {
				UserRoleMappingVO userrolemappingVO = new UserRoleMappingVO();
				userrolemappingVO.setRecordId(Integer.parseInt(object[0] + ""));
				userrolemappingVO.setRoleName(object[1] + "");
				userrolemappingVO.setLoginId(object[2] + "");
				userrolemappingVO.setStatus(Integer.parseInt(object[3] + ""));
				userRoleMappingList.add(userrolemappingVO);
			}
			return userRoleMappingList;
		} catch (Exception e) {
			logger.info("Users Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return userRoleMappingList;
	}

	@Override
	public boolean saveUserRoleMenuPrivilegesMapping(List<UserRoleMenuPrivilegesEO> userroleMenuPrivileges) {
		logger.info("UserDaoImpl : saveUserRoleMenuPrivilegesMapping method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		List<UserRoleMenuPrivilegesEO> previousData = new ArrayList<>();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UserRoleMenuPrivilegesEO> query = builder.createQuery(UserRoleMenuPrivilegesEO.class);
			Root<UserRoleMenuPrivilegesEO> allData = query.from(UserRoleMenuPrivilegesEO.class);
			query.where(builder.and(builder.equal(allData.get("status"), ActiveStatusType.ACTIVE.getStatusType()),
					builder.equal(allData.get("roleId"), userroleMenuPrivileges.get(0).getRoleId())));
			previousData = session.createQuery(query).getResultList();

			previousData.forEach(data -> {
				data.setStatus(ActiveStatusType.INACTIVE.getStatusType());
				session.update(data);
			});

			for (UserRoleMenuPrivilegesEO menuPrivilegesEO : userroleMenuPrivileges) {

				session.save(menuPrivilegesEO);
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
			logger.error("Error In saveUserRoleMenuPrivilegesMapping {}" + e.getMessage());
			return false;
		} finally {
			try {
				session.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public List<MenuSubmenuMappingVO> fetchAllMenuSubmenuMappingDatalist() {
		logger.info("UserDaoImpl : fetchAllMenuDatalist Method Invoked");
		Session session = sessionFactory.openSession();
		List<MenuSubmenuMappingVO> menuSubmenuMappingList = new ArrayList<>();
		try {
			Query query = session.createQuery("select  menu.menuName,mum.menuName as submenu \r\n"
					+ ",mappings.displaySequence,mappings.status\r\n" + "from MenuSubmenuMappingEO as mappings \r\n"
					+ "left join MenuUrlMappingEO as mum on mappings.submenuId=mum.recordId\r\n"
					+ "left join UserMenuMasterEO as menu on mappings.menuId=menu.recordId where mappings.status=:status");
			query.setParameter("status", ActiveStatusType.ACTIVE.getStatusType());
			List<Object[]> menuSubmenuMappingDatalist = (List<Object[]>) query.getResultList();
			for (Object[] object : menuSubmenuMappingDatalist) {
				MenuSubmenuMappingVO menuVO = new MenuSubmenuMappingVO();
				menuVO.setMenuName(object[0] + "");
				menuVO.setSubmenuName(object[1] + "");
				menuVO.setDisplaySequence(Integer.parseInt(object[2] + ""));
				menuVO.setStatus(Integer.parseInt(object[3] + ""));

				menuSubmenuMappingList.add(menuVO);
			}
			return menuSubmenuMappingList;
		} catch (Exception e) {
			logger.info("Users Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return menuSubmenuMappingList;
	}

	@Override
	public AssignRolePrivilegesVO fetchMenuPrivilegesByParam(AssignRolePrivilegesVO rolePrivilegesVo) {
		logger.info("ExamDataDaoImpl : fetchMenuPrivilegesByParam method invoked :");
		Session session = sessionFactory.openSession();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select menu.menuName,mum.menuName as submenu ,mum.menuUrl ");
			queryBuilder.append(",mappings.menuprivilegesVo,mappings.menuId,mappings.subMenuId,msm.displaySequence ");
			queryBuilder.append("from UserRoleMenuPrivilegesEO as mappings ");
			queryBuilder.append("join MenuUrlMappingEO as mum on mappings.subMenuId=mum.recordId ");
			queryBuilder.append(
					"join MenuSubmenuMappingEO as msm on msm.submenuId=mappings.subMenuId and msm.menuId=mappings.menuId ");
			queryBuilder.append(
					"join UserMenuMasterEO as menu on mappings.menuId=menu.recordId where mappings.status=:status and mappings.roleId=:roleid ");

			Query query = session.createQuery(queryBuilder.toString());

			query.setParameter("status", ActiveStatusType.ACTIVE.getStatusType());
			query.setParameter("roleid", rolePrivilegesVo.getRoleId());

			List<Object[]> menuSubmenuMappingDatalist = (List<Object[]>) query.getResultList();
			List<MenuSubMenuVO> list = new ArrayList<>();
			for (Object[] submenu : menuSubmenuMappingDatalist) {
				MenuSubMenuVO menuSubMenuVO = new MenuSubMenuVO();
				menuSubMenuVO.setMenu(submenu[0] + "");
				menuSubMenuVO.setSubmenu(submenu[1] + "");
				menuSubMenuVO.setSubmenuUrl(submenu[2] + "");
				menuSubMenuVO.setMenuprivileges(((MenuPrivilegesVO) submenu[3]).getListData());
				menuSubMenuVO.setMenuRecordId(Integer.parseInt(submenu[4] + ""));
				menuSubMenuVO.setSubmenuRecordId(Integer.parseInt(submenu[5] + ""));
				menuSubMenuVO.setSubmenudisplaySequence(Integer.parseInt(submenu[6] + ""));
				list.add(menuSubMenuVO);
			}
			rolePrivilegesVo.setMenuDetails(list);
			return rolePrivilegesVo;
		} catch (Exception e) {
			logger.info("Error In fetchMenuPrivilegesByParam" + e.getMessage());
		} finally {
			session.close();
		}
		return rolePrivilegesVo;
	}

	@Override
	public UsersEO fetchUserByUsername(String username) {
		Session session=sessionFactory.openSession();
        try {
        	//String userid = username.toLowerCase();
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append(" from UsersEO where loginId=:userId and isApproved=1");
            Query query = session.createQuery(queryBuilder.toString());
            query.setParameter("userId", username);
            List<UsersEO> list = query.getResultList();
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
	
	/*
	 * try (Session session = sessionFactory.openSession()) { CriteriaBuilder cb =
	 * session.getCriteriaBuilder(); CriteriaQuery<UsersEO> cQuery =
	 * cb.createQuery(UsersEO.class); Root<UsersEO> root =
	 * cQuery.from(UsersEO.class); cQuery.where(cb.and(cb.equal(root.get("loginId"),
	 * username.toLowerCase())), (cb.equal(root.get("isApproved"), 1)));
	 * TypedQuery<UsersEO> query = session.createQuery(cQuery); return
	 * query.getSingleResult(); } catch (Exception e) {
	 * logger.info("Error In fetchUserByUsername" + e.getMessage()); } return null;
	 * }
	 */

	@Override
	public boolean saveSlider(LoginSliderEO loginSlider) {
		logger.info("ExamDataDaoImpl : saveSlider method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		LoginSliderEO newloginSlider = new LoginSliderEO();
		try {
			newloginSlider.setSequence(loginSlider.getSequence());
			newloginSlider = session.get(LoginSliderEO.class, loginSlider.getSequence());
			if (newloginSlider == null) {
				session.save(loginSlider);
			} else if (newloginSlider != null) {
				newloginSlider.setBannerDescription(loginSlider.getBannerDescription());
				session.update(newloginSlider);
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
			logger.info("saveSlider Error" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@SuppressWarnings({ "unused", "unchecked", "deprecation" })
	@Override
	public List<UserRolesVO> fetchAllUserRolesForUser(Long userRecordId) {
		try (Session session = sessionFactory.openSession()) {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder
					.append("select ur.record_id recordId,ur.role_name roleName,ur.role_description roleDescription, ");
			queryBuilder.append("urm.user_office_id userOfficeId,urm.user_office_value userOfficeValue ");
			queryBuilder.append("from user_roles_mapping urm ");
			queryBuilder.append("join users u on u.record_id=urm.user_record_id ");
			queryBuilder.append("join user_roles ur on ur.record_id=urm.roles_record_id ");
			queryBuilder.append("where ur.is_active=:isActive and urm.user_record_id=:userRecordId ");

			List<UserRolesVO> userRolesList = session.createNativeQuery(queryBuilder.toString()).addScalar("recordId")
					.addScalar("roleName").addScalar("roleDescription").addScalar("userOfficeId")
					.addScalar("userOfficeValue").setResultTransformer(Transformers.aliasToBean(UserRolesVO.class))
					.setParameter("isActive", true).setParameter("userRecordId", userRecordId).list();

			return userRolesList;
		}
	}

	@Override
	public boolean saveHeader(LoginHeaderEO loginHeaderEo) {
		logger.info("ExamDataDaoImpl : saveHeader method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		LoginHeaderEO newloginHeader = new LoginHeaderEO();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<LoginHeaderEO> query = builder.createQuery(LoginHeaderEO.class);
			Root<LoginHeaderEO> root = query.from(LoginHeaderEO.class);
			query.select(root).where(builder.equal(root.get("isActive"), true));
			newloginHeader = session.createQuery(query).uniqueResult();
			if (newloginHeader == null) {
				session.save(loginHeaderEo);
			} else if (newloginHeader.getHeader() != null) {
				newloginHeader.setActive(false);
				session.save(loginHeaderEo);
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
			logger.info("Error In saveHeader Error" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public List<LoginSliderEO> fetchSliderList() {
		logger.info("UserDaoImpl : fetchSliderList Method Invoked");
		Session session = sessionFactory.openSession();
		List<LoginSliderEO> sliderDataList = new ArrayList<>();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<LoginSliderEO> query = builder.createQuery(LoginSliderEO.class);
			Root<LoginSliderEO> root = query.from(LoginSliderEO.class);
			query.select(root).where(builder.equal(root.get("isActive"), true))
					.orderBy(builder.asc(root.get("Sequence")));
			sliderDataList = session.createQuery(query).getResultList();
			return sliderDataList;
		} catch (Exception e) {
			logger.info("Error In fetchSliderList Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return sliderDataList;
	}

	@Override
	public List<LoginHeaderEO> fetchHeaderList() {
		logger.info("UserDaoImpl : fetchHeaderList Method Invoked");
		Session session = sessionFactory.openSession();
		List<LoginHeaderEO> headerDataList = new ArrayList<>();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<LoginHeaderEO> query = builder.createQuery(LoginHeaderEO.class);
			Root<LoginHeaderEO> root = query.from(LoginHeaderEO.class);
			query.select(root).where(builder.equal(root.get("isActive"), true));
			headerDataList = session.createQuery(query).getResultList();
			return headerDataList;
		} catch (Exception e) {
			logger.info("Error In fetchHeaderList Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return headerDataList;
	}

	@Override
	public Boolean hasURLAccess(String username, String url) {

		try (Session session = sessionFactory.openSession()) {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select exists( ");
			queryBuilder.append("select u.id,u.login_id from users u where u.login_id=:loginId)");
			// ,urm.roles_record_id,urmpm.fk_submenu_id,mmum.menu_url
			// queryBuilder.append("join user_roles_mapping urm on
			// u.record_id=urm.user_record_id ");
			// queryBuilder
			// .append("join user_roles_menu_privileges_mapping urmpm on
			// urmpm.fk_role_id=urm.roles_record_id ");
			// queryBuilder.append("join m_menu_url_mapping mmum on
			// mmum.record_id=urmpm.fk_submenu_id ");
			// queryBuilder.append("where u.login_id=:loginId and mmum.menu_url=:menuUrl )
			// ");
			return (Boolean) session.createNativeQuery(queryBuilder.toString()).setParameter("loginId", username)
					// .setParameter("menuUrl", url)
					.getSingleResult();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public AssignRolePrivilegesVO fetchSelectedMenuPrivileges(AssignRolePrivilegesVO rolePrivilegesVo) {
		logger.info("ExamDataDaoImpl : fetchSelectedMenuPrivileges method invoked :");
		Session session = sessionFactory.openSession();

		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select mum.menuName,mmum.menuName,mmum.menuUrl,ur.menuprivilegesVo, ");
			queryBuilder.append("msm.menuId,msm.submenuId,msm.displaySequence ");
			queryBuilder.append("from MenuSubmenuMappingEO msm ");
			queryBuilder.append("join MenuUrlMappingEO mmum on mmum.recordId=msm.submenuId ");
			queryBuilder.append("join UserMenuMasterEO mum on mum.recordId=msm.menuId ");
			queryBuilder.append("left join UserRoleMenuPrivilegesEO ur on ur.subMenuId=mmum.recordId  ");
			queryBuilder.append("and ur.menuId=mum.recordId and ur.roleId=:roleId and ur.status=:status ");
			queryBuilder.append("where msm.status=:status ");

			System.out.println(queryBuilder.toString());
			Query query = session.createQuery(queryBuilder.toString());

			query.setParameter("roleId", rolePrivilegesVo.getRoleId());
			query.setParameter("status", ActiveStatusType.ACTIVE.getStatusType());

			System.out.println(queryBuilder.toString());
			List<Object[]> menuSubmenuMappingDatalist = (List<Object[]>) query.getResultList();
			List<MenuSubMenuVO> list = new ArrayList<>();
			for (Object[] submenu : menuSubmenuMappingDatalist) {
				MenuSubMenuVO menuSubMenuVO = new MenuSubMenuVO();
				menuSubMenuVO.setMenu(submenu[0] + "");
				menuSubMenuVO.setSubmenu(submenu[1] + "");
				menuSubMenuVO.setSubmenuUrl(submenu[2] + "");
				menuSubMenuVO
						.setMenuprivileges(submenu[3] != null ? ((MenuPrivilegesVO) submenu[3]).getListData() : null);
				menuSubMenuVO.setMenuRecordId(Integer.parseInt(submenu[4] + ""));
				menuSubMenuVO.setSubmenuRecordId(Integer.parseInt(submenu[5] + ""));
				menuSubMenuVO.setSubmenudisplaySequence(Integer.parseInt(submenu[6] + ""));
				list.add(menuSubMenuVO);
			}
			rolePrivilegesVo.setMenuDetails(list);
			return rolePrivilegesVo;
		} catch (Exception e) {
			logger.info("Error In fetchSelectedMenuPrivileges" + e.getMessage());
		} finally {
			session.close();
		}
		return rolePrivilegesVo;
	}

	@Override
	public Boolean isUserNameExist(String username) {
		logger.info("UserDaoImpl : isUserNameExist Method Invoked");
		Session session = sessionFactory.openSession();
		try {
			Long count = (Long) session.createQuery("select count(*) from UsersEO where lower(loginId)='" + username + "' AND isApproved=1")
					.uniqueResult();
			return count > 0 ? true : false;
		} catch (Exception e) {
			logger.info("isUserNameExist Error" + e.getMessage());
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public AppUserDetails fetchUserDetails(UserInfo userInfo) {
		Session session = sessionFactory.openSession();
		try {
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("select roles.roleName, roles.roleDescription,");
			queryBuilder.append("coalesce(mappings.rolesRecordId,0),coalesce(mappings.userOfficeId,0)");
			queryBuilder.append(",mappings.userOfficeValue,");
			queryBuilder.append(
					"userd.loginId,roles.recordId,userd.name,contact.primaryEmailAddress,contact.primaryMobileNumber");
			queryBuilder.append(
					" from UserRoleMappingEO mappings join UserRolesEO as roles on mappings.rolesRecordId=roles.recordId");
			queryBuilder.append(" join UsersEO as userd on mappings.userRecordId=userd.Id");
			queryBuilder.append(" join UsersContactsEO as contact on contact.userId=userd.Id");
			queryBuilder
					.append(" where mappings.status=:status and userd.loginId =:username order by roles.recordId asc");

			Query query = session.createQuery(queryBuilder.toString());
			query.setParameter("username", userInfo.getUsername());
			query.setParameter("status", ActiveStatusType.ACTIVE.getStatusType());
			@SuppressWarnings("unchecked")
			Decryption decrypt = new Decryption();

			List<Object[]> rolelist = (List<Object[]>) query.getResultList();
			AppUserDetails appUserDetails = new AppUserDetails();
			List<UserDesignationVO> designationlist = new ArrayList<>();
			for (Object[] roles : rolelist) {
				appUserDetails.setLoginId(roles[5] + "");
				appUserDetails.setName(roles[7] + "");
				appUserDetails.setEmailId(decrypt.decrypt(roles[8] + ""));
				appUserDetails.setMobileNo(decrypt.decrypt(roles[9] + ""));

				UserDesignationVO designationVO = new UserDesignationVO();
				designationVO.setRecordId(Integer.parseInt(roles[6] + ""));
				designationVO.setDesignationName(roles[0] + "");
				designationVO.setDesignationDescription(roles[1] + "");
				designationVO.setUserOfficeId(Integer.parseInt(roles[3] + ""));
				designationVO.setUserOfficeName(roles[4] + "");

				designationlist.add(designationVO);
			}
			appUserDetails.setDesignationList(designationlist);
			return appUserDetails;
		} catch (Exception e) {
			logger.info("Error In fetchUserDetails" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean saveActivityLogs(ActivityLogsEO activityLogsEo) {
		logger.info("UserDaoImpl : saveActivityLogs method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(activityLogsEo);
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
			logger.error("Error In saveActivityLogs {}" + e.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception e) {
			}
		}
		return false;
	}

	@Override
	public boolean saveChangeLogs(ChangeLogsEO changeLogsEo) {
		logger.info("UserDaoImpl : saveChangeLogs method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(changeLogsEo);
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
			logger.error("Error In saveChangeLogs {}" + e.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception e) {
			}
		}
		return false;
	}

	@Override
	public Boolean saveTokenDetails(String ipAddress, String username, String tokenValue) {
		logger.info("UserDaoImpl : saveTokenDetails method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		UserTokenEO userTokenEO = new UserTokenEO();
		userTokenEO.setIpAddress(ipAddress);
		userTokenEO.setUser_id(username);
		userTokenEO.setUser_token(tokenValue);
		userTokenEO.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
		userTokenEO.setExpireOn(DateUtils.obtainCurrentTimeStamp().plus(Duration.ofHours(1)));
		UserTokenEO userData = fetchUserTokenDetailsByUsername(userTokenEO.getUser_id());
//		if(userData.getCreatedOn().isBefore(userData.getExpireOn())) {
//			userTokenEO.setUser_token(tokenValue);
//			userTokenEO.setIsActive(false);
//		}else {
//			userTokenEO.setUser_token(userData.getUser_token());
			userTokenEO.setIsActive(true);
	//	}
		try {
		//	UserTokenEO userDatas = fetchUserTokenDetailsByUsername(userTokenEO.getUser_id());
			if(userData==null) {
				session.save(userTokenEO);
			}else {
				userTokenEO.setId(userData.getId());
				session.update(userTokenEO);
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
			logger.error("Error In saveChangeLogs {}" + e.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception e) {
			}
		}
		return false;
	}

	private UserTokenEO fetchUserTokenDetailsByUsername(String user_id) {
			try (Session session = sessionFactory.openSession()) {
				CriteriaBuilder cb = session.getCriteriaBuilder();
				CriteriaQuery<UserTokenEO> cQuery = cb.createQuery(UserTokenEO.class);
				Root<UserTokenEO> root = cQuery.from(UserTokenEO.class);
				cQuery.where(cb.and(cb.equal(root.get("user_id"), user_id)));
				TypedQuery<UserTokenEO> query = session.createQuery(cQuery);
				return query.getSingleResult();
			} catch (Exception e) {
				logger.info("Error In fetchUserByUsername" + e.getMessage());
			}
			return null;
		}

	@Override
	public boolean isTokenExpired(String tokenValue) {
		logger.info("UserDaoImpl : isTokenExpired Method Invoked");
		Session session = sessionFactory.openSession();
		Integer cnt = null;
		try {
			cnt = (Integer) session
					.createQuery("select id from public.user_token_details where token='" + tokenValue + "'")
					.uniqueResult();
		} catch (Exception e) {
			logger.info("isUserNameExist Error" + e.getMessage());
		} finally {
			session.close();
		}
		return cnt != null && cnt > 0;
	}

	@Override
	public List<ActivityLogsEO> fetchAllActivityLogs(String username) {
		logger.info("UserDaoImpl : fetchAllActivityLogs Method Invoked");
		Session session = sessionFactory.openSession();
		List<ActivityLogsEO> activityLogsList = new ArrayList<>();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ActivityLogsEO> query = builder.createQuery(ActivityLogsEO.class);
			Root<ActivityLogsEO> root = query.from(ActivityLogsEO.class);

			if (username.isEmpty() || username.equals("0")) {
				query.select(root);
			} else {
				query.select(root).where(builder.equal(root.get("username"), username));
			}
			activityLogsList = session.createQuery(query).getResultList();
			return activityLogsList;
		} catch (Exception e) {
			logger.info("Error In fetchAllActivityLogs Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return activityLogsList;
	}

	@Override
	public UsersVO saveRegisteredUserData(UsersVO userVo, UserInfo userInfo) {
		logger.info("UserDaoImpl : saveUserData Method Invoked");

		UsersEO userEo = new UsersEO();

		//Long nextTableSequenceId = tableIdGenerationCounterDao
		//		.fetchOrUpdateTableIdSequence(TableNamesForIdGeneration.USERS.getTableName());
	//	if (nextTableSequenceId != null) {
		//	userEo.setId(TableIdGenerationUtil.generateTableId(nextTableSequenceId));
		//} else 
//		{
//			return userVo;
//		}
		userEo.setName(userVo.getName());
		//userEo.setRegisteredSiteId(userVo.getRegisteredSiteId());

		//userEo.setActive(true);
		//userEo.setCreatedBy(userEo.getId());
		//userEo.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
		//userEo.setUpdatedBy(userEo.getId());
		//userEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());

		UsersContactsEO usersContact = new UsersContactsEO();

		//Long nextUserContactTableSequenceId = tableIdGenerationCounterDao
		//		.fetchOrUpdateTableIdSequence(TableNamesForIdGeneration.USERS_CONTACTS.getTableName());
		//if (nextUserContactTableSequenceId != null) {
		//	usersContact.setId(TableIdGenerationUtil.generateTableId(nextUserContactTableSequenceId));
		//} else 
//		{
//			return userVo;
//		}

		usersContact.setActive(true);
	//	usersContact.setCreatedBy(userEo.getId());
		usersContact.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
	//	usersContact.setUpdatedBy(userEo.getId());
		usersContact.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());

		Encryption encryptData = new Encryption();

		usersContact.setPrimaryEmailAddress(encryptData.encrypt(userVo.getPrimaryEmailAddress()));
		usersContact.setPrimaryMobileNumber(encryptData.encrypt(userVo.getPrimaryMobileNumber()));
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(userEo);

		//	usersContact.setUserId(userEo.getId());
			session.save(usersContact);

			OtpDetailsEO otpEOEmail = new OtpDetailsEO();
			otpEOEmail.setCreatedDate(DateUtils.obtainCurrentTimeStamp());
			otpEOEmail.setExpiresOn(DateUtils.obtainCurrentTimeStamp().plusMinutes(15));
			otpEOEmail.setType(OtpType.EMAIL.getMessage());
			otpEOEmail.setOtp(CommonUtils.getRandomNumberString());
			//otpEOEmail.setUserId(userEo.getId());
			session.save(otpEOEmail);
			//logger.info("User Id : " + userEo.getId() + " EMAIL OTP : " + otpEOEmail.getOtp());
			OtpDetailsEO otpEOMobile = new OtpDetailsEO();
			otpEOMobile.setCreatedDate(DateUtils.obtainCurrentTimeStamp());
			otpEOMobile.setExpiresOn(DateUtils.obtainCurrentTimeStamp().plusMinutes(15));
			otpEOMobile.setType(OtpType.MOBILE.getMessage());
			otpEOMobile.setOtp(CommonUtils.getRandomNumberString());
		//	otpEOMobile.setUserId(userEo.getId());
			session.save(otpEOMobile);

		//	logger.info("User Id : " + userEo.getId() + " MOBILE OTP : " + otpEOMobile.getOtp());
			tx.commit();
		//	userVo.setId(userEo.getId());
			return userVo;
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.error("Error In saveRegisteredUserData {}" + e.getMessage());
		} finally {
			session.close();
		}
		return userVo;
	}

	@Override
	public String verifyOtp(OtpDetailsVerifyVO otpVerify, UserInfo userInfo) {
		logger.info("UserDaoImpl : verifyOtp Method Invoked");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try {

			// user name represent user id
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<OtpDetailsEO> cQuery = cb.createQuery(OtpDetailsEO.class);
			Root<OtpDetailsEO> root = cQuery.from(OtpDetailsEO.class);
			cQuery.where(cb.and(cb.equal(root.get("userId"), userInfo.getUserId())));
			TypedQuery<OtpDetailsEO> query = session.createQuery(cQuery);
			List<OtpDetailsEO> usersOtpDetailsList = query.getResultList();
			System.out.println("Size : " + usersOtpDetailsList.size());
			if (usersOtpDetailsList != null && usersOtpDetailsList.size() > 0) {

				for (OtpDetailsEO detailsEO : usersOtpDetailsList) {
					switch (OtpType.parse(detailsEO.getType())) {
					case EMAIL:
						if (detailsEO.getOtp().equals(otpVerify.getE_Otp())) {
							if (DateUtils.obtainCurrentTimeStamp().isAfter(detailsEO.getExpiresOn())) {
								return "expemailotp";
							}
						} else {
							return "invalidemailotp";

						}
						break;
					case MOBILE:
						if (detailsEO.getOtp().equals(otpVerify.getM_Otp())) {
							if (DateUtils.obtainCurrentTimeStamp().isAfter(detailsEO.getExpiresOn())) {
								return "expmobileotp";
							}
						} else {
							return "invalidmobileotp";

						}
						break;

					default:
						System.out.println("Default Called");
						return "invalidtype";

					}
				}
			} else {
				return "otpnotfound";
			}
			CriteriaBuilder cbContact = session.getCriteriaBuilder();
			CriteriaQuery<UsersContactsEO> cQueryContact = cbContact.createQuery(UsersContactsEO.class);
			Root<UsersContactsEO> rootContacts = cQueryContact.from(UsersContactsEO.class);
			cQueryContact.where(cbContact.and(cbContact.equal(rootContacts.get("userId"), userInfo.getUserId())));
			TypedQuery<UsersContactsEO> contactQuery = session.createQuery(cQueryContact);
			UsersContactsEO usersContactsEO = contactQuery.getSingleResult();

			// UsersContactsEO usersContactEO = (UsersContactsEO)
			// session.get(UsersContactsEO.class, usersOtpDetailsList.get(index));
			// change needed
			usersContactsEO.setPrimaryEmailVerifiedOn(DateUtils.obtainCurrentTimeStamp());
			usersContactsEO.setPrimaryEmailVerified(true);
			usersContactsEO.setPrimaryEmailVerifiedOn(DateUtils.obtainCurrentTimeStamp());
			usersContactsEO.setPrimaryMobileVerified(true);
			session.update(usersContactsEO);

			for (OtpDetailsEO detailsEO : usersOtpDetailsList) {
				session.delete(detailsEO);
			}
			tx.commit();
		} catch (Exception e) {
			try {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}
			} catch (Exception trEx) {
				logger.error("Couldn’t roll back transaction" + trEx.getMessage());
			}
			logger.error("Error in verifyOtp " + e.getMessage());
			return "error";
		} finally {
			session.close();
		}
		return "success";
	}

	@Override
	public List<UserRolesEO> fetchRoleByOfficeId(Integer officeTypeId) {
		try (Session session = sessionFactory.openSession()) {
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<UserRolesEO> cQuery = cb.createQuery(UserRolesEO.class);
			Root<UserRolesEO> root = cQuery.from(UserRolesEO.class);
			cQuery.where(cb.and(cb.equal(root.get("officeTypeId"), officeTypeId)));
			TypedQuery<UserRolesEO> query = session.createQuery(cQuery);
			return query.getResultList();
		} catch (Exception e) {
			logger.info("Error In fetchUserByUsername" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<OfficeAcronymEO> fetchAllOfficeAcronym() {
		logger.info("UserDaoImpl : fetchAllOfficeAcronym Method Invoked");
		Session session = sessionFactory.openSession();
		List<OfficeAcronymEO> officeAcronymEOList = new ArrayList<>();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<OfficeAcronymEO> query = builder.createQuery(OfficeAcronymEO.class);
			Root<OfficeAcronymEO> root = query.from(OfficeAcronymEO.class);
			query.select(root).where(builder.equal(root.get("isActive"), true));
			officeAcronymEOList = session.createQuery(query).getResultList();
			return officeAcronymEOList;
		} catch (Exception e) {
			logger.info("Error In fetchAllOfficeAcronym Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return officeAcronymEOList;
	}

	@Override
	public UsersVO fetchUserDetailsFromUsername(String username) {
		logger.info("UserDaoImpl : fetchUserDetailsFromUsername Method Invoked");
		Session session = sessionFactory.openSession();
		UsersVO usersVO = new UsersVO();
		try {
			// change needed
			Long recordId = (Long) session.createQuery("select Id from UsersEO where loginId='" + username + "'")
					.uniqueResult();
			usersVO.setId(recordId);
			return usersVO;
		} catch (Exception e) {
			logger.info("isUserNameExist Error" + e.getMessage());
		} finally {
			session.close();
		}
		return usersVO;
	}

	@Override
	public List<UsersVO> findAllUserData() {
		logger.info("UserDaoImpl : findAllUserData Method Invoked");
		Session session = sessionFactory.openSession();
		List<UsersVO> userList = new ArrayList<>();
		try {
			Query query = session.createQuery(
					"select users.name,users.loginId,contact.primaryEmailAddress,contact.primaryMobileNumber\r\n"
							+ " from UsersEO users \r\n"
							+ " left join UsersContactsEO contact on contact.userId=users.Id"
							+ " left join RegistrationSiteDetailsEO registration on registration.Id=users.registeredSiteId where users.isActive=true and users.loginId is not null");
			Decryption decrypt = new Decryption();
			List<Object[]> userrolelist = (List<Object[]>) query.getResultList();
			for (Object[] object : userrolelist) {
				UsersVO usersVO = new UsersVO();
				usersVO.setName(object[0] + "");
				usersVO.setLoginId(object[1] + "");

				usersVO.setPrimaryEmailAddress(decrypt.decrypt(object[2] + ""));
				usersVO.setPrimaryMobileNumber(decrypt.decrypt(object[3] + ""));
//				usersVO.setRegisteredSiteName("AIEEE");
				userList.add(usersVO);
			}
			return userList;
		} catch (Exception e) {
			logger.info("Users Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return userList;
	}
	@Override
	public Boolean saveLogoutDetails(String ipAddress, String username, String tokenValue) {
		logger.info("UserDaoImpl : saveTokenDetails method invoked :");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		UserTokenEO userTokenEO = new UserTokenEO();
		userTokenEO.setIpAddress(ipAddress);
		userTokenEO.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
		userTokenEO.setExpireOn(DateUtils.obtainCurrentTimeStamp().plus(Duration.ofHours(1)));
		UserTokenEO userData = fetchUserTokenDetailsByUserToken(tokenValue);
		userTokenEO.setUser_token(tokenValue);
		if(userData!=null) {
		userTokenEO.setUser_id(userData.getUser_id());
		}else {
			return false;
		}
		userTokenEO.setIsActive(false);
		try {
			
			
				userTokenEO.setId(userData.getId());
				session.update(userTokenEO);
			
			
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
			logger.error("Error In saveChangeLogs {}" + e.getMessage());
		} finally {
			try {
				session.close();
			} catch (Exception e) {
			}
		}
		return false;
	}

	private UserTokenEO fetchUserTokenDetailsByUserToken(String user_token) {
		logger.info("UserDaoImpl : fetchHeaderList Method Invoked");
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UserTokenEO> query = builder.createQuery(UserTokenEO.class);
			Root<UserTokenEO> root = query.from(UserTokenEO.class);
			query.select(root).where(builder.equal(root.get("user_token"), user_token));
			UserTokenEO headerDataList = session.createQuery(query).getSingleResult();
			return headerDataList;
		} catch (Exception e) {
			logger.info("Error In fetchHeaderList Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}


	@Override
	public String fetchTokenActiveForUserandNotExpire(String name) {
			logger.info("UserDaoImpl : fetchHeaderList Method Invoked");
			Session session = sessionFactory.openSession();
			try {
				UserTokenEO user= fetchUserTokenDetailsByUserName(name);
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<UserTokenEO> query = builder.createQuery(UserTokenEO.class);
				Root<UserTokenEO> root = query.from(UserTokenEO.class);
				query.select(root).where(builder.equal(root.get("user_id"), name)
					//	,builder.equal(root.get("isActive"), true),
					//	,builder.lessThanOrEqualTo(root.get("expireOn"), user.getCreatedOn().plus(Duration.ofHours(1)))
								);
				UserTokenEO headerDataList = session.createQuery(query).getSingleResult();
				return headerDataList.getUser_token();
			} catch (Exception e) {
				logger.info("Error In fetchHeaderList Info Display" + e.getMessage());
			} finally {
				session.close();
			}
			return null;
		}

	@Override
	public UserTokenEO findUserByToken(String username) {
		Session session = sessionFactory.openSession();
		try {
			UserTokenEO user= fetchUserTokenDetailsByUserName(username);
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UserTokenEO> query = builder.createQuery(UserTokenEO.class);
			Root<UserTokenEO> root = query.from(UserTokenEO.class);
			query.select(root).where(builder.equal(root.get("isActive"), true),
					builder.equal(root.get("user_id"), username),
					builder.lessThanOrEqualTo(root.get("expireOn"), user.getCreatedOn().plus(Duration.ofHours(1))));
			UserTokenEO headerDataList = session.createQuery(query).getSingleResult();
			return headerDataList;
		} catch (Exception e) {
			logger.info("Error In fetchHeaderList Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}
	
	private UserTokenEO fetchUserTokenDetailsByUserName(String user_name) {
		logger.info("UserDaoImpl : fetchHeaderList Method Invoked");
		Session session = sessionFactory.openSession();
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UserTokenEO> query = builder.createQuery(UserTokenEO.class);
			Root<UserTokenEO> root = query.from(UserTokenEO.class);
			query.select(root).where(builder.equal(root.get("user_id"), user_name));
			UserTokenEO headerDataList = session.createQuery(query).getSingleResult();
			return headerDataList;
		} catch (Exception e) {
			logger.info("Error In fetchHeaderList Info Display" + e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean matchBcryptPassword(String altpwd, String alternatePassword) {
	        try {
	        	return bcrypt.matches(altpwd, alternatePassword);
	        } catch (Exception e) {
	        } 
	        return false;
	    }

}
