package aishe.gov.in.service;

import java.util.List;

import aishe.gov.in.enums.ListType;
import aishe.gov.in.mastersvo.ProgressMontioringDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.ProgressMonitoringDao;
import aishe.gov.in.enums.FormType;
import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.mastersvo.InstituteDetailDTO;
import aishe.gov.in.mastersvo.ProgressMoniteringDTO;
import aishe.gov.in.mastersvo.ProgressMonitoringDTO;
import aishe.gov.in.utility.NullValueHandler;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ProgressMonitoringServiceImpl implements ProgressMonitoringService {

    @Autowired
    private ProgressMonitoringDao progressMonitoringDao;

    @Autowired
    private NullValueHandler handler;

    @Override
    public List<ProgressMoniteringDTO> getProgressMonitoring(Integer surveyYear, String stateCode, String universityId, String fromDate, String toDate, InstitutionType institutionType, Integer roleId) {
        return progressMonitoringDao.getProgressMonitoring(surveyYear, handler.nullValueHandler(stateCode), handler.nullValueHandler(universityId), handler.nullValueHandler(fromDate), handler.nullValueHandler(toDate), institutionType, roleId);
    }

    @Override
    public List<InstituteDetailDTO> getInstituteDetailOfProgressMonitoring(Integer surveyYear, String stateCode, String universityId, String fromDate, String toDate, InstitutionType institutionType, String type, FormType formType/*,int page,int pageSize,String searchText*/) {
        return progressMonitoringDao.getInstituteDetailOfProgressMonitoring(surveyYear, handler.nullValueHandler(stateCode), handler.nullValueHandler(universityId), handler.nullValueHandler(fromDate), handler.nullValueHandler(toDate), institutionType, handler.nullValueHandler(type), formType/*,page, pageSize,handler.nullValueHandler(searchText)*/);
    }

    @Override
    public ProgressMonitoringDTO getProgressMonitoringByBasicCount(Integer surveyYear, String fromDate, String toDate, InstitutionType institutionType) {
        return progressMonitoringDao.getProgressMonitoringByBasicCount(surveyYear, handler.nullValueHandler(fromDate), handler.nullValueHandler(toDate), institutionType);
    }

    @Override
    public List<?> progressMonitoringStateWise(Integer surveyYear, ListType type, String stateCode) {
        if (null != type) {
            return progressMonitoringDao.progressMonitoringStateWise(surveyYear, type, stateCode);
        } else {
            return progressMonitoringDao.progressMonitoringStateWise(surveyYear);

        }
    }


}
