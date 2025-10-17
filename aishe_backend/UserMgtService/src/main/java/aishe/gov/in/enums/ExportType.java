package aishe.gov.in.enums;

public enum ExportType {
    PDF("PDF"),
    EXCEL("EXCEL"),
	CSV("CSV"),
    JSON("JSON");
   // HTML("HTML");


    public String type;

    ExportType(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ExportType parse(String val) {
        for (ExportType msg : ExportType.values()) {
            if (msg.getType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
