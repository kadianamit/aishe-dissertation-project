package aishe.gov.in.dao;

import aishe.gov.in.enums.InstitutionTypeNew;
import aishe.gov.in.enums.InstitutionUserType;
import aishe.gov.in.enums.SurveyParticipatedStatus;
import aishe.gov.in.masterseo.UserContactDetail;

import java.util.List;

public interface UserContactDetailDao {
    public List<UserContactDetail> userContactDetail(String stateCode, InstitutionTypeNew institutionType,
                                                     String subCategory, Integer surveyYear, String districtCode, InstitutionUserType userType, SurveyParticipatedStatus participatedStatus,String universityId);

    Integer maxSurveyYear();

}
