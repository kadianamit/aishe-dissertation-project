package aishe.gov.in.enums;

public enum FormUploadStatus {
    FORM_UPLOAD(true),
    FORM_NOT_UPLOAD(false),
    ALL(null);
    private Boolean type;

    FormUploadStatus(Boolean type) {
        this.type = type;
    }

    public Boolean getType() {
        return type;
    }

    public static FormUploadStatus parse(String val) {
        for (FormUploadStatus msg : FormUploadStatus.values()) {
            if (msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
