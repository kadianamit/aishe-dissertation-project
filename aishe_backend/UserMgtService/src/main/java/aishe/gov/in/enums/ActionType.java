package aishe.gov.in.enums;

public enum ActionType {
    EDIT_REGISTRATION(1),
    CHANGE_PASSWORD(2),
    USER_DELETE(10),
    USER_APPROVED(8),
    USER_DISAPPROVED(9);


    private Integer actionType;

    ActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public Integer getActionType() {
        return actionType;
    }

    public static ActionType parse(String val) {
        for(ActionType msg : ActionType.values()) {
            if(msg.getActionType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
