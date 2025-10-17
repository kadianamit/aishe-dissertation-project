package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstituteDetailDTO {
    private String aisheCode;
    private String instituteName;
    private String formType;
    private String surveyYear;
}
