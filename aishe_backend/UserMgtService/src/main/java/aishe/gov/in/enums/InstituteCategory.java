package aishe.gov.in.enums;

public enum InstituteCategory {
    ALL("ALL"),
    COLLEGE("College Institution"),
    STANDALONE("Standalone Institution"),
    UNIVERSITY("University");


    private String type;

    InstituteCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static InstituteCategory parse(String val) {
        for (InstituteCategory msg : InstituteCategory.values()) {
            if (msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
