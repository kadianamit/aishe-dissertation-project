package aishe.gov.in.enums;

public enum SurveyYearDropDown {

	SURVEY_YEAR_2021(2021),
	SURVEY_YEAR_2022(2022),
	SURVEY_YEAR_2023(2023),
	SURVEY_YEAR_2024(2024);
  

    private Integer statusType;

    SurveyYearDropDown(Integer vernacularType) {
        this.statusType = vernacularType;
    }

    public Integer getStatusType() {
        return statusType;
    }

    public static SurveyYearDropDown parse(Integer val) {
        for (SurveyYearDropDown msg : SurveyYearDropDown.values()) {
            if (msg.getStatusType().equals(val)) {
                return msg;
            }
        }
        return null;
    }

    public static int convertIntegerMasterStatusToString(int intStatus) {
        switch (intStatus) {
            case 1:
                return SurveyYearDropDown.SURVEY_YEAR_2021.statusType;
            default:
                break;
        }
        return 0;
    }
}
