package aishe.gov.in.service;

import javax.servlet.http.HttpServletRequest;

public interface SpecialPermissionService {

	boolean assignSpecialPermissionToInstitute(String instituteType, String aisheCode, String username,
			Integer surveyYear, Boolean specialPermission,String remarks,HttpServletRequest request);

	boolean activateInactivateStandalone(String aisheCode, Integer actionId, String remarks, Integer surveyYear, String username, HttpServletRequest request);

}
