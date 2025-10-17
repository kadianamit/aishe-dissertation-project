package aishe.gov.in.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.SpecialPermissionDao;

@Service
public class SpecialPermissionServiceImpl implements SpecialPermissionService {

	@Autowired
	SpecialPermissionDao specialPermissionDao;
	@Override
	public boolean assignSpecialPermissionToInstitute(String instituteType, String aisheCode, String username,
			Integer surveyYear, Boolean specialPermission,String remarks,HttpServletRequest request) {
		return specialPermissionDao.assignSpecialPermissionToInstitute(instituteType,aisheCode,username,
				surveyYear,specialPermission,remarks,request);
	}
	@Override
	public boolean activateInactivateStandalone(String aisheCode, Integer actionId, String remarks, Integer surveyYear
			, String username,HttpServletRequest request) {
		return specialPermissionDao.activateInactivateStandalone(aisheCode,actionId,remarks,surveyYear,username,
				request);
	}
	


}