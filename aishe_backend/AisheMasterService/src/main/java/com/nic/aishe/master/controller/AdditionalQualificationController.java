package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.AdditionalQualification;
import com.nic.aishe.master.repo.AdditionalQualificationRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api/additional-qualification")
@RestController
public class AdditionalQualificationController {

    @Autowired(required = false)
    private AdditionalQualificationRepo additionalQualificationRepo;


    //@GetMapping
    public List<AdditionalQualification> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return additionalQualificationRepo.findAll();
    }
}
