package aishe.gov.in.masterseo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
public class ApproveUserDTO {
    @NotBlank
    @NotNull
    private String approvedBy;
    @NotBlank
    @NotNull
    private String userId;
    private Boolean status;
    //@NotBlank
    //@NotNull
    private String aisheCode;
    private String message;
    @JsonIgnore
    private Integer roleId;
}
