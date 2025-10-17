package aishe.gov.in.enums;

public enum SurveyParticipatedStatus {
     PARTICIPATED("1"), PENDING("2");

    private String status;

    SurveyParticipatedStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static SurveyParticipatedStatus parse(String val) {
        for (SurveyParticipatedStatus msg : SurveyParticipatedStatus.values()) {
            if (msg.getStatus().equals(val)) {
                return msg;
            }
        }
        return null;
    }
}
