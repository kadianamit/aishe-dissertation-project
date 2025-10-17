package aishe.gov.in.utility;

import aishe.gov.in.dao.UserDao;
import aishe.gov.in.exception.InvalidInputException;
import aishe.gov.in.masterseo.CaptchaValidationEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaptchaValidatorComponent {
    @Autowired
    private UserDao userDao;

   public Boolean validateCaptcha(String captcha) {
        if (userDao.getCaptcha(captcha)) {
            CaptchaValidationEO validationEO = new CaptchaValidationEO();
            validationEO.setId(null);
            validationEO.setDate_time(DateUtils.obtainCurrentTimeStamp());
            validationEO.setStatus(true);
            validationEO.setCaptcha(captcha);
            userDao.saveCaptcha(validationEO);

        } else {
            throw new InvalidInputException("Captcha has Already used !! ");
        }
        return null;
    }
}
