package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.AdmissionCriterion;
import com.nic.aishe.master.repo.AdmissionCriterionRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class AdmissionCriterionController {


    @Autowired(required = false)
    private AdmissionCriterionRepo admissionCriterionRepo;

/*
    @PostMapping("/admission-criteria")
    public AdmissionCriterion addAdmissionCriterion(@RequestBody AdmissionCriterion AdmissionCriterion) {
        return admissionCriterionRepo.save(AdmissionCriterion);
    }
*/

    @GetMapping("/admission-criteria")
    public List<AdmissionCriterion> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return admissionCriterionRepo.findAll();
    }


}
