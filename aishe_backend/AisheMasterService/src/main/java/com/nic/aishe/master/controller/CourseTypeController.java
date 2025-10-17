package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.CourseType;
import com.nic.aishe.master.repo.CourseTypeRepo;
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
public class CourseTypeController {

    @Autowired(required = false)
    private CourseTypeRepo courseTypeRepo;

    /*@PostMapping("/course-type")
    public CourseType addCourseType(@RequestBody CourseType CourseType) {
        return courseTypeRepo.save(CourseType);
    }
*/
    @GetMapping("/course-type")
    public List<CourseType> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return courseTypeRepo.findAll();
    }
}
