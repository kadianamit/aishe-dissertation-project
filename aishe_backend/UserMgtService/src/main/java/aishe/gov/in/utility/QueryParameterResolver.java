package aishe.gov.in.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class QueryParameterResolver {

    public String universityParameterBinder(String query, Integer surveyYear, String stateCode, String type/*, String searchText*/) {
        if (null != surveyYear) {
            query = query + " and  ru.survey_year= " + surveyYear;
        }
        if (null != stateCode) {
            query = query + " and  ru.statecode= '" + stateCode + "'";
        }
        if (null != type) {
            query = query + " and  ru.type_id= '" + type + "'";
        }
       /* if (null != searchText) {
            query = searchString(query, searchText, "ru");
        }*/
        return query;
    }

    public String collegeParameterBinder(String query, Integer surveyYear, String stateCode, String type, String universityId/*, String searchText*/) {
        if (null != surveyYear) {
            query = query + " and  c.survey_year= " + surveyYear;
        }
        if (null != stateCode) {
            query = query + " and  c.state_code= '" + stateCode + "'";
        }
        if (null != type) {
            query = query + " and  c.type_id= '" + type + "'";
        }
        if (null != universityId) {
            query = query + " and  c.university_id= '" + universityId + "'";
        }
       /* if (null != searchText) {
            query = searchString(query, searchText, "c");
        }*/
        return query;
    }

    public String standaloneParameterBinder(String query, Integer surveyYear, String stateCode, Integer type/*, String searchText*/) {
        if (null != surveyYear) {
            query = query + " where  si.survey_year= " + surveyYear;
        }
        if (null != stateCode) {
            query = query + " and  si.statecode= '" + stateCode + "'";
        }
        if (null != type) {
            query = query + " and  si.statebodyid= " + type;
        }
       /* if (null != searchText) {
            query = searchString(query, searchText, "si");
        }*/
        return query;
    }

    private String searchString(String query, String searchText, String type) {
        String subString = searchText.toUpperCase();
        if (subString.startsWith("C-") || subString.startsWith("S-") || subString.startsWith("U-")) {
            String aisheId = subString.trim().split("\\s*-\\s*")[1];
            query = query + "and ( cast(" + type + ".id as text) like '%" + aisheId + "%' or UPPER(" + type + ".name) like '%" + subString + "%')";
        } else {
            query = query + "and (cast(" + type + ".id as text) like '%" + subString + "%' or UPPER(" + type + ".name) like '%" + subString + "%')";
        }
        return query;
    }

    public String fromUploadParameterBinder(String query, String fromDate, String toDate, Integer surveyYear) {
        if (null != fromDate && null != toDate) {
            if (!(fromDate != "null" && toDate != "null")) {
                LocalDate fromDateFrom = DateUtils.convertStringSlashDateToDBDate(fromDate);
                LocalDate fromDateTO = DateUtils.convertStringSlashDateToDBDate(toDate);
                query = query +  " and cast(fu.upload_date as date) >='" + fromDateFrom + "' and cast(fu.upload_date as date) <='" + fromDateTO + "'";
            }
        }
        if (null != surveyYear) {
            query = query + " and  fu.survey_year= " + surveyYear;
        }
        return query;
    }
}
