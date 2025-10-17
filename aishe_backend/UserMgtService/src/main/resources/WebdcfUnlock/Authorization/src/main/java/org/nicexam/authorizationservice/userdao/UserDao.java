package org.nicexam.authorizationservice.userdao;

import java.util.List;

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
import org.nicexam.authorizationservice.usereo.UserRoleMappingEO;
import org.nicexam.authorizationservice.usereo.UserRoleMenuPrivilegesEO;
import org.nicexam.authorizationservice.usereo.UserRolesEO;
import org.nicexam.authorizationservice.usereo.UserTokenEO;
import org.nicexam.authorizationservice.usereo.UsersEO;
import org.nicexam.authorizationservice.uservo.AppUserDetails;
import org.nicexam.authorizationservice.uservo.AssignRolePrivilegesVO;
import org.nicexam.authorizationservice.uservo.MenuSubMenuVO;
import org.nicexam.authorizationservice.uservo.MenuSubmenuMappingVO;
import org.nicexam.authorizationservice.uservo.MenuUrlMappingVO;
import org.nicexam.authorizationservice.uservo.OtpDetailsVerifyVO;
import org.nicexam.authorizationservice.uservo.UserInfo;
import org.nicexam.authorizationservice.uservo.UserMenuMasterVO;
import org.nicexam.authorizationservice.uservo.UserRoleMappingVO;
import org.nicexam.authorizationservice.uservo.UserRolesVO;
import org.nicexam.authorizationservice.uservo.UsersVO;


public interface UserDao {
	/* Users */
	String saveUserData(UsersEO users);
	
	UsersVO saveRegisteredUserData(UsersVO userVo,UserInfo userInfo);
	//change needed
	UsersEO fetchUserDataById(Long id);
	
	UsersEO fetchUserByUsername(String username);

	List<UsersVO> fetchAllEbaUsersData(Integer officelevelId,Integer ebaId);

	boolean updateUserData(UsersEO userEo);

	boolean changeUserStatus(UsersEO user);
//User Role Mapping
	boolean saveuserrolemapping(List<UserRoleMappingEO> userroleMappinglistdata);
//User Menu
	boolean saveUserMenu(UserMenuMasterEO userMenuEo);

	List<UserMenuMasterEO> fetchAllMenu();
	//Menu Url Mapping
	boolean savemenuUrlMapping(MenuUrlMappingEO urlMappingEo);
	
	// Office Type
	List<OfficeTypeEO> fetchAllOfficeTypeEo();

	List<EbaNameEO> fetchAllEbaNameList();

	List<UserRolesVO> fetchAllUserRolesEbaData();

	List<UsersVO> fetchAllUserData(UserInfo userInfo);

	List<ActiveStatusEO> fetchAllActiveStatus();

	List<UserRolesVO> fetchAllUserRolesFromEbaData(Integer ebaId,Integer officelevelId);
	
	List<UserRolesVO> fetchAllUserRolesForUser(Long userRecordId);

	List<MenuUrlMappingEO> fetchAllSubMenuById();

	List<MenuUrlMappingEO> fetchAllSubmenuData();

	boolean saveMenuSubmenuMapping(MenuSubmenuMappingEO menuSubmenuMapEo);

	List<MenuUrlMappingVO> fetchAllSubmenuDatalist();

	List<MenuSubMenuVO> fetchAllMenuSubmenu(MenuSubMenuVO menuSubmenuVO);

	List<UserMenuMasterVO> fetchAllMenuDatalist();

	List<UserRoleMappingVO> fetchAllUserRoleMappingDatalist();

	boolean saveUserRoleMenuPrivilegesMapping(List<UserRoleMenuPrivilegesEO> userroleMenuPrivileges);

	List<MenuSubmenuMappingVO> fetchAllMenuSubmenuMappingDatalist();
    
	AssignRolePrivilegesVO fetchMenuPrivilegesByParam(AssignRolePrivilegesVO rolePrivilegesVo);
	
	boolean saveSlider(LoginSliderEO loginSlider);

	boolean saveHeader(LoginHeaderEO loginHeaderEo);

	List<LoginSliderEO> fetchSliderList();

	List<LoginHeaderEO> fetchHeaderList();

	Boolean hasURLAccess(String username, String url);

	AssignRolePrivilegesVO fetchSelectedMenuPrivileges(AssignRolePrivilegesVO rolePrivilegesVo);

	Boolean isUserNameExist(String username);

	AppUserDetails fetchUserDetails(UserInfo userInfo);

	boolean saveActivityLogs(ActivityLogsEO activityLogsEo);
	
	boolean saveChangeLogs(ChangeLogsEO changeLogsEo);

	List<ActivityLogsEO> fetchAllActivityLogs(String username);

	String verifyOtp(OtpDetailsVerifyVO otpVerify, UserInfo userInfo);

	List<UserRolesEO> fetchRoleByOfficeId(Integer officeTypeId);

	List<OfficeAcronymEO> fetchAllOfficeAcronym();

	UsersVO fetchUserDetailsFromUsername(String username);

	List<UsersVO> findAllUserData();
	
	public Boolean saveTokenDetails(String ipAddress, String user_name, String tokenValue);

	boolean isTokenExpired(String tokenValue);

	Boolean saveLogoutDetails(String ipAddress, String username, String tokenValue);

	String fetchTokenActiveForUserandNotExpire(String name);

	UserTokenEO findUserByToken(String username);

	boolean matchBcryptPassword(String altpwd, String alternatePassword);
}