package aishe.gov.in.mastersvo;

import aishe.gov.in.masterseo.CisoLetterEo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
public class CisoInfoDTO {
    private String agencyCode;
    private String post;
    private String name;
    private String agencyName;
    private String designation;
    private String mobile;
    private String mobileAlternate;
    private String emailId;
    private String emailIdAlternate;
    private String officeAddress;
    private Integer priority;
    private String remarks;
    private List<CisoLetterEo> letter;
}
