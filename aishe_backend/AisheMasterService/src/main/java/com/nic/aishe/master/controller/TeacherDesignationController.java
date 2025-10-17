package com.nic.aishe.master.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.nic.aishe.master.repo.AppointmentNatureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.entity.TeacherDesignation;
import com.nic.aishe.master.impl.CollegeServiceImpl;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;


@RequestMapping("/api/teacher-designation")
@RestController
public class TeacherDesignationController {

    @Autowired
    private CollegeServiceImpl collegeServiceImpl;

    @Autowired
    private AppointmentNatureRepo natureRepo;

    //u=1,c=2,s=3
    @GetMapping
    public List<TeacherDesignation> getAllTeachingStaffDesignation(@RequestParam(required = false) Integer type, @WithUser UserInfo userInfo) {
          CommonObjectOperation.usernameValidate(userInfo);
        List<TeacherDesignation> teacherDes  = collegeServiceImpl.findAllTeacherDesignation();

        List<TeacherDesignation> teacherDesignation = new ArrayList<>();
        for (int i = 0; i < teacherDes.size(); i++) {
            if (type == 1 && teacherDes.get(i).getValidForForm1() == true) {
                teacherDesignation.add(teacherDes.get(i));
            } else if (type == 2 && teacherDes.get(i).getValidForForm2() == true) {
                teacherDesignation.add(teacherDes.get(i));
            } else if (type == 3 && teacherDes.get(i).getValidForForm3() == true) {
                teacherDesignation.add(teacherDes.get(i));
            }
        }
        return teacherDesignation
                .stream()
                .sorted(Comparator.comparing(TeacherDesignation::getDesignation))
                .collect(Collectors.toList());
    }
}
