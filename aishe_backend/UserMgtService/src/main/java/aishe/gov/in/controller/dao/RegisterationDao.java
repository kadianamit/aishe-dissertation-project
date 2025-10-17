package aishe.gov.in.dao;

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
import aishe.gov.in.masterseo.UserMasterDetailStateEO;
import aishe.gov.in.masterseo.UserMasterEO;
import aishe.gov.in.masterseo.UserMasterLogDetailEO;
import aishe.gov.in.masterseo.UserMasterNewEO;
import aishe.gov.in.masterseo.UserMasterRequestDetailEO;
import aishe.gov.in.masterseo.UserRegistrationDetailEO;
import aishe.gov.in.masterseo.UserRegistrationUpdatedDetailEO;
import aishe.gov.in.mastersvo.RegisteredUserDTO;
import aishe.gov.in.security.UserInfo;

public interface RegisterationDao {
    UserMasterNewEO saveUpdateRegistration(UserMasterNewEO userMasterEO,UserInfo userInfo);

    UserMasterEO getUserRegistration(String userId);

    UserMasterDetailEO getUserByUserId(String userId);

    UserMasterDetailEO save(UserMasterDetailEO UserMasterDetailEO);

    Boolean deleteUser(UserMasterDetailEO masterEO);

    List<UserMasterDetailStateEO> getUserByCondition(Integer roleId, String aisheCode, Integer userStatus, String stateCode, String UniversityId, Integer deoRoleId, Boolean isApproved);

    List<RegisteredUserDTO> getRegisteredUser(Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved, SortBy sortBy, String instituteType, /*int page, int pageSize,*/String fromDate,String toDate,String searchText,String categoryType);

    BigInteger getRegisteredUserCont(Integer roleId, Integer surveyYear, Integer userStatus, Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String stateCode, String universityId, String aisheCode, Boolean isApproved,  String instituteType,String fromDate,String toDate,String searchText,String categoryType);

    UserMasterDetailEO getUser(ApproveUserDTO approveUserDTO);

    UserMasterDetailEO update(UserMasterDetailEO activeUser);

	UserRegistrationDetailEO saveUserRegistrationData(UserRegistrationDetailEO userMasterEO);

	UserMasterEO getUserRegistration1(String userId);

	UserRegistrationUpdatedDetailEO updateUserRegistration(UserRegistrationUpdatedDetailEO userMasterEO, boolean isUpdated, UserInfo userInfo );

	InstituteDetailEO getInstituteDetail(String aisheCode);

	UserMasterDetailNewEO approveDisapprvoeUserApproval(ApproveDisapproveUserDTO approveUserDTO,
			HttpServletRequest request);

	UserMasterRequestDetailEO saveUserRegistrationNew(UserMasterRequestDetailEO userMasterEO,HttpServletRequest request,UserInfo userInfo);

	UserMasterDetailEO getUserApproveUserDTO(ApproveDisapproveUserDTO approveUserDTO);

	List<RegisteredUserDTO> getRegisteredUserRequest(Integer roleId, Integer surveyYear, Integer userStatus,
			Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String nullValueHandler,
			String nullValueHandler2, String nullValueHandler3, Boolean isApproved, SortBy sortBy, String instituteType,
			String nullValueHandler4, String nullValueHandler5, String searchText,String categoryType);

	List<RegisteredUserDTO> getRegisteredUserDisapprovedInactive(Integer roleId, Integer surveyYear, Integer userStatus,
			Integer deoRoleId, Boolean dcfStatus, Boolean formUploadStatus, String nullValueHandler,
			String nullValueHandler2, String nullValueHandler3, Boolean isApproved, SortBy sortBy, String instituteType,
			String nullValueHandler4, String nullValueHandler5, String searchText,String categoryType);

	Boolean updateMoeViewUser(String userId, Boolean activeMoeUser);

	UserMasterLogDetailEO getUserMasterLog(String userId);


	List<UserMasterRequestDetailEO> getExpiredUserMasterRequest(String aisheCode);

	UserMasterLogDetailEO getUserLogDetail(String userId);

	UserMasterRequestDetailEO getUserRequestDetail(String userId);

	UserMasterEO getUserRegistrationAisheCode(String aisheCode);

	List<ActiveInstituteInactiveUser> institutionInactiveUser(InstitutionType institutionType,Integer surveyYear);

	Integer getMaxSurveyYearFromMaster();

	UserMasterDetailNewEO fetchUserMasterDetailEO(String userId);
}
