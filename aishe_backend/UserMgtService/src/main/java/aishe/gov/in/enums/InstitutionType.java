package aishe.gov.in.enums;

public enum InstitutionType {
    ALL("ALL"),
    COLLEGE("C"),
    STANDALONE("S"),
    UNIVERSITY("U");


    private String type;

    InstitutionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static InstitutionType parse(String val) {
        for (InstitutionType msg : InstitutionType.values()) {
            if (msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
