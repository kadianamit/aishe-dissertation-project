package com.nic.aishe.master.util;

import java.util.List;

import com.nic.aishe.master.handler.InvalidInputException;
import com.nic.aishe.master.handler.UnAuthorizationException;
import com.nic.aishe.master.security.UserInfo;

public class CommonObjectOperation {

    public static Boolean listValidate(List<?> list) {
        return null != list && !list.isEmpty();
    }

    public static Boolean stringValidate(String obj) {
        return null != obj && !("null".equals(obj)) && !obj.isEmpty();
    }

    public static Boolean integerValidate(Integer obj) {
        return null != obj;
    }

    public static Boolean booleanValidate(Boolean obj) {
        return null != obj;
    }

    public static Boolean longValidate(Long obj) {
        return null != obj;
    }

    public static Boolean doubleValidate(Double obj) {
        return null != obj;
    }

    public static Boolean objectValidate(Object obj) {
        return null != obj;
    }


    public static Boolean threeLength(String districtCode) {
        return districtCode == null || districtCode.equals("null") || districtCode.length() <= 3;
    }

    public static Boolean fiveLength(String districtCode) {
        return districtCode == null || districtCode.equals("null") || districtCode.length() <= 5;
    }

    public static Boolean sevenLength(String aisheCode) {
        return aisheCode == null || aisheCode.equals("null") || aisheCode.length() <= 7;
    }

    public static Boolean usernameValidate(Object userInfo) {
        if (userInfo == null) {
            throw new UnAuthorizationException("Unauthorised User");
        } else {
            UserInfo userInfo1 = (UserInfo) userInfo;
            if (null == userInfo1.getUsername()) {
                throw new UnAuthorizationException("Unauthorised User(Token Expired)");
            } else if (userInfo1.getUsername().length() > 45) {
                throw new InvalidInputException("Username length should be less than 45 !!");
            }
        }
        return true;
    }
}
