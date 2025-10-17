package aishe.gov.in.utility;

public enum FormType {
    COLLEGE("form2"),
    STANDALONE("form3"),
    UNIVERSITY("form1");
    private String type;

    FormType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static FormType parse(String val) {
        for (FormType msg : FormType.values()) {
            if (msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
