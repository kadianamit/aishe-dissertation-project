package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.Qualification;
import com.nic.aishe.master.repo.QualificationRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api/qualification")
@RestController
public class QualificationController {

    @Autowired(required = false)
    private QualificationRepo qualificationRepo;

    @GetMapping
    public List<Qualification> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return qualificationRepo.findAll();
    }

}
