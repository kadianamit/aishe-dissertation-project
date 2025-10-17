package aishe.gov.in.enums;

public  enum States {
    ALL("ALL", "ALL"),
    Jammu_And_Kashmir("01", "Jammu and Kashmir"), Himachal_Pradesh("02", "Himachal Pradesh"),
    Punjab("03", "Punjab"), Chandigarh("04", "Chandigarh"), Uttarakhand("05", "Uttarakhand"),
    Haryana("06", "Haryana"), Delhi("07", "Delhi"), Rajasthan("08", "Rajasthan"),
    Uttar_Pradesh("09", "Uttar Pradesh"), Bihar("10", "Bihar"), Sikkim("11", "Sikkim"),
    Arunachal_Pradesh("12", "Arunachal Pradesh"), Nagaland("13", "Nagaland"), Manipur("14", "Manipur"),
    Mizoram("15", "Mizoram"), Tripura("16", "Tripura"), Meghalaya("17", "Meghalaya"), Assam("18", "Assam"),
    West_Bengal("19", "West Bengal"), Jharkhand("20", "Jharkhand"), Odisha("21", "Odisha"),
    Chhattisgarh("22", "Chhattisgarh"), Madhya_Pradesh("23", "Madhya Pradesh"), Gujarat("24", "Gujarat"),
    //		Daman_and_Diu("25", "Daman and Diu"), Dadra_and_Nagar_Haveli("26", "Dadra and Nagar Haveli"),
    Maharashtra("27", "Maharashtra"), Andhra_Pradesh("28", "Andhra Pradesh"), Karnataka("29", "Karnataka"),
    Goa("30", "Goa"), Lakshadweep("31", "Lakshadweep"), Kerala("32", "Kerala"), Tamil_Nadu("33", "Tamil Nadu"),
    Puducherry("34", "Puducherry"), Andaman_and_Nicobar_Islands("35", "Andaman and Nicobar Islands"),
    Telangana("36", "Telangana"), Ladakh("37", "Ladakh"), The_Dadra_And_Nagar_Haveli_And_Daman_And_Diu("38", "The Dadra And Nagar Haveli And Daman And Diu") ;

    private String stateName;
    private String id;

    States(String id, String stateName) {
        this.stateName = stateName;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

}
