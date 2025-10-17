package com.nic.aishe.master.enums;


public enum StatusMaster {

    SUCCESS(1, 200, "Success", "Successfully Done"),
    FAILED(2, 500, "Failed", "Fail to Execute"),
    DUPLICATE_BASIC_PROFILE_ID(3, 401, "Udise Code Already Exist", "Udise Code Already Exist"), DUPLICATE_EXCEPTION(4, 500, "Duplicate Value", "Please Check Duplicate Value Given"),
    EXCEED_FILE_SIZE(5, 500, "Image Error", "File Size More Then 100Kb"),
    IMAGE_EXTENSTION(6, 500, "Image Error", "JPG/JPEG Format Allowed"),
    INVALID_BASIC_PROFILE_ID(7, 401, "Udise Code Invalid", "Udise Code Must Be Eleven Character"),
    DUPLICATE_EMAIL(8, 401, "Email Id Already Exist", "Email Id Already Exist"),
    DUPLICATE_MOBILE(9, 401, "Mobile Number Already Exist", "Mobile Number Already Exist"),
    EXCEL_FILE_ERROR(10, 500, "Excel File Error", "Please Fill Proper Data in Excel File."),
    PDF_FILE_ERROR(10, 500, "PDF File Error", "PDF File Error"),
    INVALID_REQUEST_INPUT(11, 501, "Invalid Request input", "Invalid Request input parameter"),
    TOKEN_EXPIRED_ERROR(12, 500, "Token Expired Error", "Token Expired");

    private long responseId;
    private long responseCode;
    private String responseMessage;
    private String responseDescription;

    StatusMaster(long responseId, long responseCode, String responseMessage, String responseDescription) {
        this.responseId = responseId;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.responseDescription = responseDescription;
    }

    public long getResponseId() {
        return responseId;
    }

    public void setResponseId(long responseId) {
        this.responseId = responseId;
    }

    public long getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(long responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }
}
