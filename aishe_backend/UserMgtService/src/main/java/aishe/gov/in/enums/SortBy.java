package aishe.gov.in.enums;

public enum SortBy {
    BY_USERID("USERID"),
    BY_INSTITUTION("INSTITUTION"),
    By_FIRSTNAME("FIRSTNAME"),
    By_UNIVERSITY("UNIVERSITY");


    private String sortType;

    SortBy(String sortType) {
        this.sortType = sortType;
    }

    public String getSortType() {
        return sortType;
    }

    public static SortBy parse(String val) {
        for (SortBy msg : SortBy.values()) {
            if (msg.getSortType().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
