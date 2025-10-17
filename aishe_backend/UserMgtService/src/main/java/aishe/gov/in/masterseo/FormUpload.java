package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "form_upload")
public class FormUpload {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "form_id")
    private String formId;
    @Column(name = "university_id")
    private String universityId;
    @Column(name = "college_institution_id")
    private Integer collegeInstitutionId;
    @Column(name = "standalone_institution_id")
    private Integer standaloneInstitutionId;
    @Column(name = "uploader_id")
    private String uploaderId;
    @Column(name = "upload_date")
    private Timestamp uploadedDate;
    @Column(name = "approver_id")
    private String approverId;
    @Column(name = "approve_date")
    private Timestamp approveDate;
    @Column(name = "pdf_form")
    private Byte pdfForm;
    @Column(name = "survey_year")
    private Integer surveyYear;
    @Column(name = "form_version")
    private String formVersion;
    @Column(name = "is_archived")
    private Boolean isArchived;
    @Column(name = "institute_type")
    private String instituteType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public Integer getCollegeInstitutionId() {
        return collegeInstitutionId;
    }

    public void setCollegeInstitutionId(Integer collegeInstitutionId) {
        this.collegeInstitutionId = collegeInstitutionId;
    }

    public Integer getStandaloneInstitutionId() {
        return standaloneInstitutionId;
    }

    public void setStandaloneInstitutionId(Integer standaloneInstitutionId) {
        this.standaloneInstitutionId = standaloneInstitutionId;
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public Timestamp getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(Timestamp uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public Timestamp getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Timestamp approveDate) {
        this.approveDate = approveDate;
    }

    public Byte getPdfForm() {
        return pdfForm;
    }

    public void setPdfForm(Byte pdfForm) {
        this.pdfForm = pdfForm;
    }

    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
    }

    public String getFormVersion() {
        return formVersion;
    }

    public void setFormVersion(String formVersion) {
        this.formVersion = formVersion;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public String getInstituteType() {
        return instituteType;
    }

    public void setInstituteType(String instituteType) {
        this.instituteType = instituteType;
    }
}
