package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.TeacherSelectionMode;
import com.nic.aishe.master.repo.TeacherSelectionRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api/selection-mode")
@RestController
//@CrossOrigin("*")
public class SelectionModeController {

    @Autowired(required = false)
    private TeacherSelectionRepo teacherSelectionRepo;


    @GetMapping
    public List<TeacherSelectionMode> getAllType(@WithUser UserInfo userInfo) {
         CommonObjectOperation.usernameValidate(userInfo);
        return teacherSelectionRepo.findAll();
    }
}
