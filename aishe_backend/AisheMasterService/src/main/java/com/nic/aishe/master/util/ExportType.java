package com.nic.aishe.master.util;

public enum ExportType {
    C("PDF"),
    S("EXCEL"),
    U("HTML");

    private final String type;

    ExportType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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
