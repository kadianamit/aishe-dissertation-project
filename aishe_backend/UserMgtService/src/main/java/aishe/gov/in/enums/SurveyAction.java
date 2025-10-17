package aishe.gov.in.enums;

public enum SurveyAction {
    CREATE(1),
    EDIT(2),
    CLOSE(3),
    RESTART(4),	
	
    REGISTRATION_CREATE(5),
	REGISTRATION_START_EDIT(6),
	REGISTRATION_END_EDIT(7),
	
	SURVEY_CREATE(8),
	SURVEY_START_EDIT(9),
	SURVEY_END_EDIT(10),
	
	SPECIAL_PERMISSION_CREATE(11),
	SPECIAL_PERMISSION_START_EDIT(12),
	SPECIAL_PERMISSION_END_EDIT(13),
	FREEZE_SURVEY_YEAR(14);
	
    private Integer actionType;

    SurveyAction(Integer actionType) {
        this.actionType = actionType;
    }

    public Integer getActionType() {
        return actionType;
    }

    public static SurveyAction parse(String val) {
        for (SurveyAction msg : SurveyAction.values()) {
            if (msg.getActionType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}