package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link aishe.gov.in.masterseo.ActivityMasterEO} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityMasterVO implements Serializable {

    @NotNull
    private Integer id;
    @NotNull
    @NotEmpty
    private String startDate;
    private String endDate;
    @NotNull
    private Integer surveyYear;

    private String remarks;
}
