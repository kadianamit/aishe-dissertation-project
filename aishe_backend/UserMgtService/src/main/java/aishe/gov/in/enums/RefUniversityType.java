package aishe.gov.in.enums;

public enum RefUniversityType {
    Central_University ("01"),
    State_Public_University ("02"),
    State_Private_University ("03"),
    Deemed_University_Government ("04"),
    Deemed_University_Government_Aided ("05"),
    Deemed_University_Private ("06"),
    Institute_of_National_Importance ("07"),
    Institute_under_State_Legislature_Act ("08"),
    Central_Open_University ("09"),
    State_Open_University ("10"),
    State_Private_Open_University ("11"),
    Other("19");
    private String type;

    RefUniversityType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
