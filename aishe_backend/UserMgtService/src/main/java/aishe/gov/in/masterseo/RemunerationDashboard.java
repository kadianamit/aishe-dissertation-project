package aishe.gov.in.masterseo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/*
 * Note: The remarks field setter methods have been modified to set null when the string is empty
 */
@Entity
@Table(name = "final_result")
public class RemunerationDashboard implements Serializable {

    private static final long serialVersionUID = 3635762168800971582L;
    @Id
    private int form_upload_id;
    private String form_id;
    private Integer survey_year;
/*    private String university_id;
    private Integer college_institution_id;
    private Integer standalone_institution_id;*/
    private String university_or_statutory_body_name;
    private String college_or_standalone_institution_name;
    @Transient
    private Boolean university_constituted_from_colleges;
    private String state_code;
    private String state_name;
    @Transient
    private String uploader_user_id;
    @Transient
    private String nodal_officer_first_name;
    @Transient
    private String nodal_officer_middle_name;
    @Transient
    private String nodal_officer_last_name;
   @Transient
    private Date uploading_time_stamp;
    private BigInteger total_number_of_programmes;

    private Integer norm_type_id;

    private Integer amount;

    //Bank data
    private String account_holder_name;
    private String account_number;
    private String ifsc_code;
    private Date updated_on;

   /* private String uno_sectorial_status;
    private Integer uno_sectorial_status_id;
    private Integer uno_sectorial_approval_status_id; //TODO:Check probably duplicate variable
    private String uno_sectorial_remarks;
    private Date uno_sectorial_timestamp;
    private String sno_status;
    private Integer sno_status_id;
   *//* private Integer sno_approval_status_id; //TODO:Check probably duplicate variable*//*
    private String sno_remarks;
    private Date sno_timestamp;*/
   /* private String mhrd_status;
    private Integer mhrd_status_id;
    private Integer mhrd_approval_status_id; //TODO:Check probably duplicate variable
*//*    private Integer common_approval_status_id;*//*
    private String mhrd_remarks;
    private Date mhrd_timestamp;*/

    //Statement dashboard data
  /*  private String statement_id;
    private Date statement_timestamp;*/
   /* private String generator_user_id;*/
    private Integer transaction_status_id;
    private String transaction_status;
    private Date transaction_timestamp;
    private String remarks;


   /* private Date log_timestamp;*/
    /*private String log_user_id;*/
  /*  private String status;*/
/*    private String displayUploadAndAccountUpdatedDate;*/
/*    private String pan;*/

    public int getForm_upload_id() {
        return form_upload_id;
    }

