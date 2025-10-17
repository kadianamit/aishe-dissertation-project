package aishe.gov.in.service;

import aishe.gov.in.masterseo.CollegeEO;
import aishe.gov.in.masterseo.Form_UploadBean;
import aishe.gov.in.masterseo.UniversityRef;
import aishe.gov.in.mastersvo.CollegeDeaffiliationAffiliationVO;
import aishe.gov.in.security.UserInfo;

public interface CollegeAffiliationDeaffiliationService {

    CollegeEO getCollegeMaster(String collegeId, Integer surveyYear);

    UniversityRef getUniversityMaster(String universityId, Integer surveyYear);

    Boolean saveUpdateCollegeAffiliationDeaffiliation(CollegeDeaffiliationAffiliationVO deaffiliationAffiliationVO, CollegeEO collegeEO, UniversityRef oldUniversityRef, UniversityRef newUnversityRef, UserInfo userInfo);

	Form_UploadBean getFormUploadForCollege(String aisheCodeToId, Integer surveyYear);

	Form_UploadBean getFormUploadForStandalone(String aisheCodeToId, Integer surveyYear);
}
