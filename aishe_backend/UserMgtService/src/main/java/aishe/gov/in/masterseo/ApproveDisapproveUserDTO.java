package aishe.gov.in.masterseo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ApproveDisapproveUserDTO {
    @NotBlank
    @NotNull
    private String approvedBy;
    @NotBlank
    @NotNull
    private String userId;
    private Integer status;
    private String aisheCode;
    private String message;
    @JsonIgnore
    private Integer roleId;
}
