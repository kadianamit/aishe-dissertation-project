package aishe.gov.in.utility;

import aishe.gov.in.enums.Constant;
import aishe.gov.in.exception.InvalidInputException;
import aishe.gov.in.exception.UnAuthorizationException;
import aishe.gov.in.security.UserInfo;

import java.util.List;
import java.util.Optional;

public class CommanObjectOperation {

    public static Boolean listValidate(List<?> list) {
        if (null != list && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static Boolean stringValidate(String obj) {
        if (null != obj && !("null".equals(obj)) && !obj.isEmpty()) {
            return true;
        }
        return false;
    }

    public static Boolean integerValidate(Integer obj) {
        if (null != obj) {
            return true;
        }
        return false;
    }

    public static Boolean booleanValidate(Boolean obj) {
        if (null != obj) {
            return true;
        }
        return false;
    }

    public static Boolean longValidate(Long obj) {
        if (null != obj) {
            return true;
        }
        return false;
    }

    public static Boolean doubleValidate(Double obj) {
        if (null != obj) {
            return true;
        }
        return false;
    }

    public static Boolean objectValidate(Object obj) {
        if (null != obj) {
            return true;
        }
        return false;
    }

    public static Boolean usernameValidate(Object userInfo) {
        if (userInfo == null) {
            throw new UnAuthorizationException("Unauthorised User");
        } else {
            UserInfo userInfo1 = (UserInfo) userInfo;
            if (null == userInfo1.getUsername()) {
                throw new UnAuthorizationException(Constant.UNAUTHORIZED_USER);
            } else if (userInfo1.getUsername().length() > 45) {
                throw new InvalidInputException("Username length should be less than 45 !!");
            }
        }
        return true;
    }

    public static void validateRequestParam(String aisheCode, String stateCode, String districtCode) {
        if (aisheCode != null && !aisheCode.equals("null") && !aisheCode.isEmpty()) {
            if ((!aisheCode.equalsIgnoreCase("ALL") && !aisheCode.matches(Constant.ONLY_DASH_ALLOWED_REGX)) || aisheCode.length() > 15) {
                throw new InvalidInputException("In-valid Aishe code !!");
            }
        }
        if (stateCode != null && !stateCode.equals("null") && !stateCode.isEmpty()) {
            if (!stateCode.equalsIgnoreCase("ALL") && stateCode.length() > 3) {
                throw new InvalidInputException("In-valid State code !!");
            }
        }
        if (districtCode != null && !districtCode.equals("null") && !districtCode.isEmpty()) {
            if (!districtCode.equalsIgnoreCase("ALL") && districtCode.length() > 3) {
                throw new InvalidInputException("In-valid District code !!");
            }
        }
    }

    public static String getStringValue(Object obj) {
        return Optional.ofNullable(obj).map(Object::toString).orElse(null);
    }

    public static Boolean getBooleanValue(Object obj) {
        return Optional.ofNullable(obj).map(Object::toString).map(Boolean::valueOf).orElse(null);
    }

    public static Integer getIntegerValue(Object obj) {
        return Optional.ofNullable(obj).map(Object::toString).map(Integer::valueOf).orElse(null);
    }


}
