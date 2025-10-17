package aishe.gov.in.mastersvo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmailVo {
    private String body;
    private String emailAddress;//": "ankushmani0@gmail.com",
    private String subject;  //": "test mail
}
