package aishe.gov.in.service;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.enums.SortBy;
import aishe.gov.in.masterseo.ActiveInstituteInactiveUser;
import aishe.gov.in.masterseo.ApproveDisapproveUserDTO;
import aishe.gov.in.masterseo.ApproveUserDTO;
import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.masterseo.UserMasterDetailEO;
import aishe.gov.in.masterseo.UserMasterDetailNewEO;
import aishe.gov.in.masterseo.UserMasterEO;
import aishe.gov.in.masterseo.UserMasterLogDetailEO;
import aishe.gov.in.masterseo.UserMasterNewEO;
import aishe.gov.in.masterseo.UserMasterRequestDetailEO;
import aishe.gov.in.masterseo.UserRegistrationDetailEO;
import aishe.gov.in.masterseo.UserRegistrationUpdatedDetailEO;
import aishe.gov.in.mastersvo.RegisteredUserDTO;
import aishe.gov.in.mastersvo.UserApprovalDTO;
import aishe.gov.in.security.UserInfo;

public interface UserRegistrationService {
    public UserMasterNewEO saveUpdateRegistration(UserMasterNewEO userMasterEO, HttpServletRequest request,UserInfo userInfo );

    public UserMasterEO getUserRegistration(String userId, Integer currentSurveyYear);

    public UserMasterDetailEO saveAndUpdateUserApproval(UserApprovalDTO approvalDTO, HttpServletRequest request);

    public Boolean deleteUser(String userId, HttpServletRequest request);

    public List<RegisteredUserDTO> getRegisteredUser(Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved, SortBy sortBy, String instituteType,/* int page, int pageSize,*/ String fromDate,String toDate,String searchText,String categoryType);

    public BigInteger getRegisteredUserCont(Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved, String instituteType, String fromDate,String toDate,String searchText,String categoryType);

    
    public UserMasterDetailEO saveUserApproval(ApproveUserDTO approveUserDTO,HttpServletRequest request) throws Exception;

	public UserRegistrationDetailEO saveUserRegistrationData(UserRegistrationDetailEO userMasterEO);

	public UserRegistrationUpdatedDetailEO updateUserRegistration(UserRegistrationUpdatedDetailEO userMasterEO, boolean isUpdated, UserInfo userInfo);

	public InstituteDetailEO getInstituteDetail(String aisheCode);

	public void updateInfoInstituteDetail(InstituteDetailEO instituteDetail);

	public UserMasterDetailNewEO approveDisapprvoeUserApproval(ApproveDisapproveUserDTO approveUserDTO,
			HttpServletRequest request);

	public UserMasterRequestDetailEO saveUserRegistrationNew(UserMasterRequestDetailEO userMasterEO,HttpServletRequest request,UserInfo userInfo);

	public List<RegisteredUserDTO> getRegisteredUserRequest(Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, 
			Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved, SortBy sortBy, String instituteType,
			/* int page, int pageSize,*/ String fromDate,String toDate,String searchText,String categoryType);

	public List<RegisteredUserDTO> getRegisteredUserDisapprovedInactive(Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, 
			Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved, SortBy sortBy, String instituteType,
			/* int page, int pageSize,*/ String fromDate,String toDate,String searchText,String categoryType);

	public Boolean updateMoeViewUser(String userId, Boolean activeMoeUser);

	List<UserMasterRequestDetailEO> getExpiredUserMasterRequest(String aisheCode);

	public UserMasterLogDetailEO getUserLogDetail(String userId);

	public UserMasterRequestDetailEO getUserRequestDetail(String userId);

	public UserMasterEO getUserRegistrationAisheCode(String aisheCode);


	List<ActiveInstituteInactiveUser> activeInstituteInactiveUser(InstitutionType institutionType,Integer surveyYear);
}
