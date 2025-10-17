package com.nic.aishe.master.service;

import com.nic.aishe.master.dto.ReturnResponse;
import com.nic.aishe.master.dto.ServiceResponse;
import com.nic.aishe.master.enums.EnumDetails.InstituitionCategory;
import com.nic.aishe.master.enums.EnumDetails.EligibilityStatus;

public interface KnowYourAisheCodeService {
    ReturnResponse collegeAisheCode(String instituteType, String aisheId, Integer surveyYear);
    ServiceResponse fetchAisheDetails(String stateCode, InstituitionCategory instituitionCategory, String surveyYear, EligibilityStatus status, String subCategory, String districtCode, String universityId);

}
