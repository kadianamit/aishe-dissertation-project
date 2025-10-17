package aishe.gov.in.mastersvo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ApprovingAuthorityDTO {

    private String firstName;
    private String middleName;
    private String lastName;
    private String roleName;
    private String mobile;
    private String landline;
    private String stdCode;
    private String emailId;
}
