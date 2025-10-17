package aishe.gov.in.utility;

import aishe.gov.in.masterseo.SurveyMastersLogEO;
import aishe.gov.in.masterseo.SurveyStatusEO;
import aishe.gov.in.masterseo.SurveyStatusLogsEO;
import aishe.gov.in.mastersvo.SurveyMasterLogDto;
import aishe.gov.in.mastersvo.SurveyStatusDTO;
import aishe.gov.in.mastersvo.SurveyStatusLogDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObjectBinderComponent {

    public List<SurveyStatusLogDTO> bindStatusDTO(List<SurveyStatusLogsEO> surveyActionLog) {
        List<SurveyStatusLogDTO> list = new ArrayList<>();
        if (surveyActionLog.size() > 0)
            for (SurveyStatusLogsEO statusEO : surveyActionLog) {

                list.add(SurveyStatusLogDTO
                        .builder()
                        .surveyYear(statusEO.getSurveyYear())
                        .actionDate(DateUtils.convertDBDateTimeToString(statusEO.getActionDate().toLocalDateTime()))
                        .userId(statusEO.getUserId())
                        .state(statusEO.getStateCode().getName())
                        .form1UploadDisabled(statusEO.getForm1UploadDisabled())
                        .form2UploadDisabled(statusEO.getForm2UploadDisabled())
                        .form3UploadDisabled(statusEO.getForm3UploadDisabled())
                        .build());
            }
        return list;
    }

    public List<SurveyMasterLogDto> bindMasterDTO(List<SurveyMastersLogEO> surveyActionLog) {
        List<SurveyMasterLogDto> list = new ArrayList<>();
        if (surveyActionLog.size() > 0)
            for (SurveyMastersLogEO statusEO : surveyActionLog) {

                list.add(SurveyMasterLogDto
                        .builder()
                        .surveyYear(statusEO.getSurveyYear())
                        .action(statusEO.getActionId().getAction())
                        .endDate(DateUtils.convertDBDateTimeToString(statusEO.getEndDate()))
                        .startDate(DateUtils.convertDBDateTimeToString(statusEO.getStartDate()))
                        .actionDate(DateUtils.convertDBDateTimeToString(statusEO.getActionDate().toLocalDateTime()))
                        .userId(statusEO.getUserId())
                        .build());
            }
        return list;
    }

    public List<SurveyStatusDTO> bindStatusDto(List<SurveyStatusEO> statusEOS) {
        List<SurveyStatusDTO> list = new ArrayList<>();
        if (statusEOS.size() > 0)
            for (SurveyStatusEO statusEO : statusEOS) {

                list.add(SurveyStatusDTO
                        .builder()
                        .surveyYear(statusEO.getStatusKey().getSurveyYear())
                        .stateCode(statusEO.getStatusKey().getStateCode())
                        .form1UploadDisabled(statusEO.getForm1UploadDisabled())
                        .form2UploadDisabled(statusEO.getForm2UploadDisabled())
                        .form3UploadDisabled(statusEO.getForm3UploadDisabled())
                        .build());
            }
        return list;
    }
}
