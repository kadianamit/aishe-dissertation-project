package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.SpecialCategory;
import com.nic.aishe.master.repo.SpecialCategoryRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
//@CrossOrigin("*")
public class SpecialCategoryController {


    @Autowired(required = false)
    private SpecialCategoryRepo specialCategoryRepo;

    @PostMapping("/special-category")
    public SpecialCategory addSpecialCategory(@RequestBody SpecialCategory SpecialCategory) {
        return specialCategoryRepo.save(SpecialCategory);
    }

    @GetMapping("/special-category")
    public List<SpecialCategory> getAllType(@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        return specialCategoryRepo.findAll();
    }


}
