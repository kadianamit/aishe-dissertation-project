package aishe.gov.in.utility;

import org.springframework.stereotype.Component;

@Component
public class AisheCodeConvertInID {

    public String aisheCodeToId(String aisheCode) {
        String[] splittedCollege = aisheCode.trim().split("\\s*-\\s*");
        return splittedCollege[1];
    }

    public String[] aisheCodeToArray(String aisheCode) {
        return aisheCode.trim().split("\\s*-\\s*");
    }
}
