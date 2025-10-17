package aishe.gov.in.dao;

import aishe.gov.in.masterseo.CaptchaValidationEO;
import aishe.gov.in.masterseo.DataSharingApiUserInformationEO;

import aishe.gov.in.masterseo.OtpDetailsMouEO;
import aishe.gov.in.masterseo.RefDataTypeRequiredEO;
import aishe.gov.in.masterseo.RefOrganizationCategoryEO;
import aishe.gov.in.masterseo.RefStatusEO;
import aishe.gov.in.masterseo.RefUserCategoryEO;

import aishe.gov.in.masterseo.DataSharingUserMasterEO;

import aishe.gov.in.masterseo.UserRequestApprovalDetailsEO;
import aishe.gov.in.mastersvo.ChangePasswordDTO;
import aishe.gov.in.mastersvo.DataSharingApiUserInformationWithStatus;
import aishe.gov.in.mastersvo.DataSharingApiUserStatusUpdate;
import aishe.gov.in.mastersvo.ForgotPasswordDto;
import aishe.gov.in.mastersvo.ResetPasswordDTO;
import aishe.gov.in.security.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DataSharingDao {

    String apiUserInformation(DataSharingApiUserInformationEO eo);

    String lastRequestId();

    List<DataSharingApiUserInformationWithStatus> apiUserInformationList(Integer status,Integer orgCategory,Integer ministryId,Integer reqiredDataTypeId,Integer id);
    Boolean apiUserInformationUpdateStatus(DataSharingApiUserStatusUpdate eo, UserInfo userInfo, HttpServletRequest request);
    UserRequestApprovalDetailsEO getLastUserActionStatus(String requestId);

    List<RefUserCategoryEO> refUserCategory(Integer id);

    List<RefDataTypeRequiredEO> refDataTypeRequired(Integer id);
    List<RefOrganizationCategoryEO> refOrgCategory(Integer id);
    List<RefStatusEO> refStatus(Integer id);
	String dataSharingUserMaster(DataSharingUserMasterEO eo, HttpServletRequest request);
	List<DataSharingUserMasterEO> userMasterRequest(String username);

    String findByEmailIdIgnoreCase(String email);

    boolean saveForgotPassword(OtpDetailsMouEO forgot);

    String verifyOtpForogtPassword(ForgotPasswordDto forgotPassword);

    boolean resetPassword(ResetPasswordDTO resetPassword, HttpServletRequest request, UserInfo userInfo);

    boolean changePassword(ChangePasswordDTO changePassword, HttpServletRequest request, UserInfo userInfo);

    boolean fetchTokenNotExpired(String authorizationHeader);

}
