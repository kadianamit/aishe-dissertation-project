package aishe.gov.in.enums;

public enum RefUniversityCollegeType {
    Affiliated_College("1"),
    Constituent_University_College("2"),
    Recognized_Center("3"),
    PG_Center_Off_Campus_Center("4"),
    Autonomous_College("5"),
    Other("6");
    private String type;

    RefUniversityCollegeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
