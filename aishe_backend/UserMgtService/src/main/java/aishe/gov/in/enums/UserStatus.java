package aishe.gov.in.enums;

public enum UserStatus {
    ALL(null),

    New_REGISTRATION(1),
    INACTIVE(3),
    USER_APPROVED(2),
    USER_DISAPPROVED(4);
    private Integer actionType;

    UserStatus(Integer actionType) {
        this.actionType = actionType;
    }

    public Integer getActionType() {
        return actionType;
    }

    public static UserStatus parse(String val) {
        for(UserStatus msg : UserStatus.values()) {
            if(msg.getActionType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
