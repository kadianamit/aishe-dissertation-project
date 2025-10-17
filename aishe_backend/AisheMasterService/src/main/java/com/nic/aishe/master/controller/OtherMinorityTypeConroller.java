package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.OtherMinorityType;
import com.nic.aishe.master.repo.OtherMinorityTypeRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api/other-minority")
@RestController
//@CrossOrigin("*")
public class OtherMinorityTypeConroller {

    @Autowired(required = false)
    private OtherMinorityTypeRepo otherMinorityType;

    @GetMapping
    public List<OtherMinorityType> getAllType(@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        return otherMinorityType.findAll();
    }
}
