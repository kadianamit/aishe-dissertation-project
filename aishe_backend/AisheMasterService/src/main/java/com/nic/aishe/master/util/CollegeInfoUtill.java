package com.nic.aishe.master.util;

import com.nic.aishe.master.entity.CollegeInfo;
import com.nic.aishe.master.entity.CollegeInstitutionFacultyInfo;
import com.nic.aishe.master.entity.StandaloneInstitutionFacultyInfo;
import com.nic.aishe.master.entity.UniversityFacultyInfo;
import com.nic.aishe.master.impl.CollegeServiceImpl;
import com.nic.aishe.master.repo.CollegeInstitutionFacultyRepo;
import com.nic.aishe.master.repo.StandaloneInstitutionFacultyRapo;
import com.nic.aishe.master.repo.UniversityFacultyRapo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

//@Component
public class CollegeInfoUtill {

    @Autowired
    private CollegeServiceImpl collegeServiceImpl;

    @Autowired
    private CollegeInstitutionFacultyRepo institutionFacultyRepo;

    @Autowired
    private StandaloneInstitutionFacultyRapo standaloneInstitutionFacultyRapo;

    @Autowired
    private UniversityFacultyRapo universityFacultyRapo;

    /**
     * this method return college info by year ad aisecode
     * @param aishecode Integer
     * @return CollegeInfo
     */
    public CollegeInfo getCollegeInfo(Integer aishecode){
            List<CollegeInfo> info = collegeServiceImpl.findCollegeByAisheCode(aishecode)
                    .stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
          CollegeInfo  collegeInfo = info.stream()
                    .max(Comparator.comparing(CollegeInfo::getSurveyYear))
                    .orElseThrow(NoSuchElementException::new);
        return collegeInfo;
    }

    /**
     * this method return college info by year ad aisecode
     * @param aishecode Integer
     * @return CollegeInfo
     */
    public CollegeInstitutionFacultyInfo getCollegeInstitutionFacultyInfo(Integer aishecode,Integer currentSurveyYear){
     List<CollegeInstitutionFacultyInfo>  infoList= institutionFacultyRepo.findAllById(aishecode,currentSurveyYear);
        if(infoList.size()==0)
            return null;
        return infoList
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .stream()
                .max(Comparator.comparing(CollegeInstitutionFacultyInfo::getSurveyYear))
                .orElseThrow(NoSuchElementException::new);

    }


    /**
     * this method return standalone info by year ad aisecode
     * @param aishecode Integer
     * @return StandaloneInstitutionFacultyInfo
     */
    public StandaloneInstitutionFacultyInfo getStandaloneInstitutionFacultyInfo(Integer aishecode,Integer currentSurveyYear){
       List<StandaloneInstitutionFacultyInfo> infoList= standaloneInstitutionFacultyRapo.findAllById(aishecode,currentSurveyYear);
        if(infoList.size()==0)
            return null;
        return infoList
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .stream()
                .max(Comparator.comparing(StandaloneInstitutionFacultyInfo::getSurveyYear))
                .orElseThrow(NoSuchElementException::new);

    }

    /**
     * this method return university info by year ad aisecode
     * @param aishecode Integer
     * @return UniversityFacultyInfo
     */
    public UniversityFacultyInfo getUniversityFacultyInfo(String aishecode,Integer currentSurveyYear){
        List<UniversityFacultyInfo>infoList= universityFacultyRapo.findAllById(aishecode,currentSurveyYear);
        if(infoList.size()==0)
            return null;
        return infoList
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
                .stream()
                .max(Comparator.comparing(UniversityFacultyInfo::getSurveyYear))
                .orElseThrow(NoSuchElementException::new);

    }
}
