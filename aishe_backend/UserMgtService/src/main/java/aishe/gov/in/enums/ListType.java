package aishe.gov.in.enums;

public enum ListType {
    EXPECTED_UNIVERSITY_FORM("1"),
    SUBMITED_UNIVERSITY_FORM("2"),
    PENDING_UNIVERSITY_FORM("3"),
    EXPECTED_COLLEGE_FORM("4"),
    SUBMITED_COLLEGE_FORM("5"),
    PENDING_COLLEGE_FORM("6"),
    EXPECTED_STANDALONE_FORM("7"),
    SUBMITED_STANDALONE_FORM("8"),
    PENDING_STANDALONE_FORM("9");

    private final String type;

    ListType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ListType parse(String val) {
        for (ListType msg : ListType.values()) {
            if (msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
