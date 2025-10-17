package aishe.gov.in.mastersvo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Setter
@Getter
public class BankDetailVO {
    @NotNull

    private Integer surveyYear;
    @NotNull
    @NotBlank

    private String userId;
    @NotNull
    @NotBlank
    private String accountHolderName;
    @NotNull
    @NotBlank
    private String accountNumber;
    @NotNull
    @NotBlank
    private String bankAddress;
    @NotNull
    @NotBlank
    private String bankName;
    @NotNull
    @NotBlank
    private String ifsc_code;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 10)
    private String pan;
    @NotNull
    private Integer pincode;
    @NotNull
    @NotBlank
    private String aisheCode;

}
