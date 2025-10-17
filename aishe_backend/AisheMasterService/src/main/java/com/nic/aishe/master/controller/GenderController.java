package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.Gender;
import com.nic.aishe.master.repo.GenderRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class GenderController {


    @Autowired(required = false)
    private GenderRepo genderRepo;

  /*  @PostMapping("/gender")
    public Gender addGender(@RequestBody Gender Gender) {
        return genderRepo.save(Gender);
    }
*/
    @GetMapping("/gender-public")
    public List<Gender> getAllType() {
        return genderRepo.findAll();
    }

    @GetMapping("/gender")
    public List<Gender> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return genderRepo.findAll();
    }


}
