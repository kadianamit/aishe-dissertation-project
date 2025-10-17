package aishe.gov.in.enums;

public enum SurveyYearConstant {
    CURRENT_SURVEY_YEAR(2022);

    private Integer statusType;

    SurveyYearConstant(Integer vernacularType) {
        this.statusType = vernacularType;
    }

    public Integer getStatusType() {
        return statusType;
    }

    public static SurveyYearConstant parse(Integer val) {
        for (SurveyYearConstant msg : SurveyYearConstant.values()) {
            if (msg.getStatusType().equals(val)) {
                return msg;
            }
        }
        return null;
    }

    public static SurveyYearConstant convertIntegerMasterStatusToString(int intStatus) {
        switch (intStatus) {
            case 1:
                return SurveyYearConstant.CURRENT_SURVEY_YEAR;
            default:
                break;
        }
        return null;
    }
}
