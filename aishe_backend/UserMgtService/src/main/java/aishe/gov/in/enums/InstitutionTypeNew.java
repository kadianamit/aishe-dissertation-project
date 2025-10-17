package aishe.gov.in.enums;

public enum InstitutionTypeNew {
    ALL("ALL"),
    COLLEGE("C"),
    STANDALONE("S"),
    UNIVERSITY("U");


    private String type;

    InstitutionTypeNew(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static InstitutionTypeNew parse(String val) {
        for (InstitutionTypeNew msg : InstitutionTypeNew.values()) {
            if (msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
