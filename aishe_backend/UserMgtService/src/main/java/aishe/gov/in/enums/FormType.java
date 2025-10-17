package aishe.gov.in.enums;

public enum FormType {
    FORM_EXPECTED(1),
    FORM_SUBMITTED(2),
    PENDING(3);

    private Integer type;

    FormType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static FormType parse(String val) {
        for(FormType msg : FormType.values()) {
            if(msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
