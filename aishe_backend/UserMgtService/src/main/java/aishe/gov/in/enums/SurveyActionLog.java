package aishe.gov.in.enums;

public enum SurveyActionLog {
    CREATE_SURVEY("create_edit_restart"),  
    REGISTRATION("registration_create"),	
	SPECIAL_PERMISSION("special_permission"),    
    CLOSE("close");

    private String actionType;

    SurveyActionLog(String actionType) {
        this.actionType = actionType;
    }

    public String getActionType() {
        return actionType;
    }

    public static SurveyActionLog parse(String val) {
        for (SurveyActionLog msg : SurveyActionLog.values()) {
            if (msg.getActionType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
