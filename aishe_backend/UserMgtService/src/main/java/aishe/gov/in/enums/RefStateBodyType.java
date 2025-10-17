package aishe.gov.in.enums;

public enum RefStateBodyType {

    Paramedical(1),
    Technical_Polytechnic(2),
    Nursing(3),
    TeacherTraining(4),
    Institutes_under_Ministries(5),
    Hotel_Management_and_Catering(6),
    PGDM_Institutes(7);
    private Integer type;

    RefStateBodyType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
