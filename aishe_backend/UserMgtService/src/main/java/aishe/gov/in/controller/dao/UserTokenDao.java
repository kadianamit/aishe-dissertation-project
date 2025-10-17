package aishe.gov.in.dao;

import java.util.List;

import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.masterseo.UserToken;
import aishe.gov.in.mastersvo.AisheCodeDetailsVo;
import aishe.gov.in.mastersvo.CollegeCountUniversityWise;
import aishe.gov.in.mastersvo.CreatedInstituteDetailsVo;
import aishe.gov.in.mastersvo.UserDetailsVo;

public interface UserTokenDao {

    public Boolean logOutWebDcf(String userId);
    public List<UserDetailsVo> userDetails(String userId);

    public Boolean validateToken(String userId, String token);

    public Boolean saveOrUpdate(UserToken userToken);

    public UserToken getUserToken(String id);
    public List<UserDetailsVo> userDetailsmOU(String userId);
	public AisheCodeDetailsVo userDetailsByAisheCodeOnCurrentSurveyYear(String instituteType, String instituteId,
			Integer currentSurveyYear);
	public List<CollegeCountUniversityWise> universityWiseCollege(Integer surveyYear);
	public List<CreatedInstituteDetailsVo> createdInstituteDetailsOnBasisAisheCode(String instituteType,String instituteId);
	public List<UserDetailsVo> userDetailsOnBasisAisheCodeAll(String aisheCode);
	public List<InstituteDetailEO> getInstituteDetail(String token, String instituteType);
}
