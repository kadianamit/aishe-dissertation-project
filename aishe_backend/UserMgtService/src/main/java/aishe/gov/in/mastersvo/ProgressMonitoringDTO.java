package aishe.gov.in.mastersvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class ProgressMonitoringDTO {

    private ProgressMonitoringBasicDTO university;
    private ProgressMonitoringBasicDTO college;
    private ProgressMonitoringBasicDTO standalone;
    private List<FinalProgressMonitoringDayWiseDTO> dayWise;

}
