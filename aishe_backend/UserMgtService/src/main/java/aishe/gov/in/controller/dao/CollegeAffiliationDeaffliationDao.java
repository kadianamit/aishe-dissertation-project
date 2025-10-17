package aishe.gov.in.dao;

import java.util.List;

import aishe.gov.in.masterseo.CollegeAffiliationLogEO;
import aishe.gov.in.masterseo.CollegeEO;
import aishe.gov.in.masterseo.Form_UploadBean;
import aishe.gov.in.masterseo.UniversityRef;
import aishe.gov.in.mastersvo.CollegeDeaffiliationAffiliationVO;
import aishe.gov.in.security.UserInfo;

public interface CollegeAffiliationDeaffliationDao {

    CollegeEO getCollegeMaster(String collegeId, Integer surveyYear);

    List<CollegeEO> getCollegeMasterList(String collegeId, Integer surveyYear);

    UniversityRef getUniversityMaster(String universityId, Integer surveyYear);

    Boolean saveCollegeAffiliationDeaffiliation(CollegeDeaffiliationAffiliationVO deaffiliationAffiliationVO, CollegeEO collegeEO, UniversityRef oldUniversityRef, UniversityRef newUnversityRef, UserInfo userInfo);

    Boolean saveUpdateCollegeAffiliationDeaffiliation(CollegeDeaffiliationAffiliationVO deaffiliationAffiliationVO, CollegeEO collegeEO, UniversityRef newUnversityRef);

    CollegeAffiliationLogEO getCollegeAffiliationLogBySurveyYear(String universityId, Integer surveyYear);

	Form_UploadBean getFormUploadForCollege(String aisheCode, Integer surveyYear);

	Form_UploadBean getFormUploadForStandalone(String aisheCode, Integer surveyYear);
}
