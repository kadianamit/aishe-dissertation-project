package com.nic.aishe.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.entity.CourseLevel;
import com.nic.aishe.master.entity.CourseLevelNew;
import com.nic.aishe.master.entity.ProgramNameNew;
import com.nic.aishe.master.repo.CourseLevelNewRepo;
import com.nic.aishe.master.repo.CourseLevelRepo;
import com.nic.aishe.master.repo.ProgramNameNewRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class CourseLevelController {

    @Autowired(required = false)
    private CourseLevelRepo courseLevelRepo;
    
    @Autowired(required = false)
    private CourseLevelNewRepo courseLevelNewRepo;
    
    @Autowired(required = false)
    private ProgramNameNewRepo programNameRepo;

    /*@PostMapping("/course-level")
    public CourseLevel addCourseLevel(@RequestBody CourseLevel courseLevel) {
        return courseLevelRepo.save(courseLevel);
    }*/

    @GetMapping("/course-level")
    public List<CourseLevel> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return courseLevelRepo.findAll();
    }
    @GetMapping("/course-level-with-programme")
	public List<CourseLevelNew> getAllCourseLevelWithProgramme(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        List<CourseLevelNew> courseLevel=courseLevelNewRepo.findAll();
        for(int i=0;i<courseLevel.size();i++) {
        	List<ProgramNameNew> programmeName = programNameRepo.findByLevelId(courseLevel.get(i).getId());
        	courseLevel.get(i).setProgramme(programmeName);
        }
        return courseLevel;
    }

}
