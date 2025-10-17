package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.University;
import com.nic.aishe.master.entity.UniversityApplicableDCF;
import com.nic.aishe.master.handler.InvalidInputException;
import com.nic.aishe.master.impl.UniversityService;
import com.nic.aishe.master.repo.SurveyMasterRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class UniversityController {


    @Autowired(required = false)
    private UniversityService UniversitysService;

    @Autowired(required = false)
    private SurveyMasterRepo surveyMasterRepo;

/*

    @PostMapping("/university")
    public ResponseEntity<?> addUniversity(@RequestBody University University) {
        University Universitys = UniversitysService.addUniversity(University);
        return ResponseEntity.ok(Universitys);
    }
*/

    @GetMapping("/university")
    public ResponseEntity<?> getAllUniversity(@RequestParam Integer year, @RequestParam(required = false) String stateCode, @RequestParam(required = false) String districtCode) {
        //List<University> Universitys = UniversitysService.getAllUniversity(year);
        if (CommonObjectOperation.stringValidate(stateCode)) {
            if (!CommonObjectOperation.threeLength(stateCode)) {
                throw new InvalidInputException("StateCode length should be less then 3 ");
            }
        }
        if (CommonObjectOperation.stringValidate(districtCode)) {
            if (!CommonObjectOperation.threeLength(districtCode)) {
                throw new InvalidInputException("districtCode length should be less then 3 ");
            }
        }

        if (null == year || 0 == year) {
            year = surveyMasterRepo.maxSurvey();
        }
        List<UniversityApplicableDCF> Universitys = new ArrayList<UniversityApplicableDCF>();
        if (year != null && stateCode != null && stateCode.equals("ALL") && stateCode.equals("ALL") && (districtCode != null && districtCode.equals("ALL"))) {
            Universitys = UniversitysService.getAllApplicableUniversity(year);
        }
        if (year != null && stateCode != null && !stateCode.equals("ALL") && (districtCode != null && districtCode.equals("ALL"))) {
            Universitys = UniversitysService.getAllApplicableUniversityByStateDistrict(year, stateCode, districtCode);
        }
        if (year != null && stateCode != null && districtCode != null) {
            Universitys = UniversitysService.getAllApplicableUniversityByStateDistrict(year, stateCode, districtCode);
        }
        if (Universitys == null || Universitys.size() == 0) {
            if (year != null && stateCode != null && stateCode.equals("ALL") && (districtCode != null && districtCode.equals("ALL"))) {
                Universitys = UniversitysService.getAllApplicableUniversity(year - 1);
            }
            if (year != null && stateCode != null && !stateCode.equals("ALL") && (districtCode != null && districtCode.equals("ALL"))) {
                Universitys = UniversitysService.getAllApplicableUniversityByStateDistrict(year - 1, stateCode, districtCode);
            }
        }
        //////////////////////

        if (year != null && stateCode != null && stateCode.equals("ALL") && districtCode == null) {
            Universitys = UniversitysService.getAllApplicableUniversity(year);
        }
        if (year != null && stateCode != null && !stateCode.equals("ALL") && districtCode == null) {
            Universitys = UniversitysService.getAllApplicableUniversityByState(year, stateCode);
        }
        if (Universitys == null || Universitys.size() == 0 && districtCode == null) {
            if (year != null && stateCode != null && stateCode.equals("ALL")) {
                Universitys = UniversitysService.getAllApplicableUniversity(year - 1);
            }
            if (year != null && stateCode != null && !stateCode.equals("ALL")) {
                Universitys = UniversitysService.getAllApplicableUniversityByState(year - 1, stateCode);
            }
        }

        if (year != null && stateCode == null && districtCode == null) {
            Universitys = UniversitysService.getAllApplicableUniversity(year);
        }

        
        
        
        List<UniversityApplicableDCF> UniversitysNewList = new ArrayList<UniversityApplicableDCF>();
        if (Universitys != null && Universitys.size() > 0) {
        	for(int i =0;i<Universitys.size();i++) {
        		UniversityApplicableDCF university = new UniversityApplicableDCF();
        		BeanUtils.copyProperties(Universitys.get(i), university);
        		university.setUniversityNameState(university.getName()+" ("+university.getStateCode().getName()+")");
        		UniversitysNewList.add(university);
        	}
        	
        	
            return ResponseEntity.ok(UniversitysNewList.stream().sorted(Comparator.comparing(UniversityApplicableDCF::getName)).collect(Collectors.toList()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/university/{id}")
    public ResponseEntity<?> getOneUniversity(@PathVariable String id, @WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        if (CommonObjectOperation.stringValidate(id)) {
            if (!CommonObjectOperation.fiveLength(id)) {
                throw new InvalidInputException("Id length should be less then 3 ");
            }
        }
        List<University> list = UniversitysService.getOneUniversity(id);
        if (list != null && list.size() > 0) {
            return ResponseEntity.ok(list.get(0));
        }
        return ResponseEntity.notFound().build();

    }

/*
    @DeleteMapping("/university/{id}")
    public ResponseEntity<?> deleteOneUniversity(@PathVariable String id) {
        Boolean isdel = UniversitysService.deleteOneUniversity(id);
        if (isdel) {
            return ResponseEntity.ok("Success deleted with id " + id);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/university")
    public ResponseEntity<?> deleteAllUniversity() {
        UniversitysService.deleteAllUniversity();

        return ResponseEntity.ok("All deleted Success !");
    }
*/

    @GetMapping("/universitylist")
    public ResponseEntity<?> getAllUniversityList(@RequestParam Integer year, @RequestParam(required = false) String stateCode) {
        //List<University> Universitys = UniversitysService.getAllUniversity(year);
        if (CommonObjectOperation.stringValidate(stateCode)) {
            if (!CommonObjectOperation.threeLength(stateCode)) {
                throw new InvalidInputException("StateCode length should be less then 3 ");
            }
        }
        List<UniversityApplicableDCF> Universitys = new ArrayList<UniversityApplicableDCF>();
        if (year != null && stateCode.equals("ALL")) {
            Universitys = UniversitysService.getAllApplicableUniversity(year);
        }
        if (year != null && !stateCode.equals(null) && !stateCode.equals("ALL")) {
            Universitys = UniversitysService.getAllApplicableUniversityByState(year, stateCode);
        }
        if (Universitys == null || Universitys.size() == 0) {
            if (year != null && stateCode.equals("ALL")) {
                Universitys = UniversitysService.getAllApplicableUniversity(year - 1);
            }
            if (year != null && !stateCode.equals(null) && !stateCode.equals("ALL")) {
                Universitys = UniversitysService.getAllApplicableUniversityByState(year - 1, stateCode);
            }
        }
        if (Universitys != null && Universitys.size() > 0) {
            return ResponseEntity.ok(Universitys.stream().sorted(Comparator.comparing(UniversityApplicableDCF::getName)).collect(Collectors.toList()));
        }
        return ResponseEntity.notFound().build();
    }
}
