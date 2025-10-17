package aishe.gov.in.enums;

public enum ActionConstant {
    SPECIAL_PERMISSION_GRANTED(39),
	SPECIAL_PERMISSION_REVOKED(40),
	SPECIAL_PERMISSION_GRANT_REVOKED(0),
	WEBDCF_UNLOCK(37),
    FINAL_SUBMITTED(1),
    FINAL_SUBMITTED_PENDING(-1);
	
    private Integer statusType;

    ActionConstant(Integer vernacularType) {
        this.statusType = vernacularType;
    }

    public Integer getStatusType() {
        return statusType;
    }

    public static ActionConstant parse(Integer val) {
        for (ActionConstant msg : ActionConstant.values()) {
            if (msg.getStatusType().equals(val)) {
                return msg;
            }
        }
        return null;
    }

    public static ActionConstant convertIntegerMasterStatusToString(int intStatus) {
        switch (intStatus) {
            case 1:
                return ActionConstant.SPECIAL_PERMISSION_GRANTED;
            case 2:
                return ActionConstant.SPECIAL_PERMISSION_REVOKED;
            case 3:
                return ActionConstant.SPECIAL_PERMISSION_GRANT_REVOKED;
            default:
                break;
        }
        return null;
    }


}
