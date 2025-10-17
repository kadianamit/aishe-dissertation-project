package aishe.gov.in.enums;

public enum OrderBy {
    AISHE_CODE("aishe_code"),
    ACTION_TIME("action_time");

    private String type;

    OrderBy(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static OrderBy parse(String val) {
        for (OrderBy msg : OrderBy.values()) {
            if (msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
