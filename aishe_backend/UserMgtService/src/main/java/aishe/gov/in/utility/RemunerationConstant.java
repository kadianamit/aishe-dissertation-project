package aishe.gov.in.utility;

public interface RemunerationConstant {
    Integer MHRD_ROLE_ID = 1;
    Integer SNO_ROLE_ID = 6;
    Integer PENDING_APPROVAL_STATUS_ID = 4;
    Integer REMUNERATION_TYPE = 1;
    String REMUNERATION_REMARKS = "AISHE Remuneration";
    Integer REMUNERATION_TRANSACTION_PENDING = 3;
    Integer REMUNERATION_TRANSACTION_APPROVED = 1;
    Integer REMUNERATION_NORM_TYPE_1 = 1;
    Integer REMUNERATION_NORM_TYPE_2 = 2;

    String REPORTS_PATH = "/remuneration/";
    String REPORT92 = "Report 92.jrxml";
    String REPORT_92_NAME = "Report 92";
    String REPORT_92_TITLE = "Remuneration Statement";
    String reportTitle = "report_title";

    String TRANSACTION_ALL_STATUS_ID = "ALL";

    Integer REPORT_ACTION_ID = 23;
    Integer BANK_AC_ACTION_ID = 7;

    String FILE_TYPE_EXCEPTION = "Only xlsx Or xls File allow to upload";

    String statementId = "statement_id";
    String statementTimestamp = "statement_timestamp";
    String generatorUserId = "generator_user_id";

    String surveyYear = "survey_year";
    String surveyYearOne = "survey_year_one";

    int zero = 0;
    int one = 1;

    int APPROVED_STATUS_ID = 1;
    int REJECTED_STATUS_ID = 2;
    String REMUNERATION_APPROVED_EMAIL_SUBJECT = "AISHE Remuneration Approved";
    String REMUNERATION_REJECTED_EMAIL_SUBJECT = "AISHE Remuneration Rejected ";

    int SNO_OFFICER = 6;
    int UNIVERSITY_OFFICER = 7;
    int STB_DTE_OFFICER = 8;
    int STATE_NURSING_BODY_OFFICER = 9;
    int SCERT_OFFICER = 10;

    String UNO_SECTORAL = "UNO/Sectoral";
    String MOE = "MOE";
    String SNO = "SNO";
    String USER_CONTACT_PERSON1 = "University Nodal Officer";
    String USER_CONTACT_PERSON2 = "State Nodal Officer";
    String TRANSACTION_SUCCESS_EMAIL_SUBJECT = "AISHE Remuneration Transferred";
    String TRANSACTION_FAILURE_EMAIL_SUBJECT = "AISHE Remuneration Failed";

    int TRANSACTION_SUCCESSFUL_STATUS_ID = 1;
    int TRANSACTION_FAILED_STATUS_ID = 2;
    int TRANSACTION_PENDING_STATUS_ID = 3;

    int BASE_SURVEY_YEAR = 2020;

}
