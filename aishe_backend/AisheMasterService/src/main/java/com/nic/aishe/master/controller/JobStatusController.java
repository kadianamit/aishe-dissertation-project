package com.nic.aishe.master.controller;

import com.nic.aishe.master.entity.JobStatus;
import com.nic.aishe.master.repo.JobStatusRepo;
import com.nic.aishe.master.security.UserInfo;
import com.nic.aishe.master.security.WithUser;
import com.nic.aishe.master.util.CommonObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin("*")
@RequestMapping("/api/job-status")
@RestController
public class JobStatusController {

    @Autowired(required = false)
    private JobStatusRepo jobStatusRepo;


    @GetMapping
    public List<JobStatus> getAllType(@WithUser UserInfo userInfo) {
        CommonObjectOperation.usernameValidate(userInfo);
        return jobStatusRepo.findAllJobStatusApplicable();
    }
}
