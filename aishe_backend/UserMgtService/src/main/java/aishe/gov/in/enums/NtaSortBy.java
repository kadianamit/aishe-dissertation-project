package aishe.gov.in.enums;

public enum NtaSortBy {
    DEFAULT("DEFAULT"),
    BY_STATE("STATE"),
    BY_DISTRICT("DISTRICT"),
    BY_AISHE_CODE("AISHE_CODE");



    private String sortType;

    NtaSortBy(String sortType) {
        this.sortType = sortType;
    }

    public String getSortType() {
        return sortType;
    }

    public static NtaSortBy parse(String val) {
        for (NtaSortBy msg : NtaSortBy.values()) {
            if (msg.getSortType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
