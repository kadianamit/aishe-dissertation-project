package org.nicexam.authorizationservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nicexam.authorizationservice.enums.Message;
import org.nicexam.authorizationservice.security.CustomLogoutSuccessHandler;
import org.nicexam.authorizationservice.security.JwtConfig;
import org.nicexam.authorizationservice.service.UserService;
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
import org.nicexam.authorizationservice.utility.CommonUtils;
import org.nicexam.authorizationservice.utility.ReturnResponse;
import org.nicexam.authorizationservice.utility.WithUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth/users")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	JwtConfig jwtConfig;
	
	@Autowired
	CustomLogoutSuccessHandler customLogoutSuccessHandler;

	/* USERS */
	@PostMapping(value = "/saveuser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> createUsers(@RequestBody UsersVONew usersVo) {
		logger.info("UserController : createUsers method invoked : {}");
		String isUserAdded = userService.saveUserNew(usersVo);
		ResponseEntity<ReturnResponse> response = null;
		switch (Message.parse(isUserAdded)) {
		case SUCCESS:
			response = new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Users has been saved successfully!!"), HttpStatus.OK);
			break;

		case EXISTS:
			response = new ResponseEntity<>(
					new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Username Already Exist"),
					HttpStatus.BAD_REQUEST);
			break;

		case ERROR:
			response = new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
			break;
		}
		return response;
	}

	/* USERS */
	@PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> saveRegisteredUser(@RequestBody UsersVO usersVo) {
		logger.info("UserController : saveUserInfo method invoked : {}");
		UsersVO usersVO = userService.saveRegisteredUser(usersVo, new UserInfo("Created User", 0l));
		ResponseEntity<ReturnResponse> response = null;
		if (usersVO.getId() != null) {
			ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(),
					"Users has been saved successfully!!");

			returnResponse.setToken(CommonUtils.getToken(usersVO.getId(), jwtConfig));
			response = new ResponseEntity<>(returnResponse, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@PutMapping(value = "/updateuser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> updateUsers(@RequestBody UsersVO usersVo, @WithUser UserInfo userInfo) {

		logger.info("UserController : updateUsers method invoked : {}");
		boolean updateUser = userService.updateUser(usersVo, userInfo);
		if (updateUser) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Users has been Updated successfully!!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/changeuserstatus/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> changeUserStatus(@PathVariable Long id, @PathVariable boolean status,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : changeUserStatus method invoked : {}");
		UsersVO usersVo = new UsersVO();
		usersVo.setId(id);
		boolean changeusersStatus = userService.deleteUser(usersVo, userInfo);
		if (changeusersStatus) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "User Status has been Updated successfully!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/userslist/{ebaId}/{officelevelId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsersVO> findAllUsers(@PathVariable Integer officelevelId, @PathVariable Integer ebaId) {
		logger.info("UserController : findAllUsers method invoked : {}");
		List<UsersVO> userList = userService.fetchAllUsers(officelevelId, ebaId);
		return userList;
	}

	@GetMapping(value = "/userlist", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsersVO> findAllUserList(@WithUser UserInfo userInfo) {
		logger.info("UserController : findAllUserList method invoked : {}");
		List<UsersVO> userList = userService.fetchAllUsersList(userInfo);
		return userList;
	}

	@GetMapping(value = "/usersdata", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UsersVO> findAllUserData() {
		logger.info("UserController : findAllUserData method invoked : {}");
		List<UsersVO> userList = userService.findAllUserData();
		return userList;
	}

	@GetMapping(value = "/rolelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserRolesVO> findAllEbaRolesList() {
		logger.info("UserController : findAllEbaRolesList method invoked : {}");
		List<UserRolesVO> rolesList = userService.fetchAllRolesList();
		return rolesList;
	}

	@GetMapping(value = "/userbyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UsersVO findUserById(@PathVariable Long id) {
		logger.info("UserController : findUserById method invoked : {}");
		UsersVO usersVo = new UsersVO();
		usersVo.setId(id);
		UsersVO user = userService.fetchUserById(usersVo);
		return user;
	}

	@PostMapping(value = "/saveroles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> createRoles(@RequestBody UserRolesVO userRoleVo,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : createRoles method invoked : {}");
		boolean isusersRolesAdded = userService.saveRoles(userRoleVo, userInfo);
		if (isusersRolesAdded) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Roles has been Saved successfully!!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/updateroles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> updateRoles(@RequestBody UserRolesVO userRoleVo,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : updateRoles method invoked : {}");
		boolean isusersRolesUpdated = userService.updateRoles(userRoleVo, userInfo);
		if (isusersRolesUpdated) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Roles has been Updated successfully!!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "/changeroletatus/{id}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> changeRoleStatus(@PathVariable int id, @PathVariable boolean status,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : changeRoleStatus method invoked : {}");
		UserRolesVO roleVo = new UserRolesVO();
		roleVo.setRecordId(id);
		boolean roleStatusChange = userService.deleteRoles(roleVo, userInfo);
		if (roleStatusChange) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "User Role Status has been Changed successfully!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/rolelists", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserRolesVO> findAllRoles() {
		logger.info("UserController : findAllRoles method invoked : {}");
		List<UserRolesVO> roleList = userService.fetchAllRoles();
		return roleList;
	}

	@GetMapping(value = "/rolebyid/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserRolesVO findRoleById(@PathVariable(required = false) Integer id) {
		logger.info("UserController : findRoleById method invoked : {}");
		UserRolesVO roleVo = new UserRolesVO();
		roleVo.setRecordId(id);
		UserRolesVO usersRoleVo = userService.fetchRoleById(roleVo);
		return usersRoleVo;
	}

	@PostMapping(value = "/userrolemapping", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> saveUserRoleMapping(@RequestBody UserRoleMappingVO userRoleMappingVo,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : saveUserRoleMapping method invoked : {}");
		boolean isUserRoleMappingAdded = userService.saveUserRoleMapping(userRoleMappingVo, userInfo);
		if (isUserRoleMappingAdded) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Users Role Mapping has been saved successfully!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/userrolemapping", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserRoleMappingVO> findAllUserRoleMapping() {
		logger.info("UserController : findAllUserRoleMapping method invoked : {}");
		List<UserRoleMappingVO> userrolemapping = userService.fetchAllUserRegionMappingData();
		return userrolemapping;
	}

	/* User Menu */
	@PostMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> createMenu(@RequestBody UserMenuMasterVO userMenuVO,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : createMenu method invoked : {}");
		boolean isuserMenuAdded = userService.saveMenu(userMenuVO, userInfo);
		if (isuserMenuAdded) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Menu has been Created Successfully!!"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/menu", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserMenuMasterVO> fetchAllUserMenu() {
		logger.info("UserController : fetchAllUserMenu method invoked : {}");
		List<UserMenuMasterVO> menuList = userService.fetchAllUserMenu();
		return menuList;
	}

	@PostMapping(value = "/menuurlmapping", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> saveMenuUrlMapping(@RequestBody MenuUrlMappingVO menuUrlMappingVO,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : createMenu method invoked : {}");
		boolean isuserMenuUrlMappingAdded = userService.saveMenuUrlMapping(menuUrlMappingVO, userInfo);
		if (isuserMenuUrlMappingAdded) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Menu Url Mapping has been Created Successfully!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/officetype", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OfficeTypeVO> fetchAllOfficeType() {
		logger.info("UserController : fetchAllOfficeType method invoked : {}");
		List<OfficeTypeVO> officeTypeList = userService.fetchAllOfficeType();
		return officeTypeList;
	}

	@GetMapping(value = "/eba", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EbaNameVO> fetchEbaName() {
		logger.info("UserController : fetchEbaName method invoked : {}");
		List<EbaNameVO> ebaNameVo = new ArrayList<>();
		ebaNameVo = userService.fetchAllEba();
		return ebaNameVo;
	}

	@GetMapping(value = "/activestatus", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ActiveStatusVO> fetchActiveStatus() {
		logger.info("UserController : fetchActiveStatus method invoked : {}");
		List<ActiveStatusVO> activeStatus = new ArrayList<>();
		activeStatus = userService.fetchAllActiveStatus();
		return activeStatus;
	}

	@GetMapping(value = "/ebawiseroles/{ebaId}/{officelevelId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserRolesVO> fetchRoleNameEbaWise(@PathVariable Integer ebaId, @PathVariable Integer officelevelId) {
		logger.info("UserController : fetchRoleNameEbaWise method invoked : {}");
		List<UserRolesVO> userroleVo = new ArrayList<>();
		userroleVo = userService.fetchAllEbaWiseRoles(ebaId, officelevelId);
		return userroleVo;
	}

	@GetMapping(value = "/submenuactive", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MenuUrlMappingVO> findSubMenuById() {
		logger.info("UserController : findSubMenuById method invoked : {}");
		List<MenuUrlMappingVO> submenuvo = userService.fetchSubmenuById();
		return submenuvo;
	}

	@GetMapping(value = "/submenu", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MenuUrlMappingVO> findAllSubMenu() {
		logger.info("UserController : findAllSubMenu method invoked : {}");
		List<MenuUrlMappingVO> submenuvo = userService.fetchAllSubmenu();
		return submenuvo;
	}

	@PostMapping(value = "/menusubmenumapping", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> saveMenuSubMenuMapping(
			@RequestBody MenuSubmenuMappingVO menuSubmenuMapppingVO, @WithUser UserInfo userInfo) {
		logger.info("UserController : createMenu method invoked : {}");
		boolean ismappingAdded = userService.saveMenuSubmenuMapping(menuSubmenuMapppingVO, userInfo);
		if (ismappingAdded) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Menu Submenu Mapping has been Saved Successfully!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/menusubmenumapping", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MenuSubmenuMappingVO> findAllMenuSubMenuMapping() {
		logger.info("UserController : findAllMenuSubMenuMapping method invoked : {}");
		List<MenuSubmenuMappingVO> MenuSubmenuMappingVO = userService.fetchAllMenuSubMenuMapping();
		return MenuSubmenuMappingVO;
	}

	@GetMapping(value = "/menuprivilege", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserMenuPrivilegesVO> findAllMenuPrivileges() {
		logger.info("UserController : findAllMenuPrivileges method invoked : {}");
		List<UserMenuPrivilegesVO> menuprivileges = userService.fetchAllMenuPrivileges();
		return menuprivileges;
	}

	@GetMapping(value = "/menusubmenu", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, List<MenuSubMenuVO>> fetchAllMenuSub() {
		MenuSubMenuVO menuSubmenuVO = new MenuSubMenuVO();
		HashMap<String, List<MenuSubMenuVO>> map = new HashMap<>();
		map.put("menuDetails", userService.fetchSelectedFields(menuSubmenuVO));
		return map;
	}

	@PostMapping(value = "/assignpermission", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> saveMenuAssignPermission(@RequestBody AssignRolePrivilegesVO menuSubmenuVO,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : createMenu method invoked : {}");
		boolean ismappingAdded = userService.saveUserRoleMenuPrivilegesMapping(menuSubmenuVO, userInfo);
		if (ismappingAdded) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Menu Submenu Mapping has been Saved Successfully!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/assignpermission/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AssignRolePrivilegesVO fetchMenuPrivilegesOnSearchParam(@PathVariable Integer roleId) {
		logger.info("UserController : fetchMenuPrivilegesOnSearchParam method invoked : ");
		AssignRolePrivilegesVO rolePrivilegesVo = new AssignRolePrivilegesVO();
		rolePrivilegesVo.setRoleId(roleId);
		return userService.fetchMenuPrivilegesByParameters(rolePrivilegesVo);
	}

	@GetMapping(value = "/selectedsubmenus/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public AssignRolePrivilegesVO fetchSelectedSubMenusSearchParam(@PathVariable Integer roleId) {
		logger.info("UserController : fetchSelectedSubMenusSearchParam method invoked : ");
		AssignRolePrivilegesVO rolePrivilegesVo = new AssignRolePrivilegesVO();
		rolePrivilegesVo.setRoleId(roleId);
		return userService.fetchSelectedMenuPrivileges(rolePrivilegesVo);
	}

	@PostMapping(value = "/slider", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> createSlider(@RequestBody LoginSliderVO slidervo,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : createSlider method invoked : {}");
		boolean isSliderAdded = userService.saveSlider(slidervo, userInfo);
		if (isSliderAdded) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Slider Details has been saved successfully!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/header", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> createHeader(@RequestBody LoginHeaderVO headervo,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : createHeader method invoked : {}");
		boolean isHeaderAdded = userService.saveHeader(headervo, userInfo);
		if (isHeaderAdded) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Header Details has been saved successfully!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/slider", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LoginSliderVO> fetchSliderList() {
		logger.info("UserController : fetchSliderList method invoked : {}");
		List<LoginSliderVO> sliderList = userService.fetchSliderList();
		return sliderList;
	}

	@GetMapping(value = "/header", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<LoginHeaderVO> fetchHeaderList() {
		logger.info("UserController : fetchHeaderList method invoked : {}");
		List<LoginHeaderVO> headerList = userService.fetchHeaderList();
		return headerList;
	}

	@GetMapping(value = "/hasaccess/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean checkUrlAccessibility(@PathVariable String username, @RequestParam String url) {
		logger.info("UserController : checkUrlAccessibility method invoked : {}");
		Boolean hasURLAccess = userService.hasURLAccess(username, url);
		logger.info("USERNAME: " + username, " URL: " + url, " RESULT: " + hasURLAccess);
		return hasURLAccess;
	}

	@GetMapping(value = "/isusernameexist/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean isUserNameExist(@PathVariable String username) {
		logger.info("UserController : isUserNameExist method invoked : {}");
		Boolean isUserNameExist = userService.isUserNameExist(username);
		return isUserNameExist;
	}

	// current login username info
	@GetMapping(value = "/username", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getCurrentUserId() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		String currentUserID = null;
		if (authentication != null)
			if (authentication.getPrincipal() instanceof UserDetails)
				currentUserID = ((UserDetails) authentication.getPrincipal()).getUsername();
			else if (authentication.getPrincipal() instanceof String)
				currentUserID = (String) authentication.getPrincipal();
		System.out.println(currentUserID);
		return currentUserID;
	}

	@GetMapping(value = "/userdetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public AppUserDetails userDetails(@WithUser UserInfo userInfo) {
		logger.info("UserController : userDetails method invoked : {}");
		AppUserDetails userdetails = userService.fetchUserDetails(userInfo);
		return userdetails;
	}

	@PostMapping(value = "/changelogs", produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean saveChangeLogs(@RequestBody ChangeLogsVO changeLogVo) {
		logger.info("UserController : createChangeLogs method invoked : {}");
		return userService.saveChangeLogs(changeLogVo);
	}

	@GetMapping(value = "/activitylog/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ActivityLogsVO> fetchAllActivityLogs(@PathVariable String username) {
		List<ActivityLogsVO> activityLogsVO = new ArrayList<>();
		activityLogsVO = userService.fetchAllActivityLogs(username);
		return activityLogsVO;
	}

	@PostMapping(value = "/verifyotp", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> verifyOtp(@RequestBody OtpDetailsVerifyVO otpVerify,
			@WithUser UserInfo userInfo) {
		logger.info("UserController : verifyOtp method invoked : {}");

		String verifystatus = userService.verifyOtp(otpVerify, userInfo);
		if (verifystatus.equalsIgnoreCase("success")) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "OTP has been Verified successfully!!"), HttpStatus.OK);
		} else {
			String message = "";
			switch (verifystatus) {
			case "otpnotfound":
				message = "OTP is not found.please resend it again!!";
				break;
			case "invalidtype":
				message = "OTP Type is not found.please resend it again!!";
				break;
			case "invalidmobileotp":
				message = "Invalid mobile/email otp";
				break;
			case "expmobileotp":
				message = "Expired mobile/email otp";
				break;
			case "invalidemailotp":
				message = "Invalid email/mobile otp";
				break;
			case "expemailotp":
				message = "Expired email/mobile otp";
				break;

			default:
				message = "Request Cannot Be Processed. Please Try Again.";
				break;
			}

			return new ResponseEntity<>(new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), message),
					HttpStatus.BAD_REQUEST);
		}
	}

//change needed
	@PostMapping(value = "/usercredentials", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> saveCredentials(@RequestBody UsersVO usersVo, @WithUser UserInfo userInfo) {
		logger.info("UserController : saveCredentials method invoked : {}");
		// usersVo.setOfficeTypeId(3);
		boolean isSaveCredentials = userService.saveCredentials(usersVo, userInfo);
		if (isSaveCredentials) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "User Credentials has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/officeacronym", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OfficeAcronymVO> fetchOfficeAcronym() {
		logger.info("UserController : fetchOfficeAcronym method invoked : {}");
		List<OfficeAcronymVO> officeAcronymList = new ArrayList<>();
		officeAcronymList = userService.fetchAllOfficeAcronym();
		return officeAcronymList;
	}

	// change needed
	@GetMapping(value = "/user/username", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long fetchUserDetails(@RequestParam String username) {
		logger.info("UserController : fetchUserDetails method invoked : {}");
		return userService.fetchUserDetailsFromUsername(username).getId();
	}

	@PostMapping(value = "/logout", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ReturnResponse> logoutUser(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		logger.info("UserController : saveCredentials method invoked : {}");
		customLogoutSuccessHandler.onLogoutSuccess(request,response,authentication);
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "User Successfully Logout!!"),
					HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/userdetailsbytoken", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserTokenEO findUserByToken(@WithUser UserInfo userInfo) {
		logger.info("UserController : findUserByToken method invoked : {}");
		UserTokenEO user = userService.findUserByToken(userInfo.getUsername());
		return user;
	}

//	@RequestMapping(value = "/logout", method = RequestMethod.POST)
//	public String logout(HttpSession session, HttpServletRequest request) {
//		if (session.isNew()) {
//			session.invalidate();
//			return "User Not Logged In!!";
//		} else {
//			session.invalidate();
//			return "User Logged out Successfully";
//		}
//		
//	}
}