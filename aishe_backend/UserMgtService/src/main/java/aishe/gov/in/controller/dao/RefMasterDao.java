package aishe.gov.in.dao;

import java.util.List;

import aishe.gov.in.masterseo.FormDetailEO;
import aishe.gov.in.masterseo.FormUpload;
import aishe.gov.in.masterseo.RefCollegeInstitution;
import aishe.gov.in.masterseo.RefDistrict;
import aishe.gov.in.masterseo.RefStandaloneInstitution;
import aishe.gov.in.masterseo.RefStandaloneInstitution1;
import aishe.gov.in.masterseo.RefState;
import aishe.gov.in.masterseo.RefStateBody;
import aishe.gov.in.masterseo.RefUniversity;
import aishe.gov.in.masterseo.RefUniversityCollegeType;
import aishe.gov.in.masterseo.RefUniversityType;
import aishe.gov.in.masterseo.SurveyStatusEO;
import aishe.gov.in.mastersvo.SurveyStatusWithUserStatusDTO;

public interface RefMasterDao {
    public RefDistrict getDistrict(String code);

    public RefState getState(String code);

    public String getCollegeName(Integer id);

    public String getStandaloneName(Integer id);

    public String getUniversityName(String id);

    public FormUpload getFormUpload(String aisheCode, Integer surveyYear);

    public List<RefUniversity> getUniversityMaster(String aisheCode, Integer surveyYear, Boolean dcfStatus, String typeId);

    public List<RefCollegeInstitution> getCollegeMaster(Integer id, Integer surveyYear, Boolean dcfStatus,String type);

    public List<RefStandaloneInstitution> getStandaloneMaster(Integer id, Integer surveyYear, Integer stateBodyId);

    public List<RefUniversity> saveRefUniversityList(List<RefUniversity> refUniversities,Integer newSurveyYear);

    public List<RefCollegeInstitution> saveRefCollegeInstitutionList(List<RefCollegeInstitution> refCollegeInstitutions, Integer newSurveyYear);

    public List<RefStandaloneInstitution> saveRefStandaloneInstitutionList(List<RefStandaloneInstitution> refStandaloneInstitutions,Integer newSurveyYear);

    public List<SurveyStatusEO> getSurveyStatus(Integer surveyYear, String stateCode);

    public List<SurveyStatusEO> saveSurveyStatus(List<SurveyStatusEO> statusEOS,Integer newSurveyYear,String userId);

    List<SurveyStatusWithUserStatusDTO> freezeSurveyYear(List<SurveyStatusWithUserStatusDTO> statusDTO);

    List<RefStateBody> getRefStateBody();
    List<RefUniversityType> getRefUniversityType();
    List<RefUniversityCollegeType> getRefUniversityCollegeType();
    List<RefStateBody> getRefStateBody(Integer id,Integer id2);
    FormDetailEO getUserDetails(Integer formUploadId);

	public Double getLattitude(String id, String upperCase);

	public Double getLongitude(String id, String upperCase);

	public boolean getFinalSubmitDone(String id, String type, Integer currentSurveyYear);


    Boolean saveCommonMaster(String query, Integer maxSurveyYear, Integer surveyYear);
}
