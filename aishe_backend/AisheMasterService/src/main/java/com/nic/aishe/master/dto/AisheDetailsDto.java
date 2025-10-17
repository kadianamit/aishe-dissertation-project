package com.nic.aishe.master.dto;

public class AisheDetailsDto {

    String aishecode;
    String institutionCategory;
    String institutionName;
    String state;
    String district;
    String affiliatingUniv;
    String eligibleSurveyYear;
    String instType;
    String mgmtBody;
    String sourceOfData;

    String status;
    String address;
    String pinCode;

    public String getAishecode() {
        return aishecode;
    }

    public void setAishecode(String aishecode) {
        this.aishecode = aishecode;
    }

    public String getInstitutionCategory() {
        return institutionCategory;
    }

    public void setInstitutionCategory(String institutionCategory) {
        this.institutionCategory = institutionCategory;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAffiliatingUniv() {
        return affiliatingUniv;
    }

    public void setAffiliatingUniv(String affiliatingUniv) {
        this.affiliatingUniv = affiliatingUniv;
    }

    public String getEligibleSurveyYear() {
        return eligibleSurveyYear;
    }

    public void setEligibleSurveyYear(String eligibleSurveyYear) {
        this.eligibleSurveyYear = eligibleSurveyYear;
    }

    public String getInstType() {
        return instType;
    }

    public void setInstType(String instType) {
        this.instType = instType;
    }

    public String getMgmtBody() {
        return mgmtBody;
    }

    public void setMgmtBody(String mgmtBody) {
        this.mgmtBody = mgmtBody;
    }

    public String getSourceOfData() {
        return sourceOfData;
    }

    public void setSourceOfData(String sourceOfData) {
        this.sourceOfData = sourceOfData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "AisheDetailsDto{" +
                "aishecode='" + aishecode + '\'' +
                ", institutionCategory='" + institutionCategory + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", affiliatingUniv='" + affiliatingUniv + '\'' +
                ", eligibleSurveyYear='" + eligibleSurveyYear + '\'' +
                ", instType='" + instType + '\'' +
                ", mgmtBody='" + mgmtBody + '\'' +
                ", sourceOfData='" + sourceOfData + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
