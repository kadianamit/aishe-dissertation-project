package org.nicexam.authorizationservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nicexam.authorizationservice.enums.ActiveStatusType;
import org.nicexam.authorizationservice.userdao.UserDao;
import org.nicexam.authorizationservice.userdao.UserRoleDao;
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
import org.nicexam.authorizationservice.usereo.UserMenuMasterEO;
import org.nicexam.authorizationservice.usereo.UserMenuPrivilegesEO;
import org.nicexam.authorizationservice.usereo.UserRoleMappingEO;
import org.nicexam.authorizationservice.usereo.UserRoleMenuPrivilegesEO;
import org.nicexam.authorizationservice.usereo.UserRolesEO;
import org.nicexam.authorizationservice.usereo.UserTokenEO;
import org.nicexam.authorizationservice.usereo.UsersEO;
import org.nicexam.authorizationservice.usereo.UsersVONew;
import org.nicexam.authorizationservice.uservo.ActiveStatusVO;
import org.nicexam.authorizationservice.uservo.ActivityLogsVO;
import org.nicexam.authorizationservice.uservo.AppUserDetails;
import org.nicexam.authorizationservice.uservo.AssignRolePrivilegesVO;
import org.nicexam.authorizationservice.uservo.ChangeLogsVO;
import org.nicexam.authorizationservice.uservo.EbaNameVO;
import org.nicexam.authorizationservice.uservo.LoginHeaderVO;
import org.nicexam.authorizationservice.uservo.LoginSliderVO;
import org.nicexam.authorizationservice.uservo.MenuPrivilegesVO;
import org.nicexam.authorizationservice.uservo.MenuSubMenuVO;
import org.nicexam.authorizationservice.uservo.MenuSubmenuMappingVO;
import org.nicexam.authorizationservice.uservo.MenuUrlMappingVO;
import org.nicexam.authorizationservice.uservo.OfficeAcronymVO;
import org.nicexam.authorizationservice.uservo.OfficeTypeVO;
import org.nicexam.authorizationservice.uservo.OtpDetailsVerifyVO;
import org.nicexam.authorizationservice.uservo.UserInfo;
import org.nicexam.authorizationservice.uservo.UserMenuMasterVO;
import org.nicexam.authorizationservice.uservo.UserMenuPrivilegesVO;
import org.nicexam.authorizationservice.uservo.UserRoleMappingVO;
import org.nicexam.authorizationservice.uservo.UserRolesVO;
import org.nicexam.authorizationservice.uservo.UsersVO;
import org.nicexam.authorizationservice.utility.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesimpl implements UserService {
	@Autowired
	UserDao userDao;

	@Autowired
	UserRoleDao userroleDao;

	/* USERS */
	@Override
	public String saveUser(UsersVO usersVo, UserInfo userInfo) {
		UsersEO userEo = new UsersEO();
		BeanUtils.copyProperties(usersVo, userEo);
		//userEo.setActive(true);
		//userEo.setCreatedBy(userInfo.getUserId());
		//userEo.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
		//userEo.setUpdatedBy(userInfo.getUserId());
		//userEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userDao.saveUserData(userEo);
	}

	@Override
	public String saveUserNew(UsersVONew usersVo) {
		UsersEO userEo = new UsersEO();
//		BeanUtils.copyProperties(usersVo, userEo);
		userEo.setLoginId(usersVo.getLoginId());
		userEo.setName(usersVo.getName());
		//userEo.setActive(true);
		userEo.setPassword(usersVo.getPassword());
//		userEo.setCreatedBy(usersVo.getUserId());
		//userEo.setEmailId(usersVo.getEmail());
		//userEo.setMobileNo(usersVo.getMobile());
	//	userEo.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
//		userEo.setUpdatedBy(usersVo.getUserId());
	//	userEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userDao.saveUserData(userEo);
	}

	@Override
	public UsersVO saveRegisteredUser(UsersVO usersVo, UserInfo userInfo) {
		UsersVO saveRegisteredUserData = userDao.saveRegisteredUserData(usersVo, userInfo);
		if (saveRegisteredUserData != null) {
			usersVo.setId(saveRegisteredUserData.getId());
		}
		return usersVo;
	}

	@Override
	public boolean updateUser(UsersVO usersVo, UserInfo userInfo) {
		UsersEO userEo = new UsersEO();
		BeanUtils.copyProperties(usersVo, userEo);
	//	userEo.setUpdatedBy(userInfo.getUserId());
	//	userEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userDao.updateUserData(userEo);
	}

	@Override
	public boolean deleteUser(UsersVO usersVo, UserInfo userInfo) {
		UsersEO userEo = new UsersEO();
		BeanUtils.copyProperties(usersVo, userEo);
		//userEo.setUpdatedBy(userInfo.getUserId());
	//	userEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userDao.changeUserStatus(userEo);
	}

	@Override
	public UsersVO fetchUserById(UsersVO userVo) {
		UsersEO userEo = new UsersEO();
	//	userEo.setId(userVo.getId());
		// change needed
	//	userEo = userDao.fetchUserDataById(userEo.getId());
		BeanUtils.copyProperties(userEo, userVo);
		return userVo;
	}

	@Override
	public List<UsersVO> fetchAllUsers(Integer officelevelId, Integer ebaId) {
		List<UsersVO> uservo = new ArrayList<>();
		uservo = userDao.fetchAllEbaUsersData(officelevelId, ebaId);
		return uservo;
	}

	@Override
	public boolean saveRoles(UserRolesVO userRoleVo, UserInfo userInfo) {
		UserRolesEO userRoleEo = new UserRolesEO();
		BeanUtils.copyProperties(userRoleVo, userRoleEo);
		userRoleEo.setCreatedBy(userInfo.getUsername());
		userRoleEo.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
		userRoleEo.setUpdatedBy(userInfo.getUsername());
		userRoleEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userroleDao.saveUserRoleData(userRoleEo);
	}

	@Override
	public boolean updateRoles(UserRolesVO userRoleVo, UserInfo userInfo) {
		UserRolesEO userRoleEo = new UserRolesEO();
		BeanUtils.copyProperties(userRoleVo, userRoleEo);
		userRoleEo.setUpdatedBy(userInfo.getUsername());
		userRoleEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userroleDao.updateUserRoleData(userRoleEo);
	}

	@Override
	public boolean deleteRoles(UserRolesVO roleVo, UserInfo userInfo) {
		UserRolesEO userRoleEo = new UserRolesEO();
		BeanUtils.copyProperties(roleVo, userRoleEo);
		userRoleEo.setUpdatedBy(userInfo.getUsername());
		userRoleEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userroleDao.changeUserRoleStatus(userRoleEo);
	}

	@Override
	public List<UserRolesVO> fetchAllRoles() {
		List<UserRolesEO> userRolesEo = new ArrayList<>();
		userRolesEo = userroleDao.fetchAllRoleData();
		List<UserRolesVO> userRolesVOList = new ArrayList<>();
		for (UserRolesEO userRolesEoList : userRolesEo) {
			UserRolesVO userVO = new UserRolesVO();
			BeanUtils.copyProperties(userRolesEoList, userVO);
			userRolesVOList.add(userVO);
		}
		return userRolesVOList;
	}

	@Override
	public UserRolesVO fetchRoleById(UserRolesVO roleVo) {
		UserRolesEO roles = new UserRolesEO();
		roles.setRecordId(roleVo.getRecordId());
		roles = userroleDao.fetchRoleDataById(roles.getRecordId());
		BeanUtils.copyProperties(roles, roleVo);
		return roleVo;
	}

	@Override
	public boolean saveUserRoleMapping(UserRoleMappingVO userRoleMappingVo, UserInfo userInfo) {
		List<UserRoleMappingEO> userroleMappinglistdata = new ArrayList<UserRoleMappingEO>();
		UsersEO checkforexistinguserid = userDao.fetchUserDataById(userRoleMappingVo.getUserRecordId());
		List<UserRolesEO> checkforexistingrolesId = userroleDao.fetchuserroledata(userRoleMappingVo.getRolesRecordId());
		if (checkforexistinguserid != null && checkforexistingrolesId != null) {
			for (int i = 0; i < userRoleMappingVo.getRolesRecordId().size(); i++) {
				UserRoleMappingEO userrolemappingeo = new UserRoleMappingEO();
				userrolemappingeo.setUserRecordId(userRoleMappingVo.getUserRecordId());
				userrolemappingeo.setRolesRecordId(userRoleMappingVo.getRolesRecordId().get(i));
				userrolemappingeo.setCreatedBy(userInfo.getUsername());
				userrolemappingeo.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
				userrolemappingeo.setUpdatedBy(userInfo.getUsername());
				userrolemappingeo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
				userrolemappingeo.setUserOfficeId(userRoleMappingVo.getUserOfficeId());
				userrolemappingeo.setUserOfficeValue(userRoleMappingVo.getUserOfficeValue());
				userrolemappingeo.setStatus(userRoleMappingVo.getStatus());
				userroleMappinglistdata.add(userrolemappingeo);
			}
			return userDao.saveuserrolemapping(userroleMappinglistdata);
		} else {
			return false;
		}
	}

	@Override
	public boolean saveMenu(UserMenuMasterVO userMenuVO, UserInfo userInfo) {
		UserMenuMasterEO userMenuEo = new UserMenuMasterEO();
		BeanUtils.copyProperties(userMenuVO, userMenuEo);
		userMenuEo.setHasChild(false);
		userMenuEo.setIdValue(null);
		userMenuEo.setMenuBlock(null);
		userMenuEo.setMenuClass(null);
		userMenuEo.setMenuIcon(null);
		userMenuEo.setMenuLocation(null);
		/*
		 * if (userMenuVO.getIsActive() == 0) { userMenuEo.setActive(false); } else if
		 * (userMenuVO.getIsActive() == 1) { userMenuEo.setActive(true); }
		 */
		userMenuEo.setLangCode("EN");
		userMenuEo.setParentId(0);
		userMenuEo.setCreatedBy(userInfo.getUsername());
		userMenuEo.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
		userMenuEo.setUpdatedBy(userInfo.getUsername());
		userMenuEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userDao.saveUserMenu(userMenuEo);
	}

	@Override
	public List<UserMenuMasterVO> fetchAllUserMenu() {
		List<UserMenuMasterVO> menulistvo = new ArrayList<>();
		menulistvo = userDao.fetchAllMenuDatalist();
		return menulistvo;
	}

	@Override
	public boolean saveMenuUrlMapping(MenuUrlMappingVO menuUrlMappingVO, UserInfo userInfo) {
		MenuUrlMappingEO urlMappingEo = new MenuUrlMappingEO();
		BeanUtils.copyProperties(menuUrlMappingVO, urlMappingEo);
		urlMappingEo.setCreatedBy(userInfo.getUsername());
		urlMappingEo.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
		urlMappingEo.setUpdatedBy(userInfo.getUsername());
		urlMappingEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userDao.savemenuUrlMapping(urlMappingEo);
	}

	@Override
	public List<OfficeTypeVO> fetchAllOfficeType() {
		List<OfficeTypeEO> officeTypeEoList = userDao.fetchAllOfficeTypeEo();
		List<OfficeTypeVO> officeTypeVoList = new ArrayList<OfficeTypeVO>();
		for (OfficeTypeEO eodata : officeTypeEoList) {
			OfficeTypeVO vodata = new OfficeTypeVO();
			BeanUtils.copyProperties(eodata, vodata);
			officeTypeVoList.add(vodata);
		}
		return officeTypeVoList;
	}

	@Override
	public List<EbaNameVO> fetchAllEba() {
		List<EbaNameEO> examnNameeolist = userDao.fetchAllEbaNameList();
		List<EbaNameVO> volist = new ArrayList<EbaNameVO>();
		for (EbaNameEO eodata : examnNameeolist) {
			EbaNameVO vodata = new EbaNameVO();
			BeanUtils.copyProperties(eodata, vodata);
			volist.add(vodata);
		}
		return volist;
	}

	@Override
	public List<UserRolesVO> fetchAllRolesList() {
		List<UserRolesVO> userlistvo = new ArrayList<>();
		userlistvo = userDao.fetchAllUserRolesEbaData();
		return userlistvo;
	}

	@Override
	public List<UsersVO> fetchAllUsersList(UserInfo userInfo) {
		List<UsersVO> userVOList = new ArrayList<>();
		userVOList = userDao.fetchAllUserData(userInfo);
		return userVOList;
	}

	@Override
	public List<ActiveStatusVO> fetchAllActiveStatus() {
		List<ActiveStatusEO> activeListEo = new ArrayList<>();
		activeListEo = userDao.fetchAllActiveStatus();
		List<ActiveStatusVO> activeVOList = new ArrayList<>();
		for (ActiveStatusEO userEoList : activeListEo) {
			ActiveStatusVO activeStatusVO = new ActiveStatusVO();
			BeanUtils.copyProperties(userEoList, activeStatusVO);
			activeVOList.add(activeStatusVO);
		}
		return activeVOList;
	}

	@Override
	public List<UserRolesVO> fetchAllEbaWiseRoles(Integer ebaId, Integer officelevelId) {
		List<UserRolesVO> userrolesvo = new ArrayList<>();
		userrolesvo = userDao.fetchAllUserRolesFromEbaData(ebaId, officelevelId);
		return userrolesvo;
	}

	@Override
	public List<MenuUrlMappingVO> fetchSubmenuById() {
		List<MenuUrlMappingEO> submenyList = userDao.fetchAllSubMenuById();
		List<MenuUrlMappingVO> submenuVOList = new ArrayList<>();
		for (MenuUrlMappingEO submenuEO : submenyList) {
			MenuUrlMappingVO submenuVO = new MenuUrlMappingVO();
			BeanUtils.copyProperties(submenuEO, submenuVO);
			submenuVOList.add(submenuVO);
		}
		return submenuVOList;
	}

	@Override
	public List<MenuUrlMappingVO> fetchAllSubmenu() {
		List<MenuUrlMappingVO> menuurlmappingVoData = new ArrayList<>();
		menuurlmappingVoData = userDao.fetchAllSubmenuDatalist();
		return menuurlmappingVoData;
	}

	@Override
	public boolean saveMenuSubmenuMapping(MenuSubmenuMappingVO menuSubmenuMapppingVO, UserInfo userInfo) {
		MenuSubmenuMappingEO menuSubmenuMapEo = new MenuSubmenuMappingEO();
		BeanUtils.copyProperties(menuSubmenuMapppingVO, menuSubmenuMapEo);
		menuSubmenuMapEo.setCreatedBy(userInfo.getUsername());
		menuSubmenuMapEo.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
		menuSubmenuMapEo.setUpdatedBy(userInfo.getUsername());
		menuSubmenuMapEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userDao.saveMenuSubmenuMapping(menuSubmenuMapEo);
	}

	@Override
	public List<UserMenuPrivilegesVO> fetchAllMenuPrivileges() {
		List<UserMenuPrivilegesEO> userprivilegesEo = new ArrayList<>();
		userprivilegesEo = userroleDao.fetchAllPrivligesData();
		List<UserMenuPrivilegesVO> privilegesVOList = new ArrayList<>();
		for (UserMenuPrivilegesEO privilegesEodata : userprivilegesEo) {
			UserMenuPrivilegesVO privilegesVO = new UserMenuPrivilegesVO();
			BeanUtils.copyProperties(privilegesEodata, privilegesVO);
			privilegesVOList.add(privilegesVO);
		}
		return privilegesVOList;
	}

	@Override
	public List<MenuSubMenuVO> fetchSelectedFields(MenuSubMenuVO menuSubmenuVO) {
		List<MenuSubMenuVO> dynamicFormFieldsVOs = userDao.fetchAllMenuSubmenu(menuSubmenuVO);
		// Map<String, List<MenuSubMenuVO>> groupByMenuMap =
		// dynamicFormFieldsVOs.stream().collect(Collectors.groupingBy(MenuSubMenuVO::getMenu));
		return dynamicFormFieldsVOs;
	}

	@Override
	public List<UserRoleMappingVO> fetchAllUserRegionMappingData() {
		List<UserRoleMappingVO> userrolemappingVoData = new ArrayList<>();
		userrolemappingVoData = userDao.fetchAllUserRoleMappingDatalist();
		return userrolemappingVoData;
	}

	@Override
	public boolean saveUserRoleMenuPrivilegesMapping(AssignRolePrivilegesVO menuSubmenuVO, UserInfo userInfo) {

		List<UserRoleMenuPrivilegesEO> userroleMenuPrivileges = new ArrayList<UserRoleMenuPrivilegesEO>();
		for (MenuSubMenuVO menuSubMenuVO : menuSubmenuVO.getMenuDetails()) {
			UserRoleMenuPrivilegesEO userroleMenuPriveleges = new UserRoleMenuPrivilegesEO();

			MenuPrivilegesVO menuPrivilegesVO = new MenuPrivilegesVO();// jsonb data save
			menuPrivilegesVO.setListData(menuSubMenuVO.getMenuprivileges());
			userroleMenuPriveleges.setMenuprivilegesVo(menuPrivilegesVO);

			userroleMenuPriveleges.setMenuId(menuSubMenuVO.getMenuRecordId());
			userroleMenuPriveleges.setSubMenuId(menuSubMenuVO.getSubmenuRecordId());
			userroleMenuPriveleges.setDisplaySequence(menuSubmenuVO.getDisplaySequence());
			userroleMenuPriveleges.setRoleId(menuSubmenuVO.getRoleId());

			userroleMenuPriveleges.setCreatedBy(userInfo.getUsername());
			userroleMenuPriveleges.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
			userroleMenuPriveleges.setUpdatedBy(userInfo.getUsername());
			userroleMenuPriveleges.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
			userroleMenuPriveleges.setStatus(ActiveStatusType.ACTIVE.getStatusType());

			userroleMenuPrivileges.add(userroleMenuPriveleges);
		}
		return userDao.saveUserRoleMenuPrivilegesMapping(userroleMenuPrivileges);
	}

	@Override
	public List<MenuSubmenuMappingVO> fetchAllMenuSubMenuMapping() {
		List<MenuSubmenuMappingVO> menusubMenuMappingVoData = new ArrayList<>();
		menusubMenuMappingVoData = userDao.fetchAllMenuSubmenuMappingDatalist();
		return menusubMenuMappingVoData;
	}

	@Override
	public AssignRolePrivilegesVO fetchMenuPrivilegesByParameters(AssignRolePrivilegesVO rolePrivilegesVo) {
		return userDao.fetchMenuPrivilegesByParam(rolePrivilegesVo);
	}

	@Override
	public boolean saveSlider(LoginSliderVO slidervo, UserInfo userInfo) {
		LoginSliderEO loginSlider = new LoginSliderEO();
		BeanUtils.copyProperties(slidervo, loginSlider);
		loginSlider.setOfficeType("NIC");
		loginSlider.setActive(true);
		loginSlider.setCreatedBy(userInfo.getUsername());
		loginSlider.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
		loginSlider.setUpdatedBy(userInfo.getUsername());
		loginSlider.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userDao.saveSlider(loginSlider);
	}

	@Override
	public boolean saveHeader(LoginHeaderVO headervo, UserInfo userInfo) {
		LoginHeaderEO loginHeaderEo = new LoginHeaderEO();
		BeanUtils.copyProperties(headervo, loginHeaderEo);
		loginHeaderEo.setOfficeType("NIC");
		loginHeaderEo.setActive(true);
		loginHeaderEo.setCreatedBy(userInfo.getUsername());
		loginHeaderEo.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
		loginHeaderEo.setUpdatedBy(userInfo.getUsername());
		loginHeaderEo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
		return userDao.saveHeader(loginHeaderEo);
	}

	@Override
	public List<LoginSliderVO> fetchSliderList() {
		List<LoginSliderEO> sliderEo = userDao.fetchSliderList();
		List<LoginSliderVO> sliderVOList = new ArrayList<>();
		for (LoginSliderEO sliderEoList : sliderEo) {
			LoginSliderVO sliderVO = new LoginSliderVO();
			BeanUtils.copyProperties(sliderEoList, sliderVO);
			sliderVOList.add(sliderVO);
		}
		return sliderVOList;
	}

	@Override
	public List<LoginHeaderVO> fetchHeaderList() {
		List<LoginHeaderEO> headerEoList = userDao.fetchHeaderList();
		List<LoginHeaderVO> headerVOList = new ArrayList<>();
		for (LoginHeaderEO headerEo : headerEoList) {
			LoginHeaderVO headerVO = new LoginHeaderVO();
			BeanUtils.copyProperties(headerEo, headerVO);
			headerVOList.add(headerVO);
		}
		return headerVOList;
	}

	@Override
	public Boolean hasURLAccess(String username, String url) {
		return userDao.hasURLAccess(username, url);
	}

	@Override
	public AssignRolePrivilegesVO fetchSelectedMenuPrivileges(AssignRolePrivilegesVO rolePrivilegesVo) {
		return userDao.fetchSelectedMenuPrivileges(rolePrivilegesVo);
	}

	@Override
	public Boolean isUserNameExist(String username) {
		return userDao.isUserNameExist(username.toLowerCase());
	}

	@Override
	public AppUserDetails fetchUserDetails(UserInfo userInfo) {
		return userDao.fetchUserDetails(userInfo);
	}

	@Override
	public Boolean saveChangeLogs(ChangeLogsVO changeLogVo) {
		ChangeLogsEO changeLogsEo = new ChangeLogsEO();
		BeanUtils.copyProperties(changeLogVo, changeLogsEo);
		changeLogsEo.setAction("Action");
		changeLogsEo.setCreated(DateUtils.obtainCurrentTimeStamp());
		changeLogsEo.setType("Type");
		return userDao.saveChangeLogs(changeLogsEo);
	}

	@Override
	public List<ActivityLogsVO> fetchAllActivityLogs(String username) {
		List<ActivityLogsEO> activityLogEOList = userDao.fetchAllActivityLogs(username);
		List<ActivityLogsVO> activityLogVOList = new ArrayList<>();
		for (ActivityLogsEO activityLogsEO : activityLogEOList) {
			ActivityLogsVO activityLogVO = new ActivityLogsVO();
			BeanUtils.copyProperties(activityLogsEO, activityLogVO);
			activityLogVOList.add(activityLogVO);
		}
		return activityLogVOList;
	}

	@Override
	public String verifyOtp(OtpDetailsVerifyVO otpVerify, UserInfo userInfo) {
		return userDao.verifyOtp(otpVerify, userInfo);
	}

//change needed
	@Override
	public boolean saveCredentials(UsersVO usersVo, UserInfo userInfo) {
		// List<UserRolesEO> userRoles = userDao.fetchRoleByOfficeId(4/*
		// usersVo.getOfficeTypeId() */);
		UsersEO usersEo = userDao.fetchUserDataById(Long.parseLong(userInfo.getUsername()));
		List<UserRoleMappingEO> userroleMappinglistdata = new ArrayList<UserRoleMappingEO>();
		Boolean isUserNameExist = userDao.isUserNameExist(usersVo.getLoginId());
		if (usersEo != null && !isUserNameExist) {
			// users.setOfficeTypeId(3);
			usersEo.setLoginId(usersVo.getLoginId());
			usersEo.setPassword(usersVo.getPassword());
			// users.setLoginCreatedOn(DateUtils.obtainCurrentTimeStamp());
			userDao.updateUserData(usersEo);

			UserRoleMappingEO userrolemappingeo = new UserRoleMappingEO();
			//userrolemappingeo.setUserRecordId(usersEo.getId());
			userrolemappingeo.setUserOfficeId(2);// static office Id for Registered User Role
			userrolemappingeo.setUserOfficeValue("National Informatics Centre");
			userrolemappingeo.setRolesRecordId(1);// static Registered User Role
			userrolemappingeo.setCreatedBy(userInfo.getUsername());
			userrolemappingeo.setCreatedOn(DateUtils.obtainCurrentTimeStamp());
			userrolemappingeo.setUpdatedBy(userInfo.getUsername());
			userrolemappingeo.setUpdatedOn(DateUtils.obtainCurrentTimeStamp());
			userrolemappingeo.setStatus(ActiveStatusType.ACTIVE.getStatusType());
			userroleMappinglistdata.add(userrolemappingeo);

			return userDao.saveuserrolemapping(userroleMappinglistdata);
		} else {
			return false;
		}
	}

	@Override
	public List<OfficeAcronymVO> fetchAllOfficeAcronym() {
		List<OfficeAcronymEO> officeAcronymListEo = new ArrayList<>();
		officeAcronymListEo = userDao.fetchAllOfficeAcronym();
		List<OfficeAcronymVO> officeAcronymVOList = new ArrayList<>();
		for (OfficeAcronymEO officeAcronym : officeAcronymListEo) {
			OfficeAcronymVO officeAcronymVO = new OfficeAcronymVO();
			BeanUtils.copyProperties(officeAcronym, officeAcronymVO);
			officeAcronymVOList.add(officeAcronymVO);
		}
		return officeAcronymVOList;
	}

	@Override
	public UsersVO fetchUserDetailsFromUsername(String username) {
		return userDao.fetchUserDetailsFromUsername(username);
	}

	@Override
	public List<UsersVO> findAllUserData() {
		return userDao.findAllUserData();

	}

	@Override
	public UsersVO fetchUserByLoginId(UsersVO userVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserTokenEO findUserByToken(String username) {
		return userDao.findUserByToken(username);
	}

}