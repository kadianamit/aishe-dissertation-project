package aishe.gov.in.mastersvo;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class SurveyMasterNewVO {
    private Integer surveyYear;

    private String startDate;

    private String endDate;
    
    private String registrationStartDate;
    
    private String registrationEndDate;
    
    private String surveyStartDate;
    
    private String surveyEndDate;
    
    private String specialPermissionStartDate;
    
    private String specialPermissionEndDate;
    
    private Boolean isSurveyFreezed;
    
}