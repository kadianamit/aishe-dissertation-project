package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.ExamSystem;
import com.nic.aishe.master.repo.ExamSystemRepo;
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
public class ExamSystemController {


    @Autowired(required = false)
    private ExamSystemRepo examSystemRepo;

    /*@PostMapping("/exam-system")
    public ExamSystem addExamSystem(@RequestBody ExamSystem ExamSystem) {
        return examSystemRepo.save(ExamSystem);
    }
*/
    @GetMapping("/exam-system")
    public List<ExamSystem> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return examSystemRepo.findAll();
    }


}
