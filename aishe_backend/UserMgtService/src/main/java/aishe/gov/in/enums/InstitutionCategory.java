package aishe.gov.in.enums;

public enum InstitutionCategory {
    IIT("indian institute of technology"),
    NIT("national institute of technology"),
    IIIT("indian institute of information technology"),
    SPA("school of planning & architecture"),
    IIM("indian institute of management"),
    IISER("indian institute of science education & research"),
    IIEST("indian institute of engineering science and technology"),
    NIPER("national institute of pharmaceutical"),
    NID("national institute of design"),
    ISI("indian statistical institute"),
    NIFT("national institute of fashion technology"),
    AIIMS("all india institute of medical science"),
    UNIVERSITY("university");
    private String type;

    InstitutionCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
