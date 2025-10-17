package aishe.gov.in.service;

import aishe.gov.in.dao.DataSharingDao;
import aishe.gov.in.exception.InvalidInputException;
import aishe.gov.in.masterseo.DataSharingApiUserInformationEO;
import aishe.gov.in.masterseo.DataSharingUserMasterEO;
import aishe.gov.in.masterseo.OtpDetailsMouEO;
import aishe.gov.in.masterseo.RefDataTypeRequiredEO;
import aishe.gov.in.masterseo.RefOrganizationCategoryEO;
import aishe.gov.in.masterseo.RefStatusEO;
import aishe.gov.in.masterseo.RefUserCategoryEO;
import aishe.gov.in.masterseo.UserRequestApprovalDetailsEO;
import aishe.gov.in.mastersvo.ChangePasswordDTO;
import aishe.gov.in.mastersvo.DataSharingApiUserInformationWithStatus;
import aishe.gov.in.mastersvo.DataSharingApiUserStatusUpdate;
import aishe.gov.in.mastersvo.ForgotPasswordDto;
import aishe.gov.in.mastersvo.ResetPasswordDTO;
import aishe.gov.in.security.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class DataSharingServiceImpl implements DataSharingService {
    @Autowired
    private DataSharingDao dataSharingDao;

    @Override
    public String apiUserInformation(DataSharingApiUserInformationEO eo) {
        return dataSharingDao.apiUserInformation(eo);
    }

    @Override
    public String lastRequestId() {
        return dataSharingDao.lastRequestId();
    }

    @Override
    public List<DataSharingApiUserInformationWithStatus> apiUserInformationList(Integer status, Integer orgCategory, Integer ministryId, Integer reqiredDataTypeId, Integer id) {
        return dataSharingDao.apiUserInformationList(status, orgCategory, ministryId, reqiredDataTypeId, id);
    }

    @Override
    public Boolean apiUserInformationUpdateStatus(DataSharingApiUserStatusUpdate eo, UserInfo userInfo, HttpServletRequest request) {
        UserRequestApprovalDetailsEO detailsEO = dataSharingDao.getLastUserActionStatus(eo.getRequestId());
        if (null != detailsEO) {
            throw new InvalidInputException(eo.getRequestId() + " is already processed !! ");
        }
        return dataSharingDao.apiUserInformationUpdateStatus(eo, userInfo, request);
    }


    @Override
    public List<RefUserCategoryEO> refUserCategory(Integer id) {
        return dataSharingDao.refUserCategory(id);
    }

    @Override
    public List<RefDataTypeRequiredEO> refDataTypeRequired(Integer id) {
        return dataSharingDao.refDataTypeRequired(id);
    }

    @Override
    public List<RefOrganizationCategoryEO> refOrgCategory(Integer id) {
        return dataSharingDao.refOrgCategory(id);
    }

    @Override
    public List<RefStatusEO> refStatus(Integer id) {
        return dataSharingDao.refStatus(id);
    }

	@Override
	public String dataSharingUserMaster(DataSharingUserMasterEO eo, HttpServletRequest request) {
		  return dataSharingDao.dataSharingUserMaster(eo,request);
	}

	@Override
	public List<DataSharingUserMasterEO> userMasterRequest(String username) {
		  return dataSharingDao.userMasterRequest(username);
	}

    @Override
    public String findByEmailIdIgnoreCase(String email) {
        return dataSharingDao.findByEmailIdIgnoreCase(email);
    }


    @Override
    public boolean saveForgotPassword(OtpDetailsMouEO forgot) {
        return dataSharingDao.saveForgotPassword(forgot);
    }


    @Override
    public String verifyOtpForogtPassword(ForgotPasswordDto forgotPassword) {
        return dataSharingDao.verifyOtpForogtPassword(forgotPassword);
    }


    @Override
    public boolean resetPassword(ResetPasswordDTO resetPassword,HttpServletRequest request,UserInfo userInfo) {
        return dataSharingDao.resetPassword(resetPassword,request,userInfo);
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePassword,HttpServletRequest request,UserInfo userInfo) {
        return dataSharingDao.changePassword(changePassword,request,userInfo);
    }
}
