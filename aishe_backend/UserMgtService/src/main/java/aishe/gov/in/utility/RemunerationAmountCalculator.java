package aishe.gov.in.utility;

import aishe.gov.in.masterseo.UserBankAccountEO;
import aishe.gov.in.mastersvo.AfiiliatedWithUniversityCount;
import aishe.gov.in.mastersvo.SelectOptionVO;
import aishe.gov.in.service.RemunerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemunerationAmountCalculator {

    @Autowired
    private AisheCodeConvertInID convert;
    @Autowired
    private RemunerationService remunerationService;

    public Integer remunerationAmount(String aisheCode, Integer surveyYear, UserBankAccountEO bankAccountEO) {

        String splitedAisheCode[] = convert.aisheCodeToArray(aisheCode);
        Integer amt = null;
        switch (splitedAisheCode[0]) {
            case "C": {
                List<SelectOptionVO> optionVOS = remunerationService.checkEligibility(aisheCode, surveyYear);
                if (CommanObjectOperation.listValidate(optionVOS)) amt = 5000;
                break;
            }
            case "S": {
                List<SelectOptionVO> optionVOS = remunerationService.checkEligibility(aisheCode, surveyYear);
                if (CommanObjectOperation.listValidate(optionVOS)) amt = 2000;
                break;
            }
            case "U": {
                List<SelectOptionVO> optionVOS = remunerationService.checkEligibility(aisheCode, surveyYear);
                if (CommanObjectOperation.listValidate(optionVOS)) {
                    bankAccountEO.setIsFirstTimeUploadDCFUniversity(true);
                    amt = 10000;
                    break;
                } else {
                    AfiiliatedWithUniversityCount afiiliatedWithUniversityCount = remunerationService.getAffiliatedCollegeCount(splitedAisheCode[1], surveyYear);
                    if (afiiliatedWithUniversityCount.getTotalAffiliatedCollege().intValue() >= 50 && 199 >= afiiliatedWithUniversityCount.getTotalAffiliatedCollege().intValue()) {
                        bankAccountEO.setNumberOfAffiliatingCollege(afiiliatedWithUniversityCount.getTotalAffiliatedCollege().intValue());
                        bankAccountEO.setNumberOfDcfSubmittedCollege(afiiliatedWithUniversityCount.getTotalFormUploadedAffiliatedCollege().intValue());
                        bankAccountEO.setIsFirstTimeUploadDCFUniversity(false);
                        amt = 10000;
                        break;
                    } else if (afiiliatedWithUniversityCount.getTotalAffiliatedCollege().intValue() >= 200 && 499 >= afiiliatedWithUniversityCount.getTotalAffiliatedCollege().intValue()) {
                        bankAccountEO.setNumberOfAffiliatingCollege(afiiliatedWithUniversityCount.getTotalAffiliatedCollege().intValue());
                        bankAccountEO.setNumberOfDcfSubmittedCollege(afiiliatedWithUniversityCount.getTotalFormUploadedAffiliatedCollege().intValue());
                        bankAccountEO.setIsFirstTimeUploadDCFUniversity(false);
                        amt = 15000;
                        break;
                    } else if (afiiliatedWithUniversityCount.getTotalAffiliatedCollege().intValue() >= 500) {
                        bankAccountEO.setNumberOfAffiliatingCollege(afiiliatedWithUniversityCount.getTotalAffiliatedCollege().intValue());
                        bankAccountEO.setNumberOfDcfSubmittedCollege(afiiliatedWithUniversityCount.getTotalFormUploadedAffiliatedCollege().intValue());
                        bankAccountEO.setIsFirstTimeUploadDCFUniversity(false);
                        amt = 20000;
                        break;
                    }
                }
            }
        }
        return amt;
    }
}
