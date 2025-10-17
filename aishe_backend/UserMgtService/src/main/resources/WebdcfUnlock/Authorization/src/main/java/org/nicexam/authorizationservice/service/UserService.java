package org.nicexam.authorizationservice.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nicexam.authorizationservice.usereo.UserTokenEO;
import org.nicexam.authorizationservice.usereo.UsersVONew;
import org.nicexam.authorizationservice.uservo.ActiveStatusVO;
import org.nicexam.authorizationservice.uservo.ActivityLogsVO;
import org.nicexam.authorizationservice.uservo.AppUserDetails;
import org.nicexam.authorizationservice.uservo.AssignRolePrivilegesVO;
import org.nicexam.authorizationservice.uservo.ChangeLogsVO;
import org.nicexam.authorizationservice.uservo.EbaNameVO;
import org.nicexam.authorizationservice.uservo.LoginHeaderVO;
import org.nicexam.authorizationservice.uservo.LoginSliderVO;
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

public interface UserService {
	/* USERS */
	String saveUser(UsersVO users,UserInfo userInfo);
	
	String saveUserNew(UsersVONew users);
	
	UsersVO saveRegisteredUser(UsersVO usersVo,UserInfo userInfo);
	
	boolean updateUser(UsersVO users,UserInfo userInfo);

	UsersVO fetchUserById(UsersVO userVo);

	List<UsersVO> fetchAllUsers(Integer officelevelId,Integer ebaId);

	boolean deleteUser(UsersVO usersVo,UserInfo userInfo);
	
	boolean saveRoles(UserRolesVO userRoleVo,UserInfo userInfo);

	boolean updateRoles(UserRolesVO userRoleVo,UserInfo userInfo);

	boolean deleteRoles(UserRolesVO roleVo,UserInfo userInfo);

	List<UserRolesVO> fetchAllRoles();

	UserRolesVO fetchRoleById(UserRolesVO roleVo);
	
	boolean saveUserRoleMapping(UserRoleMappingVO userRoleMappingVo,UserInfo userInfo); /* User Role Mapping */

	boolean saveMenu(UserMenuMasterVO userMenuVO, UserInfo userInfo); /* User Menu */

	List<UserMenuMasterVO> fetchAllUserMenu();
	
	boolean saveMenuUrlMapping(MenuUrlMappingVO menuUrlMappingVO, UserInfo userInfo);/* Menu Url Mapping */
	
	List<OfficeTypeVO> fetchAllOfficeType(); /*  Office Type */
 
	List<EbaNameVO> fetchAllEba();

	List<UserRolesVO> fetchAllRolesList(); /* fetch all roles eba list */

	List<UsersVO> fetchAllUsersList(UserInfo userInfo);

	List<ActiveStatusVO> fetchAllActiveStatus(); /* active status */

	List<UserRolesVO> fetchAllEbaWiseRoles(Integer ebaId,Integer officelevelId);

	List<MenuUrlMappingVO> fetchSubmenuById();

	List<MenuUrlMappingVO> fetchAllSubmenu();

	boolean saveMenuSubmenuMapping(MenuSubmenuMappingVO menuSubmenuMapppingVO,UserInfo userInfo);

	List<UserMenuPrivilegesVO> fetchAllMenuPrivileges();

	List<MenuSubMenuVO> fetchSelectedFields(MenuSubMenuVO menuSubmenuVO);

	List<UserRoleMappingVO> fetchAllUserRegionMappingData();

	boolean saveUserRoleMenuPrivilegesMapping(AssignRolePrivilegesVO menuSubmenuVO,UserInfo userInfo);

	List<MenuSubmenuMappingVO> fetchAllMenuSubMenuMapping();

    AssignRolePrivilegesVO fetchMenuPrivilegesByParameters(AssignRolePrivilegesVO rolePrivilegesVo);
    
	boolean saveSlider(LoginSliderVO slidervo,UserInfo userInfo);

	boolean saveHeader(LoginHeaderVO headervo,UserInfo userInfo);

	List<LoginSliderVO> fetchSliderList();

	List<LoginHeaderVO> fetchHeaderList();
	
	Boolean hasURLAccess(String username,String url);

	Boolean isUserNameExist(String username);
	
	AssignRolePrivilegesVO fetchSelectedMenuPrivileges(AssignRolePrivilegesVO rolePrivilegesVo);

	AppUserDetails fetchUserDetails(UserInfo userInfo);

	Boolean saveChangeLogs(ChangeLogsVO changeLogVo);

	List<ActivityLogsVO> fetchAllActivityLogs(String username);

	String verifyOtp(OtpDetailsVerifyVO otpVerify, UserInfo userInfo);

	boolean saveCredentials(UsersVO usersVo, UserInfo userInfo);

	List<OfficeAcronymVO> fetchAllOfficeAcronym();

	UsersVO fetchUserDetailsFromUsername(String username);

	List<UsersVO> findAllUserData();

	UsersVO fetchUserByLoginId(UsersVO userVo);

	UserTokenEO findUserByToken(String username);

}