    public void setForm_upload_id(int form_upload_id) {
        this.form_upload_id = form_upload_id;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public Integer getSurvey_year() {
        return survey_year;
    }

    public void setSurvey_year(Integer survey_year) {
        this.survey_year = survey_year;
    }

/*    public String getUniversity_id() {
        return university_id;
    }

    public void setUniversity_id(String university_id) {
        this.university_id = university_id;
    }

    public Integer getCollege_institution_id() {
        return college_institution_id;
    }

    public void setCollege_institution_id(Integer college_institution_id) {
        this.college_institution_id = college_institution_id;
    }

    public Integer getStandalone_institution_id() {
        return standalone_institution_id;
    }

    public void setStandalone_institution_id(Integer standalone_institution_id) {
        this.standalone_institution_id = standalone_institution_id;
    }*/

    public String getUniversity_or_statutory_body_name() {
        return university_or_statutory_body_name;
    }

    public void setUniversity_or_statutory_body_name(String university_or_statutory_body_name) {
        this.university_or_statutory_body_name = university_or_statutory_body_name;
    }

    public String getCollege_or_standalone_institution_name() {
        return college_or_standalone_institution_name;
    }

    public void setCollege_or_standalone_institution_name(String college_or_standalone_institution_name) {
        this.college_or_standalone_institution_name = college_or_standalone_institution_name;
    }

    public Boolean getUniversity_constituted_from_colleges() {
        return university_constituted_from_colleges;
    }

    public void setUniversity_constituted_from_colleges(Boolean university_constituted_from_colleges) {
        this.university_constituted_from_colleges = university_constituted_from_colleges;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getUploader_user_id() {
        return uploader_user_id;
    }

    public void setUploader_user_id(String uploader_user_id) {
        this.uploader_user_id = uploader_user_id;
    }

    public String getNodal_officer_first_name() {
        return nodal_officer_first_name;
    }

    public void setNodal_officer_first_name(String nodal_officer_first_name) {
        this.nodal_officer_first_name = nodal_officer_first_name;
    }

    public String getNodal_officer_middle_name() {
        return nodal_officer_middle_name;
    }

    public void setNodal_officer_middle_name(String nodal_officer_middle_name) {
        this.nodal_officer_middle_name = nodal_officer_middle_name;
    }

    public String getNodal_officer_last_name() {
        return nodal_officer_last_name;
    }

    public void setNodal_officer_last_name(String nodal_officer_last_name) {
        this.nodal_officer_last_name = nodal_officer_last_name;
    }

    public Date getUploading_time_stamp() {
        return uploading_time_stamp;
    }

    public void setUploading_time_stamp(Date uploading_time_stamp) {

        this.uploading_time_stamp = uploading_time_stamp;
    }

    public BigInteger getTotal_number_of_programmes() {
        return total_number_of_programmes;
    }

    public void setTotal_number_of_programmes(BigInteger total_number_of_programmes) {
        this.total_number_of_programmes = total_number_of_programmes;
    }

    public Integer getNorm_type_id() {
        return norm_type_id;
    }

    public void setNorm_type_id(Integer norm_type_id) {
        this.norm_type_id = norm_type_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAccount_holder_name() {
        return account_holder_name;
    }

    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public Date getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Date updated_on) {

        this.updated_on = updated_on;
    }
/*
    public String getUno_sectorial_status() {
        return uno_sectorial_status;
    }

    public void setUno_sectorial_status(String uno_sectorial_status) {
        this.uno_sectorial_status = uno_sectorial_status;
    }

    public Integer getUno_sectorial_approval_status_id() {
        return uno_sectorial_approval_status_id;
    }

    public void setUno_sectorial_approval_status_id(
            Integer uno_sectorial_approval_status_id) {
        this.uno_sectorial_approval_status_id = uno_sectorial_approval_status_id;
    }

    public Integer getSno_approval_status_id() {
        return sno_approval_status_id;
    }

    public void setSno_approval_status_id(Integer sno_approval_status_id) {
        this.sno_approval_status_id = sno_approval_status_id;
    }

    *//*public Integer getMhrd_approval_status_id() {
        return mhrd_approval_status_id;
    }

    public void setMhrd_approval_status_id(Integer mhrd_approval_status_id) {
        this.mhrd_approval_status_id = mhrd_approval_status_id;
    }*//*

    public String getUno_sectorial_remarks() {
        return uno_sectorial_remarks;
    }

    public void setUno_sectorial_remarks(String uno_sectorial_remarks) {

        //Set remarks as null if it is empty / blank (Text area fields return empty string instead of null when left empty by user).
        if (uno_sectorial_remarks != null && uno_sectorial_remarks.isEmpty()) {

            this.uno_sectorial_remarks = null;
        } else {

            this.uno_sectorial_remarks = uno_sectorial_remarks;
        }
    }

    public Date getUno_sectorial_timestamp() {
        return uno_sectorial_timestamp;
    }

    public void setUno_sectorial_timestamp(Date uno_sectorial_timestamp) {
        this.uno_sectorial_timestamp = uno_sectorial_timestamp;
    }

    public String getSno_status() {
        return sno_status;
    }

    public void setSno_status(String sno_status) {
        this.sno_status = sno_status;
    }

    public String getSno_remarks() {
        return sno_remarks;
    }

    public void setSno_remarks(String sno_remarks) {

        //Set remarks as null if it is empty / blank (Text area fields return empty string instead of null when left empty by user).
        if (sno_remarks != null && sno_remarks.isEmpty()) {

            this.sno_remarks = null;
        } else {

            this.sno_remarks = sno_remarks;
        }
    }

    public Date getSno_timestamp() {
        return sno_timestamp;
    }

    public void setSno_timestamp(Date sno_timestamp) {
        this.sno_timestamp = sno_timestamp;
    }*/

/*    public String getMhrd_status() {
        return mhrd_status;
    }

    public void setMhrd_status(String mhrd_status) {
        this.mhrd_status = mhrd_status;
    }

    public Integer getMhrd_status_id() {
        return mhrd_status_id;
    }

    public void setMhrd_status_id(Integer mhrd_status_id) {
        this.mhrd_status_id = mhrd_status_id;
    }

    public String getMhrd_remarks() {
        return mhrd_remarks;
    }

    public void setMhrd_remarks(String mhrd_remarks) {

        //Set remarks as null if it is empty / blank (Text area fields return empty string instead of null when left empty by user).
        if (mhrd_remarks != null && mhrd_remarks.isEmpty()) {

            this.mhrd_remarks = null;
        } else {

            this.mhrd_remarks = mhrd_remarks;
        }
    }

    public Date getMhrd_timestamp() {
        return mhrd_timestamp;
    }

    public void setMhrd_timestamp(Date mhrd_timestamp) {
        this.mhrd_timestamp = mhrd_timestamp;
    }*/

   /* public String getStatement_id() {
        return statement_id;
    }

    public void setStatement_id(String statement_id) {
        this.statement_id = statement_id;
    }*/

    public Integer getTransaction_status_id() {
        return transaction_status_id;
    }

    public void setTransaction_status_id(Integer transaction_status_id) {
        this.transaction_status_id = transaction_status_id;
    }

    public Date getTransaction_timestamp() {
        return transaction_timestamp;
    }

    public void setTransaction_timestamp(Date transaction_timestamp) {
        this.transaction_timestamp = transaction_timestamp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


/*
    public Integer getUno_sectorial_status_id() {
        return uno_sectorial_status_id;
    }

    public void setUno_sectorial_status_id(Integer uno_sectorial_status_id) {
        this.uno_sectorial_status_id = uno_sectorial_status_id;
    }

    public Integer getSno_status_id() {
        return sno_status_id;
    }

    public void setSno_status_id(Integer sno_status_id) {
        this.sno_status_id = sno_status_id;
    }*/

/*    public Integer getCommon_approval_status_id() {
        return common_approval_status_id;
    }

    public void setCommon_approval_status_id(Integer common_approval_status_id) {
        this.common_approval_status_id = common_approval_status_id;
    }*/
/*

    public Date getStatement_timestamp() {
        return statement_timestamp;
    }

    public void setStatement_timestamp(Date statement_timestamp) {
        this.statement_timestamp = statement_timestamp;
    }
*/

   /* public String getGenerator_user_id() {
        return generator_user_id;
    }

    public void setGenerator_user_id(String generator_user_id) {
        this.generator_user_id = generator_user_id;
    }*/

/*    public Date getLog_timestamp() {
        return log_timestamp;
    }

    public void setLog_timestamp(Date log_timestamp) {
        this.log_timestamp = log_timestamp;
    }*/


//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }


 /*   public String getDisplayUploadAndAccountUpdatedDate() {

        if (null != getUploading_time_stamp()) {

            displayUploadAndAccountUpdatedDate = getUploading_time_stamp().toString().substring(0, 19);
        }

        if (null != getUpdated_on()) {

            displayUploadAndAccountUpdatedDate = displayUploadAndAccountUpdatedDate + "<br><b>and</b><br>" + getUpdated_on().toString().substring(0, 19);
        }

        return displayUploadAndAccountUpdatedDate;
    }

    public void setDisplayUploadAndAccountUpdatedDate() {
    }
*/
    /*public String toJSONString() {

        JSONObject obj = new JSONObject();

        obj.put("state_name", state_name);
        obj.put("university_or_statutory_body_name", university_or_statutory_body_name);
        obj.put("college_or_standalone_institution_name", college_or_standalone_institution_name);
        obj.put("total_number_of_programmes", total_number_of_programmes);
        obj.put("amount", amount);

        return obj.toString();
    }*/
    /*public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

*/
}
