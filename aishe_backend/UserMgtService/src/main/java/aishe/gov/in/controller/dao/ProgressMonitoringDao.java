package aishe.gov.in.dao;

import aishe.gov.in.enums.FormType;
import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.enums.ListType;
import aishe.gov.in.mastersvo.InstituteDetailDTO;
import aishe.gov.in.mastersvo.ProgressMoniteringDTO;
import aishe.gov.in.mastersvo.ProgressMonitoringBasicDTO;
import aishe.gov.in.mastersvo.ProgressMonitoringDTO;
import aishe.gov.in.mastersvo.ProgressMontioringDetailDTO;

import java.util.List;

public interface ProgressMonitoringDao {

    List<ProgressMoniteringDTO> getProgressMonitoring(Integer surveyYear, String stateCode, String universityId, String fromDate, String toDate, InstitutionType institutionType,Integer roleId);
    List<InstituteDetailDTO>  getInstituteDetailOfProgressMonitoring(Integer surveyYear, String stateCode, String universityId, String fromDate, String toDate, InstitutionType institutionType, String type, FormType formType/*,int page,int pageSize,String searchText*/);

    ProgressMonitoringDTO getProgressMonitoringByBasicCount(Integer surveyYear, String fromDate, String toDate, InstitutionType institutionType);

    List<ProgressMontioringDetailDTO> progressMonitoringStateWise(Integer surveyYear);

    List<?> progressMonitoringStateWise(Integer surveyYear, ListType type, String stateCode);
}
