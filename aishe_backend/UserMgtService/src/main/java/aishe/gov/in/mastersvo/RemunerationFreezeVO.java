package aishe.gov.in.mastersvo;

import aishe.gov.in.utility.FormType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RemunerationFreezeVO {
    @Min(1)
    @Max(9999)
    private Integer surveyYear;
    private FormType type;
    private Boolean isFreezed;

}
