package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.RefNirfCategory;
import com.nic.aishe.master.repo.RefNirfCategoryRapo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/nirf-category")
@RestController
//@CrossOrigin("*")
public class RefNirfCategoryController {

    @Autowired
    private RefNirfCategoryRapo refNirfCategoryRapo;

    @GetMapping
    public List<RefNirfCategory> getAllType(@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        return refNirfCategoryRapo.findAll();
    }
}
