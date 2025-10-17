package aishe.gov.in.enums;

public enum LogType {
    APPROVAL("Approval Statement"),
    TRANSACTION("Transaction Statement");

    private String type;

    LogType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static LogType parse(String val) {
        for (LogType msg : LogType.values()) {
            if (msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
