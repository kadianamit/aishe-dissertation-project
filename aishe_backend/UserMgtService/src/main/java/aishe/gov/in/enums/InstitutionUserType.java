package aishe.gov.in.enums;

public enum InstitutionUserType {
    ALL("ALL"), NODAL_OFFICER("1"), INSTITUTION_HEAD("2");

    private String type;

    InstitutionUserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static InstitutionUserType parse(String val) {
        for (InstitutionUserType msg : InstitutionUserType.values()) {
            if (msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
