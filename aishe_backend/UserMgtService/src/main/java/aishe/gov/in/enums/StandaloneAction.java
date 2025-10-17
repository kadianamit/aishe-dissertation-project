package aishe.gov.in.enums;

public enum StandaloneAction {

	ACTIVATE_STANDALONE(1),
	INACTIVATE_STANDALONE(2);
  

    private Integer statusType;

    StandaloneAction(Integer vernacularType) {
        this.statusType = vernacularType;
    }

    public Integer getStatusType() {
        return statusType;
    }

    public static StandaloneAction parse(Integer val) {
        for (StandaloneAction msg : StandaloneAction.values()) {
            if (msg.getStatusType().equals(val)) {
                return msg;
            }
        }
        return null;
    }

    public static int convertIntegerMasterStatusToString(int intStatus) {
        switch (intStatus) {
            case 1:
                return StandaloneAction.ACTIVATE_STANDALONE.statusType;
            case 2:
                return StandaloneAction.INACTIVATE_STANDALONE.statusType;
            default:
                break;
        }
        return 0;
    }
}
