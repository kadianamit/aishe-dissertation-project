package aishe.gov.in.dao;

import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.masterseo.SurveyMasterLogEO;
import aishe.gov.in.masterseo.UserActionLogEO;

public interface ActionLogDao {
    public UserActionLogEO saveUserActionLog(UserActionLogEO logEO);
    public SurveyMasterLogEO saveSurveyMasterLog(SurveyMasterLogEO surveyMasterLogEO);
    Integer maxId();
	public void updateInfoInstituteDetail(InstituteDetailEO instituteDetail);
}
