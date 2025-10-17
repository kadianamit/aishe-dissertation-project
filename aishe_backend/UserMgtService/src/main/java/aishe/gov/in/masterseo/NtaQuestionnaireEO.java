package aishe.gov.in.masterseo;

import aishe.gov.in.jsonbutility.JsonUserType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.Map;

@Entity
@TypeDef(name = "JsonUserType", typeClass = JsonUserType.class)
@Getter
@Setter
@Table(name = "ntq")
public class NtaQuestionnaireEO {
    @Column(name = "state_name")
    private String state_name;
    @Column(name = "district")
    private String district;
    @Column(name = "management")
    private String management;
    @Column(name = "onwership")
    private String ownership;
    @Column(name = "institution_type")
    private String instituteType;
    @Column(name = "institute_name")
    private String instituteName;
    @Id
    @Column(name = "aishe_code")
    private String aisheCode;
    @Transient
    @Column(name = "q1")
    private Boolean q1;
    @Column(name = "q2")
    private Boolean q2;
    @Column(name = "q3")
    private Boolean q3;
    @Column(name = "q4")
    private Boolean q4;

    @Column(name = "q5_1")
    private String q5_1;


    @Type(type = "JsonUserType")
    @Column(name = "q5_2_3", columnDefinition = "jsonb")
    private List<Map<String, String>> common_q5_1;


    @Column(name = "q5_4")
    private Boolean q5_4;

    @Column(name = "q5_5")
    private String q5_5;

    @Column(name = "q5_6")
    private Boolean q5_6;

    @Column(name = "q5_7")
    private Boolean q5_7;

    @Column(name = "q5_8")
    private Boolean q5_8;

    @Column(name = "q5_9")
    private String q5_9;

    @Column(name = "q5_10")
    private Boolean q5_10;

    @Column(name = "q5_11")
    private Boolean q5_11;

    @Column(name = "q5_12")
    private String q5_12;

    @Column(name = "q5_13")
    private String q5_13;

    @Column(name = "q5_14")
    private Boolean q5_14;

    @Column(name = "q5_15")
    private Boolean q5_15;

    @Column(name = "q5_16")
    private Boolean q5_16;

    @Column(name = "q5_17")
    private String q5_17;

    @Column(name = "q5_18")
    private Boolean q5_18;

    @Column(name = "q6_1")
    private Boolean q6_1;

    @Column(name = "q6_2")
    private Boolean q6_2;

    @Column(name = "q6_3")
    private Boolean q6_3;

    @Column(name = "q6_4")
    private String q6_4;

    @Column(name = "q6_5")
    private Boolean q6_5;

    @Column(name = "q6_6")
    private Boolean q6_6;

    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;

    @Transient
    private String q5_3;
    @Transient
    private String q5_2;

    @Transient
    private String q100;
    @Transient
    private String q200;

    public String getQ5_3() {
        String finalString = null;
        if (common_q5_1 != null && !common_q5_1.isEmpty()) {
            int count = 0;
            for (Map<String, String> map : common_q5_1) {
                if (count == 0) {
                    if (map.containsKey("q5_3"))
                        finalString = map.get("q5_3");
                } else {
                    if (map.containsKey("q5_3"))
                        finalString = finalString + "," + map.get("q5_3");
                }
                count++;
            }
        }
        return finalString;
    }

    public String getQ5_2() {
        String finalString = null;
        if (common_q5_1 != null && !common_q5_1.isEmpty()) {
            int count = 0;
            for (Map<String, String> map : common_q5_1) {
                if (count == 0) {
                    if (map.containsKey("q5_2"))
                        finalString = map.get("q5_2");
                } else {
                    if (map.containsKey("q5_2"))
                        finalString = finalString + "," + map.get("q5_2");
                }
                count++;
            }
        }
        return finalString;
    }

    public String getState_name() {
        return state_name;
    }

    public String getQ200() {
        Double finalString = null;
        if (common_q5_1 != null && !common_q5_1.isEmpty()) {
            int count = 0;
            for (Map<String, String> map : common_q5_1) {
                if (count == 0) {
                    if (map.containsKey("q5_3"))
                        finalString = Double.valueOf(map.get("q5_3"));
                } else {
                    if (map.containsKey("q5_3"))
                        finalString = Double.valueOf(finalString )+ Double.valueOf(map.get("q5_3"));
                }
                count++;
            }
        }
        return finalString == null ? "" : String.valueOf(finalString);
    }

    public String getQ100() {
        Integer finalString = null;
        if (common_q5_1 != null && !common_q5_1.isEmpty()) {
            int count = 0;
            for (Map<String, String> map : common_q5_1) {
                if (count == 0) {
                    if (map.containsKey("q5_2"))
                        finalString = Integer.valueOf(map.get("q5_2"));
                } else {
                    if (map.containsKey("q5_2"))
                        finalString = Integer.valueOf(finalString) + Integer.valueOf(map.get("q5_2"));
                }
                count++;
            }
        }
        return finalString == null ? "" : String.valueOf(finalString);
    }
}
