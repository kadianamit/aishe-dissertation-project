package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.ReligiousCommunity;
import com.nic.aishe.master.repo.ReligiousCommunityRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api/religious-community")
@RestController
//@CrossOrigin("*")
public class ReligiousCommunityController {

    @Autowired(required = false)
    private ReligiousCommunityRepo religiousCommunityRepo;

    @GetMapping
    public List<ReligiousCommunity> getAllType(@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        return religiousCommunityRepo.findAll();
    }
}
