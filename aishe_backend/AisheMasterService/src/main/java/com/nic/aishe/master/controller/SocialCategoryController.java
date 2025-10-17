package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.SocialCategory;
import com.nic.aishe.master.repo.SocialCategoryRepo;
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
//@CrossOrigin("*")
public class SocialCategoryController {


    @Autowired(required = false)
    private SocialCategoryRepo socialCategoryRepo;

/*    @PostMapping("/social-category")
    public SocialCategory addSocialCategory(@RequestBody SocialCategory SocialCategory) {
        return socialCategoryRepo.save(SocialCategory);
    }*/

    @GetMapping("/social-category")
    public List<SocialCategory> getAllType(@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        return socialCategoryRepo.findAll();
    }


}
