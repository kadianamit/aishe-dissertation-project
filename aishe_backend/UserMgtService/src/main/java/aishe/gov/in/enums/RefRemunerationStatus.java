package aishe.gov.in.enums;

public enum RefRemunerationStatus {

    ALL(0, "ALL"),
    PENDING(1, "Pending"),

    APPROVED(2, "Approved"),

    REJECT(3, "Reject"),

    DEFERRED(4, "Deferred"),
    LOCKED(13, "Locked"),

    ACCOUNT_DETAIL_LOCKED(13, "A/C Details Locked"),

    BANK_ADVISE_SENT(5, "Bank Advise Sent"),

    PAYMENT_SUCCESSFUL(6, "Payment Successful"),

    PAYMENT_PENDING(7, "Payment Pending"),

    ERRONEOUS_BANK_ACCOUNT(8, "Erroneous Bank Account(Payment Failed)"),

    ERRONEOUS_IFSC_CODE(9, "Erroneous IFSC Code(Payment Failed)"),

    ERRONEOUS_ACCOUNT_AND_IFSC(10, "Erroneous Bank Account and IFSC Code both(Payment Failed)"),

    OTHER_REASONS(11, "Other Reasons(Payment Failed)"),

    OTHERS(12, "Others");


    private Integer id;
    private String status;

    RefRemunerationStatus(Integer id, String status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
