package aishe.gov.in.service;

import aishe.gov.in.dao.UserContactDetailDao;
import aishe.gov.in.enums.InstitutionTypeNew;
import aishe.gov.in.enums.InstitutionUserType;
import aishe.gov.in.enums.SurveyParticipatedStatus;
import aishe.gov.in.masterseo.UserContactDetail;
import aishe.gov.in.utility.CommanObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserContactDetailServiceImpl implements UserContactDetailService {
    @Autowired
    private UserContactDetailDao contactDetailDao;

    @Override
    public List<UserContactDetail> userContactDetail(String stateCode, InstitutionTypeNew institutionType, String subCategory, Integer surveyYear, String districtCode, InstitutionUserType userType, SurveyParticipatedStatus participatedStatus,String universityId) {
        if (!CommanObjectOperation.integerValidate(surveyYear))
            surveyYear = contactDetailDao.maxSurveyYear();
        return contactDetailDao.userContactDetail(stateCode, institutionType, subCategory, surveyYear, districtCode,
                userType, participatedStatus,universityId);
    }
}
